package org.bryantinsurance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/login", "/api/logout", "/api/client", "/api/user", "api/carrier/create/**")
                .permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().csrf().disable();

//        http.authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(new JsonHttp403ForbiddenEntryPoint());
//
//        http.authorizeRequests().antMatchers("/**").authenticated();

    }

    class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             AuthenticationException e) throws IOException, ServletException {
            //send JSON msg
            httpServletResponse.getWriter().println("YAY");

        }
    }
}
