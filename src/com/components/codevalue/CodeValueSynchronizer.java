package com.components.codevalue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.common.dao.ResultSetData;

public class CodeValueSynchronizer implements java.lang.Runnable {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
	private Date lastModDate = null;
	private Date lastDelDate = null;
	private Thread thread = null;
	private boolean stopSignal = false;
	
	private boolean isStopSignal() {
		return stopSignal;
	}
	
	public void setStopSignal(boolean stopSignal) {
		this.stopSignal = stopSignal;
	}
	
	public CodeValueSynchronizer() {
		System.out.println("Start CodeValueSynchronizer...");
		thread = new Thread(this);
	}
	
	public void start() {
		if (!thread.isAlive())
			thread.start();
	}
	
	public void removeInstance() {
		setStopSignal(true);
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}
	
	@Override
	public void run() {
		while (!isStopSignal()) {
			execute();
			try {
				Thread.sleep(1000 * 5);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	private void execute() {
		if (lastModDate == null) {
			// 처음 로딩 시점
			ResultSetData codeList = this.sqlExecute("SELECT GROUP_CODE, CODE, CODE_NAME, LAST_UPDATE_DATE"
					+ " FROM CODE"
					+ " WHERE USE_FLAG = 'Y'"
					+ " ORDER BY LAST_UPDATE_DATE DESC",
				"GROUP_CODE,CODE,CODE_NAME,LAST_UPDATE_DATE");
			if (codeList != null && codeList.size() > 0) {
				populate(codeList);
				lastDelDate = lastModDate;
			}
			
			ResultSetData deletedCodeList = this.sqlExecute("SELECT GROUP_CODE, CODE, CODE_NAME, LAST_UPDATE_DATE"
					+ " FROM CODE"
					+ " WHERE USE_FLAG = 'N'"
					+ " AND LAST_UPDATE_DATE > TO_DATE('" + formatter.format(lastDelDate) + "', 'MM/dd/yyyy HH24:mi:ss')"
					+ " ORDER BY LAST_UPDATE_DATE DESC",
				"GROUP_CODE,CODE,CODE_NAME,LAST_UPDATE_DATE");
			if (deletedCodeList != null && deletedCodeList.size() > 0) {
				Map<String, String> resultMap = (Map<String, String>) deletedCodeList.get(0);
				Date modDate = null;
				try {
					modDate = formatter.parse(String.valueOf(resultMap.get("LAST_UPDATE_DATE")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				lastDelDate = modDate;
			}
		} else {
			ResultSetData deletedCodeList = this.sqlExecute("SELECT GROUP_CODE, CODE, CODE_NAME, LAST_UPDATE_DATE"
					+ " FROM CODE"
					+ " WHERE USE_FLAG = 'N'"
					+ " AND LAST_UPDATE_DATE > TO_DATE('" + formatter.format(lastDelDate) + "', 'MM/dd/yyyy HH24:mi:ss')"
					+ " ORDER BY LAST_UPDATE_DATE DESC",
				"GROUP_CODE,CODE,CODE_NAME,LAST_UPDATE_DATE");
			if (deletedCodeList != null && deletedCodeList.size() > 0)
				clear(deletedCodeList);
			
			ResultSetData addedOrModifiedCodeList = this.sqlExecute("SELECT GROUP_CODE, CODE, CODE_NAME, LAST_UPDATE_DATE"
					+ " FROM CODE"
					+ " WHERE USE_FLAG = 'Y'"
					+ " AND LAST_UPDATE_DATE > TO_DATE('" + formatter.format(lastModDate) + "', 'MM/dd/yyyy HH24:mi:ss')"
					+ " ORDER BY LAST_UPDATE_DATE DESC",
				"GROUP_CODE,CODE,CODE_NAME,LAST_UPDATE_DATE");
			if (addedOrModifiedCodeList != null && addedOrModifiedCodeList.size() > 0)
				populate(addedOrModifiedCodeList);
		}
	}
	
	private void populate(ResultSetData codeList) {
		for (int i = 0; i < codeList.size(); i++) {
			Map<String, String> resultMap = (Map<String, String>) codeList.get(i);
			String groupCode = resultMap.get("GROUP_CODE");
			String key = resultMap.get("CODE");
			String value = resultMap.get("CODE_NAME");
			System.out.println("##################### [CodeValueSynchronizer.put] key:" + key + ", value:" + value + ", groupCode:" + groupCode);
			Date modDate = null;
			try {
				modDate = formatter.parse(String.valueOf(resultMap.get("LAST_UPDATE_DATE")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (i == 0)
				lastModDate = modDate;
			CodeValueCache.addOrModify(key, value, groupCode);
		}
	}
	
	private void clear(ResultSetData codeList) {
		for (int i = 0; i < codeList.size(); i++) {
			Map<String, String> resultMap = (Map<String, String>) codeList.get(i);
			String groupCode = resultMap.get("GROUP_CODE");
			String key = resultMap.get("CODE");
			String value = resultMap.get("CODE_NAME");
			System.out.println("##################### [CodeValueSynchronizer.del] key:" + key + ", value:" + value + ", groupCode:" + groupCode);
			Date modDate = null;
			try {
				modDate = formatter.parse(String.valueOf(resultMap.get("LAST_UPDATE_DATE")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (i == 0)
				lastDelDate = modDate;
			CodeValueCache.remove(key, groupCode);
		}
	}
	
	private ResultSetData sqlExecute(String sql, String returnColumn) {
		ResultSetData returnValue = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://210.96.202.111:3306/myver_dev?autoReconnect=true", "myver", "myver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rset = pstmt.executeQuery();
			int rowCnt = 0;
			if (returnColumn != null) {
				rset.last();
				rowCnt = rset.getRow();
				rset.beforeFirst();
				returnValue = new ResultSetData(rowCnt);
				Map<String, String> resultMap = null;
				String[] returnColumnArr = returnColumn.trim().split(",");
				for (int i = 0; rset.next(); i++) {
					resultMap = new HashMap<String, String>();
					for (int j = 0; j < returnColumnArr.length; j++)
						resultMap.put(returnColumnArr[j], rset.getString(returnColumnArr[j]));
					returnValue.add(i, resultMap);
				}
			}
			rset.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return returnValue;
	}
}
