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
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectById(long id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserEntity selectByAccountAndPassword(LoginParamVo param) {
        return userMapper.selectByAccountAndPassword(param);
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
    public void insert(UserEntity user) {
        user.setCreateTime(new Timestamp(new Date().getTime()));
        userMapper.insert(user);
    }

    @Override
    public void updateById(UserEntity user) {
        user.setUpdateTime(new Timestamp(new Date().getTime()));
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(long id) {
        userMapper.deleteById(id);
    }

}