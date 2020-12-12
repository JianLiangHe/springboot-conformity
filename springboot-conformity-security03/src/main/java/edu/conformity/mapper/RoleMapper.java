package edu.conformity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.conformity.pojo.Role;

@Mapper
public interface RoleMapper {

	Role getById(@Param("id") Integer id);
	
}
