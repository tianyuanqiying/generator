package cn.chinaclear.sz.app.process.param.${packageName}.service.impl;

import cn.chinaclear.sz.bpmframework.bpm.param.model.dto.ParametersResult;
import cn.chinaclear.sz.bpmframework.bpm.param.service.ParamDataHandler;

import cn.chinaclear.sz.bpmframework.bpm.process.context.ProcessContext;
import cn.chinaclear.sz.bpmframework.bpm.protocol.model.dto.ComponentData;
import cn.chinaclear.sz.component.${packageName}.model.dto.${componentName}DTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

/**
* <p> 参数数据处理器接口 </p>
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
public class ${componentName}ParamDataHandler implements ParamDataHandler<${componentName}DTO> {
}