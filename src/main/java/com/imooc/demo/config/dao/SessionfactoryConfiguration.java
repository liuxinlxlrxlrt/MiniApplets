package com.imooc.demo.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionfactoryConfiguration {
    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;

    @Value("${mapper_path}")
    private String mapperPath;

    @Value("${entity_package}")
    private String entityPackage;


    @Autowired
    @Qualifier("dataSource")//按照名字加载dataSource限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置配置文件的路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        //PathMatchingResourcePatternResolver是一个Ant模式通配符的Resource查找器，
        // 可以用来查找类路径下或者文件系统中的资源(spring扫描classpath下面的xx文件)
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //CLASSPATH_ALL_URL_PREFIX="classpath*:"意思是
        // 告诉springboot容器要去classpath*:和resources路径下去找寻mapper文件
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;


        // 设置 mapper 对应的 XML 文件的路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置 mapper 接口所在的包
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }

}
