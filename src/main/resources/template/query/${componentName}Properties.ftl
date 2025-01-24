package cn.chinaclear.sz.app.query.${packageName}.config;

import cn.chinaclear.sz.bpmframework.web.adapter.properties.AbstractExternalServiceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
* <p> ${componentCnName}查询配置类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Configuration
@PropertySource("classpath:properties/${componentName}.properties")
@ConfigurationProperties(prefix="csdc.query.${kebabCaseComponentName}")
public class ${componentName}Properties extends AbstractExternalServiceProperties {

}