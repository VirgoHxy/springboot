package com.hxy.boot.springboot.service;

import com.hxy.boot.common.entity.mysql.UserEntity;
import com.hxy.boot.common.entity.mysql4test.RecordEntity;
import com.hxy.boot.common.service.mysql.impl.UserServiceImpl;
import com.hxy.boot.common.service.mysql4test.impl.RecordServiceImpl;
import com.hxy.boot.common.vo.user.LoginParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RecordServiceImpl recordServiceImpl;

    public UserEntity login(@Validated LoginParamVO param) {
        return userServiceImpl.selectByAccountAndPassword(param);
    }

    // 单数据源事务
    @Transactional("mysqlTransactionManager")
    public void add(@Validated(UserEntity.AddUser.class) UserEntity userEntity) {
        // 多次操作同一个mysql数据源 出现错误会回滚
        userServiceImpl.insert(userEntity);
    }

    public void update(@Validated(UserEntity.UpdateUser.class) UserEntity userEntity) {
        userServiceImpl.updateById(userEntity);
    }

    // 多数据源事务
    @Transactional("jtaTransactionManager")
    public void remove(@Validated(UserEntity.RemoveUser.class) UserEntity userEntity) {
        userServiceImpl.deleteById(userEntity.getId());
        // 其他数据源操作
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setContent("user delete");
        recordServiceImpl.insert(recordEntity);
        // int demo = 1/0; // 这个注释取消就会触发事务 上面的动作都会回滚 也就是说删除不会生效 插入不会生效
    }

}
