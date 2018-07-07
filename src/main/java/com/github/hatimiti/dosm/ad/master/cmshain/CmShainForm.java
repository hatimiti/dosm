package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.ad.master.Mode;
import com.github.hatimiti.dosm.base.Form;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import com.github.hatimiti.dosm.validator.UnusedCmShainId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 入力チェック(TERASORUNA): https://terasolunaorg.github.io/guideline/5.3.0.RELEASE/ja/ArchitectureInDetail/WebApplicationDetail/Validation.html
 */
@Data
@EqualsAndHashCode
public class CmShainForm implements Form {

	@UnusedCmShainId
	@NotBlank(groups = Mode.Upd.class)
	private Long cmShainId;

	private Long cmKaishaId;
	@NotBlank
	@Size(max = 50)
	private String shainSei;
	@NotBlank
	@Size(max = 50)
	private String shainMei;
	@Size(max = 100)
	private String shainSeiEn;
	@Size(max = 100)
	private String shainMeiEn;

	@NotBlank
	private String loginCd;

	@NotBlank(groups = Mode.Reg.class)
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
