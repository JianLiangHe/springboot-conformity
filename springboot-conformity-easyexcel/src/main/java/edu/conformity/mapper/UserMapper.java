package edu.conformity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.conformity.pojo.User;

@Mapper
public interface UserMapper {

	List<User> findAll();
	
}
