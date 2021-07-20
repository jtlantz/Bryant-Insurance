package org.bryantinsurance;

import org.bryantinsurance.user.CustomUserDetailsService;
import org.bryantinsurance.util.AjaxUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.formLogin().permitAll();
        //permit all
        http.authorizeRequests()
                .antMatchers("/","/api/login", "/api/logout").permitAll();
        //USER
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user**").hasRole("ADMIN");

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "api/client/**").hasRole("USER");

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").hasRole("ADMIN");

//        http.authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
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

    class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint{

        @Override
        public void commence(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             AuthenticationException e) throws IOException, ServletException {
            //send JSON msg
            String ajaxJsonResponse = AjaxUtils.convertToString(SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Page you are trying to access doesnt exist or you dont have permission")
                    .build()
            );
            httpServletResponse.getWriter().println(ajaxJsonResponse);
        }
    }
}