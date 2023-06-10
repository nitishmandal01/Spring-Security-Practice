package com.masai.springsecurityapp1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests( (auth)->auth
				.requestMatchers("/admin","/user","welcome").permitAll()
				.anyRequest().authenticated()
				
				
		).csrf(csrf -> csrf.disable())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		return http.build();
  }
//  Approach 1-=-=-=-=-=-=-=-=-=-=-=-=-=-=--
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		
//		UserDetails admin= User.withDefaultPasswordEncoder()
//					.username("admin")
//					.password("1234")
//					.authorities("admin")
//					.build();
//		
//		UserDetails user= User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("1234")
//				.authorities("read")
//				.build();
//		
//		UserDetails customer= User.withDefaultPasswordEncoder()
//				.username("ravish")
//				.password("1234")
//				.authorities("read")
//				.build();
//
//		return new InMemoryUserDetailsManager(admin,user,customer);
//		
//	}
	
//	Approach 2-=-=-=-=-==-=-=-=-=-=-=-=-=-=----------===
	
	@Bean
	public InMemoryUserDetailsManager userDetails() {
	
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
	  UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
	    UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
	    userDetailsService.createUser(admin);
	    userDetailsService.createUser(user);
	    return userDetailsService;
	}

	 @Bean
	 public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	 }
}
