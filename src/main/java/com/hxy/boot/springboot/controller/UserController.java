package com.hxy.boot.springboot.controller;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.vo.ApiResponseVo;
import com.hxy.boot.common.vo.user.LoginParamVo;
import com.hxy.boot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponseVo<UserEntity> login(@RequestBody @Validated LoginParamVo loginParam) {
        // 这里password传入Integer类型 会自动转换为String类型
        return new ApiResponseVo<>(userService.login(loginParam));
    }

    @PostMapping("/add")
    public ApiResponseVo<String> add(@RequestBody @Validated(UserEntity.AddUser.class) UserEntity userEntity) {
        ApiResponseVo<String> apiResponseVo = new ApiResponseVo<>("");
        userService.add(userEntity);
        return apiResponseVo;
    }

    @PostMapping("/update")
    public ApiResponseVo<String> update(@RequestBody @Validated(UserEntity.UpdateUser.class) UserEntity userEntity) {
        ApiResponseVo<String> apiResponseVo = new ApiResponseVo<>("");
        userService.update(userEntity);
        return apiResponseVo;
    }

    @PostMapping("/remove")
    public ApiResponseVo<String> remove(@RequestBody @Validated(UserEntity.RemoveUser.class) UserEntity userEntity) {
        ApiResponseVo<String> apiResponseVo = new ApiResponseVo<>("");
        userService.remove(userEntity);
        return apiResponseVo;
    }
}