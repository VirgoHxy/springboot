package com.hxy.boot.springboot.service;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.service.mysql.impl.UserServiceImpl;
import com.hxy.boot.common.vo.user.LoginParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service("userService")
@Validated
public class UserService {

    @Autowired
    private UserServiceImpl userService;

    public UserEntity login(@Validated LoginParamVo param) throws Exception {
        return userService.selectByAccountAndPassword(param);
    }

    // @Transactional("mysqlTransactionManager")
    public void addUser(@Validated(UserEntity.AddUser.class) UserEntity userEntity) throws Exception {
        userService.insert(userEntity);
    }

    // @Transactional("mysqlTransactionManager")
    public void updateUser(@Validated(UserEntity.UpdateUser.class) UserEntity userEntity) throws Exception {
        userService.updateById(userEntity);
    }

}
