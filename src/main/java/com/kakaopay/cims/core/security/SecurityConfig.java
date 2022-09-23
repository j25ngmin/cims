package com.kakaopay.cims.core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
@Configuration
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${api.version}")
    private String version;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().disable() // rest api -> 기본 설정 disable
        .csrf().disable() // csrf -> 비활성화, security 설정 시 기본값으로 활성화 되어있음
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 활용 안함
        .and()
        .authorizeRequests()
        .antMatchers("/"+version+"/auth/login"
        		, "/api/"+version+"/inquiries/**").permitAll() // 가입, 로그인에 대한 권한 해제
        .anyRequest().hasAnyRole("USER", "ADMIN")  // 나머지 요청에 대해 ADMIN ROLE 을 가져야만 접근 가능
        .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
