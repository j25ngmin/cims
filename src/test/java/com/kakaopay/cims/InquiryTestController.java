package com.kakaopay.cims;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.kakaopay.cims.api.inquiry.service.InquiryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class InquiryTestController {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@MockBean
	InquiryService inquiryService;
	
	@Test
	public void get() throws Exception {
		log.debug(" ---- junit test ---- ");
		String result = testRestTemplate.getForObject("/api/v1/inquiries", String.class);
		assertThat(result).isNullOrEmpty();
	}

}
