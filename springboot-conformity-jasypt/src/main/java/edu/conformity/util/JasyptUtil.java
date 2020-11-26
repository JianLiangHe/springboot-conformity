package edu.conformity.util;

import java.util.UUID;

import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JasyptUtil {

	private Logger logger = LoggerFactory.getLogger(JasyptUtil.class);
	
	@Autowired
	private StringEncryptor stringEncryptor;
	
	/**
	 * 生成秘钥
	 * @return
	 */
	public String createPrivateKey() {
		String privateKey = UUID.randomUUID().toString();
		logger.info("生成秘钥：" + privateKey);
		return privateKey;
	}
	
	/**
	 * 加密
	 * @param param
	 * @return
	 */
	public String encrypt(String param) {
		logger.info("开始加密：" + param);
		param = stringEncryptor.encrypt(param);
		logger.info("加密结果：" + param);
		return param;
	}
	
	/**
	 * 解密
	 * @param param
	 * @return
	 */
	public String decrypt(String param) {
		logger.info("开始解密：" + param);
		param = stringEncryptor.decrypt(param);
		logger.info("解密结果：" + param);
		return param;
	}
	
}
