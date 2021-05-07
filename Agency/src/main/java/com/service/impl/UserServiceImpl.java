package com.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.RequestUserDTO;
import com.model.Authority;
import com.model.User;
import com.repository.AuthRepository;
import com.repository.UserRepository;
import com.security.auth.TemporarilyBlockedException;
import com.service.UserService;




@Service
public class UserServiceImpl implements UserService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Override
//	public Optional<User> findOne(Long id) {
//		return userRepository.findById(id);
//	}

	

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}

	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}

	@Override
	public User save(RequestUserDTO userRequest) {
		User u = new User();
		u.setUsername(userRequest.getUsername());
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setFirstName(userRequest.getFirstName());
		u.setLastName(userRequest.getLastName());
		u.setBirthDate(userRequest.getBirth());
		
		Authority authority = authRepository.findByName(userRequest.getAuthorityName());
		
		List<Authority> authList = new ArrayList<Authority>();
		authList.add(authority);
		
		u.setAuthorities(authList);
		
		u = this.userRepository.save(u);
		return u;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}
	
	public void changePassword(String oldPassword, String newPassword) {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (authenticationManager != null) {
			logger.info("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			logger.error("No authentication manager set. can't change Password!");

			return;
		}

		logger.info("Changing password for user '" + username + "'");

		User user = (User) loadUserByUsername(username);

		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
	
	public void changeOthersPassword(String username, String newPassword) {	
		User user = (User) loadUserByUsername(username);

		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
	

	@Override
	public Authentication authenticate(String username, String password) throws TemporarilyBlockedException {
	
		
        try {
			Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			
			
			return authentication;
		}
		catch (Exception e) {
			
			logger.warn("Could not authenticate user with credentials: " + username + ", " + password);
		}
		
		return null;
	}

//	@Override
//	public User delete(Long id) {
//		
//		Optional<User> optional = userRepository.findById(id);
//		if(!optional.isEmpty()) {
//			User user = optional.get();
//			user.setAuthorities(null);
//			userRepository.save(user);
//			userRepository.deleteById(id);
//			
//			return optional.get();
//		}
//		return null;
//	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	
}