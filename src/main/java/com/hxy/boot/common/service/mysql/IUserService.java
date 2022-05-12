package com.hxy.boot.common.service.mysql;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.vo.user.LoginParamVo;

public interface IUserService {
    UserEntity selectById(long id);

    UserEntity selectByAccountAndPassword(LoginParamVo param);

    UserEntity selectByAccount(String account);

    Long existByAccount(String account);
    Long existById(Long id);

    void insert(UserEntity user);

    void updateById(UserEntity user);

    void deleteById(long id);
}
