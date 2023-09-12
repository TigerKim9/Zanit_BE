package io.cloudtype.Demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
	
	private final CorsConfig corsConfig;
	
    
    
	@Autowired 
	private CustomUserDetailsService customUserDetailsService;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http	
        .csrf().disable()
        .authorizeRequests()
//                .antMatchers("/design","orders")
//                .hasRole("USER")
     // ↓ /sample/admin/**  주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
				.antMatchers("/coupon/**").hasAnyRole("ADMIN", "MEMBER")
        		.antMatchers("/member/**").hasAnyRole("ADMIN","MEMBER")
     			.antMatchers("/admin/**").hasRole("ADMIN")
     			.anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .formLogin()
				.loginPage("/login")
				.usernameParameter("userphone")
				.passwordParameter("userpassword")
				.loginProcessingUrl("/loginOk")
				.defaultSuccessUrl("/home")
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

		@Bean
		AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
				throws Exception {
			return authenticationConfiguration.getAuthenticationManager();
		}

}
