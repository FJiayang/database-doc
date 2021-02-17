package top.fjy8018.db.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author F嘉阳
 * @date 2021-01-22 22:06
 */
@Data
@Validated
@ConfigurationProperties(prefix = "doc.env")
public class EnvConfigurationProperties {

    /**
     * 多数据源集合
     */
    private Map<String, DatabaseProperties> db;

    /**
     * 产品码映射文件路径
     */
    @NotBlank(message = "文档输出目录不能为空")
    private String fileDir;

    /**
     * 产品码映射文件路径
     */
    @NotBlank(message = "文档输出类型不能为空")
    private String fileType;

    @Data
    @Validated
    public static class DatabaseProperties {
        /**
         * 代码目录
         */
        @NotBlank(message = "数据库用户名不能为空")
        private String username;

        /**
         * 脚本目录
         */
        @NotBlank(message = "数据库密码不能为空")
        private String password;

        /**
         * 产品码映射文件路径
         */
        @NotBlank(message = "数据库地址不能为空")
        private String address;
    }
}
