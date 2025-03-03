package cn.chinaclear.sz.app.query.${packageName}.validation;

import cn.chinaclear.sz.app.query.${packageName}.constant.${componentName}Constant;
import cn.chinaclear.sz.bpmframework.query.validation.QueryValidation;
import cn.chinaclear.sz.component.common.validation.JsonSchemaValidator;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import java.util.HashMap;

/**
* <p> ${componentCnName}查询条件校验器类 </p>
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
public class ${componentName}QueryValidation implements QueryValidation {

    @Override
    public Map<String, Object> validateQueryCondition(Map<String, Object> queryParams) {
        //jsonschema校验
        JsonSchemaValidator validator = JsonSchemaValidator.builder().build();
        Map<String, Object> errorData = validator.validateForQuery(queryParams, ${componentName}Constant.JSON_SCHEMA_PATH);

        //自定义校验
        validateQueryConditionCustom(queryParams, errorData);
        return errorData;
    }

    /**
     * <p>查询条件自定义校验</p>
     * <p>=====START=====</p>
     * <p>参数校验逻辑</p>
     * <p>=====END=======</p>
     * @param queryParams 查询参数
     * @param errorData 错误原因
     */
    private void validateQueryConditionCustom(Map<String, Object> queryParams, Map<string, Object> errorData) {
        
    }
}