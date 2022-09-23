package com.kakaopay.cims.core.authentication.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.kakaopay.cims.core.security.AuthorizedUser;
import com.kakaopay.cims.core.security.CustomAuthenticationProvider;
import com.kakaopay.cims.core.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    private void getAuthentication(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password, authorities);
        authenticationManager.authenticate(authenticationToken);
    }

    public  String getJwtToken(AuthorizedUser authorizedEmployee) {
        return jwtTokenProvider.createToken(authorizedEmployee.getUserId(), authorizedEmployee.getAuthorities()
                .stream().map(Object::toString).collect(Collectors.toList()), authorizedEmployee.getRoles()
                .stream().map(Object::toString).collect(Collectors.toList()));
    }

    public String login(String userId, String password) {
        Authentication authentication = customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
        AuthorizedUser employee = (AuthorizedUser) authentication.getPrincipal();
        return getJwtToken(employee);
    }

}