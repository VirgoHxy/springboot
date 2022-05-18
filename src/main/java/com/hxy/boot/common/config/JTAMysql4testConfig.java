package com.hxy.boot.common.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import lombok.Data;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.hxy.boot.*.mapper.mysql4test", sqlSessionFactoryRef = "mysql4testSqlSessionFactory", sqlSessionTemplateRef = "mysql4testSqlSessionTemplate")
@ConfigurationProperties(prefix = "spring.datasource.mysql4test")
@Data
public class JTAMysql4testConfig {
    private String jdbcUrl;
    private String username;
    private String password;

    @Bean(name = "mysql4test")
    public DataSource mysql4testDataSource() {
        // 不采用多数据事务
        // return DataSourceBuilder.create().build();

        // 采用多数据源事务
        Properties properties = new Properties();
        properties.setProperty("URL", jdbcUrl);
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaProperties(properties);
        ds.setUniqueResourceName("mysql4testDataSource");
        // 这里需要注意 不同的数据源类型 要用不同的驱动
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        return ds;
    }


    @Bean(name = "mysql4testSqlSessionFactory")
    public SqlSessionFactory mysql4testSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mysql4testDataSource());
        // 添加别名包路径 在yml/properties设置不能生效
        factoryBean.setTypeAliasesPackage("com.hxy.boot.common.entity.mysql4test");
        // 添加设置匹配路径规则
        factoryBean.setVfs(SpringBootVFS.class);
        // 配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        // 下划线转驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        // 关闭缓存
        configuration.setCacheEnabled(false);
        // 配置打印sql语句
        configuration.setLogImpl(StdOutImpl.class);
        // 设定配置
        factoryBean.setConfiguration(configuration);
        // 配置mapper路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/mapper/mysql4test/*/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "mysql4testTransactionManager")
    public DataSourceTransactionManager mysql4testTransactionManager() {
        return new DataSourceTransactionManager(mysql4testDataSource());
    }

    @Bean(name = "mysql4testSqlSessionTemplate")
    public SqlSessionTemplate mysql4testSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysql4testSqlSessionFactory());
    }
}