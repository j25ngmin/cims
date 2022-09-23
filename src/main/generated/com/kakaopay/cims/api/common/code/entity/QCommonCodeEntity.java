package com.kakaopay.cims.api.common.code.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonCodeEntity is a Querydsl query type for CommonCodeEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommonCodeEntity extends EntityPathBase<CommonCodeEntity> {

    private static final long serialVersionUID = -5420082L;

    public static final QCommonCodeEntity commonCodeEntity = new QCommonCodeEntity("commonCodeEntity");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> commonCodeSeq = createNumber("commonCodeSeq", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath createUserId = createString("createUserId");

    public final StringPath groupCode = createString("groupCode");

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath modifyUserId = createString("modifyUserId");

    public QCommonCodeEntity(String variable) {
        super(CommonCodeEntity.class, forVariable(variable));
    }

    public QCommonCodeEntity(Path<? extends CommonCodeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonCodeEntity(PathMetadata metadata) {
        super(CommonCodeEntity.class, metadata);
    }

}

