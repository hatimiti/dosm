package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.AccessUser;
import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class LoginService {

	@Resource
	AccessUser accessUser;
	@Resource
	CmShainRepository cmShainRepository;

	public LoginService(final CmShainRepository cmShainRepository) {
		this.cmShainRepository = cmShainRepository;
	}

	/*
	 * 一覧検索
	 */

	public void login(
			LoginForm form) {

		Optional<CmShain> shain = Optional.ofNullable(
				this.cmShainRepository.selectByLoginCdAndPassword(form.getLoginCd(), form.getPassword()));

		shain.ifPresent(this::setAccessUserDto);
		shain.ifPresentOrElse(this::setAccessUserDto, () -> {
//			throw new AppMessagesException(new AppMessage(WARN, "msg.error.login"));
		});
	}

	public void dummyLogin() {
		CmShain shain = new CmShain();
		shain.setCmShainId(0L);
		shain.setShainSei("ダミー");
		shain.setShainMei("ログイン");
		setAccessUserDto(shain);
	}

	private void setAccessUserDto(CmShain shain) {
		this.accessUser.setId(String.valueOf(shain.getCmShainId()));
		this.accessUser.setLastName(shain.getShainSei());
		this.accessUser.setFirstName(shain.getShainMei());
		this.accessUser.setLogged(true);
	}
}
