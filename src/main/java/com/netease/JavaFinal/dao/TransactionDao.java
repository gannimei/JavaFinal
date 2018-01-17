package com.netease.JavaFinal.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.netease.JavaFinal.meta.Transaction;

public interface TransactionDao {

	@Results({
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
	})
	@Select("select t.*,c.title,c.icon from trx t inner join content c on c.id=t.contentId where t.personId=#{personId}")
	public List<Transaction> GetByPersonId(int personId);
	
	@Results({
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
	})
	@Select("select t.*,c.title,c.icon from trx t inner join content c on c.id=t.contentId where t.contentId=#{contentId}")
	public Transaction GetByContentId(int contentId);
	
	@InsertProvider(type = TransactionMapperProvider.class, method = "insertAll")
	public int InsertList(List<Transaction> list);
	
}
