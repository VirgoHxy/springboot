package com.hxy.boot.common.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.vo.user.LoginParamVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity selectByAccountAndPassword(LoginParamVo param);

    UserEntity selectByAccount(String account);
}
