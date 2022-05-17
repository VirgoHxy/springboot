package com.hxy.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

// 首先要在@SpringBootApplication排除该类,它会读取datasource属性并自动配置单数据源
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            // log.info("Let's inspect the beans provided by Spring Boot:");
            // 开始检查spring boot 提供的 beans
            // String[] beanNames = ctx.getBeanDefinitionNames();
            // Arrays.sort(beanNames);
            // for (String beanName : beanNames) {
            //     System.out.println(beanName);
            // }
        };
    }
}
