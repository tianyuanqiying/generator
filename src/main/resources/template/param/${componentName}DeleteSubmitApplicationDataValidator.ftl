package cn.chinaclear.sz.app.process.param.${packageName}.validation;

import cn.chinaclear.sz.bpmframework.bpm.param.validation.AbstractParamDataValidator;
import cn.chinaclear.sz.bpmframework.bpm.process.validation.ValidatorContext;
import cn.chinaclear.sz.app.process.param.${packageName}.service.impl.${componentName}ParamDataHandler;
import cn.chinaclear.sz.app.process.param.${packageName}.service.impl.${componentName}ParamHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
* <p> 删除提交表单验证器实现类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Slf4j
@Component
public class ${componentName}DeleteSubmitApplicationDataValidator extends AbstractParamDataValidator {

    public ${componentName}DeleteSubmitApplicationDataValidator(${componentName}ParamHandler paramHandler, ${componentName}ParamDataHandler paramDataHandler) {
        super(paramHandler, paramDataHandler);
    }

    /**
     * 验证业务逻辑
     * @param bizId 业务编码
     * @param params 提交的表单业务数据列表
     * @param context 参数验证器上下文对象
     */
    @Override
    protected void validateBusinessLogic(String bizId, List<Map<String, Object>> params, ValidatorContext context) {

    }

}
