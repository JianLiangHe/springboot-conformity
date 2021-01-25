package edu.conformity.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUtil {

	/**
	 * 过期时间
	 */
	private static final long EXPIRE_TIME = 5 * 60 *1000;
	
	/**
	 * jwt 密钥
	 */
	private static final String SECRET = "jwt_secret";
	
	/**
	 * 生成签名，5分钟后过期
	 * @param userId
	 * @return
	 */
	public static String sign(String userId) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			return JWT.create()
					// 将userId 保存到 token里面
					.withAudience(userId)
					// 五分钟后token过期
					.withExpiresAt(date)
					// token的密钥
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据token获取userId
	 * @param token
	 * @return
	 */
	public static String getUserId(String token) {
		try {
			String userId = JWT.decode(token).getAudience().get(0);
			return userId;
		} catch (JWTDecodeException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 校验token
	 * @param token
	 * @return
	 */
	public static boolean checkSign(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
			throw new RuntimeException("token 无效，请重新获取!");
		}
	}
	
}
