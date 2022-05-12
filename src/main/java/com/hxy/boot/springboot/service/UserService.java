package com.hxy.boot.springboot.service;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.vo.user.LoginParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service("userService")
@Validated
public class UserService {

    @Autowired
    private com.hxy.boot.common.service.mysql.impl.UserService userService;

    public UserEntity login(@Validated LoginParamVo param) throws Exception {
        UserEntity user = userService.selectByAccountAndPassword(param);
        if (user == null) {
            throw new Exception("login failed!");
        }
        return user;
    }

    // @Transactional("mysqlTransactionManager")
    public void addUser(@Validated(UserEntity.AddUser.class) UserEntity userEntity) throws Exception {
        Long id = userService.existByAccount(userEntity.getAccount());
        if (id != null) {
            StringBuilder messsage = new StringBuilder(userEntity.getAccount());
            messsage.append(" already exists!");
            throw new Exception(messsage.toString());
        }
        userService.insert(userEntity);
    }

    // @Transactional("mysqlTransactionManager")
    public void updateUser(@Validated(UserEntity.UpdateUser.class) UserEntity userEntity) throws Exception {
        Long id = userService.existById(userEntity.getId());
        if (id == null) {
            StringBuilder messsage = new StringBuilder("userId is ");
            messsage.append(userEntity.getId()).append(", user is not exists!");
            throw new Exception(messsage.toString());
        }
        userService.updateById(userEntity);
    }

}
