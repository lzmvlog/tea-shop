package top.lzmvlog.common.utils;

/**
 * 字符工具
 * @author zhang1591313226@163.com
 * @since 2021-02-23 10:19
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param charSequence
     * @return
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0 || charSequence.toString().trim().length() == 0;
    }

    /**
     * 判断字符串是否非空
     * @param charSequence
     * @return
     */
    public static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null || charSequence.length() != 0 || charSequence.toString().trim().length() != 0;
    }

    /**
     * 取文件后缀 根据 . 进行截取
     * @param resourceName 文件名称
     * @return
     */
    public static String getSuffix(String resourceName) {
        int index = resourceName.lastIndexOf('.');
        return resourceName.substring(index + 1);
    }

}
