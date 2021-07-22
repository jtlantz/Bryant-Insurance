package org.bryantinsurance;

import org.bryantinsurance.dto.SimpleResponseDTO;
import org.bryantinsurance.util.AjaxUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.formLogin().permitAll();
        //permit all
        http.authorizeRequests()
                .antMatchers("/","/api/login", "/api/logout", "/api/current_user").permitAll();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user").hasAnyRole("USER", "ADMIN");

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/**").hasRole("ADMIN");

        http.authorizeRequests()
                .antMatchers(HttpMethod.PATCH, "/api/user/**").hasAnyRole("ADMIN", "USER");

        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAnyRole("ADMIN", "USER");

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/client/**").hasAnyRole("ADMIN", "USER");

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/role").hasAnyRole("ADMIN", "USER");

        http.authorizeRequests()
                .antMatchers("/api/carrier").hasAnyRole("ADMIN", "USER");

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").hasRole("ADMIN");

        http.exceptionHandling()
                .authenticationEntryPoint(new JsonHttp403ForbiddenEntryPoint());
        http.authorizeRequests().antMatchers("/**").authenticated();
    }

    class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint{

        @Override
        public void commence(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             AuthenticationException e) throws IOException {
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
