package com.github.hatimiti.dosm.base;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

import static java.util.Optional.ofNullable;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

/**
 * ログインユーザに関する情報を保持する。
 * @author hatimiti
 *
 */
@Data
@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class AccessUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String id = "NONE";

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
	private Integer authroleId;

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

	public String getName() {
		return Locale.JAPAN.equals(locale)
				? String.format("%s %s", getLastName(), getFirstName())
				: String.format("%s %s", getFirstName(), getLastName());
	}

}
