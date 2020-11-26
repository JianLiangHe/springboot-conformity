package edu.conformity.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/password/")
public class PasswordController {

	@Value("${user.username}")
	private String username;
	
	@Value("${user.pwd}")
	private String pwd;
	
	@Autowired
	private StringEncryptor stringEncryptor;
	
	@ApiOperation("密码加密")
	@RequestMapping(value = "encrypt", method = RequestMethod.GET)
	public String encrypt(@RequestParam(value = "param", required = true) String param) {
		String str = stringEncryptor.encrypt(param);
		return str;
	}
	
	@ApiOperation("密码解密")
	@RequestMapping(value = "decrypt", method = RequestMethod.GET)
	public String decrypt(@RequestParam(value = "param", required = true) String param) {
		String str = stringEncryptor.decrypt(param);
		return str;
	}
	
	@ApiOperation("获取配置文件用户信息，加密后带解密")
	@RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
	public String getUserInfo() {
		String str = "username: " + username + "\npwd: " + pwd;
		return str;
	}
	
}
