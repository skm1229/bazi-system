package com.bazi.common.util;

import org.apache.commons.lang3.StringUtils;
import skm.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期解析工具类
 *
 * @author skm1229
 * @version 1.0.0
 */
public class DateParseUtil {

    /**
     * 支持的日期格式列表（按优先级排序）
     */
    private static final String[] SUPPORTED_FORMATS = {
        "yyyy年M月d日HH时mm分",        // 主要中文格式（不补零）
        "yyyy年MM月dd日HH时mm分ss秒",  // DateUtil的默认格式
        "yyyy-MM-dd HH:mm:ss",        // 标准格式
        "yyyy-MM-dd"                  // 简单日期格式
    };

    /**
     * 解析日期字符串，支持多种格式
     *
     * @param dateStr 日期字符串
     * @return 解析后的Date对象
     * @throws ParseException 当所有格式都无法解析时抛出异常
     */
    public static Date parseDate(String dateStr) throws ParseException {
        if (StringUtils.isBlank(dateStr)) {
            throw new ParseException("日期字符串不能为空", 0);
        }

        // 尝试使用支持的格式解析
        for (String format : SUPPORTED_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(dateStr);
            } catch (ParseException ignored) {
                // 继续尝试下一个格式
            }
        }

        // 尝试使用DateUtil的方法作为兜底
        try {
            return DateUtil.stringToDate(dateStr);
        } catch (Exception e) {
            throw new ParseException("无法解析日期格式: " + dateStr +
                                   "，支持的格式: 2003年1月15日00时00分, yyyy-MM-dd HH:mm:ss 等", 0);
        }
    }

    /**
     * 格式化日期为标准格式字符串 (yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串，如果date为null返回空字符串
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 格式化日期为中文格式字符串 (yyyy年M月d日HH时mm分)
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串，如果date为null返回空字符串
     */
    public static String formatDateChinese(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日HH时mm分");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
