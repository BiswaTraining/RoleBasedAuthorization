package com.example.RoleBasedAuthorization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RoleBasedAuthorization.entity.User;
import com.example.RoleBasedAuthorization.repository.UserRepository;

@Service
public class AuthorizationService {
	@Autowired UserRepository userRepository;
	
	public String addUser(User user) {
		userRepository.save(user);
		return "User saved successfully";
	}
	
	public List<User> getAllPatientData(String patient){
		return userRepository.getAllDataByRole(patient);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
