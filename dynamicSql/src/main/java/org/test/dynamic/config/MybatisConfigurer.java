/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package org.test.dynamic.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;


/**
 * Mybatis & Mapper & PageHelper 配置
 */
@Configuration
@Slf4j
public class MybatisConfigurer {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        try {
            SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
            factory.setDataSource(dataSource);
            factory.setTypeAliasesPackage("org.test.dynamic");
            //添加XML目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return factory.getObject();
        } catch (Exception e) {
            log.error("mybatis init error",e);
        }
        return null;
    }


}

