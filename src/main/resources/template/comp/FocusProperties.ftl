package cn.chinaclear.sz.component.${packageName}.properties;

import cn.chinaclear.sz.bpmframework.web.adapter.properties.AbstractExternalServiceProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
* <p> Focus配置类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Data
@Component
@PropertySource("classpath:properties/${componentName}.properties")
@ConfigurationProperties(prefix = "csdc.component.${kebabCaseComponentName}")
public class FocusProperties extends AbstractExternalServiceProperties{

}