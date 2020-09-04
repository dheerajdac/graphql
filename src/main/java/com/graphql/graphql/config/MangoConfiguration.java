package com.graphql.graphql.config;

import com.mongodb.MongoCredential;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoClientSettingsFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MangoConfiguration {

    private static final String dataBaseName = "Notes";

    @Value("${database.name}")
    private String name;

    @Value("${database.username}")
    private String userName;

    @Value("${database.password}")
    private String password;

    @Value("${database.host}")
    private String host;

    @Value("${database.port}")
    private Integer port;

    @Bean
    public MongoClientSettingsFactoryBean mongoSetting() {
        final MongoClientSettingsFactoryBean mongoClientSettingsFactoryBean = new MongoClientSettingsFactoryBean();
        mongoClientSettingsFactoryBean.setPoolMinSize(10);
        mongoClientSettingsFactoryBean.setPoolMinSize(20);
        return mongoClientSettingsFactoryBean;
    }

    @Bean
    public MongoClientFactoryBean mongoClient() throws Exception {
        final MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost(host);
        mongo.setPort(port);
        MongoCredential createCredential = MongoCredential.createCredential(userName, name, password.toCharArray());
        mongo.setCredential(new MongoCredential[]{createCredential});
        return mongo;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient().getObject(), dataBaseName);
    }
}