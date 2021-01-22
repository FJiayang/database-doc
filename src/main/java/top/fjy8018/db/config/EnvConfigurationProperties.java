package top.fjy8018.db.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author F嘉阳
 * @date 2021-01-22 22:06
 */
@Data
@Component
@Validated
@ConfigurationProperties(prefix = "ocm.env.db")
public class EnvConfigurationProperties {

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
}
