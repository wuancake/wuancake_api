# wuancake_api
默认读取/etc/application.properties配置文件，可在启动类WuancakeApiApplication启动类中修改配置文件位置，配置如下：
    
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.url=jdbc:mysql://数据库地址:3306/数据库名?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true	  
    spring.datasource.username=账号													 
    spring.datasource.password=密码													 													 
    mybatis.configuration.map-underscore-to-camel-case=true
    
    
##编译：

修改项目pom.xml中这个地方,将项目打包为war或者jar

` <packaging>war</packaging>`

在项目根目录下使用maven命令,生成的target包下的xxx.war：

`mvn clean package`
