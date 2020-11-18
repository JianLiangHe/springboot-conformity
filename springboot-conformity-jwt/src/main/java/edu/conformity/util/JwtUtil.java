package edu.conformity.util;

import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

	/**
	 * 过期时间
	 */
	private static final long EXPIRE_TIME = 24*60*60*1000;
	
	/**
	 * token秘钥
	 */
	private static final String TOKEN_SECRET = "hejianliang";
	
	/**
	 * 生成签名
	 * @param username
	 * @param userId
	 * @return
	 */
	public static String sgin(String username, String userId) {
		// 过期时间
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		
		// 私钥加密算法
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		
		// 设置头信息
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		
		// 附带username和userID生成签名
		return JWT.create()
				.withHeader(header)
				.withClaim("loginname", username)
				.withClaim("userId",userId)
				.withExpiresAt(date)
				.sign(algorithm);
	}
	
	public static boolean verity(String token) {
		boolean result = false;
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
	
}
