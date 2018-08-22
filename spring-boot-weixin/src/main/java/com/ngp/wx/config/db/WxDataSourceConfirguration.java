package com.ngp.wx.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class WxDataSourceConfirguration {

    @Primary
    @Bean(name ="wxDataSource")
    @ConfigurationProperties(prefix = "dbwx.datasource.hikari")
    public DataSource wxDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "wxSqlSessionFactory")
    public SqlSessionFactory wxSqlSessionFactory(@Qualifier("wxDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mappers/wx/*.xml"));
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
        bean.setTypeAliasesPackage("com.ngp.db.dao.vo");
        return bean.getObject();
    }

    @Bean(name = "wxDataSourceTransactionManager")
    public DataSourceTransactionManager wxDataSourceTransactionManager(@Qualifier("wxDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name ="wxSqlSessionTemplate")
    public SqlSessionTemplate wxSqlSessionTemplate (@Qualifier("wxSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
