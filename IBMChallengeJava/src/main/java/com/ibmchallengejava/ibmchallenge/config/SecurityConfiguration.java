//package com.ibmchallengejava.ibmchallenge.config;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration  {
//
//    private static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
//
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("").password("").roles("ROLE_ANONYMOUS");
////                .withUser("user").password("password").roles("USER").and()
//                //.withUser("admin").password("password").roles("USER","ADMIN");
//    }
//}
