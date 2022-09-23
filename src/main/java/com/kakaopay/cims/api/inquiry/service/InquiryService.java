package com.kakaopay.cims.api.inquiry.service;



import java.time.LocalDateTime;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kakaopay.cims.api.inquiry.dto.InquiryDto;
import com.kakaopay.cims.api.inquiry.entity.InquiryEntity;
import com.kakaopay.cims.api.inquiry.mapper.InquiryMapStruct;
import com.kakaopay.cims.api.inquiry.repository.InquiryRepository;
import com.kakaopay.cims.api.user.dto.UserDto;
import com.kakaopay.cims.api.user.service.UserService;
import com.kakaopay.cims.core.error.ValidationCustomException;
import com.kakaopay.cims.core.security.AuthorizedUser;
import com.kakaopay.cims.core.security.AuthorizedUserSecurityAdapter;
import com.kakaopay.cims.core.util.CommonUtil;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;
    
    @Autowired
    private UserService userService;
    
    private InquiryMapStruct inquiryMapStruct = Mappers.getMapper(InquiryMapStruct.class);

    /***
     * 문의 목록 조회
     * @return
     */
    public Page<InquiryDto> findAllInquiries(Specification<InquiryEntity> specs, Pageable pageable) {
        Page<InquiryEntity> inquiryEntities = inquiryRepository.findAll(Specification.where(specs), pageable);
        Page<InquiryDto> inquiryDtos = inquiryEntities.map(inquiryMapStruct::toDto);
        return inquiryDtos;
    }
        
    /***
     * 문의 단건 조회
     * @return
     */
    public InquiryDto findById(Long inquirySeq) {
    	InquiryEntity checkInquiryEntity = inquiryRepository.findById(inquirySeq).orElseThrow(() -> new ResourceNotFoundException());
        InquiryDto inquiryDto = inquiryMapStruct.toDto(checkInquiryEntity);
        return inquiryDto;
    }
    
    /***
     * 문의 등록
     * @return
     */
    public InquiryDto saveInquiry(InquiryDto inquiryDto) {
    	UserDto userDto = null;
    	try {
    		userDto = userService.findByUserId(inquiryDto.getInquiryUserId());
    	}catch(ResourceNotFoundException e) {
    		userDto = null;	
    	}finally {
    		if(userDto == null || !("001").equals(userDto.getUserType())) {
        		throw new ValidationCustomException("해당 고객ID로 등록된 고객은 없습니다.");
        	}
    	}
    	
    	inquiryDto.setStatus("001");
    	inquiryDto.setInquiryDate(LocalDateTime.now());
    	inquiryDto.setCreateUserId(inquiryDto.getInquiryUserId());
    	inquiryDto.setModifyUserId(inquiryDto.getInquiryUserId());

    	InquiryEntity inquiryEntity = inquiryMapStruct.toEntity(inquiryDto);
        InquiryDto inquiryDtos = inquiryMapStruct.toDto(inquiryRepository.save(inquiryEntity));
        return inquiryDtos;
    }
    
    /***
     * 문의 접수
     * @return
     */
    public InquiryDto acceptInquiry(Long inquirySeq) {
    	AuthorizedUser authorizedUser = ((AuthorizedUserSecurityAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorizedUser();
    	String loginUserId = authorizedUser.getUserId();
    	
    	InquiryDto updateInquiryDto = findById(inquirySeq);
    	String answerUserId = updateInquiryDto.getAnswerUserId();
    	if(answerUserId != null && !answerUserId.isBlank()) {
    		throw new ValidationCustomException("이미 담당자가 지정된 문의건입니다.");
    	}
    	updateInquiryDto.setStatus("002");
    	updateInquiryDto.setModifyDate(LocalDateTime.now());
    	updateInquiryDto.setModifyUserId(loginUserId);
    	updateInquiryDto.setAnswerUserId(loginUserId);
    	InquiryEntity inquiryEntity = inquiryMapStruct.toEntity(updateInquiryDto);
    	inquiryRepository.save(inquiryEntity);
        InquiryDto inquiryDtos = inquiryMapStruct.toDto(inquiryRepository.save(inquiryEntity));
        return inquiryDtos;
    }
    
    /***
     * 문의 수정
     * @return
     */
    public InquiryDto updateInquiry(Long inquirySeq, InquiryDto inquiryDto) {
    	AuthorizedUser authorizedUser = ((AuthorizedUserSecurityAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorizedUser();
    	String loginUserId = authorizedUser.getUserId();
    	String answerUserId = inquiryDto.getAnswerUserId();
    	
    	if(!loginUserId.equals(answerUserId)) {
    		throw new ValidationCustomException("문의를 접수한 상담사가 답변을 등록할 수 있습니다.");
    	}
    	
    	if(("002").equals(inquiryDto.getStatus())) {
    		inquiryDto.setStatus("003");
    		inquiryDto.setAnswerDate(LocalDateTime.now());
    	}else {
    		throw new ValidationCustomException("이미 완료된 문의를 수정할 수 없습니다.");
    	}
    	
    	InquiryDto updateInquiryDto = findById(inquirySeq);
    	updateInquiryDto.setModifyDate(LocalDateTime.now());
    	updateInquiryDto.setModifyUserId(loginUserId);
    	BeanUtils.copyProperties(inquiryDto, updateInquiryDto, CommonUtil.getNullPropertyNames(inquiryDto));
    	InquiryEntity inquiryEntity = inquiryMapStruct.toEntity(inquiryDto);
    	inquiryRepository.save(inquiryEntity);
        InquiryDto inquiryDtos = inquiryMapStruct.toDto(inquiryRepository.save(inquiryEntity));
        return inquiryDtos;
    }
    
    /***
     * 문의 삭제
     * @return
     */
    public void deleteInquiry(Long inquirySeq) {
    	InquiryDto updateInquiryDto = findById(inquirySeq);
    	InquiryEntity inquiryEntity = inquiryMapStruct.toEntity(updateInquiryDto);
    	inquiryRepository.delete(inquiryEntity);
    }

}
