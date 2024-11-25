package com.chinaclear.sz.component.util;

import com.intellij.openapi.ui.Messages;

public class GeneratorUtil {

    public static void showErrorMessage(String message) {
        Messages.showErrorDialog(message, "错误提示");
    }


    /**
     * 大驼峰 转换为 中划线分割  UserManager => user-manager
     * @param input 大驼峰字符串
     * @return 中划线字符串
     */
    public static String toKebabCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    result.append('-');
                }
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
