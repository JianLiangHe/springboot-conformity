package edu.conformity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
// maxInactiveIntervalInSeconds 默认是1800秒过期，这里改为60秒
// RedisFlushMode有两个参数：ON_SAVE（表示在response commit前刷新缓存），IMMEDIATE（表示只要有更新，就刷新缓存）
// redisNamespace : redis中的key
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60, 
	redisFlushMode = RedisFlushMode.ON_SAVE, 
	redisNamespace = "springboot-conformity-session01")
public class RedisSessionConfig {

}
