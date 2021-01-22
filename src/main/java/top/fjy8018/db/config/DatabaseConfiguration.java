package top.fjy8018.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fjy8018.db.constant.DatabaseCons;

import javax.sql.DataSource;

/**
 * @author F嘉阳
 * @date 2021-01-22 22:01
 */
@Configuration
public class DatabaseConfiguration {

    @Autowired
    private EnvConfigurationProperties properties;

    @Bean(DatabaseCons.SEQUENCE)
    public DataSource basicDataSource(){
        return getDataSource(DatabaseCons.SEQUENCE);
    };

    @Bean(DatabaseCons.CONSOLE)
    public DataSource consoleDataSource(){
        return getDataSource(DatabaseCons.CONSOLE);
    };
    @Bean(DatabaseCons.CTR)
    public DataSource ctrDataSource(){
        return getDataSource(DatabaseCons.CTR);
    };
    @Bean(DatabaseCons.CUS)
    public DataSource cusDataSource(){
        return getDataSource(DatabaseCons.CUS);
    };
    @Bean(DatabaseCons.DAYBAT)
    public DataSource daybatDataSource(){
        return getDataSource(DatabaseCons.DAYBAT);
    };
    @Bean(DatabaseCons.EDOC)
    public DataSource edocDataSource(){
        return getDataSource(DatabaseCons.EDOC);
    };
    @Bean(DatabaseCons.FLOW)
    public DataSource flowDataSource(){
        return getDataSource(DatabaseCons.FLOW);
    };
    @Bean(DatabaseCons.LIMIT)
    public DataSource limitDataSource(){
        return getDataSource(DatabaseCons.LIMIT);
    };
    @Bean(DatabaseCons.LOAN)
    public DataSource loanDataSource(){
        return getDataSource(DatabaseCons.LOAN);
    };
    @Bean(DatabaseCons.NLS)
    public DataSource nlsDataSource(){
        return getDataSource(DatabaseCons.NLS);
    };
    @Bean(DatabaseCons.PBOC)
    public DataSource pbocDataSource(){
        return getDataSource(DatabaseCons.PBOC);
    };
    @Bean(DatabaseCons.REPORT)
    public DataSource reportDataSource(){
        return getDataSource(DatabaseCons.REPORT);
    };
    @Bean(DatabaseCons.RISK)
    public DataSource riskDataSource(){
        return getDataSource(DatabaseCons.RISK);
    };
    @Bean(DatabaseCons.RULE)
    public DataSource ruleDataSource(){
        return getDataSource(DatabaseCons.RULE);
    };

    private DataSource getDataSource(String database){
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(properties.getAddress()+database);
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }
}
