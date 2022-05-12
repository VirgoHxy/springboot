package com.hxy.boot.common.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.hxy.boot.*.mapper.mysql", sqlSessionFactoryRef = "mysqlSqlSessionFactory", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MysqlConfig {
    @Autowired
    @Qualifier("mysql")
    private DataSource dataSourceMysql;

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceMysql);
        // 打印sql语句
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        // 配置打印sql语句
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);
        // 配置mapper路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/mapper/mysql/*/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(dataSourceMysql);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSqlSessionFactory());
    }
}
