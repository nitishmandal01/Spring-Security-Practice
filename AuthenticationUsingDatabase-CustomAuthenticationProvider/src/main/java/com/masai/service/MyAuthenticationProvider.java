package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
			String username = auth.getName();
			String password = auth.getCredentials().toString();
			
			Optional<Customer> opt= customerRepository.findByEmail(username);
			
			if(opt.isEmpty()) {
				throw new BadCredentialsException("No User registerd with this details");
			}else {
				Customer customer  = opt.get();
				if(passwordEncoder.matches(password,customer.getPassword() )) {
					
					List<GrantedAuthority> authorities = new ArrayList<>();
					
//					authorities.add(new SimpleGrantedAuthority(customer.getRole()));
					
					return new UsernamePasswordAuthenticationToken(username, password, authorities);
					
				} else
					throw new BadCredentialsException("Invalid Password");
			}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
