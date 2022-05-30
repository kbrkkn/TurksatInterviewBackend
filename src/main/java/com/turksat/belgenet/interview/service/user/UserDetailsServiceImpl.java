package com.turksat.belgenet.interview.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.turksat.belgenet.interview.models.ApplicationUser;
import com.turksat.belgenet.interview.security.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
		super();
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = findByUserName(username);
		return JwtUserDetails.create(applicationUser);
	}

	public ApplicationUser findByUserName(String username) {
		return applicationUserRepository.findByUserName(username);
	}
	
	public UserDetails loadByUserId(Long id) {
		ApplicationUser applicationUser=applicationUserRepository.findById(id).get();
		return JwtUserDetails.create(applicationUser);
				
	}

}
