package cn.chinaclear.sz.app.query.${packageName}.handler;

import cn.chinaclear.sz.app.query.${packageName}.constant.${componentName}Constant;
import cn.chinaclear.sz.bpmframework.query.service.instance.handler.QueryMetaDataHandler;
import cn.chinaclear.sz.component.common.enums.SchemaMetaDataKey;
import cn.chinaclear.sz.component.common.util.JsonSchemaUtils;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.Map;

/**
* <p> ${componentCnName}查询jsonschema处理器类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Component
@RequiredArgsConstructor
public class ${componentName}QueryMetaDataHandler implements QueryMetaDataHandler {
    @Override
    public Map<String, Object> getFieldMetaData() {
        return JsonSchemaUtils.getFieldMetaData(${componentName}Constant.JSON_SCHEMA_PATH, SchemaMetaDataKey.QUERY);
    }
}