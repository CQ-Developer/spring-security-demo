package org.huhu.spring.security.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.springframework.security.core.context.SecurityContextHolder.MODE_THREADLOCAL;

@Configuration
public class ProjectConfig {

    /**
     * 不推荐在WEB应用程序中使用 {@link SecurityContextHolder#MODE_GLOBAL} 模式,
     * 这会导致所有线程访问同一个 {@link SecurityContext} 修改或删除.
     * 由于 {@link SecurityContext} 不是线程安全的, {@link SecurityContextHolder#MODE_GLOBAL} 模式还会导致并发问题.
     */
    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(MODE_THREADLOCAL);
    }

}
