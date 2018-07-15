package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.AccessUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmShainAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CmShainAuthenticationFilter.class);
    public static final String URI = LoginController.URI + "/authenticate";

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response) throws AuthenticationException {

        final var token = new UsernamePasswordAuthenticationToken(
                createUserDetails(request, response), obtainPassword(request));
        setDetails(request, token);
        return getAuthenticationManager().authenticate(token);
    }

    private UserDetails createUserDetails(
            final HttpServletRequest request, final HttpServletResponse response) {

        final AccessUser user = new AccessUser();
        user.setId(obtainUsername(request));
        user.setUserAgent(request.getHeader("User-Agent"));
        user.setIpAddress(request.getRemoteAddr());
        user.setLocale(request.getLocale());
        return user;
    }

}
