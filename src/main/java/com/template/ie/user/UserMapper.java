package com.template.ie.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.template.ie.user.model.User;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user WHERE id=#{id}")
	@Results(id = "user", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "username", column = "username"), 
			@Result(property = "name", column = "name"),
			@Result(property = "age", column = "age"), 
			@Result(property = "balance", column = "balance")})
	User queryUserById(@Param("id") Long id);
}