package com.greatlearning.studentmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.studentmanagement.model.User;
import com.greatlearning.studentmanagement.service.UserService;
import com.greatlearning.studentmanagement.repository.UserRepository;
import com.greatlearning.studentmanagement.security.config.SecurityDetails;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new SecurityDetails(user);
	}

}
