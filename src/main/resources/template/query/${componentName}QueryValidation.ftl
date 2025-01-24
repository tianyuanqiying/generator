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
        return new HashMap<>();
    }
}