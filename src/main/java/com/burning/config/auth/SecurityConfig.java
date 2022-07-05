package com.burning.config.auth;

import com.burning.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 규칙 설정
     *
     * @param http
     * @throws Exception
     */
    private final UserService userService;

    @Override
    public void configure(WebSecurity web)throws Exception{
        //인증을 무시하기 위한 설정
        web.ignoring().antMatchers("/css/**","/js/**","/images/**","/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http       //other configure params.
                .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and()//권한 모두 허용
                .formLogin()
                    .usernameParameter("login_id")
                    .loginPage("/signin")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/signout")) //로그아웃 시 URL 재정의
                    .logoutSuccessUrl("/")//로그아웃 성공시 redirect 이동
                    .invalidateHttpSession(true) //HTTP Session 초기화
                    .and()
                .exceptionHandling();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        //로그인 처리를 하기 위한 AuthenticationManagerBuilder를 설정
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}