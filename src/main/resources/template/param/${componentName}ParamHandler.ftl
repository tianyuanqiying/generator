package cn.chinaclear.sz.app.process.param.${packageName}.service.impl;

import org.springframework.stereotype.Service;
import cn.chinaclear.sz.bpmframework.bpm.param.service.ParamHandler;
import java.util.Map;

/**
* <p> 参维处理器接口 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Service
public class ${componentName}ParamHandler implements ParamHandler {

    /**
     * 获取参维流程实例名后缀
     * @param actionType 参维类型
     * @param bizData    业务数据
     * @return 参维流程实例名后缀
     */
    @Override
    public String getInstanceNameSuffix(String actionType, Map<String, Object> bizData) {
        return null;
    }

    /**
     * 获取参维流程实例并行控制id后缀
     * @param actionType 参维类型
     * @param bizData    业务数据
     * @return 参维流程实例并行控制id后缀
     */
    @Override
    public String getExclusiveIdSuffix(String actionType, Map<String, Object> bizData) {
        return null;
    }

    /**
     * 获取参维uniqueId的中文名
     * @return uniqueId的中文名
     */
    @Override
    public String getUniqueIdName() {
        return null;
    }
}