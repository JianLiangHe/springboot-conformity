package edu.conformity.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.conformity.mapper.RoleMapper;
import edu.conformity.mapper.UserMapper;
import edu.conformity.mapper.UserRoleMapper;
import edu.conformity.pojo.Role;
import edu.conformity.pojo.User;
import edu.conformity.pojo.UserRole;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.getByUsername(username);
		
		if (null == user) {
			throw new UsernameNotFoundException("用户不存在！");
		}

		List<Role> roleList = new ArrayList<Role>();
		List<UserRole> userRoleList = userRoleMapper.findByUserId(user.getId());
		Iterator<UserRole> userRoleIt = userRoleList.iterator();

		while(userRoleIt.hasNext()) {
			UserRole obj = userRoleIt.next();
			int userRoleId = obj.getId();
			
			Role role = roleMapper.getById(userRoleId);
			roleList.add(role);
		}
		
		user.setRoleList(roleList);
		
		return user;
	}
	
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.getByUsername(username);
		
		if (null == user) {
			throw new UsernameNotFoundException("用户不存在！");
		}

		List<Role> roleList = new ArrayList<Role>();
		List<UserRole> userRoleList = userRoleMapper.findByUserId(user.getId());
		Iterator<UserRole> userRoleIt = userRoleList.iterator();

		while(userRoleIt.hasNext()) {
			UserRole obj = userRoleIt.next();
			int userRoleId = obj.getId();
			
			Role role = roleMapper.getById(userRoleId);
			roleList.add(role);
		}
		
		user.setRoleList(roleList);
		
		return user;
	}*/

}
