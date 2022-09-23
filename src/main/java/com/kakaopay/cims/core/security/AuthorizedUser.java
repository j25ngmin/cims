package com.kakaopay.cims.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizedUser implements UserDetails {

    private static final long serialVersionUID = -6399633679042460684L;

	private String userId;

    private String userName;
   
    private String userType;
    
    private Boolean isRoleAdmin;

    @NotBlank // TODO 로그인Dto를 분리할 것 인가
    private String password = "{bcrypt}";

    private List<GrantedAuthority> authorities = new ArrayList<>();

    private List<Long> roles = new ArrayList<>();


    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = (List) authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
    	return this.password;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUsername() {
        return this.userName;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled() {
        return true;
    }
}



