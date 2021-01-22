package top.fjy8018.db.service;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import top.fjy8018.db.config.EnvConfigurationProperties;
import top.fjy8018.db.constant.DatabaseCons;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author F嘉阳
 * @date 2021-01-22 22:00
 */
@Slf4j
@Service
public class GenerateService {

    /**
     * 用HashMap存储便于检索
     */
    private static Multimap<String,String> tableMap = HashMultimap.create();
    
    @Autowired
    private ApplicationContext context;

    @Autowired
    private EnvConfigurationProperties properties;

    private void loadDatafile(){
        ClassPathResource classPathResource = new ClassPathResource("table.dat");
        try (InputStream inputStream = classPathResource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 按行读取
                String[] split = line.split("\\|");
                if (split.length < 2) {
                    throw new RuntimeException("数据表映射有误，["+line+"]长度错误:" + line);
                }
                // 存储映射
                tableMap.put(split[0],split[1]);
            }
        } catch (IOException e) {
            log.error("装载数据表文件异常，",e);
        }
        log.info("装载数据表文件成功");
    }

    public void generate(){
        loadDatafile();
        // 遍历所有数据库
        Field[] fields = DatabaseCons.class.getFields();
        for (Field d : fields) {
            String databaseName = d.getName();
            // 数据库连接
            DataSource dataSource = context.getBean(databaseName, DataSource.class);
            // 表集合
            Collection<String> tableList = tableMap.get(databaseName);
            screw(dataSource,tableList);
        }
    }

    private void screw(DataSource dataSource, Collection<String> tableList) {
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(properties.getFileDir())
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.WORD)
                //自定义文件名称
                .fileName("自定义文件名称").build();

        //指定表
        ArrayList<String> tableName = new ArrayList<>(tableList);
        ProcessConfig processConfig = ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(tableName)
                .build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("数据库设计文档生成")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig)
                .build();
        //执行生成
        new DocumentationExecute(config).execute();
    }
}
