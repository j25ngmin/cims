package com.kakaopay.cims.api.inquiry.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SearchVO {

    @NotBlank
    private String[] keywords = {};

    private String userNo;

    private String gender;

    private Integer age;

    private List<Integer> notInclude;

}
