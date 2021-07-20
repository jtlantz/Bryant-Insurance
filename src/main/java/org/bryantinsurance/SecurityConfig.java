package org.bryantinsurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.formLogin().permitAll();
        http.authorizeRequests()
                .antMatchers("/","/api/login", "/api/logout").permitAll();
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        http.exceptionHandling()
                .authenticationEntryPoint(new JsonHttp403ForbiddenEntryPoint());
        http.authorizeRequests().antMatchers("/**").authenticated();
//        http.formLogin().permitAll();
//        http.authorizeRequests()
//                .antMatchers("/", "api/login", "api/logout")
//                .hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint{

        @Override
        public void commence(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             AuthenticationException e) throws IOException, ServletException {
            //send JSON msg
            httpServletResponse.getWriter().println("You dont have access to this page");
        }
    }
}