package edu.conformity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.conformity.pojo.UserRole;

@Mapper
public interface UserRoleMapper {

	List<UserRole> findByUserId(@Param("userId") Integer userId);
	
}
