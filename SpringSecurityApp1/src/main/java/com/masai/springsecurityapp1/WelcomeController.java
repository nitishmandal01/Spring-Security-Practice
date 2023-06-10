package com.masai.springsecurityapp1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@PostMapping("/welcome")
	public ResponseEntity<String> welcome(){
		
	 return new ResponseEntity<String>("Welcome to Masai App without security",HttpStatus.
   ACCEPTED);
	}
		
	
	@GetMapping("/welcomeP")
	public ResponseEntity<String> welcomeP(){
		
			return new ResponseEntity<String>("Welcome to Masai App with Security",HttpStatus
     .ACCEPTED);
	}

	@GetMapping("/admin")
	public ResponseEntity<String> admin(){
		
		return new ResponseEntity<String>("Welcome to Masai App for Admin",HttpStatus.
					 ACCEPTED);
		}
	
	@GetMapping("/user")
	public ResponseEntity<String> user(){
		
		return new ResponseEntity<String>("Welcome to Masai App for User",HttpStatus.
					 ACCEPTED);
		}
	
}
