package com.guanshan.nlp.webapp;

/**
 * Created by Wuneng.Zhang on 2017/5/31.
 */
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;

import javax.sql.DataSource;

@Configuration
@ImportResource(locations={"classpath:spring/*.xml"})
public class appConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        System.out.println("FilterRegistrationBean...");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean(name = "nlpDataSource")
    @Qualifier("nlpDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.nlp")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "nlpJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("nlpDataSource")
                                                     DataSource dsPostgres) {
        return new JdbcTemplate(dsPostgres);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("nlpDataSource") DataSource dsPostgres) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setDataSource(dsPostgres);
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        return sessionFactory.getObject();
    }
}
