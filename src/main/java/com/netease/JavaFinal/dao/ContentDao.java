package com.netease.JavaFinal.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.netease.JavaFinal.meta.Content;

public interface ContentDao {

	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "price", column = "price"),
		@Result(property = "title", column = "title"),
		@Result(property = "image", column = "icon"),
		@Result(property = "summary", column = "abstract"),
		@Result(property = "detail", column = "text")
	})
	@Select("select * from content where id=#{id}")
	public Content GetById(int id);
	
	@Insert("insert into content(title,price,icon,abstract,text) values(#{title},#{price},#{image},#{summary},#{detail})")
	@Options(useGeneratedKeys = true, keyProperty = "id")   
	public int Insert(Content content);
	
}
