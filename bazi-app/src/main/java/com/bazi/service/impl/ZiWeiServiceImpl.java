package com.bazi.service.impl;

import com.bazi.dto.ziwei.ZiWeiDto;
import com.bazi.service.ZiWeiService;
import com.bazi.vo.ziwei.ZiWeiVo;
import com.bazi.common.util.DateParseUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import skm.core.ziwei.ZiWei;
import skm.core.ziwei.settings.ZiWeiJiChuSetting;

import java.util.*;

/**
 * 紫微斗数排盘服务实现类
 * 
 * <p>负责处理紫微斗数排盘的核心业务逻辑，包括参数转换、紫微计算和结果封装</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class ZiWeiServiceImpl implements ZiWeiService {

    /**
     * 紫微斗数排盘
     * 
     * @param dto 紫微斗数排盘输入参数
     * @return 紫微斗数排盘结果
     */
    @Override
    public ZiWeiVo paiPan(ZiWeiDto dto) {
        // 1. 参数转换
        ZiWeiJiChuSetting setting = new ZiWeiJiChuSetting();
        BeanUtils.copyProperties(dto, setting);
        
        // 处理日期字符串转换
        if (StringUtils.isNotBlank(dto.getDate())) {
            try {
                Date parsedDate = DateParseUtil.parseDate(dto.getDate());
                setting.setDate(parsedDate);
            } catch (Exception e) {
                throw new RuntimeException("日期解析失败: " + e.getMessage(), e);
            }
        }

        // 2. 紫微斗数计算
        ZiWei ziWei = new ZiWei(setting);

        // 3. 结果封装
        ZiWeiVo vo = new ZiWeiVo();

        // 基本信息
        vo.setName(ziWei.getName());
        vo.setSex(ziWei.getSex()); // bazi-util已经返回"男"/"女"字符串
        vo.setZao(ziWei.getZao());
        vo.setOccupy(ziWei.getOccupy());

        // 日期时间信息
        vo.setSolarStr(ziWei.getSolarStr());
        vo.setLunarStr(ziWei.getLunarStr());
        vo.setSolarDate(ziWei.getSolarDate());
        vo.setLunarDate(ziWei.getLunarDate());
        vo.setXingQi(ziWei.getXingQi());
        vo.setJiJie(ziWei.getJiJie());
        vo.setShengXiao(ziWei.getShengXiao());
        vo.setXingZuo(ziWei.getXingZuo());
        vo.setYueXiang(ziWei.getYueXiang());
        vo.setYueJiang(ziWei.getYueJiang());
        vo.setYueJiangShen(ziWei.getYueJiangShen());
        vo.setWuBuYuShi(ziWei.getWuBuYuShi() ? "是" : "否");
        vo.setTaiSuiType(ziWei.getTaiSuiType());
        vo.setNanNvYinYang(ziWei.getNanNvYinYang());

        // 天干地支
        vo.setYearGan(ziWei.getYearGan());
        vo.setMonthGan(ziWei.getMonthGan());
        vo.setDayGan(ziWei.getDayGan());
        vo.setHourGan(ziWei.getHourGan());
        vo.setYearZhi(ziWei.getYearZhi());
        vo.setMonthZhi(ziWei.getMonthZhi());
        vo.setDayZhi(ziWei.getDayZhi());
        vo.setHourZhi(ziWei.getHourZhi());
        vo.setYearGanZhi(ziWei.getYearGanZhi());
        vo.setMonthGanZhi(ziWei.getMonthGanZhi());
        vo.setDayGanZhi(ziWei.getDayGanZhi());
        vo.setHourGanZhi(ziWei.getHourGanZhi());

        // 紫微斗数核心信息
        vo.setMingGongGongWei(ziWei.getMingGongGongWei());
        vo.setShenGongGongWei(ziWei.getShenGongGongWei());
        vo.setWuXingJu(ziWei.getWuXingJu());
        vo.setMingGongDiZhi(ziWei.getMingGongDiZhi());
        vo.setShenGongDiZhi(ziWei.getShenGongDiZhi());
        vo.setMingZhu(ziWei.getMingZhu());
        vo.setShenZhu(ziWei.getShenZhu());

        // 十二宫信息
        vo.setShiErMingGong(ziWei.getShiErMingGong());
        vo.setShiErGongDiZhi(ziWei.getShiErGongDiZhi());
        vo.setShiErGongZhuXing(ziWei.getShiErGongZhuXing(false)); // 需要传入boolean参数
        // vo.setShiErGongZhuXingMark() 方法不存在，跳过

        // 重要星曜宫位
        vo.setZiWeiXingGongWei(ziWei.getZiWeiXingGongWei());
        vo.setTianFuXingGongWei(ziWei.getTianFuXingGongWei());
        vo.setZiWeiXingGongDiZhi(ziWei.getZiWeiXingGongDiZhi());
        vo.setTianFuXingGongDiZhi(ziWei.getTianFuXingGongDiZhi());

        // 四化星信息
        vo.setHuaLuXingGongWei(ziWei.getHuaLuXingGongWei());
        vo.setHuaQuanXingGongWei(ziWei.getHuaQuanXingGongWei());
        vo.setHuaKeXingGongWei(ziWei.getHuaKeXingGongWei());
        vo.setHuaJiXingGongWei(ziWei.getHuaJiXingGongWei());
        vo.setHuaLuXingGongWeiLueJie(ziWei.getHuaLuXingGongWeiLueJie());
        vo.setHuaQuanXingGongWeiLueJie(ziWei.getHuaQuanXingGongWeiLueJie());
        vo.setHuaKeXingGongWeiLueJie(ziWei.getHuaKeXingGongWeiLueJie());
        vo.setHuaJiXingGongWeiLueJie(ziWei.getHuaJiXingGongWeiLueJie());

        // 年干诸星
        vo.setNianGanZhuXing(ziWei.getNianGanZhuXing());

        // 年支诸星
        vo.setNianZhiZhuXing(ziWei.getNianZhiZhuXing());

        // 时支诸星（实际存在的方法）
        vo.setShiXiZhuXing(ziWei.getShiZhiZhuXing());

        // 小限
        vo.setXiaoXian(ziWei.getXiaoXian());

        // 十二宫博士
        vo.setShiErGongBoShi(ziWei.getShiErGongBoShi());

        // 其他方法不存在，暂时跳过
        // 十二宫四化星
        vo.setShiErGongSiHuaXing(ziWei.getShiErGongSiHuaXing());

        // 流年信息（使用DTO中的流年支）
        vo.setLiuNianZhi(dto.getLiuNianZhi());

        return vo;
    }
}
