package com.kakaopay.cims.api.common.code.entity;

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
@Table(name="COMMON_CODE")
public class CommonCodeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="COMMON_CODE_SEQ", unique = true, nullable = false)
    private Integer commonCodeSeq;
    
    @Column (name="GROUP_CODE")
    private String groupCode;

    @Column (name="CODE")
    private String code;
    
    @Column (name="CREATE_USER_ID")
    private String createUserId;
    
    @Column (name="CREATE_DATE")
    @CreatedDate
    private LocalDateTime createDate;
    
    @Column (name="MODIFY_USER_ID")
    private String modifyUserId;

    @Column (name="MODIFY_DATE")
    @LastModifiedDate
    private LocalDateTime modifyDate;

}
