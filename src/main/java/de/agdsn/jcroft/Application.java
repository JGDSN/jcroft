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
        //read configuration
        String groupName = JCroftConfiguration.getValue("hz_group_name");
        String groupPassword = JCroftConfiguration.getValue("hz_group_password");
        String instanceName = JCroftConfiguration.getValue("hz_instance_name");
        String memberStr = JCroftConfiguration.getValue("hz_members");

        String members[] = memberStr.split(",");

        Config config = new Config();
        config.getGroupConfig().setName(groupName).setPassword(groupPassword);
        TcpIpConfig ipc = config.getNetworkConfig().getJoin().getTcpIpConfig();
        ipc.setEnabled(true);

        for (String member : members) {
            ipc.addMember(member);
        }

        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.setInstanceName(instanceName);

        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}