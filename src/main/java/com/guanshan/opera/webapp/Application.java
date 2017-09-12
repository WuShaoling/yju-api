package com.guanshan.opera.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.jms.ConnectionFactory;

/**
 * @function Spring Boot 应用入口函数
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.guanshan.opera.webapp.rds","com.guanshan.opera", "com.guanshan.opera.webapp.security", "com.guanshan.opera.webapp.middleware"})
@MapperScan(basePackages = "com.guanshan.opera.webapp.dao")
@EnableConfigurationProperties
@EnableAsync
@EnableJms
@ServletComponentScan(basePackages = {"com.guanshan.opera.webapp.security"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application implements CommandLineRunner{

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    /**
     * @function 主入口函数
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
//        jmsTemplate.convertAndSend("mailbox", "info@example.com");
//        MessageSender messageSender = new MessageSender();
//        messageSender.userLogin(1L,"testname");
    }


    @Override
    public void run(String... args) throws Exception {
//        MessageSender messageSender = new MessageSender();
//        messageSender.userLogin(1L,"testname");
    }
}
