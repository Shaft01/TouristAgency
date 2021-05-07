package com.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dto.RequestUserDTO;
import com.model.User;
import com.security.auth.TemporarilyBlockedException;

import java.util.List;




public interface UserService extends UserDetailsService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(RequestUserDTO userRequest);
	User saveUser(User user);
	Authentication authenticate(String username, String password) throws TemporarilyBlockedException;
//	void changePassword(String oldPassword, String newPassword);
//	void changeOthersPassword(String username, String newPassword); 
//	Optional<User> findOne(Long id);
//	User delete(Long id);
	


}