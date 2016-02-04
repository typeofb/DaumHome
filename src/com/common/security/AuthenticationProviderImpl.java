package com.common.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		if (!password.equals(userDetails.getPassword()))
			throw new BadCredentialsException("패스워드를 확인해 주세요.");
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		result.setDetails(userDetails);
		return result;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
