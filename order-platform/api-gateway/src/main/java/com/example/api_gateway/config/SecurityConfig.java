//package com.example.api_gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http)
//    {
//        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)   // ⛔ disable basic auth
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)   // ⛔ disable login page
//                .authorizeExchange(e->e.pathMatchers("/actuator/**")
//                        .permitAll()
//                        .pathMatchers("/fallback/**")
//                        .permitAll()
//                        .anyExchange().authenticated())
////                .oauth2ResourceServer(o->o.jwt(Customizer.withDefaults()))
//                .build();
//    }
//}
