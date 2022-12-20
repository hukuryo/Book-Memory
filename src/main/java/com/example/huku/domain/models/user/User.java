package com.example.huku.domain.models.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.huku.domain.models.book.Book;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer Id;

	@Column(name = "bookId")
	private int bookId;

	// @OneToMany
	// private List<Book> book;

	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "roleName")
	private String roleName;
	
	public User() {
		
	}

	
	public User(String username, String password, String roleName) {
		this.username = username;
		this.password = password;
		this.roleName = roleName;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
}