package com.kakaopay.cims.api.inquiry.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquiryEntity is a Querydsl query type for InquiryEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInquiryEntity extends EntityPathBase<InquiryEntity> {

    private static final long serialVersionUID = -1930969664L;

    public static final QInquiryEntity inquiryEntity = new QInquiryEntity("inquiryEntity");

    public final StringPath answerContent = createString("answerContent");

    public final DateTimePath<java.time.LocalDateTime> answerDate = createDateTime("answerDate", java.time.LocalDateTime.class);

    public final StringPath answerUserId = createString("answerUserId");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath createUserId = createString("createUserId");

    public final StringPath inquiryContent = createString("inquiryContent");

    public final DateTimePath<java.time.LocalDateTime> inquiryDate = createDateTime("inquiryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> inquirySeq = createNumber("inquirySeq", Long.class);

    public final StringPath inquiryUserId = createString("inquiryUserId");

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath modifyUserId = createString("modifyUserId");

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public QInquiryEntity(String variable) {
        super(InquiryEntity.class, forVariable(variable));
    }

    public QInquiryEntity(Path<? extends InquiryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquiryEntity(PathMetadata metadata) {
        super(InquiryEntity.class, metadata);
    }

}

