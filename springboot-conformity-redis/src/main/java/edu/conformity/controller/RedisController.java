package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.entity.User;
import edu.conformity.util.JsonUtil;

@RestController
@RequestMapping("/redis/")
public class RedisController {

	@Autowired
	private StringRedisTemplate tempalte;
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password
			) {
		// 判断key（用户）是否存在
		boolean flag = tempalte.hasKey(username);
		if (flag) {
			return "该用户已存在，请重新输入";
		}
		
		String id = String.valueOf(System.currentTimeMillis());
		User user = new User(id, username, password);
		
		// 将用户对象转为json串，保存到redis
		String userJsonStr = JsonUtil.objectToJson(user);
		tempalte.opsForValue().set(username, userJsonStr);
		
		return "success";
	}
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String get(
			@RequestParam(value = "username", required = true) String username 
			) {
		String userJsonStr = tempalte.opsForValue().get(username);
		
		if (userJsonStr == null || userJsonStr.length() <= 0) {
			return "该用户不存在";
		}
		
		User user = JsonUtil.jsonToPojo(userJsonStr, User.class);
		System.out.println(user);
		return userJsonStr;
	}
	
}
