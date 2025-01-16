package cn.chinaclear.sz.component.${packageName}.model;

import cn.chinaclear.sz.component.common.base.AbstractBusinessComponent;
import cn.chinaclear.sz.component.common.base.AbstractPersistentObject;
import cn.chinaclear.sz.component.common.properties.DateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.BeanUtils;

/**
* <p> ${componentCnName}组件业务实体类 </p>
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
@EqualsAndHashCode(callSuper = true)
@Schema(description = "${componentCnName}组件业务实体")
public class ${componentName}BO extends AbstractBusinessComponent {

    @Override
    public void setUniqueId() {
    }


    @Override
    public void parseUniqueId() {
    }

    @Override
    public AbstractPersistentObject transToPersistentObject() {
        ${componentName}PO po = new ${componentName}PO();
        BeanUtils.copyProperties(this, po);
        return po;
    }
}