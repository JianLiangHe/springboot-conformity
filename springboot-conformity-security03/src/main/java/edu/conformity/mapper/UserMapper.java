package edu.conformity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.conformity.pojo.User;

@Mapper
public interface UserMapper {

	User getByUsername(@Param("username") String username);
	
}
