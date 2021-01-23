package top.fjy8018.db.handler.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.fjy8018.db.config.EnvConfigurationProperties;
import top.fjy8018.db.constant.DatabaseEnums;
import top.fjy8018.db.handler.DatabaseHandler;

import javax.sql.DataSource;

/**
 * @author F嘉阳
 * @date 2021-01-23 9:08
 */
@Component
public class EdocDatabaseHandler implements DatabaseHandler {

    @Autowired
    private EnvConfigurationProperties properties;

    /**
     * 获取数据库名称
     */
    @Override
    public String getDatabaseName() {
        return DatabaseEnums.EDOC.getName();
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    @Override
    public DataSource getDataSource() {
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(properties.getAddress()+DatabaseEnums.EDOC.getName());
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }
}
