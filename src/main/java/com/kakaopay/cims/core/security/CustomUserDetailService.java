package com.kakaopay.cims.core.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kakaopay.cims.api.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // TODO 제거 고민중 (API 요청시 마다 호출)
        AuthorizedUser authorizedUser = userService.findAuthCustomByUserId(userId);
        if(authorizedUser == null) throw new UsernameNotFoundException(String.format("userId : [%s] 찾을 수 없습니다", userId));

        return new AuthorizedUserSecurityAdapter(authorizedUser);
    }
}