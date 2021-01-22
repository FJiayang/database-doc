package top.fjy8018.db;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.fjy8018.db.service.GenerateService;

import javax.sql.DataSource;
import java.util.ArrayList;

@SpringBootApplication
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
