package com.chinaclear.sz.component.ui;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
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
    private JPanel packageNamePanel;

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

        //如果是查询理性， 则 子项目名的全小写为包名， 大驼峰为类名前缀
        if (ModuleEnum.QUERY.getName().equals(menuTypeSelected.toString())) {
            packageName = subProjectName.toLowerCase();
            componentName = Character.toUpperCase(subProjectName.charAt(0)) + subProjectName.substring(1);
        }

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

            //如果是查询
            if (ItemEvent.SELECTED == e.getStateChange() && ModuleEnum.QUERY.getName().equals(selectedItem.toString())) {
                //包名不显示
                packageNamePanel.setVisible(false);
                //类名前缀不显示
                componentNameEnPanel.setVisible(false);
                //组件中文名称显示
                componentNameCnPanel.setVisible(true);
                //convertId显示
                convertPanel.setVisible(true);
                return;
            }

            if (ItemEvent.SELECTED == e.getStateChange() && ModuleEnum.PARAM.getName().equals(selectedItem.toString())) {
                //包名不显示
                packageNamePanel.setVisible(true);
                //类名前缀显示
                componentNameEnPanel.setVisible(true);
                //组件中文名称显示
                componentNameCnPanel.setVisible(true);
                //convertId显示
                convertPanel.setVisible(true);
                return;
            }

            if (ItemEvent.SELECTED == e.getStateChange() && ModuleEnum.COMPONENT.getName().equals(selectedItem.toString())) {
                //包名不显示
                packageNamePanel.setVisible(true);
                //类名前缀显示
                componentNameEnPanel.setVisible(true);
                //组件中文名称显示
                componentNameCnPanel.setVisible(true);
                //convertId不显示
                convertPanel.setVisible(false);
                return;
            }

            //如果menutype下拉框选择了其他类型的应用
            if (ItemEvent.SELECTED == e.getStateChange() && ModuleEnum.PROCESS.getName().equals(selectedItem.toString())) {
                //包名不显示
                packageNamePanel.setVisible(true);
                //显示 版本号下拉框， 打包插件输入框， 不显示类名前缀输入框。
                convertPanel.setVisible(true);
                componentNameEnPanel.setVisible(false);
                componentNameCnPanel.setVisible(false);
            }
        }
    }
}
