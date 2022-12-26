package com.example.huku.app.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.huku.domain.models.user.User;
import com.example.huku.infrastructure.repository.user.UserDetailsImpl;
import com.example.huku.infrastructure.repository.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
    public void register(String username, String password) {
        String sql = "INSERT INTO user( name, password) VALUES(?, ?)";
        jdbcTemplate.update(sql, username, passwordEncoder.encode(password));
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		return new UserDetailsImpl(user);
	}
}
