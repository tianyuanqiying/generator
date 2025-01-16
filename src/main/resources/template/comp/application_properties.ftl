server.port=8090

debug=false
logging.level.org.springframework=Info
logging.level.cn.chinaclear=Info
logging.level.org.apache.kafka=ERROR

csdc.message.servers=123123

spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=


mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
mybatis.mapper-locations=classpath:sql/*.xml

<#noparse>
focus_host=${FOCUS_HOST}
</#noparse>
