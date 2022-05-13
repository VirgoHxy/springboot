package com.hxy.boot.springboot.controller;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.vo.ApiResponseVo;
import com.hxy.boot.common.vo.user.LoginParamVo;
import com.hxy.boot.springboot.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.io.IOException;
import java.util.Map;

@RequestMapping("user")
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/demo")
    public String demo(
            @RequestParam(name = "param1", defaultValue = "1") Integer p1,
            @RequestParam(name = "param2") @Max(value = 12) Integer p2
    ) {
        // @RequestParam可以读取get中的params,post中的form-data
        // 没传param2则会报错 required默认为true
        // @Max为constraints的验证注解 不能大于12
        // 传了param1: p1 + p2;没有传param1: 1 + p2
        return String.valueOf(p1 + p2);
    }

    @GetMapping("/demo1")
    public String demo1(@RequestParam Map<String, String> params) {
        // 这里Map就算定义为 Map<String, Integer> 得到的值还是字符串
        Integer p1 = Integer.valueOf(params.get("param1"));
        Integer p2 = Integer.valueOf(params.get("param2"));
        return String.valueOf(p1 + p2);
    }

    @PostMapping("/demo2")
    public String demo2(HttpServletRequest request) throws IOException {
        // 获取请求数据流
        ServletInputStream is = request.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = is.read(buf)) != -1) {
            sb.append(new String(buf, 0, len));
        }
        System.out.println(sb.toString());
        return "获取到的文本内容为：" + sb.toString();
    }

    @PostMapping("/login")
    public ApiResponseVo<UserEntity> login(@RequestBody @Validated LoginParamVo loginParam) throws Exception {
        // 获取json 这里password传入Integer类型 会自动转换为String类型
        return new ApiResponseVo<>(userService.login(loginParam));
    }

    @PostMapping("/addUser")
    public ApiResponseVo<Null> addUser(@RequestBody @Validated(UserEntity.AddUser.class) UserEntity userEntity) throws Exception {
        // 获取json 这里password传入Integer类型 会自动转换为String类型
        ApiResponseVo<Null> apiResponseVo = new ApiResponseVo<>(null);
        userService.addUser(userEntity);
        return apiResponseVo;
    }

    @PostMapping("/updateUser")
    public ApiResponseVo<Null> updateUser(@RequestBody @Validated(UserEntity.UpdateUser.class) UserEntity userEntity) throws Exception {
        // 获取json 这里password传入Integer类型 会自动转换为String类型
        ApiResponseVo<Null> apiResponseVo = new ApiResponseVo<>(null);
        userService.updateUser(userEntity);
        return apiResponseVo;
    }
}