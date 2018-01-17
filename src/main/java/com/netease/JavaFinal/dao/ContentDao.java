package com.netease.JavaFinal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.netease.JavaFinal.meta.Content;

public interface ContentDao {

	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "price", column = "price"),
		@Result(property = "title", column = "title"),
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class),
		@Result(property = "summary", column = "abstract"),
		@Result(property = "detail", column = "text", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
		
	})
	@Select("select * from content where id=#{id}")
	public Content GetById(int id);
	
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "price", column = "price"),
		@Result(property = "title", column = "title"),
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class),
		@Result(property = "summary", column = "abstract"),
		@Result(property = "detail", column = "text", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
		
	})
	@Select("select * from content")
	public List<Content> GetAll();
	
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "price", column = "price"),
		@Result(property = "title", column = "title"),
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class),
		@Result(property = "summary", column = "abstract"),
		@Result(property = "detail", column = "text", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
		
	})
	@Select("select * from content c where not exists(select * from trx t where t.contentId=c.id)")
	public List<Content> GetUnBuy();
	
	@Insert("insert into content(title,price,icon,abstract,text) values(#{title},#{price},#{image,jdbcType=BLOB},#{summary},#{detail,jdbcType=BLOB})")
	@Options(useGeneratedKeys = true, keyProperty = "id")   
	public int Insert(Content content);
	
}
