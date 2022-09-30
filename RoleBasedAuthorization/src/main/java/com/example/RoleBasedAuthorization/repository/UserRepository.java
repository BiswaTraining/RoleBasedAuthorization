package com.example.RoleBasedAuthorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.RoleBasedAuthorization.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("Select u from User u where u.username= :username")
	public User getUserByUserName(@Param ("username") String username);
	
	
	@Query("Select u from User u where u.role= :role")
	public List<User> getAllDataByRole(@Param ("role") String role);
}
