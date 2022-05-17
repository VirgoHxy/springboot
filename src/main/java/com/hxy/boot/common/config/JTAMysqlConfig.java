package com.hxy.boot.common.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import lombok.Data;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.hxy.boot.*.mapper.mysql", sqlSessionFactoryRef = "mysqlSqlSessionFactory", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
@ConfigurationProperties(prefix = "spring.datasource.mysql")
@Data
public class JTAMysqlConfig {
    private String jdbcUrl;
    private String username;
    private String password;

    @Qualifier("mysql")
    @Bean(name = "mysql")
    public DataSource mysqlDataSource() {
        // 不采用多数据事务
        // return DataSourceBuilder.create().build();

        // 采用多数据源事务
        Properties properties = new Properties();
        properties.setProperty("URL", jdbcUrl);
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaProperties(properties);
        ds.setUniqueResourceName("mysqlDataSource");
        // 这里需要注意 不同的数据源类型 要用不同的驱动
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        return ds;
    }


    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mysqlDataSource());
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
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/mapper/mysql/*/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSqlSessionFactory());
    }
}