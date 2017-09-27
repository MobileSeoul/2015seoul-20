package com.binmap.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.PoolManager;

public class BinmapDAO {
	PoolManager pool =PoolManager.getInstance();
	   
	   public void update(String bin_id, String status) {
	      SqlSession session=pool.getSession();
	      Binmap dto = new Binmap();
	      dto.setBin_id(Integer.parseInt(bin_id));
	      dto.setStatus(Integer.parseInt(status));
	      
	      session.update("Binmap.update", dto);
	      session.commit();
	      session.close();
	   }
	   
	   public List selectAll() {
	      List list=null;
	      SqlSession session=pool.getSession();
	      list=session.selectList("Binmap.selectAll");
	      session.close();
	      return list;
	   }
}
