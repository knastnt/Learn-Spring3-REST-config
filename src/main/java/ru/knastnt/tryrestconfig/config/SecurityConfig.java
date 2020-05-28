package ru.knastnt.tryrestconfig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);

        auth.inMemoryAuthentication()
                .withUser("kostya")
                .password("{noop}12345")
                .authorities("SCOPE_write", "SCOPE_read")
                .and()
                .withUser("vasya")
                .password("{noop}1")
                .authorities("SCOPE_read");

    }


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
                    .antMatchers(HttpMethod.GET, "/user/info", "/api/foos/**").hasAuthority("SCOPE_read")
                    .antMatchers(HttpMethod.PATCH, "/api/foos/{^[\\d+]$}").hasAuthority("SCOPE_write")
                    .antMatchers(HttpMethod.POST, "/api/foos").hasAuthority("SCOPE_write")
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout().logoutSuccessUrl("/")
//                .oauth2ResourceServer()
//                .jwt();
                .and()
                    .csrf().ignoringAntMatchers("/api/foos/{^[\\d+]$}") //отключение csrf для адреса
                .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler); //Устанавливаем перехватчик для исключений Access Denied
    }
}
