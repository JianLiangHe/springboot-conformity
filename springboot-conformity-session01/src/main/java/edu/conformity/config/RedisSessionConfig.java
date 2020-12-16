package edu.conformity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
// maxInactiveIntervalInSeconds 默认是1800秒过期，这里改为60秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class RedisSessionConfig {

}
