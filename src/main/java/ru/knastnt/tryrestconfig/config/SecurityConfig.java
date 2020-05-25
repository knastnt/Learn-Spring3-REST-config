package ru.knastnt.tryrestconfig.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * если кто-то голосует за, пропускаем,
         * если хоть 1 голосует против не пускаем,
         * если никто не проголосовал не пускаем.
         *
         * При необходимости добавляйте исключения. Исключения должны быть как можно более строгие.
         * Явно указывайте тип http метода, а url должен быть как можно более полным.
         */
        http
                .cors() //Для разрешения обработки запросов с другого источника URL
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/info", "/api/foos/**")
                .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/api/foos")
                .hasAuthority("SCOPE_write")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
