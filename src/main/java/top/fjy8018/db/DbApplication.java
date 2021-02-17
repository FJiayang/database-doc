package top.fjy8018.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.fjy8018.db.config.EnvConfigurationProperties;
import top.fjy8018.db.service.GenerateService;

@SpringBootApplication
@EnableConfigurationProperties({EnvConfigurationProperties.class})
public class DbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }

    @Autowired
    private GenerateService generateService;

    @Override
    public void run(String... args) throws Exception {
        generateService.generate();
    }
}
