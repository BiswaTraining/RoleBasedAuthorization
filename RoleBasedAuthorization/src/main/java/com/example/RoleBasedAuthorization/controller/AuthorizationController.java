package com.example.RoleBasedAuthorization.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RoleBasedAuthorization.entity.User;
import com.example.RoleBasedAuthorization.service.AuthorizationService;

@RestController
@RequestMapping("/user")
public class AuthorizationController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String response = authorizationService.addUser(user);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}	
	
	@GetMapping("/viewAllPatientData")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DOCTOR')")
    public ResponseEntity<List<User>> viewAllPatientData(Principal principal) {
		System.out.println("Logged in user has the name "+principal.getName());
		List<User> patientsData = authorizationService.getAllPatientData("PATIENT");
        return new ResponseEntity<List<User>>(patientsData,HttpStatus.OK);
    }

    @GetMapping("/viewAllData")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> loadUsers() {
        return authorizationService.getAllUsers();
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('PATIENT')")
    public String testPatientAccess() {
        return "Patient can only access this end point";
    }
    
    @GetMapping("/test/nurse")
    @PreAuthorize("hasAuthority('NURSE')")
    public String testNurseAccess() {
        return "Nurse can only access this end point";
    }

}
