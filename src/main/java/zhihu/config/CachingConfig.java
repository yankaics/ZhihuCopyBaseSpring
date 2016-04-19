package zhihu.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZJ on 2016/4/19.
 */
@Configuration
@EnableCaching
public class CachingConfig {

	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}
}
