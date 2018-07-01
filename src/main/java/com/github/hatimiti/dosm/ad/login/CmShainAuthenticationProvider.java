package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.AccessUser;
import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Configuration
public class CmShainAuthenticationProvider implements AuthenticationProvider {
    private final CmShainRepository cmShainRepository;

    @Autowired
    public CmShainAuthenticationProvider(CmShainRepository cmShainRepository) {
        this.cmShainRepository = cmShainRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var user = (AccessUser) authentication.getPrincipal();
        final var password = (String) authentication.getCredentials();

        Optional<CmShain> cmShain = ofNullable(this.cmShainRepository.selectByLoginCdAndPassword(user.getId(), password));

        Collection<GrantedAuthority> authorityList = new ArrayList<>();
        if (cmShain.isPresent()) {
            setAccessUserDto(user, cmShain.get());
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            throw new BadCredentialsException(String.format("Authentication Error: user=%s, password=%s", user, password));
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private void setAccessUserDto(AccessUser accessUser, CmShain shain) {
//        accessUser.setId(String.valueOf(shain.getCmShainId()));
        accessUser.setLastName(shain.getShainSei());
        accessUser.setFirstName(shain.getShainMei());
        accessUser.setLogged(true);
    }
}
