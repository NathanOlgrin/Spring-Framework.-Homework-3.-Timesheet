//package ru.gb.timesheet.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.core.GrantedAuthorityDefaults;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
////@EnableMethodSecurity(securedEnabled = true)
//public class SecurityConfiguration {
//
////    @Bean
////    GrantedAuthorityDefaults grantedAuthorityDefaults(){
////        return new GrantedAuthorityDefaults("");
////    }
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
////        return httpSecurity
////                .authorizeHttpRequests(requests -> requests
////                        .requestMatchers("/home/projects/**").hasAuthority("admin")
//////                       .requestMatchers("/home/projects/**").hasRole("admin" //admin)
////                        .requestMatchers("/home/timesheets/**").hasAnyAuthority("admin", "user")
////                        .requestMatchers("/timesheets/**").hasAnyAuthority("admin", "rest")
////                        .requestMatchers("/projects/**").hasAnyAuthority("rest")
////                        .requestMatchers("/users/**").hasAnyAuthority("rest")
////                        .anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults())
////                .rememberMe(rememberMe -> rememberMe.key("uniqieAndSecret"))
////                .logout(logout -> logout.deleteCookies("JSESSIONID"))
////                .build();
////    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
