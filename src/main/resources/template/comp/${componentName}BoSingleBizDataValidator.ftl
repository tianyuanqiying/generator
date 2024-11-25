package cn.chinaclear.sz.component.${packageName}.validation;

import cn.chinaclear.sz.component.common.base.SingleBizDataValidator;
import cn.chinaclear.sz.component.${packageName}.model.${componentName}BO;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
* <p>组件开发实现的对每一条组件业务数据进行的业务校验</p>
*
* <p> =======修改记录 开始 ======= </p>
* <p> ${createDate} 1.0.0 ${authorName} 新建</p>
* <p> =======修改记录 结束 ======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Component
public class ${componentName}BoSingleBizDataValidator implements SingleBizDataValidator<${componentName}BO> {

    @Override
    public void validateForAdd(${componentName}BO bizData, Map<String, Object> errorData ) {
    }

    @Override
    public void validateForUpdate(${componentName}BO bizData, Map<String, Object> errorData ) {
    }

    @Override
    public void validateForDelete(${componentName}BO bizData, Map<String, Object> errorData ) {
    }

}