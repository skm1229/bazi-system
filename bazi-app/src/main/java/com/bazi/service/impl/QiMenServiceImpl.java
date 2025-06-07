package com.bazi.service.impl;

import com.bazi.dto.qimen.QiMenDto;
import com.bazi.service.QiMenService;
import com.bazi.vo.qimen.QiMenVo;
import com.bazi.common.util.DateParseUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import skm.core.qimen.QiMenZhuanPan;
import skm.core.qimen.settings.QiMenZhuanPanJiChuSetting;

import java.util.*;

/**
 * 奇门遁甲排盘服务实现类
 * 
 * <p>负责处理奇门遁甲排盘的核心业务逻辑，包括参数转换、奇门计算和结果封装</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class QiMenServiceImpl implements QiMenService {

    /**
     * 奇门遁甲排盘
     * 
     * @param dto 奇门遁甲排盘输入参数
     * @return 奇门遁甲排盘结果
     */
    @Override
    public QiMenVo paiPan(QiMenDto dto) {
        // 1. 参数转换
        QiMenZhuanPanJiChuSetting setting = new QiMenZhuanPanJiChuSetting();
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

        // 2. 奇门遁甲计算
        QiMenZhuanPan qiMen = new QiMenZhuanPan(setting);

        // 3. 结果封装
        QiMenVo vo = new QiMenVo();

        // 基本信息
        vo.setName(qiMen.getName());
        vo.setSex(qiMen.getSex()); // bazi-util已经返回"男"/"女"字符串
        vo.setZao(qiMen.getZao());
        vo.setOccupy(qiMen.getOccupy());

        // 日期时间信息
        vo.setSolarStr(qiMen.getSolarStr());
        vo.setLunarStr(qiMen.getLunarStr());
        vo.setXingQi(qiMen.getXingQi());
        vo.setJiJie(qiMen.getJiJie());
        vo.setShengXiao(qiMen.getShengXiao());
        vo.setXingZuo(qiMen.getXingZuo());
        vo.setYueXiang(qiMen.getYueXiang());
        vo.setYueJiang(qiMen.getYueJiang());
        vo.setYueJiangShen(qiMen.getYueJiangShen());
        vo.setWuBuYuShi(qiMen.getWuBuYuShi() ? "是" : "否");

        // 八字信息
        vo.setBaZi(Arrays.asList(
                qiMen.getYearGanZhi(),
                qiMen.getMonthGanZhi(),
                qiMen.getDayGanZhi(),
                qiMen.getHourGanZhi()
        ));

        vo.setBaZiWuXing(Arrays.asList(
                qiMen.getYearGanZhiWuXing(),
                qiMen.getMonthGanZhiWuXing(),
                qiMen.getDayGanZhiWuXing(),
                qiMen.getHourGanZhiWuXing()
        ));

        vo.setBaZiKongWang(Arrays.asList(
                qiMen.getYearGanZhiKongWang(),
                qiMen.getMonthGanZhiKongWang(),
                qiMen.getDayGanZhiKongWang(),
                qiMen.getHourGanZhiKongWang()
        ));

        vo.setBaZiNaYin(Arrays.asList(
                qiMen.getYearGanZhiNaYin(),
                qiMen.getMonthGanZhiNaYin(),
                qiMen.getDayGanZhiNaYin(),
                qiMen.getHourGanZhiNaYin()
        ));

        // 天干地支
        vo.setYearGan(qiMen.getYearGan());
        vo.setMonthGan(qiMen.getMonthGan());
        vo.setDayGan(qiMen.getDayGan());
        vo.setHourGan(qiMen.getHourGan());
        vo.setYearZhi(qiMen.getYearZhi());
        vo.setMonthZhi(qiMen.getMonthZhi());
        vo.setDayZhi(qiMen.getDayZhi());
        vo.setHourZhi(qiMen.getHourZhi());
        vo.setYearGanZhi(qiMen.getYearGanZhi());
        vo.setMonthGanZhi(qiMen.getMonthGanZhi());
        vo.setDayGanZhi(qiMen.getDayGanZhi());
        vo.setHourGanZhi(qiMen.getHourGanZhi());

        // 五行信息
        vo.setYearGanWuXing(qiMen.getYearGanWuXing());
        vo.setMonthGanWuXing(qiMen.getMonthGanWuXing());
        vo.setDayGanWuXing(qiMen.getDayGanWuXing());
        vo.setHourGanWuXing(qiMen.getHourGanWuXing());
        vo.setYearZhiWuXing(qiMen.getYearZhiWuXing());
        vo.setMonthZhiWuXing(qiMen.getMonthZhiWuXing());
        vo.setDayZhiWuXing(qiMen.getDayZhiWuXing());
        vo.setHourZhiWuXing(qiMen.getHourZhiWuXing());
        vo.setYearGanZhiWuXing(qiMen.getYearGanZhiWuXing());
        vo.setMonthGanZhiWuXing(qiMen.getMonthGanZhiWuXing());
        vo.setDayGanZhiWuXing(qiMen.getDayGanZhiWuXing());
        vo.setHourGanZhiWuXing(qiMen.getHourGanZhiWuXing());

        // 空亡信息
        vo.setYearGanZhiKongWang(qiMen.getYearGanZhiKongWang());
        vo.setMonthGanZhiKongWang(qiMen.getMonthGanZhiKongWang());
        vo.setDayGanZhiKongWang(qiMen.getDayGanZhiKongWang());
        vo.setHourGanZhiKongWang(qiMen.getHourGanZhiKongWang());

        // 纳音信息
        vo.setYearGanZhiNaYin(qiMen.getYearGanZhiNaYin());
        vo.setMonthGanZhiNaYin(qiMen.getMonthGanZhiNaYin());
        vo.setDayGanZhiNaYin(qiMen.getDayGanZhiNaYin());
        vo.setHourGanZhiNaYin(qiMen.getHourGanZhiNaYin());

        // 奇门遁甲核心信息
        vo.setFuTou(qiMen.getFuTou());
        vo.setJieQi(qiMen.getJieQi());
        vo.setSanYuan(qiMen.getSanYuan());
        vo.setJuShu(qiMen.getJuShu());
        vo.setYinYangDun(qiMen.getYinYangDun());
        vo.setXunShou(qiMen.getXunShou());
        vo.setXunShouYiZhang(qiMen.getXunShouYiZhang());
        vo.setZhiFu(qiMen.getZhiFu());
        vo.setZhiShi(qiMen.getZhiShi());

        // 天地太乙
        vo.setTianYi(qiMen.getTianYi());
        vo.setDiYi(qiMen.getDiYi());
        vo.setTaiYi(qiMen.getTaiYi());

        // 九宫信息
        vo.setDiPan(qiMen.getDiPan());
        vo.setTianPan(qiMen.getTianPan());
        vo.setRenPan(qiMen.getRenPan());
        vo.setShenPan(qiMen.getShenPan());

        // 伏吟反吟
        vo.setFuYin(qiMen.getFuYin());
        vo.setFanYin(qiMen.getFanYin());

        // 击刑入墓
        vo.setLiuYiJiXing(qiMen.getLiuYiJiXing());
        vo.setQiYiRuMu(qiMen.getQiYiRuMu());

        // 门迫
        vo.setMenPoLink(qiMen.getMenPoLink());

        // 九遁
        vo.setJiuDun(qiMen.getJiuDun());

        return vo;
    }
}
