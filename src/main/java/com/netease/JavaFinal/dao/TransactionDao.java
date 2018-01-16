package com.netease.JavaFinal.dao;

import org.apache.ibatis.annotations.Select;

import com.netease.JavaFinal.meta.Transaction;

public interface TransactionDao {

	@Select("select * from trx where personId=#{personId}")
	public Transaction GetByPersonId(int personId);
	
	@Select("select * from trx where contentId=#{contentId}")
	public Transaction GetByContentId(int contentId);
	
}
