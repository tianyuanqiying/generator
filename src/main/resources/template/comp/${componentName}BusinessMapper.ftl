package cn.chinaclear.sz.component.${packageName}.dao;

import cn.chinaclear.sz.component.${packageName}.model.${componentName}PO;
import cn.chinaclear.sz.component.common.service.mapper.CommonMapper;
import jdk.jfr.Registered;
import org.apache.ibatis.annotations.Mapper;

/**
* <p> ${componentCnName}组件数据库访问类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Mapper
@Registered
public interface ${componentName}BusinessMapper extends CommonMapper<${componentName}PO> {
}