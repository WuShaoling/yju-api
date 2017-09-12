package com.guanshan.opera.webapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class Insight365UserDetails implements UserDetails {

	private static Logger logger = LoggerFactory.getLogger(Insight365UserDetails.class);

	private static final long serialVersionUID = 9033286999006651490L;

	protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority("ROLE_NEW");

	protected Insight365User insight365User;

	protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	public Insight365UserDetails() {
	}

	public Insight365UserDetails(Insight365User user) {
		this.insight365User = user;

		initialAuthorities();

	}

	private void initialAuthorities() {
		// Default, everyone have it

		this.grantedAuthorities.add(DEFAULT_USER_ROLE);


	//	logger.debug("kjasdhfkjdsh="+roleList);

	}

	/**
	 * Return authorities, more information see {@link #initialAuthorities()}
	 *
	 * @return Collection of GrantedAuthority
	 */
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return insight365User.getPassword();
		//return "52334e304d6a41784e7a417a4d444d3d";//Gst20170303
		//有时候看上去最蠢的办法往往是最有效的办法
	}

	@Override
	public String getUsername() {
		return insight365User.getUsername();
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

	public Insight365User user() {
		return insight365User;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{user=").append(insight365User);
		sb.append('}');
		return sb.toString();
	}
}
