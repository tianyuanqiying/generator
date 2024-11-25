package cn.chinaclear.sz.component.${packageName};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
* <p> 启动应用 </p>
* <p> =======修改记录======= </p>
* <p> ${createDate} 1.0.0 ${authorName} </p>
* <p> =======修改记录======= </p>
*
* @author ${authorName}
* @version 1.0.0
* @since 1.0.0
*/
@SpringBootApplication(scanBasePackages = "cn.chinaclear.sz.*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}