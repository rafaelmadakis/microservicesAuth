package com.rafael.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafael.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository UserRepository;

	@Autowired
	public UserService(com.rafael.auth.repository.UserRepository userRepository) {
		UserRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = UserRepository.findByUserName(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("UserName " + username + " not found ");
		}

	}

}
