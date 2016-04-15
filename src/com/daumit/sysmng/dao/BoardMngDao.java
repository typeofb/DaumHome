package com.daumit.sysmng.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.dao.QuerySupport;
import com.common.dao.ResultSetData;
import com.common.dao.SearchCondition;
import com.daumit.sysmng.dto.BoardMngDto;

@Repository("BoardMngDao")
public class BoardMngDao extends QuerySupport {
	
	public ResultSetData selectBoardList(Map<String, Object> paramMap, SearchCondition sc) {
//		String sql = "SELECT USER_ID AS POST_ID, USER_NAME AS USR_NM, BRAND_NAME AS TITLE, ROLE AS READ_CNT, LAST_UPDATE_DTIME AS REG_DT"
//				  + " FROM USER"
//				  + " ORDER BY LAST_UPDATE_DTIME DESC"
//				  + " LIMIT :targetPage, :rowSize";
//		return queryForResultSet(sql, paramMap);
		String sql = "SELECT IDX AS POST_ID, USNAME AS USR_NM, WBTITLE AS TITLE, WBHIT AS READ_CNT, WBDATE AS REG_DT"
				  + " FROM WBOARD_DATA"
				  + " WHERE STATE = :state";
		return queryForPage(sql, paramMap, sc);
	}
	
//	public int selectBoardCnt(Map<String, Object> paramMap) {
//		String sql = "SELECT COUNT(1) TOTAL_ROW_SIZE"
//				  + " FROM WBOARD_DATA"
//				  + " WHERE STATE = :state";
//		return queryForInt(sql, paramMap);
//	}
	
	public Map<String, Object> selectBoardDetail(Map<String, Object> paramMap) {
		String sql = "SELECT IDX AS POST_ID, WBTITLE AS TITLE, WBTEXT AS CONTENTS, WBDATE AS REG_DT, WBHIT AS READ_CNT, USIDX AS USR_ID"
				  + " FROM WBOARD_DATA"
				  + " WHERE IDX = :postId";
		return queryForMap(sql, paramMap);
	}
	
	public int insertBoard(BoardMngDto paramMap) {
		String sql = "INSERT INTO WBOARD_DATA (IDX, USNAME, WBTITLE, WBTEXT, WBDATE, STATE)"
				  + " VALUES (TB_WBOARD_DATA_SEQ.NEXTVAL, 'test', :title, :contents, SYSDATE, 'Y')";
		return update(sql, paramMap);
	}
	
	public int updateBoard(BoardMngDto paramMap) {
		String sql = "UPDATE WBOARD_DATA SET WBTITLE = :title, WBTEXT = :contents"
				  + " WHERE IDX = :postId";
		return update(sql, paramMap);
	}
	
	public int deleteBoard(BoardMngDto paramMap) {
		String sql = "UPDATE WBOARD_DATA SET WB_NOTICE = 'N', STATE = 'N'"
				  + " WHERE IDX = :postId";
		return update(sql, paramMap);
	}
}
