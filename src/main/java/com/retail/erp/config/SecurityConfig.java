package com.retail.erp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retail.erp.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() 
        .and()
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/auth/**").permitAll()
            	    .requestMatchers("/api/**").permitAll()
            	    // ADMIN only access
						
						  .requestMatchers("/api/products/**").hasRole("ADMIN")
						  .requestMatchers("/api/vendors/**").hasRole("ADMIN")
						  .requestMatchers("/api/customers/**").hasRole("ADMIN")
						  .requestMatchers("/api/purchases/**").hasRole("ADMIN")
						  .requestMatchers("/api/reports/**").hasRole("ADMIN")
						  .requestMatchers("/api/users/**").hasRole("ADMIN")
						  .requestMatchers("/api/sites/**").hasRole("ADMIN")
						  .requestMatchers("/api/suppliers/**").hasRole("ADMIN")
						  .requestMatchers("/api/units/**").hasRole("ADMIN")
						  .requestMatchers("/api/items/**").hasRole("ADMIN")
						  .requestMatchers("/api/units/**").hasRole("ADMIN")
						  
						  // ADMIN or CASHIER 
						  .requestMatchers("/api/sales/**").hasAnyRole("ADMIN","CASHIER") 
						  .requestMatchers("/api/inventory/**").hasAnyRole("ADMIN","CASHIER") 
						  .requestMatchers("/api/customers/**").hasAnyRole("ADMIN","CASHIER") // split properly
						  .requestMatchers("/api/vendors/**").hasAnyRole("ADMIN", "CASHIER")
						  .requestMatchers("/api/quotations/**").hasAnyRole("ADMIN", "CASHIER")
						  .requestMatchers("/api/departments/**").hasAnyRole("ADMIN", "CASHIER")
						  .requestMatchers("/api/itemcategories/**").hasAnyRole("ADMIN", "CASHIER")
						  .requestMatchers("/api/quotations/**").hasAnyRole("ADMIN", "CASHIER")
						 

            	    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
