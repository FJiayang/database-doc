# MySQL数据字典快速生成工具

## 来源
基于[Screw](https://gitee.com/leshalv/screw)二次封装



## 功能

1. 导出数据库数据表结构和定义
2. 支持通配符
3. 支持精确匹配
4. 支持跨库



## 使用方法

### 使用编译好的jar包运行

1. 在jar包外新增配置文件`application.yml`

   内容样例：

   ```yaml
   doc:
     env:
       # 输出位置
       file-dir: E:\tmp\database-doc
       # 支持WORD、HTML、MD
       file-type: WORD
       db:
         blade:
           address: jdbc:mysql://192.168.133.128:3306/
           password: root
           username: root
         nacos:
           address: jdbc:mysql://192.168.133.128:3306/
           password: root
           username: root
   ```

   

2. 在jar包外新增表范围文件`table.dat`，配置需要输出的表

   内容样例：

   ```
   blade|*
   nacos|config_info
   nacos|config_info_aggr
   nacos|config_info_beta
   nacos|config_info_tag
   nacos|config_tags_relation
   nacos|group_capacity
   nacos|his_config_info
   nacos|permissions
   nacos|roles
   nacos|tenant_capacity
   nacos|tenant_info
   nacos|users
   ```

3. 运行jar包即可

   ```bash
   $ java jar database-doc-0.0.1-SNAPSHOT.jar
   ```

   

### 使用源码运行

1. 修改工程目录下配置文件`src/main/resources/application.yml`

   内容样例：

   ```yaml
   doc:
     env:
       # 输出位置
       file-dir: E:\tmp\database-doc
       # 支持WORD、HTML、MD
       file-type: WORD
       db:
         blade:
           address: jdbc:mysql://192.168.133.128:3306/
           password: root
           username: root
         nacos:
           address: jdbc:mysql://192.168.133.128:3306/
           password: root
           username: root
   ```

   

2. 修改工程目录下表范围文件`src/main/resources/table.dat`，配置需要输出的表

   内容样例：

   ```
   blade|*
   nacos|config_info
   nacos|config_info_aggr
   nacos|config_info_beta
   nacos|config_info_tag
   nacos|config_tags_relation
   nacos|group_capacity
   nacos|his_config_info
   nacos|permissions
   nacos|roles
   nacos|tenant_capacity
   nacos|tenant_info
   nacos|users
   ```

3. 进入启动类`src/main/java/top/fjy8018/db/DbApplication.java`，启动SpringBoot项目即可

