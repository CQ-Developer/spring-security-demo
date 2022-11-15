# Spring Security In Action

for more details, please check other branches.

```shell
# windows系统若执行失败, 可以在命令前加 winpty
# 生成HTTPS证书, 可以直接在git-bash中运行
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
````

```shell
# 转换到PKCS12格式
# Public Key Cryptography Standards #12
openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"
```

check doc with [excalidraw](https://excalidraw.com/).

## servlet

there are something should be noted in servlet environment:

---

`org.springframework.security.web.context.SecurityContextPersistenceFilter` is deprecated since `3.0`, but it is
configured by default.

It is recommended to use `org.springframework.security.web.context.SecurityContextHolderFilter` instead.
It can be configured like below:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // set requireExplicitSave to true
                // will inform spring use SecurityContextHolderFilter to instead SecurityContextPersistenceFilter
                .securityContext(config -> config.requireExplicitSave(true))
                .build();
    }

}
```

---

`org.springframework.security.web.access.intercept.AuthorizationFilter` is available since `5.5`,
but spring security still use `org.springframework.security.web.access.intercept.FilterSecurityInterceptor` as default.

It is recommended to use `org.springframework.security.web.access.intercept.AuthorizationFilter` instead.
It can be configured like below:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // use authorizeHttpRequests() to instead authorizeRequests()
                // will inform spring to use AuthorizationFilter instead FilterSecurityInterceptor
                .authorizeHttpRequests(config -> config.anyRequest().authenticated())
                .build();
    }

}
```
