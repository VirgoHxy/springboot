package com.hxy.boot.common.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spring.profiles")
@Getter
@Setter
public class ProfileActiveVo {

    private String active;

}
