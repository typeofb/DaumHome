package com.daumit.daummng.dao;

public class OracleDaoImpl implements ICommonDao {

	@Override
	public void select(String data) {
		System.out.println("Oracle을 이용하여 " + data + "를 조회");
	}

	@Override
	public void insert(String data) {
		System.out.println("Oracle을 이용하여 " + data + "를 추가");
	}

	@Override
	public void update(String data) {
		System.out.println("Oracle을 이용하여 " + data + "를 수정");
	}

	@Override
	public void delete(String data) {
		System.out.println("Oracle을 이용하여 " + data + "를 삭제");
	}
}
