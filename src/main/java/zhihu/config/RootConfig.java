package zhihu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ZJ on 2016/4/13.
 */
@Configuration
@Import({DataConfig.class,JPAConfig.class,CachingConfig.class})
@ComponentScan(basePackages = {"zhihu.config","zhihu.dao","zhihu.security","zhihu.service"})
public class RootConfig {
}
