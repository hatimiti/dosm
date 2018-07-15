package com.github.hatimiti.dosm.base;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import static java.util.Optional.ofNullable;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * ログインユーザに関する情報を保持する。
 * @author hatimiti
 *
 */
//@Component
//@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
@Data
public class AccessUser implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String id;

	/** ユーザ名(名) */
	private String firstName;

	/** ユーザ名(姓) */
	private String lastName;

	/** IPアドレス */
	private String ipAddress;

	/** UserAgent */
	private String userAgent;

	/** ログイン済であれば true */
	private boolean isLogged;

	/** 権限ロールID */
	private Collection<? extends GrantedAuthority> authorities;

	/** アクセス元ロケール */
	private Locale locale;

	/** 表示言語 */
	private String langCd;

	/** 初期化済 */
	private boolean isInitialized;

	public void invalidate() {
		copyProperties(new AccessUser(), this);
	}

	public String getId() {
		return ofNullable(id).orElse("");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableCollection(authorities);
	}

	@Override
	public String getPassword() {
	    throw new UnsupportedOperationException();
	}

	@Override
	public String getUsername() {
		return Locale.JAPAN.equals(locale)
				? String.format("%s %s", getLastName(), getFirstName())
				: String.format("%s %s", getFirstName(), getLastName());
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
}
