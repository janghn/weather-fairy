package org.example.weatherproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       /* http
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/userlist", "/home","/join","/login","/signup","/main")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .usernameParameter("USER_ID")
                                .passwordParameter("USER_PW")
                                .loginPage("/login")
                                .loginProcessingUrl("/main")
                                .defaultSuccessUrl("/main")
                );

        return http.build();*/
        http
                .authorizeRequests()
                .antMatchers("/userlist", "/home","/join","/login","/signup","/main").permitAll()
//                .antMatchers("/api/user","/login","/signup","/main").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable(); // csrf 비활성화
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
