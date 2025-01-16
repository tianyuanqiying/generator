package cn.chinaclear.sz.app.process.param.${packageName}.service.impl.formdata;

import cn.chinaclear.sz.bpmframework.bpm.param.service.ParamActivityFormDataHandler;
import cn.chinaclear.sz.bpmframework.bpm.process.context.ProcessContext;
import cn.chinaclear.sz.bpmframework.bpm.protocol.model.dto.ComponentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
* <p> 参维业务表单数据处理器接口 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Service
@RequiredArgsConstructor
public class ${componentName}SubmitApplicationParamActivityFormDataHandler implements ParamActivityFormDataHandler {

    /**
     * 保存参维数据
     * @param bizId 唯一主键id
     * @param pContext 流程上下文
     * @param params 列表数据
     * @param bizCode 业务编码
     */
    @Override
    public void saveParamFormData(String bizId, List<Map<String, Object>> params, ProcessContext pContext, String bizCode) {

    }

    /**
     * 提交参维数据
     * @param bizId 唯一主键id
     * @param pContext 流程上下文
     * @param params 列表数据
     * @param bizCode 业务编码
     */
    @Override
    public void submitParamFormData(String bizId, List<Map<String, Object>> params, ProcessContext pContext, String bizCode) {

    }

    /**
     * 查询参维数据
     * @param bizId 唯一主键id
     * @param pContext 流程上下文
     * @return 业务数据
     */
    @Override
    public List<ComponentData> getParamFormData(String bizId, ProcessContext pContext) {
        return null;
    }
}