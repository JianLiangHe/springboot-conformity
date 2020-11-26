package edu.conformity.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import edu.conformity.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public Long getExpire(String key) {
		return stringRedisTemplate.getExpire(key);
	}

	@Override
	public void expire(String key, Long timeout) {
		stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	@Override
	public Long increment(String key, long time) {
		return stringRedisTemplate.opsForValue().increment(key, time);
	}

	@Override
	public Set<String> keys(String pattern) {
		return stringRedisTemplate.keys(pattern);
	}

	@Override
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public void set(String key, String value, Long timeout) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key).toString();
	}

}
