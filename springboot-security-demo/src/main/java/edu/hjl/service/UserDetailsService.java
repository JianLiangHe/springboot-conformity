package edu.hjl.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

	/**
	 * 根据username加载user
	 * @param username
	 * @return
	 */
	UserDetails loadUserByUsername(String username);
	
}
