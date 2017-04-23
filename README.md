基于quartz-2.2.2, spring-boot-1.0.2,  spring-4.0.3, mysql5.6搭建的quartz集群项目示例

#### 项目使用

- 创建数据库
```
CREATE DATABASE quartz_cluster_demo DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

- 初始化数据库

下载sql脚本文件，或拷贝项目中的tables_mysql_innodb.sql文件
```
wget https://github.com/quartz-scheduler/quartz/blob/31291cef9038c856ea718ec27eab2f2f4a431a1b/quartz-core/src/main/resources/org/quartz/impl/jdbcjobstore/tables_mysql_innodb.sql
```

导入脚本文件
```
mysql -u root -p123456 -Dquartz_cluster_demo < tables_mysql_innodb.sql
```

- 修改数据库连接配置文件db.properties, 默认配置信息
```
jdbcUrl=jdbc:mysql://127.0.0.1:3306/quartz_cluster_demo
username=root
password=123456
```

- 启动应用
主类 demo.quartz.cluster.App

