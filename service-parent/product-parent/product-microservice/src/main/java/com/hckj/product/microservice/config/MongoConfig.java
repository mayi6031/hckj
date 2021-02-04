package com.hckj.product.microservice.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Mongo配置
 *
 * @author ：yuhui
 * @date ：Created in 2020/9/28 17:05
 */
@Configuration
public class MongoConfig {

    @Autowired
    private MongoProperties mongoProperties;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(primaryFactory(mongoProperties));
    }

    @Bean
    public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
        MongoClientURI uri = new MongoClientURI(mongo.getUri());
        return new SimpleMongoDbFactory(uri);
    }
}
