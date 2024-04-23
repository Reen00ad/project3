package com.example.pproject3.SecurityConfig;

import com.example.pproject3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .authorizeRequests()
//                .requestMatchers("/api/v1/auth/registerCustomer","/api/v1/auth/registerEmployee").permitAll()
//               // .requestMatchers("/api/v1/todo/getalla").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/customer/getall","/api/v1/customer/update","/api/v1/account/add").hasAuthority("CUSTOMER")
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutUrl("/api/v1/auth/logout")
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//                .and()
//                .httpBasic();
//        return httpSecurity.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/registerCustomer","/api/v1/auth/registerEmployee").permitAll()
                .requestMatchers("/api/v1/auth/getall/{username}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/delete/{userNameAdmin}/{userName}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/update").permitAll()
                .requestMatchers("/api/v1/employee/update").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/update").hasAuthority("CUSTOMER")
               // .requestMatchers("/api/v1/auth/delete/{userNameAdmin}/{userName}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/employee/activateAccount/{id}").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/account/accountDetails/{accid}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/getall").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/account/deposite/{accid}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/withdraw/{accid}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/transfer/{accid1}/{accid2}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/employee/blockAccount/{id}").hasAuthority("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}
