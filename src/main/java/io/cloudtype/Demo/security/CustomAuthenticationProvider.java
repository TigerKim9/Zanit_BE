package io.cloudtype.Demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("authentication정보:{}", authentication);
		
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(authentication.getName());
		
		if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getUser().getUserPassword())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}
		
		log.info("user : {}",user.getUser().getUserName());
					 
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	


	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
}