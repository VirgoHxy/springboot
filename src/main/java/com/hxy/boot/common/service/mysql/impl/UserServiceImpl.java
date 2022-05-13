package com.hxy.boot.common.service.mysql.impl;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.mapper.mysql.UserMapper;
import com.hxy.boot.common.service.mysql.IUserService;
import com.hxy.boot.common.vo.user.LoginParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service("commonUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectById(long id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserEntity selectByAccountAndPassword(LoginParamVo param) throws Exception {
        UserEntity user = userMapper.selectByAccountAndPassword(param);
        if (user == null) {
            throw new Exception("login failed!");
        }
        return user;
    }

    @Override
    public UserEntity selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public Long existByAccount(String account) {
        UserEntity user = userMapper.selectByAccount(account);
        return user.getId();
    }

    @Override
    public Long existById(Long id) {
        UserEntity user = userMapper.selectById(id);
        return user == null ? null : user.getId();
    }

    @Override
    public void insert(UserEntity user) throws Exception {
        String account = user.getAccount();
        Long id = this.existByAccount(account);
        if (id != null) {
            StringBuilder messsage = new StringBuilder(account);
            messsage.append(" already exists!");
            throw new Exception(messsage.toString());
        }
        user.setCreateTime(new Timestamp(new Date().getTime()));
        userMapper.insert(user);
    }

    @Override
    public void updateById(UserEntity user) throws Exception {
        Long id = this.existById(user.getId());
        if (id == null) {
            StringBuilder messsage = new StringBuilder("userId is ");
            messsage.append(id).append(", user is not exists!");
            throw new Exception(messsage.toString());
        }
        user.setUpdateTime(new Timestamp(new Date().getTime()));
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(long id) {
        userMapper.deleteById(id);
    }

}