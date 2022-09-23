package com.kakaopay.cims.api.inquiry.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 고객 : InquiryDtoGroupA
 * 상담사 : InquiryDtoGroupB
 * @author min
 *
 */

@Getter
@Setter
@NoArgsConstructor
public class InquiryDto {

	/*
	 * InquiryDtoGroupA : 고객 등록
	 * InquiryDtoGroupB : 상담사 답변
	 * InquiryDtoGroupC : 상담사 접수
	 * 
	 * */
	
    private Long inquirySeq;

    @NotBlank(message = "고객ID는 필수 입력 값입니다.", groups=InquiryDtoGroupA.class)
    @Size(min=1, max=50, message="고객ID를 {min}~{max}자 사이로 입력해주세요.", groups=InquiryDtoGroupA.class)
    private String inquiryUserId;

    @NotBlank(message = "제목은 필수 입력 값입니다.", groups=InquiryDtoGroupA.class)
    @Size(min=1, max=100, message="제목을 {min}~{max}자 사이로 입력해주세요.", groups=InquiryDtoGroupA.class)
    private String title;

    private String status;
    
    private String statusName;

    @NotBlank(message = "문의내용은 필수 입력 값입니다.", groups=InquiryDtoGroupA.class)
    @Size(min=1, max=500, message="문의내용을 {min}~{max}자 사이로 입력해주세요.", groups=InquiryDtoGroupA.class)
    private String inquiryContent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inquiryDate;

    @NotBlank(message = "상담사ID는 필수 입력 값입니다.", groups=InquiryDtoGroupB.class)
    @NotBlank(message = "상담사ID는 필수 입력 값입니다.", groups=InquiryDtoGroupC.class)
    @Size(min=1, max=50, message="상담사ID를 {min}~{max}자 사이로 입력해주세요.", groups=InquiryDtoGroupB.class)
    @Size(min=1, max=50, message="상담사ID를 {min}~{max}자 사이로 입력해주세요.", groups=InquiryDtoGroupC.class)
    private String answerUserId;
    
    @NotBlank(message = "답변내용은 필수 입력 값입니다.", groups=InquiryDtoGroupB.class)  
    @Size(min=1, max=500, message="답변내용을 {min}~{max}자 사이로 입력해주세요.", groups=InquiryDtoGroupB.class)
    private String answerContent;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime answerDate;
    
    private String createUserId;
    
    private LocalDateTime createDate;

    private String modifyUserId;

    private LocalDateTime modifyDate;


}
