package himedia.campus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * [Spring Security 설정 파일]
 *  - 스프링 부트 2.7x버전 이후 WebSecurityConfigurerAdapter가 Deprecated 되었다.
 *  - @EnableWebSecurity : 모든 요청 URL이 스프링 시큐리티 제어를 받도록ㄷ 함
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.mvcMatchers("/admin/**").hasRole("ADMIN")
        	.mvcMatchers("/**").permitAll()
        	.anyRequest().authenticated()
        	.and()
	        .formLogin()
            .loginPage("/member/login")
            .defaultSuccessUrl("/")
            .usernameParameter("memberId")
            .passwordParameter("memberPw")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
}
