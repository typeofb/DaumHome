package com.common.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = Arrays.asList((GrantedAuthority) new SimpleGrantedAuthority("ROLE_USER"));
		EPUser epUser = new EPUser();
		epUser.setUsername(username);
		epUser.setPassword("1234");
		epUser.setAuthorities(authorities);
		return epUser;
	}
}
