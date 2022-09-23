package com.kakaopay.cims.core.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kakaopay.cims.api.user.service.UserService;
import com.kakaopay.cims.core.error.ValidationCustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        System.out.println("password : "+password);
        
            // 1. 사용자 정보 확인
            AuthorizedUser userSimple = userService.findAuthCustomByUserId(userId);
            if (userSimple == null) {
                throw new ValidationCustomException("사용자가 존재하지 않습니다.");
            }
            
            String encPassword = passwordEncoder.encode(password);
            System.out.println("password1 : "+password);
            System.out.println("password2 : "+userSimple.getPassword());

            if(!passwordEncoder.matches(password, userSimple.getPassword())) {
            	 throw new ValidationCustomException("비밀번호가 일치하지 않습니다.");
            }
            
            if(!("002").equals(userSimple.getUserType())) {
            	throw new ValidationCustomException("상담사만 로그인이 가능합니다.");
            }
            
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

            // 2. 권한 부여
            if (userSimple.getIsRoleAdmin()) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(Role.ADMIN.getKey()));
            }else {
                grantedAuthorityList.add(new SimpleGrantedAuthority(Role.USER.getKey()));
            }
            userSimple.setAuthorities(grantedAuthorityList);


       return new UsernamePasswordAuthenticationToken(userSimple, null, grantedAuthorityList);

    }

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}