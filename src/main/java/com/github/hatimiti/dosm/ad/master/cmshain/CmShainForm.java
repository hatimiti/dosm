package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.ad.master.Mode;
import com.github.hatimiti.dosm.base.Form;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;

/**
 * 入力チェック(TERASORUNA): https://terasolunaorg.github.io/guideline/5.3.0.RELEASE/ja/ArchitectureInDetail/WebApplicationDetail/Validation.html
 */
@Data
@EqualsAndHashCode
public class CmShainForm implements Form {

	private Long cmShainId;
	private Long cmKaishaId;
	@NotEmpty
	private String shainSei;
	private String shainMei;
	private String shainSeiEn;
	private String shainMeiEn;

	private String loginCd;
	private String password;
	private Integer versionNo;

	private Mode mode;

	public void copyFrom(final CmShain entity) {
		this.cmShainId = entity.getCmShainId();
		this.cmKaishaId = entity.getCmKaishaId();
		this.shainSei = entity.getShainSei();
		this.shainMei = entity.getShainMei();
		this.shainSeiEn = entity.getShainSeiEn();
		this.shainMeiEn = entity.getShainMeiEn();
		this.loginCd = entity.getLoginCd();
		this.password = entity.getPassword();
		this.versionNo = entity.getVersionNo();
	}

	String getEncryptedPassword() {
		return new BCryptPasswordEncoder().encode(password);
	}

}
