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
@MapperScan(basePackages = "com.hxy.boot.*.mapper.postgresql", sqlSessionFactoryRef = "postgresqlSqlSessionFactory", sqlSessionTemplateRef = "postgresqlSqlSessionTemplate")
public class PostgresQLConfig {
    @Autowired
    @Qualifier("postgresql")
    private DataSource dataSourcePostgresQL;


    @Bean(name = "postgresqlSqlSessionFactory")
    public SqlSessionFactory postgresqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourcePostgresQL);
        // 打印sql语句
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        // 配置打印sql语句
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);
        // 配置mapper路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/mapper/postgresql/*/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "postgresqlTransactionManager")
    public DataSourceTransactionManager postgresqlTransactionManager() {
        return new DataSourceTransactionManager(dataSourcePostgresQL);
    }

    @Bean(name = "postgresqlSqlSessionTemplate")
    public SqlSessionTemplate postgresqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(postgresqlSqlSessionFactory());
    }
}
