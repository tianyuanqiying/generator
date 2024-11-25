package cn.chinaclear.sz.component.${packageName}.model.dto;

import cn.chinaclear.sz.component.common.base.AbstractQueryComponent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* <p> ${componentCnName}与Focus交互类 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "${componentCnName}")
public class ${componentName}Param extends AbstractQueryComponent {

    @Override
    public void setUniqueId() {
    }
}