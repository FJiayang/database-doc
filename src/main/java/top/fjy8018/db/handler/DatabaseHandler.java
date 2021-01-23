package top.fjy8018.db.handler;

import javax.sql.DataSource;

/**
 * 数据库连接处理器
 * @author F嘉阳
 * @date 2021-01-22 22:13
 */
public interface DatabaseHandler {

    /**
     * 获取数据库名称
     */
    String getDatabaseName();

    /**
     * 获取数据库连接
     * @return
     */
    DataSource getDataSource();
}
