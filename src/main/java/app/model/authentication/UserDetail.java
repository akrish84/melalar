package app.model.authentication;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.model.User;

public class UserDetail implements UserDetails {
	
	private String email;
	private String password;
	private boolean isActive;
	private long userId;

	public UserDetail() {}

	public UserDetail(Optional<User> user) {
		this.email = user.get().getEmail();
		this.password = user.get().getPassHash();
		this.isActive = user.get().isActive();
		this.userId = user.get().getId();
	}
	
//	Might have to add authorities based on user roles.
//	Currently hard coded.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
		return isActive;
	}
	
	public long getUserId() {
		return userId;
	}

}
