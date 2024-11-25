package com.chinaclear.sz.component.common;

import cn.hutool.core.collection.CollUtil;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface PathResolver {
    /**
     * 相对路径处理为绝对路径
     * @param directories 目录
     * @param moduleInfo 变量
     * @return 绝对路径
     */
    default List<String> handlePath(List<String> directories, ModuleInfo moduleInfo) {
        List<String> realPaths = new ArrayList<>();
        if (CollUtil.isEmpty(directories)) {
            return realPaths;
        }

        for (String directory : directories) {
            //替换掉替换符 ${packageName};
            String path = directory.replace("${packageName}", moduleInfo.getPackageName());

            //避免开头配置不清楚，统一加上File.separator
            if (!path.startsWith(File.separator)) {
                path = File.separator + path;
            }

            //目录路径 = 基础目录路径 + 子项目名称 + path
            String realPath = moduleInfo.getBaseDir() + File.separator + moduleInfo.getSubProjectName() + path;
            realPaths.add(realPath);
        }
        return realPaths;
    }
}
