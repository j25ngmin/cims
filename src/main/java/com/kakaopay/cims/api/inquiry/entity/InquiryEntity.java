package com.kakaopay.cims.api.inquiry.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class) 
@Table(name="INQUIRY")
public class InquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="INQUIRY_SEQ", unique = true, nullable = false)
    private Long inquirySeq;

    @Column (name="INQUIRY_USER_ID")
    private String inquiryUserId;

    @Column (name="TITLE")
    private String title;

    @Column (name="STATUS", nullable = false)
    private String status;

    @Column (name="INQUIRY_CONTENT")
    private String inquiryContent;

    @Column (name="INQUIRY_DATE", nullable = false)
    private LocalDateTime inquiryDate;

    @Column (name="ANSWER_USER_ID")
    private String answerUserId;
    
    @Column (name="ANSWER_CONTENT")
    private String answerContent;
    
    @Column (name="ANSWER_DATE")
    private LocalDateTime answerDate;
    
    @Column (name="CREATE_USER_ID")
    private String createUserId;
    
    @Column (name="CREATE_DATE", nullable = false)
    @CreatedDate
    private LocalDateTime createDate;
    
    @Column (name="MODIFY_USER_ID")
    private String modifyUserId;

    @Column (name="MODIFY_DATE", nullable = false)
    @LastModifiedDate
    private LocalDateTime modifyDate;


}
