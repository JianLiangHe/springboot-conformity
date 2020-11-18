package edu.conformity.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.util.JwtUtil;
import edu.conformity.util.RtnResult;

@RestController
@RequestMapping("/user/")
@CrossOrigin("*")
public class UserController {

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public RtnResult login(
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			HttpServletResponse response
			) {
		// 假设用户输入的数据全部都正确
		String token = JwtUtil.sgin(loginName, password);
		if (token != null) {
			// 把token设置在cookie中
			Cookie cookie = new Cookie("token", token);
			response.addCookie(cookie);
			return RtnResult.success("success", token);
		} else {
			return RtnResult.fail();
		}
	}
	
	@RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
	public RtnResult getUserInfo(
			HttpServletRequest request,
			@RequestParam(value = "loginName", required = true) String loginName
			) {
		String token = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null ) {
			for (Cookie cookie : cookies) {
				if (cookie.getName() != null && cookie.getName().equals("token")) {
					token = cookie.getValue();
					break;
				}
			}
			
			if (token != null) {
				// 验证
				boolean verity = JwtUtil.verity(token);
				
				if (verity) {
					return RtnResult.success("成功");
				}
			}
		}
		return RtnResult.fail("你没有登录");
	}
	
}
