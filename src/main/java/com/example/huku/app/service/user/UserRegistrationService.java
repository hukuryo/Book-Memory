package com.example.huku.app.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.huku.domain.models.user.User;
import com.example.huku.domain.models.user.UserRegistrationForm;
import com.example.huku.infrastructure.repository.user.UserRepository;

@Service
public class UserRegistrationService {
    
    @Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	public User findById(Integer id){
        return userRepository.findById(id).get();
    }
	
	public void userRegistration( String username, String password) {
		String hashedPassword = passwordEncoder.encode(password);
		userRepository.saveAndFlush(new User(username, hashedPassword, "GENERAL"));
	}
}
