package io.cloudtype.Demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	private CustomUserDetailsService customUserDetailsService;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/design","orders")
//                .hasRole("USER")
     // ↓ /sample/admin/**  주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
        		.antMatchers("/member/**").hasAnyRole("ADMIN","MEMBER")
     			.antMatchers("/admin/**").hasRole("ADMIN")
     			.anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .formLogin()
				.loginPage("/login")
				.usernameParameter("userid")
				.passwordParameter("userpassword")
				.loginProcessingUrl("/loginOk")
				.defaultSuccessUrl("/")
				.and()
				.rememberMe()
				.key("secret")
				.rememberMeParameter("autoLogin")
				.tokenValiditySeconds(86400)
				.userDetailsService(customUserDetailsService)
				.and()
				.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
                ;

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = CustomUserDetails
//                .getUsername()
//                .getPassword()
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

	// PasswordEncoder 를 bean 으로 IoC 에 등록
		@Bean
		public BCryptPasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
//		
//		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable();   // CSRF 비활성화
//			http.authorizeRequests()
//				.antMatchers("/landing/user/**").authenticated()
//				.antMatchers("/landing/admin/**").access("hasRole('ROLE_ADMIN')")
//				.anyRequest().permitAll()
//				.and()
//				.formLogin()
//				.loginPage("/login")
//				.usernameParameter("userid")
//				.passwordParameter("userpassword")
//				.loginProcessingUrl("/loginOk")
//				.defaultSuccessUrl("/")
//				.and()
//				.rememberMe()
//				.key("secret")
//				.rememberMeParameter("autoLogin")
//				.tokenValiditySeconds(86400)
//				
////				.userDetailsService()
//				.and()
//				.logout()
//				.logoutUrl("/logout")
//				.invalidateHttpSession(true)
//				
//				
//				
//				;
//		}

}
