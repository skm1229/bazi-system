package com.bazi.service.impl;

import com.bazi.dto.liuyao.LiuYaoDto;
import com.bazi.service.LiuYaoService;
import com.bazi.vo.liuyao.LiuYaoVo;
import com.bazi.common.util.DateParseUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import skm.core.liuyao.LiuYao;
import skm.core.liuyao.settings.LiuYaoJiChuSetting;
import skm.core.liuyao.settings.LiuYaoShenShaSetting;

import java.util.*;
import java.util.Date;

/**
 * 六爻排盘服务实现类
 * 
 * <p>负责处理六爻排盘的核心业务逻辑，包括参数转换、六爻计算和结果封装</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class LiuYaoServiceImpl implements LiuYaoService {

    /**
     * 六爻排盘
     * 
     * @param dto 六爻排盘输入参数
     * @return 六爻排盘结果
     */
    @Override
    public LiuYaoVo paiPan(LiuYaoDto dto) {
        // 1. 参数转换
        LiuYaoJiChuSetting setting = new LiuYaoJiChuSetting();
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

        // 2. 六爻计算
        LiuYao liuYao = new LiuYao(setting);
        
        // 设置神煞（使用默认设置）
        LiuYaoShenShaSetting shenShaSetting = new LiuYaoShenShaSetting();
        liuYao.liuYaoShenShaSetting(shenShaSetting);

        // 3. 结果封装
        LiuYaoVo vo = new LiuYaoVo();

        // 基本信息
        vo.setName(liuYao.getName());
        vo.setSex(liuYao.getSex()); // bazi-util已经返回"男"/"女"字符串
        vo.setZao(liuYao.getZao());
        vo.setOccupy(liuYao.getOccupy());

        // 日期时间信息
        vo.setSolarStr(liuYao.getSolarStr());
        vo.setLunarStr(liuYao.getLunarStr());
        vo.setSolarDate(liuYao.getSolarDate());
        vo.setLunarDate(liuYao.getLunarDate());
        vo.setXingQi(liuYao.getXingQi());
        vo.setJiJie(liuYao.getJiJie());
        vo.setShengXiao(liuYao.getShengXiao());
        vo.setXingZuo(liuYao.getXingZuo());
        vo.setYueXiang(liuYao.getYueXiang());
        vo.setYueJiang(liuYao.getYueJiang());
        vo.setYueJiangShen(liuYao.getYueJiangShen());
        vo.setWuBuYuShi(liuYao.getWuBuYuShi() ? "是" : "否");

        // 八字信息
        vo.setBaZi(Arrays.asList(
                liuYao.getYearGanZhi(),
                liuYao.getMonthGanZhi(),
                liuYao.getDayGanZhi(),
                liuYao.getHourGanZhi()
        ));

        vo.setBaZiWuXing(Arrays.asList(
                liuYao.getYearGanZhiWuXing(),
                liuYao.getMonthGanZhiWuXing(),
                liuYao.getDayGanZhiWuXing(),
                liuYao.getHourGanZhiWuXing()
        ));

        vo.setBaZiKongWang(Arrays.asList(
                liuYao.getYearGanZhiKongWang(),
                liuYao.getMonthGanZhiKongWang(),
                liuYao.getDayGanZhiKongWang(),
                liuYao.getHourGanZhiKongWang()
        ));

        vo.setBaZiNaYin(Arrays.asList(
                liuYao.getYearGanZhiNaYin(),
                liuYao.getMonthGanZhiNaYin(),
                liuYao.getDayGanZhiNaYin(),
                liuYao.getHourGanZhiNaYin()
        ));

        // 天干地支
        vo.setYearGan(liuYao.getYearGan());
        vo.setMonthGan(liuYao.getMonthGan());
        vo.setDayGan(liuYao.getDayGan());
        vo.setHourGan(liuYao.getHourGan());
        vo.setYearZhi(liuYao.getYearZhi());
        vo.setMonthZhi(liuYao.getMonthZhi());
        vo.setDayZhi(liuYao.getDayZhi());
        vo.setHourZhi(liuYao.getHourZhi());
        vo.setYearGanZhi(liuYao.getYearGanZhi());
        vo.setMonthGanZhi(liuYao.getMonthGanZhi());
        vo.setDayGanZhi(liuYao.getDayGanZhi());
        vo.setHourGanZhi(liuYao.getHourGanZhi());

        // 五行信息
        vo.setYearGanWuXing(liuYao.getYearGanWuXing());
        vo.setMonthGanWuXing(liuYao.getMonthGanWuXing());
        vo.setDayGanWuXing(liuYao.getDayGanWuXing());
        vo.setHourGanWuXing(liuYao.getHourGanWuXing());
        vo.setYearZhiWuXing(liuYao.getYearZhiWuXing());
        vo.setMonthZhiWuXing(liuYao.getMonthZhiWuXing());
        vo.setDayZhiWuXing(liuYao.getDayZhiWuXing());
        vo.setHourZhiWuXing(liuYao.getHourZhiWuXing());
        vo.setYearGanZhiWuXing(liuYao.getYearGanZhiWuXing());
        vo.setMonthGanZhiWuXing(liuYao.getMonthGanZhiWuXing());
        vo.setDayGanZhiWuXing(liuYao.getDayGanZhiWuXing());
        vo.setHourGanZhiWuXing(liuYao.getHourGanZhiWuXing());

        // 空亡信息
        vo.setYearGanZhiKongWang(liuYao.getYearGanZhiKongWang());
        vo.setMonthGanZhiKongWang(liuYao.getMonthGanZhiKongWang());
        vo.setDayGanZhiKongWang(liuYao.getDayGanZhiKongWang());
        vo.setHourGanZhiKongWang(liuYao.getHourGanZhiKongWang());

        // 纳音信息
        vo.setYearGanZhiNaYin(liuYao.getYearGanZhiNaYin());
        vo.setMonthGanZhiNaYin(liuYao.getMonthGanZhiNaYin());
        vo.setDayGanZhiNaYin(liuYao.getDayGanZhiNaYin());
        vo.setHourGanZhiNaYin(liuYao.getHourGanZhiNaYin());

        // 卦象信息
        vo.setShangGua(liuYao.getShangGuaName());
        vo.setShangGuaAs(liuYao.getShangGuaAs());
        vo.setXiaGua(liuYao.getXiaGuaName());
        vo.setXiaGuaAs(liuYao.getXiaGuaAs());

        // 本卦信息
        vo.setBenGua(liuYao.getBenGuaName());
        vo.setBenGuaAs(liuYao.getBenGuaAs());
        vo.setBenGuaType(liuYao.getBenGuaType());
        vo.setBenGuaGuaShen(liuYao.getBenGuaGuaShen());
        vo.setBenGuaGuaCi(liuYao.getBenGuaGuaCi());
        vo.setBenGuaLiuYaoName(liuYao.getBenGuaLiuYaoName());
        vo.setBenGuaLiuYaoAs(liuYao.getBenGuaLiuYaoAs());
        vo.setBenGuaLiuYaoShiYing(liuYao.getBenGuaLiuYaoShiYing());
        vo.setBenGuaLiuYaoLiuQin(liuYao.getBenGuaLiuYaoLiuQin());
        vo.setBenGuaLiuYaoGanZhi(liuYao.getBenGuaLiuYaoGanZhi());
        vo.setBenGuaLiuYaoWuXing(liuYao.getBenGuaLiuYaoWuXing());
        vo.setBenGuaLiuYaoLiuShen(liuYao.getBenGuaLiuYaoLiuShen());
        vo.setBenGuaLiuYaoYaoCi(liuYao.getBenGuaLiuYaoYaoCi());
        vo.setBenGuaLiuYaoGanZhiNaYin(liuYao.getBenGuaLiuYaoNaYin());

        return vo;
    }
}
