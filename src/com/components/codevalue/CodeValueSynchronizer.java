package com.components.codevalue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.common.dao.ResultSetData;

public class CodeValueSynchronizer implements java.lang.Runnable {

	private long lastModDate = 0;
	private long lastDelDate = 0;
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
		if (lastModDate == 0) {
			// 처음 로딩 시점
			ResultSetData codeList = this.sqlExecute("SELECT CODE_GROUP_ID, CODE, CODE_NAME, LAST_UPDATE_DTIME"
					+ " FROM CODE"
					+ " WHERE USE_YN = 'Y'"
					+ " ORDER BY LAST_UPDATE_DTIME DESC",
					"CODE_GROUP_ID,CODE,CODE_NAME,LAST_UPDATE_DTIME");
			populate(codeList);
			lastDelDate = lastModDate;
			
			ResultSetData deletedCodeList = this.sqlExecute("SELECT CODE_GROUP_ID, CODE, CODE_NAME, LAST_UPDATE_DTIME"
					+ " FROM CODE"
					+ " WHERE USE_YN = 'N' AND LAST_UPDATE_DTIME > '" + lastDelDate + "'"
					+ " ORDER BY LAST_UPDATE_DTIME DESC",
					"CODE_GROUP_ID,CODE,CODE_NAME,LAST_UPDATE_DTIME");
			if (deletedCodeList != null && deletedCodeList.size() > 0) {
				Map<String, String> resultMap = (Map<String, String>) deletedCodeList.get(0);
				long modDate = Long.parseLong(resultMap.get("LAST_UPDATE_DTIME"));
				lastDelDate = modDate;
			}
		} else {
			ResultSetData deletedCodeList = this.sqlExecute("SELECT CODE_GROUP_ID, CODE, CODE_NAME, LAST_UPDATE_DTIME"
					+ " FROM CODE"
					+ " WHERE USE_YN = 'N' AND LAST_UPDATE_DTIME > '" + lastDelDate + "'"
					+ " ORDER BY LAST_UPDATE_DTIME DESC",
					"CODE_GROUP_ID,CODE,CODE_NAME,LAST_UPDATE_DTIME");
			clear(deletedCodeList);
			
			ResultSetData addedOrModifiedCodeList = this.sqlExecute("SELECT CODE_GROUP_ID, CODE, CODE_NAME, LAST_UPDATE_DTIME"
					+ " FROM CODE"
					+ " WHERE USE_YN = 'Y' AND LAST_UPDATE_DTIME > '" + lastModDate + "'"
					+ " ORDER BY LAST_UPDATE_DTIME DESC",
					"CODE_GROUP_ID,CODE,CODE_NAME,LAST_UPDATE_DTIME");
			populate(addedOrModifiedCodeList);
		}
	}
	
	private void populate(ResultSetData codeList) {
		for (int i = 0; i < codeList.size(); i++) {
			Map<String, String> resultMap = (Map<String, String>) codeList.get(i);
			String codeGroupId = resultMap.get("CODE_GROUP_ID");
			String key = resultMap.get("CODE");
			String value = resultMap.get("CODE_NAME");
			System.out.println("##################### [CodeValueSynchronizer.put] key:" + key + ", value:" + value + ", codeGroupId:" + codeGroupId);
			long modDate = Long.parseLong(resultMap.get("LAST_UPDATE_DTIME"));
			if (i == 0)
				lastModDate = modDate;
			CodeValueCache.addOrModify(key, value, codeGroupId);
		}
	}
	
	private void clear(ResultSetData codeList) {
		for (int i = 0; i < codeList.size(); i++) {
			Map<String, String> resultMap = (Map<String, String>) codeList.get(i);
			String codeGroupId = resultMap.get("CODE_GROUP_ID");
			String key = resultMap.get("CODE");
			String value = resultMap.get("CODE_NAME");
			System.out.println("##################### [CodeValueSynchronizer.del] key:" + key + ", value:" + value + ", codeGroupId:" + codeGroupId);
			long modDate = Long.parseLong(resultMap.get("LAST_UPDATE_DTIME"));
			if (i == 0)
				lastDelDate = modDate;
			CodeValueCache.remove(key, codeGroupId);
		}
	}
	
	private ResultSetData sqlExecute(String sql, String returnColumn) {
		ResultSetData returnValue = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://210.96.202.111:3306/myver_dev?autoReconnect=true", "myver", "myver");
			Connection conn = DriverManager.getConnection(null, null, null);
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
