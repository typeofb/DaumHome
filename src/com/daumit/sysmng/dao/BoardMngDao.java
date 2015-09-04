package com.daumit.sysmng.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.dao.QuerySupport;
import com.common.dao.ResultSetData;
import com.daumit.sysmng.dto.BoardMngDto;

@Repository("BoardMngDao")
public class BoardMngDao extends QuerySupport {
	
	public ResultSetData selectBoardList(Map<String, Object> paramMap) {
//		String sql = "SELECT USER_ID AS POST_ID, USER_NAME AS USR_NM, BRAND_NAME AS TITLE, ROLE AS READ_CNT, LAST_UPDATE_DTIME AS REG_DT"
//				  + " FROM USER"
//				  + " ORDER BY LAST_UPDATE_DTIME DESC"
//				  + " LIMIT :targetPage, :rowSize";
//		return queryForResultSet(sql, paramMap);
		String sql = "SELECT USER_ID AS POST_ID, USER_NAME AS USR_NM, WORK_PLACE AS TITLE, FEMALE AS READ_CNT, JOIN_DATE AS REG_DT"
				  + " FROM TB_ORG_USER"
				  + " WHERE 1 = 1";
		return queryForPage(sql, paramMap);
	}
	
	public int selectBoardCnt(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) TOTAL_ROW_SIZE FROM USER";
		return queryForInt(sql, paramMap);
	}
	
	public Map<String, Object> selectBoardDetail(Map<String, Object> paramMap) {
		String sql = "SELECT USER_ID AS POST_ID, BRAND_NAME AS TITLE, EMAIL_ADDRESS AS CONTENTS, LAST_UPDATE_DTIME AS REG_DT, ROLE AS READ_CNT, LAST_UPDATE_USER_ID AS USR_ID"
				  + " FROM USER"
				  + " WHERE USER_ID = :postId";
		return queryForMap(sql, paramMap);
	}
	
	public int insertBoard(BoardMngDto paramMap) {
		String sql = "INSERT INTO USER (USER_ID, USER_NAME, BRAND_NAME, EMAIL_ADDRESS, LAST_UPDATE_DTIME)"
				  + " VALUES (:title, 'test', 'TEST', :contents, CURTIME())";
		return update(sql, paramMap);
	}
	
	public int updateBoard(BoardMngDto paramMap) {
		String sql = "UPDATE USER SET BRAND_NAME = :title, EMAIL_ADDRESS = :contents"
				  + " WHERE USER_ID = :postId";
		return update(sql, paramMap);
	}
	
	public int deleteBoard(BoardMngDto paramMap) {
		String sql = "DELETE FROM USER WHERE USER_ID = :postId";
		return update(sql, paramMap);
	}
}
