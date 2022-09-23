package com.kakaopay.cims.api.common.code.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonCodeDto {

    private Integer commonCodeSeq;
    
    private String groupCode;

    private String code;
    
    private String createUserId;
    
    private LocalDateTime createDate;
    
    private String modifyUserId;

    private LocalDateTime modifyDate;
}
