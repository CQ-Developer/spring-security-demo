package org.huhu.spring.security.demo.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
@Documented
@WithSecurityContext(factory = CustomizedWithSecurityContextFactory.class)
public @interface WithCustomizedAuthentication {

    String username();

}
