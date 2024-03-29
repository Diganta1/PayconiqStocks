package com.payconiq.rest.webservices.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.payconiq.rest.webservices.model.User;
import com.payconiq.rest.webservices.repository.UserRepository;

/**
 * security model
 * @author diganta
 *
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {
	
	private static final String INVALID_CREDENTIALS="Invalid credential.";
	private static final String ROLE_ADMIN="ROLE_ADMIN";
	

	@Autowired
	private UserRepository userDao;
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException(INVALID_CREDENTIALS);
		}
		return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority(ROLE_ADMIN));
	}

}