package demo.service.mongodb.conf;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by babylon on 2016/7/13.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class MongoConf {

    @Value("${spring.data.mongodb.host}")
    private String uri;

    @Value("${spring.data.mongodb.db}")
    private String db;


    public @Bean Mongo mongo() throws Exception {
//        String sURI = String.format("mongodb://%s:%s@%s:%d/%s", "user", "password", "localhost", 27017, "mydb");
        MongoClientURI uri = new MongoClientURI(this.uri);
        return new MongoClient(uri);
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), this.db);
    }

}
