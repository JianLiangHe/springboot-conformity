package edu.conformity.test;

import java.util.UUID;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTest {

	private static Logger logger = LoggerFactory.getLogger(PasswordTest.class);
	
	@Value("${user.username}")
	private String username;
	
	@Value("${user.pwd}")
	private String pwd;
	
	@Resource
    private StringEncryptor stringEncryptor;
	
	/**
	 *生成UUID，用于作为秘钥
	 */
	@Test
	public void test() {
		String uuid = UUID.randomUUID().toString();
		System.out.println("秘钥：" + uuid);
	}
	
	/**
	 * 对密码进行加密
	 */
	@Test
	public void contextLoads() {
		System.out.println("加密密码：" + stringEncryptor.encrypt("root"));
	}
	
	/**
	 * 对密码进行解密
	 */
	@Test
	public void decrypt() {
		System.out.println("解密密码：" + stringEncryptor.decrypt("WwtllxhsM7819aOrYC0p/+ySlqFjEWkI7Qh84gNG9osSBf7IeL/RYCZKanxItvYv"));
	}
	
	/**
	 * 打印用户信息
	 */
	@Test
	public void userInfo() {
		System.out.println("username: " + username + "\n pwd: " + pwd);
	}
	
}
