package org.huhu.spring.security.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import java.util.List;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

@SpringBootApplication
public class App {

    private final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));
        SpringApplication.run(App.class, args);
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void ready(ApplicationReadyEvent applicationReadyEvent) {
        ObjectProvider<FilterChainProxy> beanProvider = applicationReadyEvent
                .getApplicationContext()
                .getBeanProvider(FilterChainProxy.class);
        beanProvider.ifAvailable(this::printFilters);
    }

    /**
     * 打印出在当前配置下, spring security
     * 在 {@link FilterChainProxy} 中所配置的所有 {@link Filter}.
     */
    private void printFilters(FilterChainProxy filterChainProxy) {
        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
        logger.info("[{}] instances of SecurityFilterChain in application.", filterChains.size());
        filterChains.forEach(this::doPrint);
    }

    private void doPrint(SecurityFilterChain securityFilterChain) {
        List<Filter> filters = securityFilterChain.getFilters();
        for (int i = 0; i < filters.size(); i++) {
            logger.info("{} - {}", i, filters.get(i).getClass().getSimpleName());
        }
    }

}
