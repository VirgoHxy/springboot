package com.hxy.boot.springboot.controller;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.utils.annotation.MyApiResponse;
import com.hxy.boot.common.utils.annotation.MyRestController;
import com.hxy.boot.common.utils.annotation.MyTokenRequired;
import com.hxy.boot.common.utils.util.JWTUtil;
import com.hxy.boot.common.vo.user.LoginParamVO;
import com.hxy.boot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@MyRestController(path = "user", required = false)
@MyApiResponse
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Validated LoginParamVO loginParam) {
        Map<String, Object> map = new HashMap<>();
        UserEntity userEntity = userService.login(loginParam);
        map.put("userInfo", userEntity);
        map.put("token", JWTUtil.createToken(userEntity));
        return map;
    }

    @MyTokenRequired
    @PostMapping("/add")
    public void add(@RequestBody @Validated(UserEntity.AddUser.class) UserEntity userEntity) {
        userService.add(userEntity);
    }

    @MyTokenRequired
    @PostMapping("/update")
    public void update(@RequestBody @Validated(UserEntity.UpdateUser.class) UserEntity userEntity) {
        userService.update(userEntity);
    }

    @MyTokenRequired
    @PostMapping("/remove")
    public void remove(@RequestBody @Validated(UserEntity.RemoveUser.class) UserEntity userEntity) {
        userService.remove(userEntity);
    }
}