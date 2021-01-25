package edu.conformity.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSONObject;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		String msg = e.getMessage();
		if (msg == null || msg.equals("")) {
			msg = "服务器出错";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", msg);
		return jsonObject;
	}
	
}
