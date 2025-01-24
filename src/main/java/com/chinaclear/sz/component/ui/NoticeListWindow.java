package com.chinaclear.sz.component.ui;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.chinaclear.sz.component.common.GeneratorConstant;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.generator.DirectoryGenerator;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author sfhuang
 * @sine 2024/9/10 14:59
 */
public class NoticeListWindow {
    private JPanel content;
    private JComboBox<String> menusType;
    private JTextField subProjectName;
    private JButton btnGen;
    private JButton btnClear;
    private JButton btnClose;
    private JTextField convertId;
    private JPanel convertPanel;
    private JTextField packageName;
    private JTextField authorField;
    private JTextField componentNameEnField;
    private JPanel componentNameEnPanel;
    private JTextField componentNameCnField;
    private JPanel componentNameCnPanel;

    private Project project;
    private ToolWindow toolWindow;

    public void init() {
        subProjectName.setText("");
        packageName.setText("");
        authorField.setText("");
        componentNameEnPanel.setVisible(false);
        componentNameCnPanel.setVisible(false);
        //四种应用类型
        menusType.addItem("流程");
        menusType.addItem("参维");
        menusType.addItem("查询");
        menusType.addItem("组件");

        btnGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateDirectory();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subProjectName.setText("");
                packageName.setText("");
                convertId.setText("");
                authorField.setText("");
                componentNameEnField.setText("");
                menusType.setSelectedIndex(0);
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide();
            }
        });

        //添加应用类型下拉框监听器
        menusType.addItemListener(new MenuTypeSelectedListener());
    }

    /**
     * 根据不同的应用类型查询不同的依赖框架版本
     *
     * @param menuType 应用类型
     * @return 版本列表
     */
    private List<String> queryVersionList(String menuType) {
        try {
            //根据应用类型发送不同的URL请求
            StringBuilder queryUrlBuilder = new StringBuilder();
            queryUrlBuilder.append("http://maven.sz.chinaclear.cn/service/rest/v1/search?");
            if (ModuleEnum.PROCESS.getName().equals(menuType)) {
                queryUrlBuilder.append("group=cn.chinaclear.sz.bpmframework&name=bpm-process-spring-boot-starter");
            } else if (ModuleEnum.PARAM.getName().equals(menuType)) {
                queryUrlBuilder.append("group=cn.chinaclear.sz.bpmframework.param&name=param-maintain-spring-boot-starter");
            } else if (ModuleEnum.QUERY.getName().equals(menuType)) {
                queryUrlBuilder.append("group=cn.chinaclear.sz.bpmframework.query&name=query-spring-boot-starter");
            }

            cn.hutool.http.HttpRequest get = HttpUtil.createGet(queryUrlBuilder.toString());
            //用户身份认证表示
            get.header("Authorization", getAuthorization());

            String response = get.execute().body();

            //发HTTP请求
            Map<String, Object> responseMap = JSONUtil.toBean(response, HashMap.class);

            //解析items字段，拿到version列表信息
            List<Map<String, Object>> items = Optional.ofNullable(responseMap.get("items")).map(p -> (List) p).orElse(new ArrayList());
            //解析结果，拿到版本列表；
            List<String> versionList = items.stream().map(item -> {
                String repository = Optional.ofNullable(item.get("repository")).map(repo -> repo.toString()).orElse("");
                String version = Optional.ofNullable(item.get("version")).map(v -> v.toString()).orElse("").split("-")[0];
                if (repository.equals("snapshots")) {
                    version = version + "-SNAPSHOT";
                }
                return version;
            }).sorted((v1, v2) -> {
                String version = v1.split("-")[0];
                String version2 = v2.split("-")[0];
                return version2.compareTo(version);
            }).limit(5).distinct().toList();
            return versionList;
        } catch (Exception exception) {
            System.out.println("发生异常：" + exception.getMessage());
            return new ArrayList<>();
        }
    }

    private String getAuthorization() {
        //先从环境变量获取 maven库的身份信息
        String authorization = System.getenv(GeneratorConstant.AUTHORIZATION);
        if (StrUtil.isNotBlank(authorization)) {
            return authorization;
        }

        //环境变量没有，则从path.properties文件里获取。
        authorization = SingleCacheRegistry.getCache(CacheKey.CONFIG).get(GeneratorConstant.AUTHORIZATION);
        return authorization;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public void setAuthorField(JTextField authorField) {
        this.authorField = authorField;
    }

    public NoticeListWindow(Project project, final ToolWindow toolWindow) {
        this.project = project;
        this.toolWindow = toolWindow;
        this.init();
    }

    private void generateDirectory() {
        String subProjectName = this.getSubProjectName().getText();
        if (StrUtil.isBlankIfStr(subProjectName)) {
            Messages.showErrorDialog("子项目名称不能为空", "错误提示");
            return;
        }
        String packageName = this.getPackageName().getText();
        if (StrUtil.isBlankIfStr(packageName)) {
            Messages.showErrorDialog("包名不能为空", "错误提示");
            return;
        }

        Object menuTypeSelected = this.getMenusType().getSelectedItem();
        if (Objects.isNull(menuTypeSelected)) {
            Messages.showErrorDialog("菜单类型不能为空", "错误提示");
            return;
        }

        String authorName = this.getAuthorField().getText();
        if (StrUtil.isBlank(authorName)) {
            Messages.showErrorDialog("开发人员的OA名称不能为空", "错误提示");
            return;
        }


        //组件不存在转换ID
        String convertId = Optional.ofNullable(this.getConvertId().getText()).orElse("");

        String componentName = Optional.ofNullable(componentNameEnField.getText()).orElse("");
        String componentCnName = Optional.ofNullable(componentNameCnField.getText()).orElse("");
        //创建目录生成器
        DirectoryGenerator generator = DirectoryGenerator.builder()
                .subProjectName(subProjectName)
                .type(menuTypeSelected.toString())
                .baseDir(this.getProject().getBasePath())
                .version("")
                .convertId(convertId)
                .packageName(packageName)
                .authorName(authorName)
                .componentName(componentName)
                .componentCnName(componentCnName)
                .build();
        generator.generate();

        //弹出提示框
        Messages.showMessageDialog(this.getProject(), "生成目录成功, 右键项目选择【Reload from Disk】刷新页面", "提示", Messages.getInformationIcon());
    }

    public JPanel getContent() {
        return content;
    }

    public void setContent(JPanel content) {
        this.content = content;
    }

    public JComboBox<String> getMenusType() {
        return menusType;
    }

    public void setMenusType(JComboBox<String> menusType) {
        this.menusType = menusType;
    }

    public JTextField getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(JTextField subProjectName) {
        this.subProjectName = subProjectName;
    }

    public JButton getBtnGen() {
        return btnGen;
    }

    public void setBtnGen(JButton btnGen) {
        this.btnGen = btnGen;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(JButton btnClear) {
        this.btnClear = btnClear;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ToolWindow getToolWindow() {
        return toolWindow;
    }

    public void setToolWindow(ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
    }

    public JTextField getConvertId() {
        return convertId;
    }

    public void setConvertId(JTextField convertId) {
        this.convertId = convertId;
    }

    public JTextField getPackageName() {
        return packageName;
    }

    public void setPackageName(JTextField packageName) {
        this.packageName = packageName;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public class MenuTypeSelectedListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object selectedItem = menusType.getSelectedItem();
            //选择了组件类型，隐藏 打包插件输入框和框架版本下拉框, 否则显示打包插件和框架版本下拉框
            if (ItemEvent.SELECTED == e.getStateChange()
            && (ModuleEnum.COMPONENT.getName().equals(selectedItem.toString())
               || ModuleEnum.PARAM.getName().equals(selectedItem.toString())
                    || ModuleEnum.QUERY.getName().equals(selectedItem.toString()))
            ) {
                componentNameEnPanel.setVisible(true);
                componentNameCnPanel.setVisible(true);
                convertPanel.setVisible(false);
                if (ModuleEnum.PARAM.getName().equals(selectedItem.toString())
                || ModuleEnum.QUERY.getName().equals(selectedItem.toString())) {
                    convertPanel.setVisible(true);
                }
                return;
            }

            //如果menutype下拉框选择了其他类型的应用
            if (ItemEvent.SELECTED == e.getStateChange()) {
                //显示 版本号下拉框， 打包插件输入框， 不显示类名前缀输入框。
                convertPanel.setVisible(true);
                componentNameEnPanel.setVisible(false);
                componentNameCnPanel.setVisible(false);
            }
        }
    }
}
