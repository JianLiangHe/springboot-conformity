package edu.hjl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.hjl.pojo.Admin;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		Admin admin = new Admin();
		
		if (username.equals("employee")) {
			admin.setUsername("employee");
			admin.setPassword("123456");
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_EMPLOYEE");
			grantedAuthorities.add(grantedAuthority);
			// 创建用户，用户判断权限
			return new User(
					admin.getUsername(), 
					new BCryptPasswordEncoder().encode(admin.getPassword()), 
					grantedAuthorities);
		} 
		
		if (username.equals("admin")) {
			admin.setUsername("admin");
			admin.setPassword("123456");
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
			grantedAuthorities.add(grantedAuthority);
			// 创建用户，用户判断权限
			return new User(
					admin.getUsername(), 
					new BCryptPasswordEncoder().encode(admin.getPassword()), 
					grantedAuthorities);
		}
		
		return null;
	}

}
