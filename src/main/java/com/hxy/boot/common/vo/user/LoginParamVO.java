package com.hxy.boot.common.vo.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginParamVO {

    @NotBlank
    private String account;

    @NotBlank
    private String password;

}
