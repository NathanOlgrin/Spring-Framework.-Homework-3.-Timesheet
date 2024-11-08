package ru.gb.timesheet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import ru.gb.timesheet.model.Roles;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

//    @Bean
//    GrantedAuthorityDefaults grantedAuthorityDefaults(){
//        return new GrantedAuthorityDefaults("");
//    }

//    @Bean
//    SecurityFilterChain noSecurity(HttpSecurity http) throws Exception{
//        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(it -> it.anyRequest().permitAll()).build();
//    }

//    @Bean
//    SecurityFilterChain securityKeycloak(HttpSecurity http) throws Exception{
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(it -> it.requestMatchers("/timesheets/**").hasRole("timesheet"))
//                .oauth2ResourceServer(oAuth2ResourceServerConfigurer -> oAuth2ResourceServerConfigurer
//                        .jwt(jwtConfigurer -> {
//                            JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//                            converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//                                Map<String, List<String>> realmAccess = jwt.getClaim("realm_access");
//                                List<String> roles = realmAccess.get("roles");
//
//                                return roles.stream().map(SimpleGrantedAuthority::new).map(it -> (GrantedAuthority) it).toList();
//                            });
//                            jwtConfigurer.jwtAuthenticationConverter(converter);
//                        })
//                )
////                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
////                    httpSecurityOAuth2ResourceServerConfigurer.configure(http);
////                })
//                .build();
//    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/home/projects/**").hasAuthority("admin")
//                       .requestMatchers("/home/projects/**").hasRole("admin" //admin)
                        .requestMatchers("/home/timesheets/**").hasAnyAuthority("admin", "user")
                        .requestMatchers("/timesheets/**").hasAnyAuthority("admin", "rest")
                        .requestMatchers("/projects/**").hasAnyAuthority("rest")
                        .requestMatchers("/users/**").hasAnyAuthority("rest")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe.key("uniqieAndSecret"))
                .logout(logout -> logout.deleteCookies("JSESSIONID"))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
