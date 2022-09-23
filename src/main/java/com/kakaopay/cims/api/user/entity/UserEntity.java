package com.kakaopay.cims.api.user.entity;

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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="USER")
public class UserEntity {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="USER_SEQ", unique = true, nullable = false)
	private Long userSeq;
   
    @Column (name="USER_ID")
    private String userId;
    
    @Column (name="USER_NAME")
    private String userName;

    @Column (name="PASSWORD")
    private String password;
    
    @Column (name="USER_TYPE")
    private String userType;
    
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
