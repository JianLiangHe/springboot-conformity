package edu.conformity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.conformity.pojo.User;

@Mapper
public interface UserMapper {

	List<User> findAll();
	
	void save(@Param("user") User user);
	
}
