package cn.chinaclear.sz.component.${packageName}.service.impl;

import cn.chinaclear.sz.bpmframework.bpm.protocol.model.dto.ComponentData;
import cn.chinaclear.sz.bpmframework.bpm.protocol.model.dto.ViolationDetail;
import cn.chinaclear.sz.bpmframework.bpm.protocol.model.enums.EffectStatus;
import cn.chinaclear.sz.bpmframework.bpm.protocol.model.util.CommandResultUtil;
import cn.chinaclear.sz.bpmframework.web.api.ApiResult;
import cn.chinaclear.sz.bpmframework.web.client.RestDataClient;
import cn.chinaclear.sz.component.${packageName}.dao.${componentName}BusinessMapper;
import cn.chinaclear.sz.component.${packageName}.model.${componentName}BO;
import cn.chinaclear.sz.component.${packageName}.model.${componentName}PO;
import cn.chinaclear.sz.component.${packageName}.properties.FocusProperties;
import cn.chinaclear.sz.component.${packageName}.service.${componentName}BusinessService;
import cn.chinaclear.sz.component.common.base.AbstractQueryComponent;
import cn.chinaclear.sz.component.common.service.AbstractCommonService;
import cn.chinaclear.sz.component.common.util.BizStructureUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
* <p> ${componentCnName}组件业务服务接口实现类 </p>
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
@Slf4j
public class ${componentName}BusinessServiceImpl extends AbstractCommonService<${componentName}BO, ${componentName}BusinessMapper>  implements ${componentName}BusinessService {

    /**
    * 请求客户端
    */
    private final RestDataClient restDataClient;

    /**
     * focus接口配置类
    */
    private final FocusProperties properties;

}



