package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.AccessUser;
import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class CmShainAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(CmShainAuthenticationProvider.class);

    private final CmShainRepository cmShainRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CmShainAuthenticationProvider(
            final CmShainRepository cmShainRepository,
            final PasswordEncoder passwordEncoder) {
        this.cmShainRepository = cmShainRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        val user = (AccessUser) authentication.getPrincipal();
        val cmShain = Optional.ofNullable(this.cmShainRepository.selectByLoginCd(user.getId()));

        val password = (String) authentication.getCredentials();
        val authorities = new ArrayList<GrantedAuthority>();

        if (passwordEncoder.matches(password, cmShain.get().getPassword())) {
            // TODO
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            setupUserDetails(user, cmShain.get(), authorities);
        } else {
            throw new BadCredentialsException(String.format("Authentication Error: user=%s", user));
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private void setupUserDetails(
            final AccessUser accessUser,
            final CmShain shain,
            final Collection<? extends GrantedAuthority> authorities) {

        accessUser.setId(String.valueOf(shain.getCmShainId()));
        accessUser.setLastName(shain.getShainSei());
        accessUser.setFirstName(shain.getShainMei());
        accessUser.setAuthorities(authorities);
        accessUser.setLogged(true);
    }
}
