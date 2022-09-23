package com.kakaopay.cims.api.user.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private Long userSeq;
	
    private String userId;

    private String password;
    
    private String userName;
    
    private String userType;
    
    private String createUserId;
    
    private LocalDateTime createDate;
    
    private String modifyUserId;
    
    private LocalDateTime modifyDate;

}
