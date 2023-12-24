package com.poc.security.service;

import com.poc.security.entity.UserEvent;
import com.poc.security.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserDetailsFactory userDetailsFactory;
	private final AuthoritiesFactory authoritiesFactory;

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		log.info("Attempting to load user by username: {}", userName);

		final UserEvent user = this.userRepository.findByUserNameIgnoringCase(userName)
				.orElseThrow(() -> {
					log.error("User not found with username: {}", userName);
					return new UsernameNotFoundException(userName);
				});

		final Set<SimpleGrantedAuthority> authorities = this.authoritiesFactory.createAuthorities(user.getRoles());

		UserDetails userDetails = this.userDetailsFactory.createUserDetails(user.getUserName(), user.getPassword(), authorities);
		log.info("User loaded successfully: {}", userName);

		return userDetails;
	}
}
