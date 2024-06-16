package com.example.BloggingApplication.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String mobileNo;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany
    private Set<User> followers = new HashSet<>();

    public Long getId() {
    	return this.id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public String getMobileNo() {
    	return this.mobileNo;
    }
    public void setMobileNo(String mobileNo) {
    	this.mobileNo = mobileNo;
    } 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

