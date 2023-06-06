package com.hoangyen.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoangyen.model.User;
import com.hoangyen.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepository  userRepository; 
	public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	@Override
	public  UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
//		String pass;
//		Set<GrantedAuthority> authorities = new HashSet<>();
//		if(username == "admin") {
//			pass = "admin";
//			authorities.add(new SimpleGrantedAuthority("ADMIN"));	
//		}
//		else if(username =="sales") {
//			pass = "sales";
//			authorities.add(new SimpleGrantedAuthority("SALES"));
//		}
//		else {
//			pass = "user";
//			authorities.add(new SimpleGrantedAuthority("USER"));
//		}
//		return new org.springframework.security.core.userdetails.User(username, new BCryptPasswordEncoder().encode(pass), authorities);
		User u = userRepository.getUserByUsername(username);
		if(u ==null) {
			throw new UsernameNotFoundException("Could not find user");
			
		}
		Set<GrantedAuthority> authorities = u.getRoles().stream()
			    .map(role -> new SimpleGrantedAuthority(role.getName()))
			    .collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(username, u.getPassword(), authorities);
	
	}
	
}
