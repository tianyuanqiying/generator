package cn.chinaclear.sz.app.query.${packageName}.handler;

import cn.chinaclear.sz.app.query.${packageName}.model.${componentName}Detail;
import cn.chinaclear.sz.app.query.${packageName}.service.${componentName}Service;
import cn.chinaclear.sz.bpmframework.query.model.result.DetailResult;
import cn.chinaclear.sz.bpmframework.query.model.result.ExecutorResult;
import cn.chinaclear.sz.bpmframework.query.service.instance.handler.QueryHandler;
import cn.chinaclear.sz.component.common.model.QueryResult;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.Map;

/**
* <p> ${componentCnName}查询处理器类 </p>
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
public class ${componentName}QueryHandler implements QueryHandler<${componentName}Detail> {
    private final ${componentName}Service service;

    @Override
    public ExecutorResult<${componentName}Detail> executeQuery(Map<String, Object> queryCondition) {
        QueryResult<${componentName}Detail> queryResult = service.queryData(queryCondition);
        ExecutorResult<${componentName}Detail> result = new ExecutorResult<>();
        result.setDataList(queryResult.getResult());
        result.setPageCond(queryResult.getPageCond());
        return result;
    }

    @Override
    public DetailResult executeDetail(String detailId) {
        return QueryHandler.super.executeDetail(detailId);
    }

    @Override
    public Class getItemType() {
        return ${componentName}Detail.class;
    }
}
