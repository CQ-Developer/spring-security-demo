package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.ProjectConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        // 通过Spring直接注入当前已认证的用户
        return "Hello, " + authentication.getName() + "!";
    }

    /**
     * 通过设置 {@link SecurityContextHolderStrategy} 让异步方法也能获得 {@link SecurityContext}
     *
     * @see ProjectConfig#initializingBean()
     */
    @Async
    @GetMapping("/bye")
    public void goodbye() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getName());
    }

    /**
     * 使用默认的模式 {@link SecurityContextHolder#MODE_THREADLOCAL}
     *
     * @see ProjectConfig#initializingBean()
     */
    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            // 可以获取到由DelegatingSecurityContextCallable转发来的SecurityContext
            return securityContext.getAuthentication().getName();
        };
        // 在一个异步线程的任务中调用SecurityContext
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // 通过DelegatingSecurityContextCallable将SecurityContext转发
            DelegatingSecurityContextCallable<String> delegatingSecurityContextCallable = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + executorService.submit(delegatingSecurityContextCallable).get() + "!";
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * 使用默认的模式 {@link SecurityContextHolder#MODE_THREADLOCAL},
     * 通过 {@link DelegatingSecurityContextExecutorService} 转发 {@link SecurityContext}.
     *
     * @see ProjectConfig#initializingBean()
     */
    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            // 可以获取到由DelegatingSecurityContextExecutorService转发来的SecurityContext
            return securityContext.getAuthentication().getName();
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService = new DelegatingSecurityContextExecutorService(executorService);

        try {
            // 通过DelegatingSecurityContextExecutorService将SecurityContext转发
            return "Hola, " + executorService.submit(task).get() + "!";
        } finally {
            executorService.shutdown();
        }
    }

}
