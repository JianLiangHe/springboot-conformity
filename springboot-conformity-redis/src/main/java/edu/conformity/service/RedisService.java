package edu.conformity.service;

import java.util.Set;

public interface RedisService {

	Long getExpire(String key);
	
	void expire(String key, Long timeout);
	
	Long increment(String key, long time);
	
	Set<String> keys(String pattern);
	
	void delete(String key);
	
	void set(String key, String value, Long timeout);
	
	String get(String key);
	
}
