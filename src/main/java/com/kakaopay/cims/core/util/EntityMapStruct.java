package com.kakaopay.cims.core.util;

public interface EntityMapStruct<D, E> {

    E toEntity(D dto);

    D toDto(E entity);
}
