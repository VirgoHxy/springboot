package com.hxy.boot.common.utils.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
public class JTAConfig {
    // 多数据源事务类型
    @Bean(name = "jtaTransactionManager")
    public JtaTransactionManager jtaTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
