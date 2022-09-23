package com.kakaopay.cims.api.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kakaopay.cims.api.inquiry.dto.InquiryDto;
import com.kakaopay.cims.api.inquiry.dto.InquiryDtoGroupA;
import com.kakaopay.cims.api.inquiry.dto.InquiryDtoGroupB;
import com.kakaopay.cims.api.inquiry.entity.InquiryEntity;
import com.kakaopay.cims.api.inquiry.service.InquiryService;
import com.sipios.springsearch.anotation.SearchSpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${api.prefix.uri}/${api.version}")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;
    
    @Value("${api.version}")
    private String version;

    
    /***
     * 문의 목록 조회
     * @param keywords
     * @param userNo
     * @return
     */
	@GetMapping("/inquiries")
    public ResponseEntity<Object> getInquiries(@SearchSpec Specification<InquiryEntity> specs, @PageableDefault(size=2) Pageable pageable){

		log.info("specs : " + specs);
		
        Page<InquiryDto> InquiryDtos = inquiryService.findAllInquiries(specs, pageable);

        if(InquiryDtos == null || InquiryDtos.isEmpty()) {
            return new ResponseEntity<Object>(InquiryDtos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Object>(InquiryDtos, HttpStatus.OK);
    }
    
    /***
     * 문의 단건 조회
     * @param keywords
     * @param userNo
     * @return
     */
    @GetMapping("/inquiries/{inquirySeq}")
    //@RequestMapping(value="/inquiries/{inquirySeq}", method = RequestMethod.GET)
    public ResponseEntity<InquiryDto> getInquiryById(@PathVariable("inquirySeq") Long inquirySeq) {
        InquiryDto inquiryDto = inquiryService.findById(inquirySeq);
        return new ResponseEntity<InquiryDto>(inquiryDto, HttpStatus.OK);
    }
    
    /***
     * 문의 등록
     * @param keywords
     * @param userNo
     * @return
     */
	@PostMapping("/inquiries")
    public ResponseEntity<Void> saveInquiry(@RequestBody @Validated(InquiryDtoGroupA.class) InquiryDto inquiryDto, UriComponentsBuilder ucBuilder) {
    	InquiryDto saveInquiryDto = inquiryService.saveInquiry(inquiryDto);
    	HttpHeaders headers = new HttpHeaders();
    	Long inquirySeq = saveInquiryDto.getInquirySeq();
    	
        headers.setLocation(ucBuilder.path(version + "/inquiries/{inquirySeq}").buildAndExpand(inquirySeq).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	/***
     * 문의 접수
     * @param keywords
     * @param userNo
     * @return
     */
    @PutMapping("/accept-inquiries/{inquirySeq}")
    public ResponseEntity<InquiryDto> acceptInquiry(@PathVariable Long inquirySeq) {
    	InquiryDto updateInquiryDto = inquiryService.acceptInquiry(inquirySeq);
        return new ResponseEntity<InquiryDto>(updateInquiryDto, HttpStatus.OK);
    }
    
    /***
     * 문의 수정
     * @param keywords
     * @param userNo
     * @return
     */
    @PutMapping("/inquiries/{inquirySeq}")
    public ResponseEntity<InquiryDto> updateInquiry(@PathVariable Long inquirySeq, @RequestBody @Validated(InquiryDtoGroupB.class)InquiryDto inquiryDto) {
    	InquiryDto updateInquiryDto = inquiryService.updateInquiry(inquirySeq, inquiryDto);
        return new ResponseEntity<InquiryDto>(updateInquiryDto, HttpStatus.OK);
    }
    
    /***
     * 문의 삭제
     * @param keywords
     * @param userNo
     * @return
     */
    @DeleteMapping("/inquiries/{inquirySeq}")
    public ResponseEntity<Void> deleteInquiries(@PathVariable Long inquirySeq) {
    	inquiryService.deleteInquiry(inquirySeq);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
