package com.poc.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.security.constants.Constants;
import com.poc.security.dto.request.AuthenticationRequestDTO;
import com.poc.security.dto.response.AuthenticationResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final ObjectMapper objectMapper;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
		this.authenticationManager = authenticationManager;
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("Attempt to authenticate user");
		try {
			AuthenticationRequestDTO authRequest = this.objectMapper.readValue(request.getInputStream(), AuthenticationRequestDTO.class);

			return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authRequest.getUserName(), authRequest.getPassword(), new HashSet<>()));
		} catch (IOException e) {
			log.error("Error parsing authentication request", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication auth) throws IOException {
		String token = generateJwtToken(auth);
		prepareHttpResponse(response, token);
	}

	private String generateJwtToken(Authentication auth) {
		final String authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		Instant now = Instant.now();
		Date issuedAt = Date.from(now);
		Date expiration = Date.from(now.plusSeconds(Constants.TOKEN_EXPIRATION_TIME));

		String token = Jwts.builder()
				.setIssuedAt(issuedAt)
				.setIssuer(Constants.ISSUER_INFO)
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.claim(Constants.AUTHORITIES_KEY, authorities)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, Keys.secretKeyFor(SignatureAlgorithm.HS512))
				.compact();

		log.info("JWT token generated successfully for user: {}", ((User) auth.getPrincipal()).getUsername());
		return token;
	}

	private void prepareHttpResponse(HttpServletResponse response, String token) throws IOException {
		response.addHeader(HttpHeaders.AUTHORIZATION, Constants.TOKEN_BEARER_PREFIX + " " + token);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		AuthenticationResponseDTO responseDTO = AuthenticationResponseDTO.builder().accessToken(token).build();
		response.getWriter().write(objectMapper.writeValueAsString(responseDTO));
		response.getWriter().flush();

		log.info("Authentication successful, response sent to client");
	}
}
