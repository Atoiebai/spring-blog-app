package net.springboot.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


public class SpringConfig implements WebMvcConfigurer {

    @Value("${spring.datasource.username}")
    String user; //username for database connection

    @Value("${spring.datasource.password}")
    String password; //password for database connection

    @Value("${spring.datasource.url}")
    String urlDb; //url for database connection

    @Value("${spring.datasource.driver-class-name")
    String driverClassName;


    @Bean
    protected DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(urlDb);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    protected JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
