package com.kakaopay.cims.api.common.code.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaopay.cims.api.common.code.mapper.CommonCodeMapStruct;
import com.kakaopay.cims.api.common.code.repository.CommonCodeRepository;

@Service
public class CommonCodeService {

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    private CommonCodeMapStruct commonCodeMapStruct = Mappers.getMapper(CommonCodeMapStruct.class);


}
