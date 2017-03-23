package com.rest.template.services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.template.dto.entity.User;
import com.rest.template.dto.repository.UserRepository;

@Service
public class GenericUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		username = username.toLowerCase();

		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		} else if (!user.getActivated()) {
			throw new UsernameNotFoundException("Username " + username + " inactive");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getGrantedAuthorities(user.getUsername()));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
		Collection<? extends GrantedAuthority> authorities;
		if (username.equals("admin")) {
			authorities = Arrays.asList(() -> "ROLE_ADMIN");
		} else {
			authorities = Arrays.asList(() -> "ROLE_USER");
		}
		return authorities;
	}
}