package cn.chinaclear.sz.component.${packageName}.model;

import cn.chinaclear.sz.component.common.base.AbstractBusinessComponent;
import cn.chinaclear.sz.component.common.base.AbstractPersistentObject;
import cn.chinaclear.sz.component.common.properties.DateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
* <p> ${componentCnName}数据库实体类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "${componentCnName}组件持久化实体")
//TODO:设置表名
@Table(name = "")
public class ${componentName}PO extends AbstractPersistentObject {

    /*
    * @return 业务实体对象
    */
    @Override
    public AbstractBusinessComponent transToBusinessComponent() {
        ${componentName}BO bo = new ${componentName}BO();
        BeanUtils.copyProperties(this, bo);
        return bo;
    }
}