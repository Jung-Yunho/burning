package com.burning.config.auth;

import com.burning.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    /**
     * 규칙 설정
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/images/**","/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http       //other configure params.
                .authorizeRequests()
                .antMatchers("/","/signup","/signin","/errorPage","/index").permitAll()
                .anyRequest().authenticated()  //그외의 path들은 인증된(로그인된) 유저만 접근 허용
                //로그인
                .and()
                .formLogin()
                .usernameParameter("loginID")
                .passwordParameter("pwd1")
                .loginPage("/signin")
                .defaultSuccessUrl("/")
                .permitAll()
                //로그아웃
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }



    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}