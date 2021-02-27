package top.lzmvlog.common.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.istack.internal.NotNull;
import top.lzmvlog.common.enums.EncryptEnum;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 通用工具类
 *
 * @author chenghao
 * @since 2021-02-27
 */
public class CommonUtil {

    /**
     * 获取一个数占另外一个数的占比
     *
     * @param val1 占比数
     * @param val2 总数
     * @return val1占val2的百分比
     */
    public static String getPercentage(@NotNull Number val1, @NotNull Number val2) {
        // 任意参数被传0则返回0
        if (val1.intValue() == 0 || val2.intValue() == 0) {
            return "0";
        }
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后1位
        numberFormat.setMaximumFractionDigits(1);
        // 设置四舍五入
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        return numberFormat.format((Convert.toFloat(val1) / Convert.toFloat(val2)) * 100);
    }

    /**
     * 获取指定日期范围内工作日的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 工作日天数
     */
    public static int getWeekday(@NotNull Date startDate, @NotNull Date endDate) {
        // 获取中国/重庆时区
        TimeZone zone = TimeZone.getTimeZone("Asia/Chongqing");
        return getWeekday(startDate, endDate, zone);
    }

    /**
     * 获取指定日期范围内工作日的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 工作日天数
     */
    public static int getWeekday(@NotNull Date startDate, @NotNull Date endDate, @NotNull TimeZone zone) {
        // 根据时区获取日历信息
        Calendar clOne = Calendar.getInstance(zone);
        Calendar clTwo = Calendar.getInstance(zone);
        clOne.setTime(startDate);
        clTwo.setTime(endDate);

        int days = 0;
        while (clOne.compareTo(clTwo) <= 0) {
            if (clOne.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && clOne.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
                days++;
            clOne.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    /**
     * 根据两点经纬度得算距离
     *
     * @param jdStr 经度
     * @param wdStr 纬度
     * @return 距离（米）
     */
    public static Integer getDistance(@NotNull String jdStr, @NotNull String wdStr) {
        // 分割经纬度
        List<Double> jdNumber = Convert.toList(Double.class, StrUtil.splitTrim(jdStr, StrUtil.COMMA));
        List<Double> wdNumber = Convert.toList(Double.class, StrUtil.splitTrim(wdStr, StrUtil.COMMA));
        if (jdNumber.size() != 2 || wdNumber.size() != 2) {
            throw new IllegalArgumentException("不规范的参数");
        }
        return getDistance(jdNumber.get(0), jdNumber.get(1), wdNumber.get(0), wdNumber.get(1));
    }

    /**
     * 根据两点经纬度得算距离
     *
     * @param startJd 起始经度
     * @param startWd 起始纬度
     * @param endJd   截止经度
     * @param endWd   截止纬度
     * @return 距离（米）
     */
    public static Integer getDistance(@NotNull Double startJd,
                                      @NotNull Double startWd,
                                      @NotNull Double endJd,
                                      @NotNull Double endWd) {
        double pk = 180 / 3.14169;
        double a1 = startJd / pk;
        double a2 = startWd / pk;
        double b1 = endJd / pk;
        double b2 = endWd / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        // 不保留小数
        return Convert.toInt(NumberUtil.round((6371000 * tt), 0));
    }

    /**
     * 处理敏感字符
     *
     * @param strData 字符串数据
     * @param encrypt 加密枚举类（后续补充）
     *                {@link EncryptEnum#MOBILE_PHONE}
     *                {@link EncryptEnum#EMAIL}
     *                {@link EncryptEnum#ID_NUMBER}
     * @return 处理后的字符
     */
    public static String handleSensitiveChar(@NotNull String strData, @NotNull EncryptEnum encrypt) {
        switch (encrypt) {
            case MOBILE_PHONE:
                strData = strData.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                break;
            case EMAIL:
                strData = strData.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
                break;
            case ID_NUMBER:
                strData = strData.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
                break;
            default:
                break;
        }
        return strData;
    }

//    /**
//     * 解析数据生成日志内容
//     *
//     * @param operatingType 操作类型 0 保存 1 修改
//     * @param fields        需要进行判断的字段s
//     * @param logDto        房源日志重要参数传输层
//     * @param otherFormat   其他日志格式
//     * @return 日志内容
//     */
//    @SneakyThrows
//    public static String analysisDataGenerateLogContent(String operatingType, String fields, String logDto, String... otherFormat) {
//        // 解析日志参数传输层
//         =JSON.parseObject();
//        // 解析配置json
//        HashMap<String, Object> config = StrUtil.isNotBlank(maps.get(HousingResourceConstants.LOG_IMPORTANT_CONFIG)) ?
//                jsonMapper.readValue(maps.get(HousingResourceConstants.LOG_IMPORTANT_CONFIG), new TypeReference<HashMap<String, Object>>() {
//                }) : null;
//        // 解析旧数据json
//        HashMap<String, Object> oldData = StrUtil.isNotBlank(maps.get(HousingResourceConstants.LOG_IMPORTANT_OLD)) ?
//                jsonMapper.readValue(maps.get(HousingResourceConstants.LOG_IMPORTANT_OLD), new TypeReference<HashMap<String, Object>>() {
//                }) : new HashMap<>();
//        // 解析新数据json
//        HashMap<String, Object> newData = StrUtil.isNotBlank(maps.get(HousingResourceConstants.LOG_IMPORTANT_NEW)) ?
//                jsonMapper.readValue(maps.get(HousingResourceConstants.LOG_IMPORTANT_NEW), new TypeReference<HashMap<String, Object>>() {
//                }) : null;
//        // 解析回调json
//        HashMap<String, Object> callback = StrUtil.isNotBlank(maps.get(HousingResourceConstants.LOG_IMPORTANT_CALLBACK)) ?
//                jsonMapper.readValue(maps.get(HousingResourceConstants.LOG_IMPORTANT_CALLBACK), new TypeReference<HashMap<String, Object>>() {
//                }) : null;
//        if (ObjectUtil.isNull(config) || config.isEmpty() || ObjectUtil.isNull(newData) || newData.isEmpty()) {
//            return "";
//        }
//        StringJoiner logContent = new StringJoiner(";");
//        // 分割字段
//        List<String> fieldList = StrUtil.splitTrim(fields, StrUtil.COMMA);
//        // 新增日志内容
//        if (HousingResourceConstants.SAVE_INFO.equals(operatingType)) {
//            // 判断日志格式：处理某些日志需要特殊的日志格式
//            String strFormat = ObjectUtil.isNotEmpty(otherFormat) ? otherFormat[0] : HousingLogConstants.HOUSING_XZ_FORMAT;
//            if (!fieldList.isEmpty()) {
//                // 有固定的field
//                fieldList.forEach(field -> {
//                    String configFieldData = Convert.toStr(config.get(field), "");
//                    if (StrUtil.isNotBlank(configFieldData)) {
//                        String newFieldData = Convert.toStr(newData.get(field), "");
//                        if (StrUtil.isNotBlank(newFieldData)) {
//                            if (StrUtil.containsAny(field, Convert.toStrArray(HousingResourceConstants.FILE_FIELDS))) {
//                                logContent.add(StrUtil.format(strFormat, config.get(field), StrUtil.format(HousingLogConstants.FILE_SPECIAL_FORMAT, HousingLogConstants.DEFAULT_FUNCTION_NAME, newFieldData)));
//                            } else {
//                                logContent.add(StrUtil.format(strFormat, config.get(field), newFieldData));
//                            }
//                        }
//                    }
//                });
//            } else {
//                // 无固定的field，解析config
//                config.forEach((k, v) -> {
//                    String newFieldData = Convert.toStr(newData.get(k), "");
//                    if (StrUtil.isNotBlank(newFieldData)) {
//                        if (ObjectUtil.isNotNull(callback) && !callback.isEmpty()) {
//                            // 获取callback function
//                            logContent.add(StrUtil.format(strFormat, v, StrUtil.format(HousingLogConstants.FILE_SPECIAL_FORMAT, callback.get(k), newFieldData)));
//                        } else {
//                            logContent.add(StrUtil.format(strFormat, v, newFieldData));
//                        }
//                    }
//                });
//            }
//        }
//        // 修改日志内容
//        else {
//            // 判断日志格式：处理某些日志需要特殊的日志格式
//            String strFormat = ObjectUtil.isNotEmpty(otherFormat) ? otherFormat[0] : HousingLogConstants.HOUSING_XG_FORMAT;
//            if (!fieldList.isEmpty()) {
//                // 有固定的field
//                fieldList.forEach(field -> {
//                    String configFieldData = Convert.toStr(config.get(field), "");
//                    if (StrUtil.isNotBlank(configFieldData)) {
//                        String newFieldData = Convert.toStr(newData.get(field), "");
//                        String oldFieldData = Convert.toStr(oldData.get(field), "");
//                        if (!newFieldData.equals(oldFieldData)) {
//                            if (StrUtil.containsAny(field, Convert.toStrArray(HousingResourceConstants.FILE_FIELDS))) {
//                                logContent.add(StrUtil.format(strFormat, configFieldData,
//                                        StrUtil.format(HousingLogConstants.FILE_SPECIAL_FORMAT, HousingLogConstants.DEFAULT_FUNCTION_NAME, oldFieldData),
//                                        StrUtil.format(HousingLogConstants.FILE_SPECIAL_FORMAT, HousingLogConstants.DEFAULT_FUNCTION_NAME, newFieldData)));
//                            } else {
//                                logContent.add(StrUtil.format(strFormat, configFieldData, oldFieldData, newFieldData));
//                            }
//                        }
//                    }
//                });
//            } else {
//                // 解决oldData空数据的问题
//                boolean oldStatus = ObjectUtil.isNull(oldData);
//                // 无固定的field，解析config
//                config.forEach((k, v) -> {
//                    String newFieldData = Convert.toStr(newData.get(k), "");
//                    String oldFieldData = Convert.toStr(oldData.get(k), "");
//                    if (oldStatus || (!newFieldData.equals(oldFieldData))) {
//                        if (ObjectUtil.isNotNull(callback) && !callback.isEmpty()) {
//                            logContent.add(StrUtil.format(strFormat, v,
//                                    StrUtil.format(HousingLogConstants.FILE_SPECIAL_FORMAT, callback.get(k), oldStatus ? "" : oldFieldData),
//                                    StrUtil.format(HousingLogConstants.FILE_SPECIAL_FORMAT, callback.get(k), newFieldData)));
//                        } else {
//                            logContent.add(StrUtil.format(strFormat, v, oldStatus ? "" : oldFieldData, newFieldData));
//                        }
//                    }
//                });
//            }
//        }
//        return logContent.toString();
//    }
}
