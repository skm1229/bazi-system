package com.bazi.service.impl;

import com.bazi.dto.meihua.MeiHuaDto;
import com.bazi.service.MeiHuaService;
import com.bazi.vo.meihua.MeiHuaVo;
import com.bazi.common.util.DateParseUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import skm.core.meihua.MeiHua;
import skm.core.meihua.settings.MeiHuaJiChuSetting;

import java.util.*;

/**
 * 梅花易数排盘服务实现类
 * 
 * <p>负责处理梅花易数排盘的核心业务逻辑，包括参数转换、梅花计算和结果封装</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class MeiHuaServiceImpl implements MeiHuaService {

    /**
     * 梅花易数排盘
     *
     * @param dto 梅花易数排盘输入参数
     * @return 梅花易数排盘结果
     */
    @Override
    public MeiHuaVo paiPan(MeiHuaDto dto) {
        // 1. 参数转换
        MeiHuaJiChuSetting setting = new MeiHuaJiChuSetting();
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

        // 2. 梅花易数计算
        MeiHua meiHua = new MeiHua(setting);

        // 3. 结果封装
        MeiHuaVo vo = new MeiHuaVo();

        // 基本信息
        vo.setName(meiHua.getName());
        vo.setSex(meiHua.getSex()); // bazi-util已经返回"男"/"女"字符串
        vo.setZao(meiHua.getZao());
        vo.setOccupy(meiHua.getOccupy());

        // 日期时间信息
        vo.setSolarStr(meiHua.getSolarStr());
        vo.setLunarStr(meiHua.getLunarStr());
        vo.setXingQi(meiHua.getXingQi());
        vo.setJiJie(meiHua.getJiJie());
        vo.setShengXiao(meiHua.getShengXiao());
        vo.setXingZuo(meiHua.getXingZuo());
        vo.setYueXiang(meiHua.getYueXiang());
        vo.setYueJiang(meiHua.getYueJiang());
        vo.setYueJiangShen(meiHua.getYueJiangShen());
        vo.setWuBuYuShi(meiHua.getWuBuYuShi() ? "是" : "否");

        // 八字信息
        vo.setBaZi(Arrays.asList(
                meiHua.getYearGanZhi(),
                meiHua.getMonthGanZhi(),
                meiHua.getDayGanZhi(),
                meiHua.getHourGanZhi()
        ));

        vo.setBaZiWuXing(Arrays.asList(
                meiHua.getYearGanZhiWuXing(),
                meiHua.getMonthGanZhiWuXing(),
                meiHua.getDayGanZhiWuXing(),
                meiHua.getHourGanZhiWuXing()
        ));

        vo.setBaZiKongWang(Arrays.asList(
                meiHua.getYearGanZhiKongWang(),
                meiHua.getMonthGanZhiKongWang(),
                meiHua.getDayGanZhiKongWang(),
                meiHua.getHourGanZhiKongWang()
        ));

        vo.setBaZiNaYin(Arrays.asList(
                meiHua.getYearGanZhiNaYin(),
                meiHua.getMonthGanZhiNaYin(),
                meiHua.getDayGanZhiNaYin(),
                meiHua.getHourGanZhiNaYin()
        ));

        // 天干地支
        vo.setYearGan(meiHua.getYearGan());
        vo.setMonthGan(meiHua.getMonthGan());
        vo.setDayGan(meiHua.getDayGan());
        vo.setHourGan(meiHua.getHourGan());
        vo.setYearZhi(meiHua.getYearZhi());
        vo.setMonthZhi(meiHua.getMonthZhi());
        vo.setDayZhi(meiHua.getDayZhi());
        vo.setHourZhi(meiHua.getHourZhi());
        vo.setYearGanZhi(meiHua.getYearGanZhi());
        vo.setMonthGanZhi(meiHua.getMonthGanZhi());
        vo.setDayGanZhi(meiHua.getDayGanZhi());
        vo.setHourGanZhi(meiHua.getHourGanZhi());

        // 五行信息
        vo.setYearGanWuXing(meiHua.getYearGanWuXing());
        vo.setMonthGanWuXing(meiHua.getMonthGanWuXing());
        vo.setDayGanWuXing(meiHua.getDayGanWuXing());
        vo.setHourGanWuXing(meiHua.getHourGanWuXing());
        vo.setYearZhiWuXing(meiHua.getYearZhiWuXing());
        vo.setMonthZhiWuXing(meiHua.getMonthZhiWuXing());
        vo.setDayZhiWuXing(meiHua.getDayZhiWuXing());
        vo.setHourZhiWuXing(meiHua.getHourZhiWuXing());
        vo.setYearGanZhiWuXing(meiHua.getYearGanZhiWuXing());
        vo.setMonthGanZhiWuXing(meiHua.getMonthGanZhiWuXing());
        vo.setDayGanZhiWuXing(meiHua.getDayGanZhiWuXing());
        vo.setHourGanZhiWuXing(meiHua.getHourGanZhiWuXing());

        // 空亡信息
        vo.setYearGanZhiKongWang(meiHua.getYearGanZhiKongWang());
        vo.setMonthGanZhiKongWang(meiHua.getMonthGanZhiKongWang());
        vo.setDayGanZhiKongWang(meiHua.getDayGanZhiKongWang());
        vo.setHourGanZhiKongWang(meiHua.getHourGanZhiKongWang());

        // 纳音信息
        vo.setYearGanZhiNaYin(meiHua.getYearGanZhiNaYin());
        vo.setMonthGanZhiNaYin(meiHua.getMonthGanZhiNaYin());
        vo.setDayGanZhiNaYin(meiHua.getDayGanZhiNaYin());
        vo.setHourGanZhiNaYin(meiHua.getHourGanZhiNaYin());

        // 梅花易数核心信息
        vo.setShangGuaNumber(dto.getPaiPanShu());
        vo.setXiaGuaNumber(dto.getPaiPanShu());
        vo.setDongYaoNumber(dto.getPaiPanShu());
        vo.setDongYao(meiHua.getDongYao() != null ? meiHua.getDongYao().toString() : "");

        // 卦象信息
        vo.setShangGua(meiHua.getShangGuaName());
        vo.setShangGuaAs(meiHua.getShangGuaAs());
        vo.setXiaGua(meiHua.getXiaGuaName());
        vo.setXiaGuaAs(meiHua.getXiaGuaAs());

        // 本卦信息
        vo.setBenGua(meiHua.getBenGuaName());
        vo.setBenGuaAs(meiHua.getBenGuaAs());
        // vo.setBenGuaType() 方法不存在，跳过
        vo.setBenGuaGuaCi(meiHua.getBenGuaGuaCi());
        vo.setBenGuaLiuYaoName(meiHua.getBenGuaLiuYaoName());
        vo.setBenGuaLiuYaoAs(meiHua.getBenGuaLiuYaoAs());
        vo.setBenGuaLiuYaoYaoCi(meiHua.getBenGuaLiuYaoYaoCi());
        // vo.setBenGuaLink() 方法不存在，跳过

        // 变卦信息
        vo.setBianGua(meiHua.getBianGuaName());
        vo.setBianGuaAs(meiHua.getBianGuaAs());
        // vo.setBianGuaType() 方法不存在，跳过
        vo.setBianGuaGuaCi(meiHua.getBianGuaGuaCi());
        vo.setBianGuaLiuYaoName(meiHua.getBianGuaLiuYaoName());
        vo.setBianGuaLiuYaoAs(meiHua.getBianGuaLiuYaoAs());
        vo.setBianGuaLiuYaoYaoCi(meiHua.getBianGuaLiuYaoYaoCi());
        // vo.setBianGuaLink() 方法不存在，跳过

        // 互卦信息
        vo.setHuGua(meiHua.getHuGuaName());
        vo.setHuGuaAs(meiHua.getHuGuaAs());
        // vo.setHuGuaType() 方法不存在，跳过
        vo.setHuGuaGuaCi(meiHua.getHuGuaGuaCi());
        vo.setHuGuaLiuYaoName(meiHua.getHuGuaLiuYaoName());
        vo.setHuGuaLiuYaoAs(meiHua.getHuGuaLiuYaoAs());
        vo.setHuGuaLiuYaoYaoCi(meiHua.getHuGuaLiuYaoYaoCi());
        // vo.setHuGuaLink() 方法不存在，跳过

        // 错卦信息
        vo.setCuoGua(meiHua.getCuoGuaName());
        vo.setCuoGuaAs(meiHua.getCuoGuaAs());
        // vo.setCuoGuaType() 方法不存在，跳过
        vo.setCuoGuaGuaCi(meiHua.getCuoGuaGuaCi());
        vo.setCuoGuaLiuYaoName(meiHua.getCuoGuaLiuYaoName());
        vo.setCuoGuaLiuYaoAs(meiHua.getCuoGuaLiuYaoAs());
        vo.setCuoGuaLiuYaoYaoCi(meiHua.getCuoGuaLiuYaoYaoCi());
        // vo.setCuoGuaLink() 方法不存在，跳过

        // 综卦信息
        vo.setZongGua(meiHua.getZongGuaName());
        vo.setZongGuaAs(meiHua.getZongGuaAs());
        // vo.setZongGuaType() 方法不存在，跳过
        vo.setZongGuaGuaCi(meiHua.getZongGuaGuaCi());
        vo.setZongGuaLiuYaoName(meiHua.getZongGuaLiuYaoName());
        vo.setZongGuaLiuYaoAs(meiHua.getZongGuaLiuYaoAs());
        vo.setZongGuaLiuYaoYaoCi(meiHua.getZongGuaLiuYaoYaoCi());
        // vo.setZongGuaLink() 方法不存在，跳过

        // 卦码
        vo.setGuaMa(meiHua.getGuaMa());

        return vo;
    }
}
