package edu.conformity.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import edu.conformity.annotation.JwtToken;
import edu.conformity.util.JwtUtil;

@RestController
@RequestMapping("/jwt/")
public class JwtController {

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Object login(
			@RequestBody Map<String, Object> map) {
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		JSONObject jsonObject = new JSONObject();
		String userId = UUID.randomUUID().toString();
		// 生成签名
		String token = JwtUtil.sign(userId);
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("userId", userId);
		userInfo.put("username", username);
		userInfo.put("password", password);
		
		jsonObject.put("token", token);
		jsonObject.put("userInfo", userInfo);
		
		return jsonObject;
	}
	
	@JwtToken
	@RequestMapping(value = "getMessage", method = RequestMethod.GET)
	public String getMessage() {
		return "你已通过验证！";
	}
	
}
