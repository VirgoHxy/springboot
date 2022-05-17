package com.hxy.boot.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RequestMapping("test")
@RestController
@Validated
@Slf4j
public class TestController {

    @GetMapping("/demo")
    public String demo(
            @RequestParam(name = "param1", defaultValue = "1") Integer param1,
            @RequestParam(name = "param2") @Max(value = 12) Integer param2
    ) {
        // @RequestParam可以读取get中的params,post中的form-data
        // 没传param2则会报错 required默认为true
        // @Max为constraints的验证注解 不能大于12
        // 传了param1: p1 + p2;没有传param1: 1 + p2
        return String.valueOf(param1 + param2);
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
        log.info(sb.toString());
        if (Objects.equals(sb.toString(), "")) {
            throw new NullPointerException();
        }
        return "获取到的文本内容为：" + sb.toString();
    }
}