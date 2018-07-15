package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.AccessUser;
import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import lombok.val;
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

@Configuration
public class CmShainAuthenticationProvider implements AuthenticationProvider {
    private final CmShainRepository cmShainRepository;

    @Autowired
    public CmShainAuthenticationProvider(CmShainRepository cmShainRepository) {
        this.cmShainRepository = cmShainRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        val user = (AccessUser) authentication.getPrincipal();
        val password = (String) authentication.getCredentials();

        val cmShain = Optional.ofNullable(this.cmShainRepository
                .selectByLoginCdAndPassword(user.getId(), password));

        val authorities = new ArrayList<GrantedAuthority>();
        if (cmShain.isPresent()) {
            // TODO
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            setupUserDetails(user, cmShain.get(), authorities);
        } else {
            throw new BadCredentialsException(String.format("Authentication Error: user=%s, password=%s", user, password));
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
