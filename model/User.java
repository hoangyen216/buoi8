package com.hoangyen.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	


	@ManyToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name ="UserRole",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
			)
	private Set<Role> roles= new HashSet<>();



	public Integer getUser_id() {
		return user_id;
	}



	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
}
