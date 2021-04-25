package edu.conformity.util;

import java.io.Serializable;

/**
 * 结果集返回类
 * @author hejianliang
 *
 */
public class RtnResult implements Serializable {

	/**
	 * 返回布尔结果
	 */
	private boolean result;
	
	/**
	 * code
	 */
	private String code;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 数据
	 */
	private Object data;

	public RtnResult() {
		super();
	}

	public RtnResult(boolean result, String code, String message, Object data) {
		super();
		this.result = result;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
