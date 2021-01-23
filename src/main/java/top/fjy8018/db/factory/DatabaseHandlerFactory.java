package top.fjy8018.db.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import top.fjy8018.db.handler.DatabaseHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接池策略简单工厂
 * @author F嘉阳
 * @date 2021-01-23 9:13
 */
@Component
public class DatabaseHandlerFactory implements InitializingBean, ApplicationContextAware {

    private static final Map<String, DatabaseHandler> DATABASE_HANDLER_MAP = new ConcurrentHashMap<>(13);

    private ApplicationContext appContext;

    /**
     * Spring 容器中所有的 DatabaseHandler 注册到DATABASE_HANDLER_MAP
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        appContext.getBeansOfType(DatabaseHandler.class)
                .values()
                .forEach(handler -> DATABASE_HANDLER_MAP.put(handler.getDatabaseName(), handler));
    }

    /**
     * 通过数据库名称获取连接池
     * @param databaseName {@link top.fjy8018.db.constant.DatabaseCons}
     * @return
     */
    public DatabaseHandler getDatabaseHandler(String databaseName){
        return DATABASE_HANDLER_MAP.get(databaseName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
