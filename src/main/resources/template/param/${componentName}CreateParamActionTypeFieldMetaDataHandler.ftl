package cn.chinaclear.sz.app.process.param.${packageName}.service.impl.metadata;

import cn.chinaclear.sz.bpmframework.bpm.param.service.ParamActionTypeFieldMetaDataHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
* <p> 参维业务表单生效数据处理器接口 </p>
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
public class ${componentName}CreateParamActionTypeFieldMetaDataHandler implements ParamActionTypeFieldMetaDataHandler {

    @Override
    public Map<String, Object> getFieldMetaData(String activityDefId) {
        return null;
    }
}