package com.turksat.belgenet.interview.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.turksat.belgenet.interview.models.ApplicationUser;

public class JwtUserDetails implements UserDetails {
private Long id;
private String username;
private String password;
private Collection<? extends GrantedAuthority> authorities;


	public JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.authorities = authorities;
}
	
	public static JwtUserDetails create(ApplicationUser user) {
		List<GrantedAuthority>authList=new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("user"));
		return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), authList);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;

	}

	@Override
	public boolean isAccountNonLocked() {
		return true;

	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

}
