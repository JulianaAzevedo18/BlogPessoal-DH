package com.blogpessoal.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogpessoal.blogpessoal.model.Cliente;
import com.blogpessoal.blogpessoal.repository.ClienteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ClienteRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> user = userRepository.findByUsuario(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
		return user.map(UserDetailsImpl::new).get();
	}

}
