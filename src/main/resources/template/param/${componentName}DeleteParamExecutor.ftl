package cn.chinaclear.sz.app.process.param.${packageName}.service.impl.executor;

import cn.chinaclear.sz.bpmframework.bpm.param.service.ParamExecutor;
import cn.chinaclear.sz.bpmframework.bpm.protocol.model.enums.EffectStatus;
import cn.chinaclear.sz.bpmframework.web.api.ApiResult;
import cn.chinaclear.sz.bpmframework.web.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
* <p> 删除参数生效处理器实现类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ${componentName}DeleteParamExecutor implements ParamExecutor {

    /**
     * 将表单数据转换成实际类型对象， 并调用业务竖井接口， 如果生效成功， 返回EFFECTED, 否则返回EFFECT_FAILED
     * @param bizId 唯一主键id
     * @param params 表单数据对象列表
     * @return 生效结果
     */
    @Override
    public ApiResult<EffectStatus> confirmExecute(String bizId, List<Map<String, Object>> params) {
        return null;
    }
}