# wuancake_api
默认读取/etc/application.properties配置文件，可在启动类WuancakeApiApplication启动类中修改配置文件位置，配置如下：
    
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.url=jdbc:mysql://数据库地址:3306/数据库名?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true	  
    spring.datasource.username=账号													 
    spring.datasource.password=密码													   
    server.port=端口															  
    mybatis.configuration.map-underscore-to-camel-case=true
