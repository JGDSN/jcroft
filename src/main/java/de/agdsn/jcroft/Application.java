package de.agdsn.jcroft;

import com.hazelcast.config.Config;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableCaching
@ComponentScan
@EnableAutoConfiguration
@EntityScan("de.agdsn.jcroft")
@EnableJpaRepositories("de.agdsn.jcroft")
public class Application {

    public static final Logger MAIN_LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application");
        try {
            JCroftConfiguration.readConfig(new File("settings/jcroft.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //Start the spring web service
        SpringApplication.run(Application.class, args);
    }

    @Bean
    HazelcastInstance hazelcastInstance() {
        //TODO: fetch values from config instead
        Config config = new Config();
        config.getGroupConfig().setName("dev").setPassword("dev-pass");
        TcpIpConfig ipc = config.getNetworkConfig().getJoin().getTcpIpConfig();
        ipc.setEnabled(true);
        ipc.addMember("127.0.0.1");
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.setInstanceName("cache-1");

        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}