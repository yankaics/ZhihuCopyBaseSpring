package zhihu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZJ on 2016/4/13.
 */
@Configuration
@ComponentScan(basePackages = {"zhihu.config","zhihu.dao","zhihu.security","zhihu.service","zhihu.model"})
public class RootConfig {
}
