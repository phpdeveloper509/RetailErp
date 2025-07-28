package com.retail.erp.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.retail.erp.repository.UserRepository;
import com.retail.erp.service.SessionService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtAuthFilter extends GenericFilter {

	private static final long serialVersionUID = 1L;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionService sessionService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authHeader = httpRequest.getHeader("Authorization");
		System.out.println("==== JwtAuthFilter ====");
		System.out.println("Authorization Header: " + authHeader);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			System.out.println("Extracted Token: " + token);

			boolean valid = jwtTokenProvider.validateToken(token);
			boolean active = sessionService.isActive(token);

			System.out.println("Token valid: " + valid);
			System.out.println("Session active: " + active);

			if (valid && active) {
				String username = jwtTokenProvider.getUsernameFromToken(token);
				System.out.println("Token Username: " + username);

				userRepository.findByEmail(username).ifPresentOrElse(user -> {
					System.out.println("User found in DB: " + user.getEmail() + " | Role: " + user.getRole());

					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							user,
							null,
							List.of(() -> "ROLE_" + user.getRole().name()) // e.g., ROLE_ADMIN
					);
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(auth);

				}, () -> {
					System.out.println("User not found in DB.");
				});

			} else {
				System.out.println("Token is invalid or session expired.");
			}
		} else {
			System.out.println("Authorization header missing or does not start with Bearer.");
		}

		chain.doFilter(request, response);
	}
}
