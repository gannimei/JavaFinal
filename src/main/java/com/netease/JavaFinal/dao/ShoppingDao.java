package com.netease.JavaFinal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.netease.JavaFinal.meta.Shopping;

public interface ShoppingDao {

	@Results({
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
	})
	@Select("SELECT s.*,c.price,c.icon,c.title from shopping s inner join content c on s.contentId=c.id where s.contentId=#{contentId} and s.personId=#{personId}")
	public Shopping GetByContentIdAndPersonId(@Param("contentId") int contentId,@Param("personId") int personId);
	
	@Results({
		@Result(property = "image", column = "icon", jdbcType = JdbcType.BLOB, typeHandler = BlobTypeHandler.class)
	})
	@Select("SELECT s.*,c.price,c.icon,c.title from shopping s inner join content c on s.contentId=c.id")
	public List<Shopping> GetAll();
	
	@Insert("insert into shopping(personId,contentId,number,time) values(#{personId},#{contentId},#{number},#{time})")
	public int Insert(Shopping shopping);
	
	@Update("update shopping set number=#{number},time=#{time} where id=#{id}")
	public void Update(Shopping shopping);
	
	@Delete("delete from shopping")
	public void DeleteAll();
	
}
