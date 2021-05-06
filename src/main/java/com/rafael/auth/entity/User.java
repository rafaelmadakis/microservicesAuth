package com.rafael.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "accountNonExpired")
	private Boolean aAccountNonExpired;

	@Column(name = "accountNonLocked")
	private Boolean accountNonLocked;

	@Column(name = "credentialsNonExpired")
	private Boolean credentialsNonExpired;

	@Column(name = "enabled")
	private Boolean enabled;

	@ManyToMany
	@JoinTable(name = "user_permission", joinColumns = {
			@JoinColumn(referencedColumnName = "id_user") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "id_permissions") })
	private List<Permission> permissions;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getPermissions();
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		this.permissions.stream().forEach(p -> {
			roles.add(p.getDescription());
		});

		return roles;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
