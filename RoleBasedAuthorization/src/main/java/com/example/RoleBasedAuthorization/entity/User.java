package com.example.RoleBasedAuthorization.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="USER_DETAILS")
public class User {
	@Id
	private int user_id;
	private String title;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String role;
}
