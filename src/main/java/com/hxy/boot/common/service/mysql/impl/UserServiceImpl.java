package com.hxy.boot.common.service.mysql.impl;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.mapper.mysql.UserMapper;
import com.hxy.boot.common.service.mysql.IUserService;
import com.hxy.boot.common.vo.BusinessExceptionVo;
import com.hxy.boot.common.vo.user.LoginParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectById(long id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserEntity selectByAccountAndPassword(LoginParamVo param) {
        UserEntity user = userMapper.selectByAccountAndPassword(param);
        if (user == null) {
            throw new BusinessExceptionVo("账号或密码不正确!");
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
        return user == null ? null : user.getId();
    }

    @Override
    public Long existById(Long id) {
        UserEntity user = userMapper.selectById(id);
        return user == null ? null : user.getId();
    }

    @Override
    public void insert(UserEntity userEntity) {
        String account = userEntity.getAccount();
        Long id = this.existByAccount(account);
        if (id != null) {
            throw new BusinessExceptionVo("account: " + account + " 已存在!");
        }
        userEntity.setCreateTime(new Timestamp(new Date().getTime()));
        userMapper.insert(userEntity);
    }

    @Override
    public void updateById(UserEntity userEntity) {
        Long id = this.existById(userEntity.getId());
        if (id == null) {
            throw new BusinessExceptionVo("用户不存在!");
        }
        userEntity.setUpdateTime(new Timestamp(new Date().getTime()));
        userMapper.updateById(userEntity);
    }

    @Override
    public void deleteById(long id) {
        Long userid = this.existById(id);
        if (userid == null) {
            throw new BusinessExceptionVo("用户已删除!");
        }
        userMapper.deleteById(id);
    }

}