package com.netease.JavaFinal.dao;

import org.apache.ibatis.annotations.Select;

import com.netease.JavaFinal.meta.Person;

public interface PersonDao {

	@Select("select * from person where userName=#{userName}")
	public Person GetByUserName(String userName);
	
}
