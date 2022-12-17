//package himedia.campus.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
///*
// * [Spring Security 설정 파일]
// *  - 스프링 부트 2.7x버전 이후 WebSecurityConfigurerAdapter가 Deprecated 되었다.
// * 
// */
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//    
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//    	return (web) -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
//    }
//}
