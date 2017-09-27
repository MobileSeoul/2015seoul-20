package com.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.PoolManager;

public class BoardDAO {
	PoolManager mybatisManager = PoolManager.getInstance();

	public List selectAll() {
		List<Board> list = null;
		SqlSession session = mybatisManager.getSession();
		list = session.selectList("Board.selectAll");
		session.close();
		return list;
	}

	public void insert(Board dto) {
		SqlSession session = mybatisManager.getSession();
		session.insert("Board.insert", dto);
		session.commit();
		session.close();
	}
}
