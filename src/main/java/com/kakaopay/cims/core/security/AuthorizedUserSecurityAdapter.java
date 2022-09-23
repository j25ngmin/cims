package com.kakaopay.cims.core.security;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class AuthorizedUserSecurityAdapter extends User {
    private AuthorizedUser authorizedUser;

    public AuthorizedUserSecurityAdapter(AuthorizedUser authorizedUser) {
        super(authorizedUser.getUserId(), authorizedUser.getPassword(),  Arrays.asList((authorizedUser.getIsRoleAdmin() ?
                (new SimpleGrantedAuthority(Role.ADMIN.getKey())) : new SimpleGrantedAuthority(Role.USER.getKey()))));
        this.authorizedUser = authorizedUser;
    }
}