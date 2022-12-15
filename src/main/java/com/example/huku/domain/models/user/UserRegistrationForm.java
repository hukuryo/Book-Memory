package com.example.huku.domain.models.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.huku.domain.models.book.Book;

import lombok.Data;

@Data
public class UserRegistrationForm{

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank
	@Size(min = 0, max = 20)
	private String username;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String confirmPassword;
	
	@AssertTrue
	public boolean isPasswordValid() {
		if(password == null || confirmPassword == null) {
			return false;
		}
		return password.equals(confirmPassword);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
}
