package edu.conformity.test;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 设置属性
	 */
	@Test
	public void set() {
		stringRedisTemplate.opsForValue().set("username", "root");
		String username = stringRedisTemplate.opsForValue().get("username");
		System.out.println(username);
	}
	
	/**
	 * 设置属性，并带过期时间
	 */
	@Test
	public void set2() {
		stringRedisTemplate.opsForValue().set("username", "root", 10, TimeUnit.SECONDS);
		String username = stringRedisTemplate.opsForValue().get("username");
		System.out.println(username);
		try {
			Thread.sleep(11000);
			username = stringRedisTemplate.opsForValue().get("username");
			System.out.println("睡眠11s， username：" + username);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
