package com.chinaclear.sz.component.ui;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.chinaclear.sz.component.generator.DirectoryGenerator;
import com.chinaclear.sz.component.generator.ModuleEnum;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

/**
 * @author sfhuang
 * @sine 2024/9/10 14:59
 */
public class NoticeListWindow {
    private JPanel content;
    private JComboBox<String> selectVer;

    private JComboBox<String> menusType;
    private JTextField menuId;
    private JButton btnGen;
    private JButton btnClear;
    private JButton btnClose;


    private Project project;
    private ToolWindow toolWindow;

    public void init(){
        menuId.setText("");

        //四种应用类型
        menusType.addItem("流程");
        menusType.addItem("参维");
        menusType.addItem("查询");
        menusType.addItem("组件");

        //默认的应用类型为流程类型，因此，版本列表系那是流程框架的版本号列表；
        List<String> versionList = queryVersionList(ModuleEnum.PROCESS.getName());
        if (CollUtil.isEmpty(versionList)) {
            versionList = CollUtil.newArrayList("V1.0.0", "V2.0.0", "V3.0.0");
        }
        for (String version : versionList) {
            getSelectVer().addItem(version);
        }

        btnGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateDirectory();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuId.setText("");
                selectVer.setSelectedIndex(0);
                menusType.setSelectedIndex(0);
            }
        });
       btnClose.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               toolWindow.hide();
           }
       });

        menusType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //选中时出发；
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object selectedItem = menusType.getSelectedItem();
                    if (StrUtil.isBlankIfStr(selectedItem)) {
                        return;
                    }

                    //获取版本号
                    List<String> versionList = queryVersionList(selectedItem.toString());

                    //版本号设置给版本号下拉框
                    for (String version : versionList) {
                        getMenusType().addItem(version);
                    }
                }
            }
        });
    }

    /**
     * 根据不同的应用类型查询不同的依赖框架版本
     * @param menuType 应用类型
     * @return 版本列表
     */
    private List<String> queryVersionList(String menuType) {
        try{
            //根据应用类型发送不同的URL请求
            StringBuilder queryUrlBuilder = new StringBuilder();
            queryUrlBuilder.append("http://maven.sz.chinaclear.cn/service/rest/v1/search?");
            if (ModuleEnum.PROCESS.getName().equals(menuType)) {
                queryUrlBuilder.append("group=cn.chinaclear.sz.bpmframework&&name=bpm-process-spring-boot-starter");
            }else if (ModuleEnum.PARAM.getName().equals(menuType)) {
                queryUrlBuilder.append("group=cn.chinaclear.sz.bpmframework.param&&name=param-maintain-spring-boot-starter");
            }else if (ModuleEnum.QUERY.getName().equals(menuType)) {
                queryUrlBuilder.append("group=cn.chinaclear.sz.bpmframework.query&&name=query-spring-boot-starter");
            }

            //发HTTP请求
            String response = HttpUtil.get(queryUrlBuilder.toString(), 5000);
            Map<String, Object> responseMap = JSONUtil.toBean(response, HashMap.class);

            //解析items字段，拿到version列表信息
            List<Map<String, Object>> items = Optional.ofNullable(responseMap.get("items")).map(p -> (List) p).orElse(new ArrayList());
            if (CollUtil.isEmpty(items)) {
                return new ArrayList<>();
            }
            //解析结果，拿到版本列表；
            List<String> versionList = items.stream().map(item -> {
                String repository = Optional.ofNullable(item.get("repository")).map(repo -> repo.toString()).orElse("");
                String version = Optional.ofNullable(item.get("version")).map(v -> v.toString()).orElse("");
                if (repository.equals("snapshots")) {
                    version = version + "-SNAPSHOT";
                }
                return version;
            }).sorted((v1, v2) -> {
                String version = v1.split("-")[0];
                String version2 = v2.split("-")[0];
                return version.compareTo(version2);
            }).toList();
            return versionList;
        }catch (Exception exception) {
            System.out.println("发生异常：" + exception.getMessage());
            return new ArrayList<>();
        }
    }

    public NoticeListWindow(Project project, final ToolWindow toolWindow) {
        this.project = project;
        this.toolWindow = toolWindow;
        this.init();
    }

    private void generateDirectory() {
        String menuId = this.getMenuId().getText();
        if (StrUtil.isBlankIfStr(menuId)) {
            System.out.println("菜单ID不能为空");
            return;
        }

        Object menuTypeSelected = this.getMenusType().getSelectedItem();
        if (Objects.isNull(menuTypeSelected)) {
            System.out.println("菜单类型不能为空");
            return;
        }

        Object version = this.getSelectVer().getSelectedItem();
        String menuType = menuTypeSelected.toString();
        //创建目录生成器
        DirectoryGenerator generator = DirectoryGenerator.builder()
                .id(menuId)
                .type(menuType)
                .baseDir(this.getProject().getBasePath())
                .version(version.toString())
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

    public JComboBox<String> getSelectVer() {
        return selectVer;
    }

    public void setSelectVer(JComboBox<String> selectVer) {
        this.selectVer = selectVer;
    }

    public JComboBox<String> getMenusType() {
        return menusType;
    }

    public void setMenusType(JComboBox<String> menusType) {
        this.menusType = menusType;
    }

    public JTextField getMenuId() {
        return menuId;
    }

    public void setMenuId(JTextField menuId) {
        this.menuId = menuId;
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
}
