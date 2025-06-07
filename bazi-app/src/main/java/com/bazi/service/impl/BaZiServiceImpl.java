package com.bazi.service.impl;

import com.bazi.dto.bazi.*;
import com.bazi.service.BaZiService;
import com.bazi.common.util.DateParseUtil;
import com.bazi.common.exception.BusinessException;
import com.bazi.vo.bazi.*;

import com.bazi.vo.bazi.common.TimeDataVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import skm.core.bazi.BaZi;
import skm.core.bazi.settings.BaZiGanZhiLiuYiSetting;
import skm.core.bazi.settings.BaZiJiChuSetting;
import skm.core.bazi.maps.BaZiJiChuMap;
import skm.core.bazi.settings.BaZiShenShaSetting;
import skm.core.bazi.utils.BaZiDaYunLiuNianUtil;
import skm.core.bazi.utils.BaZiGanZhiLiuYiUtil;
import skm.core.bazi.utils.BaZiShenShaUtil;
import skm.utils.CommonUtil;
import skm.utils.DateUtil;

import java.util.*;

/**
 * 八字排盘服务实现类
 *
 * <p>负责处理八字排盘的核心业务逻辑，包括参数转换、八字计算和结果封装</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class BaZiServiceImpl implements BaZiService {

    Map<String, String> shiShenMap = BaZiJiChuMap.SHI_SHEN; // 十神（日干+其他干支为键）
    Map<String, List<String>> diZhiCangGanMap = BaZiJiChuMap.DI_ZHI_CANG_GAN; // 地支藏干（地支为键）
    Map<String, String> shiErZhangShengMap = BaZiJiChuMap.SHI_ER_ZHANG_SHENG; // 十二长生（日干+地支为键）
    Map<String, String> kongWangMap = BaZiJiChuMap.KONG_WANG; // 空亡（干支为键）
    Map<String, String> naYinMap = BaZiJiChuMap.NA_YIN; // 纳音（干支为键）


    /**
     * 八字排盘
     *
     * @param dto 八字排盘输入参数
     * @return 八字排盘结果
     */
    @Override
    public BaZiVo paiPan(BaZiDto dto) {
        // 1. 参数转换
        BaZiJiChuSetting setting = new BaZiJiChuSetting();
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

        // 2. 八字计算
        BaZi baZi = new BaZi(setting);

        // 3. 结果封装
        BaZiVo vo = new BaZiVo();

        // 基本信息
        vo.setName(baZi.getName());
        vo.setSex(baZi.getSex()); // bazi-util已经返回"男"/"女"字符串
        vo.setOccupy(baZi.getOccupy());
        vo.setAddress(dto.getAddress());

        // 日期时间信息
        vo.setSolarStr(baZi.getSolarStr());
        vo.setLunarStr(baZi.getLunarStr());
        vo.setSolarDate(baZi.getSolarDate());
        vo.setLunarDate(baZi.getLunarDate());
        vo.setXingQi(baZi.getXingQi());
        vo.setJiJie(baZi.getJiJie());
        vo.setShengXiao(baZi.getShengXiao());
        vo.setXingZuo(baZi.getXingZuo());
        vo.setYueXiang(baZi.getYueXiang());
        vo.setYueJiang(baZi.getYueJiang());
        vo.setYueJiangShen(baZi.getYueJiangShen());
        vo.setWuBuYuShi(baZi.getWuBuYuShi() ? "是" : "否");

        // 九星五行信息
        vo.setYearJiuXingWuXing(baZi.getYearJiuXingWuXing());
        vo.setMonthJiuXingWuXing(baZi.getMonthJiuXingWuXing());
        vo.setDayJiuXingWuXing(baZi.getDayJiuXingWuXing());
        vo.setHourJiuXingWuXing(baZi.getHourJiuXingWuXing());

        // 九星方位信息
        vo.setYearJiuXingFangWei(baZi.getYearJiuXingFangWei());
        vo.setMonthJiuXingFangWei(baZi.getMonthJiuXingFangWei());
        vo.setDayJiuXingFangWei(baZi.getDayJiuXingFangWei());
        vo.setHourJiuXingFangWei(baZi.getHourJiuXingFangWei());

        // 九星方位描述
        vo.setYearJiuXingFangWeiMiaoShu(baZi.getYearJiuXingFangWeiMiaoShu());
        vo.setMonthJiuXingFangWeiMiaoShu(baZi.getMonthJiuXingFangWeiMiaoShu());
        vo.setDayJiuXingFangWeiMiaoShu(baZi.getDayJiuXingFangWeiMiaoShu());
        vo.setHourJiuXingFangWeiMiaoShu(baZi.getHourJiuXingFangWeiMiaoShu());

        // 冲生肖信息
        vo.setYearChongShengXiao(baZi.getYearChongShengXiao());
        vo.setMonthChongShengXiao(baZi.getMonthChongShengXiao());
        vo.setDayChongShengXiao(baZi.getDayChongShengXiao());
        vo.setHourChongShengXiao(baZi.getHourChongShengXiao());

        // 太岁信息
        vo.setTaiSuiType(baZi.getTaiSuiType());

        // 星宿信息
        vo.setXingXiu(baZi.getXingXiu());
        vo.setXiuJiXiong(baZi.getXingXiuJiXiong());
        vo.setXiuJiXiongGeJue(baZi.getXingXiuJiXiongGeJue());

        // 彭祖百忌
        vo.setPengZuBaiJi(baZi.getPengZuBaiJi());

        // 四柱干支
        vo.setYearGanZhi(baZi.getYearGanZhi());
        vo.setMonthGanZhi(baZi.getMonthGanZhi());
        vo.setDayGanZhi(baZi.getDayGanZhi());
        vo.setHourGanZhi(baZi.getHourGanZhi());

        // 天干地支
        vo.setYearGan(baZi.getYearGan());
        vo.setMonthGan(baZi.getMonthGan());
        vo.setDayGan(baZi.getDayGan());
        vo.setHourGan(baZi.getHourGan());
        vo.setYearZhi(baZi.getYearZhi());
        vo.setMonthZhi(baZi.getMonthZhi());
        vo.setDayZhi(baZi.getDayZhi());
        vo.setHourZhi(baZi.getHourZhi());

        // 天干十神关系
        vo.setYearGanZhiZhuXing(baZi.getYearGanZhiZhuXing());
        vo.setMonthGanZhiZhuXing(baZi.getMonthGanZhiZhuXing());
        vo.setDayGanZhiZhuXing(baZi.getDayGanZhiZhuXing());
        vo.setHourGanZhiZhuXing(baZi.getHourGanZhiZhuXing());

        // 五行信息
        vo.setYearGanWuXing(baZi.getYearGanWuXing());
        vo.setMonthGanWuXing(baZi.getMonthGanWuXing());
        vo.setDayGanWuXing(baZi.getDayGanWuXing());
        vo.setHourGanWuXing(baZi.getHourGanWuXing());
        vo.setYearZhiWuXing(baZi.getYearZhiWuXing());
        vo.setMonthZhiWuXing(baZi.getMonthZhiWuXing());
        vo.setDayZhiWuXing(baZi.getDayZhiWuXing());
        vo.setHourZhiWuXing(baZi.getHourZhiWuXing());
        vo.setYearGanZhiWuXing(baZi.getYearGanZhiWuXing());
        vo.setMonthGanZhiWuXing(baZi.getMonthGanZhiWuXing());
        vo.setDayGanZhiWuXing(baZi.getDayGanZhiWuXing());
        vo.setHourGanZhiWuXing(baZi.getHourGanZhiWuXing());

        // 地支藏干
        vo.setYearZhiCangGan(baZi.getYearZhiCangGan());
        vo.setMonthZhiCangGan(baZi.getMonthZhiCangGan());
        vo.setDayZhiCangGan(baZi.getDayZhiCangGan());
        vo.setHourZhiCangGan(baZi.getHourZhiCangGan());

        // 副星信息
        vo.setYearGanZhiFuXing(baZi.getYearGanZhiFuXing());
        vo.setMonthGanZhiFuXing(baZi.getMonthGanZhiFuXing());
        vo.setDayGanZhiFuXing(baZi.getDayGanZhiFuXing());
        vo.setHourGanZhiFuXing(baZi.getHourGanZhiFuXing());

        // 自坐信息
        vo.setYearGanZhiZiZuo(baZi.getYearGanZhiZiZuo());
        vo.setMonthGanZhiZiZuo(baZi.getMonthGanZhiZiZuo());
        vo.setDayGanZhiZiZuo(baZi.getDayGanZhiZiZuo());
        vo.setHourGanZhiZiZuo(baZi.getHourGanZhiZiZuo());

        // 星运信息
        vo.setYearGanZhiXingYun(baZi.getYearGanZhiXingYun());
        vo.setMonthGanZhiXingYun(baZi.getMonthGanZhiXingYun());
        vo.setDayGanZhiXingYun(baZi.getDayGanZhiXingYun());
        vo.setHourGanZhiXingYun(baZi.getHourGanZhiXingYun());

        // 纳音信息
        vo.setYearGanZhiNaYin(baZi.getYearGanZhiNaYin());
        vo.setMonthGanZhiNaYin(baZi.getMonthGanZhiNaYin());
        vo.setDayGanZhiNaYin(baZi.getDayGanZhiNaYin());
        vo.setHourGanZhiNaYin(baZi.getHourGanZhiNaYin());

        // 空亡信息
        vo.setYearGanZhiKongWang(baZi.getYearGanZhiKongWang());
        vo.setMonthGanZhiKongWang(baZi.getMonthGanZhiKongWang());
        vo.setDayGanZhiKongWang(baZi.getDayGanZhiKongWang());
        vo.setHourGanZhiKongWang(baZi.getHourGanZhiKongWang());

        // 天干地支留意
        vo.setTianGanLiuYi(baZi.getTianGanLiuYi());
        vo.setDiZhiLiuYi(baZi.getDiZhiLiuYi());

        // 人元司令分野
        vo.setRenYuan(baZi.getRenYuanSiLingFenYe());

        // 节气信息
        vo.setBirthSolarTerms(baZi.getChuShengJie());
        vo.setPrevJie(baZi.getPrevJie());
        vo.setPrevJieDateStr(baZi.getPrevJieDateStr());
        vo.setPrevJieDayNumber(baZi.getPrevJieDay());
        vo.setNextJie(baZi.getNextJie());
        vo.setNextJieDateStr(baZi.getNextJieDateStr());
        vo.setNextJieDayNumber(baZi.getNextJieDay());

        // 气信息
        vo.setBirthQi(baZi.getChuShengQi());
        vo.setPrevQi(baZi.getPrevQi());
        vo.setPrevQiDateStr(baZi.getPrevQiDateStr());
        vo.setPrevQiDayNumber(baZi.getPrevQiDay());
        vo.setNextQi(baZi.getNextQi());
        vo.setNextQiDateStr(baZi.getNextQiDateStr());
        vo.setNextQiDayNumber(baZi.getNextQiDay());

        // 早晚子时
        vo.setZao(baZi.getZao());

        // 胎命身信息
        vo.setTaiYuan(baZi.getTaiYuan(true));
        vo.setTaiXi(baZi.getTaiXi(true));
        vo.setMingGong(baZi.getMingGong(true));
        vo.setShenGong(baZi.getShenGong(true));

        // 命卦信息
        vo.setMingGua(baZi.getMingGua());
        vo.setMingGuaFenXi(baZi.getMingGuaFenXi());

        // 骨重信息
        List<String> guZhongInfo = baZi.getGuZhong();
        if (guZhongInfo != null && guZhongInfo.size() >= 2) {
            vo.setGuZhong(guZhongInfo.get(0));
            vo.setGuZhongGeJue(guZhongInfo.get(1));
        }

        // 格局判断
        vo.setGeJu(baZi.getGeJu());

        // 日柱论命
        vo.setDayZhuLunMing(baZi.getDayZhuLunMing());

        // 姻缘分析
        vo.setYinYuanFenXi(baZi.getYinYuanFenXi());

        // 五行分析
        vo.setWuXingFenXi(baZi.getWuXingFenXi());

        // 五行分析
        vo.setWuXingQueShi(baZi.getWuXingQueShi());
        vo.setWuXingWangShuai(baZi.getWuXingWangShuai());
        vo.setShenTiQiangRuo(baZi.getShenTiQiangRuo());
        vo.setXiYongShen(baZi.getXiYongShen());
        vo.setXiYongShenFangWei(baZi.getXiYongShenFangWei());

        // 起运信息
        vo.setQiYun(baZi.getSolarQiYun());
        vo.setQiYunDateStr(baZi.getSolarQiYunDateStr());
        vo.setJiaoYun(baZi.getSolarJiaoYun());

        // 八字列表信息
        vo.setBaZi(Arrays.asList(
                baZi.getYearGanZhi(),
                baZi.getMonthGanZhi(),
                baZi.getDayGanZhi(),
                baZi.getHourGanZhi()
        ));

        vo.setBaZiWuXing(Arrays.asList(
                baZi.getYearGanZhiWuXing(),
                baZi.getMonthGanZhiWuXing(),
                baZi.getDayGanZhiWuXing(),
                baZi.getHourGanZhiWuXing()
        ));

        vo.setBaZiNaYin(Arrays.asList(
                baZi.getYearGanZhiNaYin(),
                baZi.getMonthGanZhiNaYin(),
                baZi.getDayGanZhiNaYin(),
                baZi.getHourGanZhiNaYin()
        ));

        vo.setBaZiKongWang(Arrays.asList(
                baZi.getYearGanZhiKongWang(),
                baZi.getMonthGanZhiKongWang(),
                baZi.getDayGanZhiKongWang(),
                baZi.getHourGanZhiKongWang()
        ));

        // 五行统计
        vo.setWuXingCount(Arrays.asList(
                "木:" + baZi.getWuXingMuCount(false),
                "火:" + baZi.getWuXingHuoCount(false),
                "土:" + baZi.getWuXingTuCount(false),
                "金:" + baZi.getWuXingJinCount(false),
                "水:" + baZi.getWuXingShuiCount(false)
        ));
        setWuXingCounts(baZi, vo);

        // 神煞信息
        vo.setYearGanZhiShenSha(baZi.getYearGanZhiShenSha());
        vo.setMonthGanZhiShenSha(baZi.getMonthGanZhiShenSha());
        vo.setDayGanZhiShenSha(baZi.getDayGanZhiShenSha());
        vo.setHourGanZhiShenSha(baZi.getHourGanZhiShenSha());

        // 小儿关煞
        vo.setXiaoErGuanSha(null);

        // 修复太岁类型
        String taiSuiType = baZi.getTaiSuiType();
        vo.setTaiSuiType(taiSuiType != null ? taiSuiType : "顺");

        // 设置未实现的字段为null
        vo.setDaYun(null);
        vo.setLiuNian(null);
        vo.setZhengCaiYun(null);
        vo.setPianCaiYun(null);
        vo.setZhengTaoHua(null);
        vo.setPianTaoHua(null);

        return vo;
    }


    /**
     * 设置五行统计信息
     *
     * @param baZi 八字对象
     * @param vo   结果VO对象
     */
    private void setWuXingCounts(BaZi baZi, BaZiVo vo) {
        // 获取各类五行数量
        int totalMu = baZi.getWuXingMuCount(false);
        int totalHuo = baZi.getWuXingHuoCount(false);
        int totalTu = baZi.getWuXingTuCount(false);
        int totalJin = baZi.getWuXingJinCount(false);
        int totalShui = baZi.getWuXingShuiCount(false);

        int totalWithCangGanMu = baZi.getWuXingMuCount(true);
        int totalWithCangGanHuo = baZi.getWuXingHuoCount(true);
        int totalWithCangGanTu = baZi.getWuXingTuCount(true);
        int totalWithCangGanJin = baZi.getWuXingJinCount(true);
        int totalWithCangGanShui = baZi.getWuXingShuiCount(true);

        // 设置天干五行数量
        vo.setGanMuCount(countGanWuXing(baZi, "木"));
        vo.setGanHuoCount(countGanWuXing(baZi, "火"));
        vo.setGanTuCount(countGanWuXing(baZi, "土"));
        vo.setGanJinCount(countGanWuXing(baZi, "金"));
        vo.setGanShuiCount(countGanWuXing(baZi, "水"));

        // 设置地支五行数量
        vo.setZhiMuCount(totalMu - vo.getGanMuCount());
        vo.setZhiHuoCount(totalHuo - vo.getGanHuoCount());
        vo.setZhiTuCount(totalTu - vo.getGanTuCount());
        vo.setZhiJinCount(totalJin - vo.getGanJinCount());
        vo.setZhiShuiCount(totalShui - vo.getGanShuiCount());

        // 设置藏干五行数量
        vo.setCangGanMuCount(totalWithCangGanMu - totalMu);
        vo.setCangGanHuoCount(totalWithCangGanHuo - totalHuo);
        vo.setCangGanTuCount(totalWithCangGanTu - totalTu);
        vo.setCangGanJinCount(totalWithCangGanJin - totalJin);
        vo.setCangGanShuiCount(totalWithCangGanShui - totalShui);
    }

    /**
     * 统计天干五行数量
     *
     * @param baZi   八字对象
     * @param wuXing 五行类型
     * @return 数量
     */
    private int countGanWuXing(BaZi baZi, String wuXing) {
        String[] ganArray = {baZi.getYearGan(), baZi.getMonthGan(), baZi.getDayGan(), baZi.getHourGan()};
        return (int) Arrays.stream(ganArray)
                .filter(gan -> wuXing.equals(BaZiJiChuMap.TIAN_GAN_WU_XING.get(gan)))
                .count();
    }


    /**
     * 大运初始化 - 获取大运、流年、流月信息
     *
     * @param dto 大运初始化参数
     * @return 大运初始化结果
     */
    @Override
    public DaYunInitializeVo daYunInit(DaYunInitializeDto dto) {
        // 1、处理数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支


        // 2.1、计算大运数据、流年数据、小运数据、流月数据
        BaZiDaYunLiuNianUtil dyUtil = new BaZiDaYunLiuNianUtil(dto.getDayGan(), dto.getSex(), dto.getQiYunLiuPai(), dto.getSolarDate());
        dyUtil.daYun();// 计算大运数据
        dyUtil.liuNian(1); // 计算流年数据
        dyUtil.xiaoYun(1); // 计算小运数据
        dyUtil.liuYue(1, 1, dto.getSolarDate().getYear() + 1900); // 计算流月数据
        List<List<String>> daYun = dyUtil.getDaYun();// 获取大运数据
        List<List<String>> liuNian = dyUtil.getLiuNian(); // 获取流年数据
        List<List<String>> xiaoYun = dyUtil.getXiaoYun(); // 获取小运数据
        List<List<String>> liuYue = dyUtil.getLiuYue(); // 获取流月数据
        // 2.2、数据封装
        List<TimeDataVo> daYunPackage = TimeDataVo.daYunEncapsulation(daYun); // 大运封装
        List<TimeDataVo> liuNianPackage = TimeDataVo.liuNianEncapsulation(liuNian); // 流年封装
        List<TimeDataVo> xiaoYunPackage = TimeDataVo.xiaoYunEncapsulation(xiaoYun); // 小运封装
        List<TimeDataVo> liuYuePackage = TimeDataVo.liuYueEncapsulation(liuYue); // 流月封装


        // 3、处理大运干支、流年干支
        String daYunGanZhi = ""; // 大运干支
        String daYunGan = "";  // 大运干
        String daYunZhi = "";  // 大运支
        String liuNianGanZhi = ""; // 流年干支
        String liuNianGan = "";  // 流年干
        String liuNianZhi = "";  // 流年支
        if (xiaoYun.size() != 0) {
            daYunGanZhi = xiaoYun.get(0).get(0);
        } else {
            daYunGanZhi = daYun.get(1).get(0);
        }
        if (daYun.size() != 0) {
            liuNianGanZhi = liuNian.get(0).get(0);
        }
        if (StringUtils.isNotBlank(daYunGanZhi) && daYunGanZhi.length() >= 1) daYunGan = daYunGanZhi.substring(0, 1);
        if (StringUtils.isNotBlank(daYunGanZhi) && daYunGanZhi.length() >= 2) daYunZhi = daYunGanZhi.substring(1, 2);
        if (StringUtils.isNotBlank(liuNianGanZhi) && liuNianGanZhi.length() >= 1) liuNianGan = liuNianGanZhi.substring(0, 1);
        if (StringUtils.isNotBlank(liuNianGanZhi) && liuNianGanZhi.length() >= 2) liuNianZhi = liuNianGanZhi.substring(1, 2);

        // 4、计算命盘
        // 4.1、计算主星
        String daYunZhuXing = shiShenMap.get(dto.getDayGan() + daYunGan);// 大运主星
        String liuNianZhuXing = shiShenMap.get(dto.getDayGan() + liuNianGan); // 流年主星
        // 4.2、计算藏干
        List<String> daYunCangGan = diZhiCangGanMap.get(daYunZhi); // 大运藏干
        List<String> liuNianCangGan = diZhiCangGanMap.get(liuNianZhi); // 流年藏干
        // 4.3、计算副星
        List<String> daYunFuXing = new ArrayList<>();
        List<String> liuNianFuXing = new ArrayList<>();
        if (daYunCangGan != null) {
            for (String cangGan : daYunCangGan) {
                daYunFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 大运副星
            }
        }
        if (liuNianCangGan != null) {
            for (String cangGan : liuNianCangGan) {
                liuNianFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 流年副星
            }
        }

        // 4.4、计算自坐
        String daYunZiZuo = shiErZhangShengMap.get(daYunGanZhi); // 大运自坐
        String liuNianZiZuo = shiErZhangShengMap.get(liuNianGanZhi); // 流年自坐
        // 4.5、计算星运
        String daYunXingYun = shiErZhangShengMap.get(dto.getDayGan() + daYunZhi); // 大运星运
        String liuNianXingYun = shiErZhangShengMap.get(dto.getDayGan() + liuNianZhi); // 流年星运
        // 4.6、计算空亡
        String daYunKongWang = kongWangMap.get(daYunGanZhi); // 大运空亡
        String liuNianKongWang = kongWangMap.get(liuNianGanZhi); // 流年空亡
        // 4.7、计算纳音
        String daYunNaYin = naYinMap.get(daYunGanZhi); // 大运纳音
        String liuNianNaYin = naYinMap.get(liuNianGanZhi); // 流年纳音

        // 4.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                daYunGanZhi,                                 // 大运干支
                liuNianGanZhi,                              // 流年干支
                CommonUtil.EMPTY,                            // 流月干支（暂时为空）
                CommonUtil.EMPTY,                            // 流日干支（暂时为空）
                CommonUtil.EMPTY                             // 流时干支（暂时为空）
        );

        List<String> daYunShenSha = ssUtil.getDaYunGanZhiShenSha();     // 大运神煞
        List<String> liuNianShenSha = ssUtil.getLiuNianGanZhiShenSha(); // 流年神煞

        // 5、计算干支留意
        BaZiJiChuSetting setting = new BaZiJiChuSetting();
        BeanUtils.copyProperties(dto, setting);
        // 4.9、计算干支留意
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                daYunGan,                               // 第6个参数：大运干
                liuNianGan,                             // 第7个参数：流年干
                CommonUtil.EMPTY,                       // 第8个参数：流月干（暂时为空）
                CommonUtil.EMPTY,                       // 第9个参数：流日干（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时干（暂时为空）
        );

        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                daYunZhi,                               // 第6个参数：大运支
                liuNianZhi,                             // 第7个参数：流年支
                CommonUtil.EMPTY,                       // 第8个参数：流月支（暂时为空）
                CommonUtil.EMPTY,                       // 第9个参数：流日支（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时支（暂时为空）
        );

        // 6、封装数据
        DaYunInitializeVo vo = new DaYunInitializeVo();
        vo.setDaYun(daYunPackage); // 大运
        vo.setLiuNian(liuNianPackage); // 流年
        vo.setXiaoYun(xiaoYunPackage); // 小运
        vo.setLiuYue(liuYuePackage); // 流月
        vo.setDaYunZhuXing(daYunZhuXing); // 大运主星
        vo.setLiuNianZhuXing(liuNianZhuXing); // 流年主星
        vo.setDaYunGanZhi(daYunGanZhi); // 大运干支
        vo.setLiuNianGanZhi(liuNianGanZhi); // 流年干支
        vo.setDaYunCangGan(daYunCangGan); // 大运藏干
        vo.setLiuNianCangGan(liuNianCangGan); // 流年藏干
        vo.setDaYunFuXing(daYunFuXing); // 大运副星
        vo.setLiuNianFuXing(liuNianFuXing); // 流年副星
        vo.setDaYunZiZuo(daYunZiZuo); // 大运自坐
        vo.setLiuNianZiZuo(liuNianZiZuo); // 流年自坐
        vo.setDaYunXingYun(daYunXingYun); // 大运星运
        vo.setLiuNianXingYun(liuNianXingYun); // 流年星运
        vo.setDaYunKongWang(daYunKongWang); // 大运空亡
        vo.setLiuNianKongWang(liuNianKongWang); // 流年空亡
        vo.setDaYunNaYin(daYunNaYin); // 大运纳音
        vo.setLiuNianNaYin(liuNianNaYin); // 流年纳音
        vo.setDaYunShenSha(daYunShenSha); // 大运神煞
        vo.setLiuNianShenSha(liuNianShenSha); // 流年神煞
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意


        return vo;
    }

    /**
     * 大运切换 - 根据选择的大运轮数获取对应的流年和流月信息
     *
     * @param dto 大运参数
     * @return 大运切换结果
     */
    @Override
    public DaYunVo daYun(DaYunDto dto) {
        // 1、处理数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支
        String daYunGanZhi = dto.getDaYunGan() + dto.getDaYunZhi(); // 大运干支

        // 2.1、计算流年数据、小运数据、流月数据
        BaZiDaYunLiuNianUtil dylnUtil = new BaZiDaYunLiuNianUtil(dto.getDayGan(), dto.getSex(), dto.getQiYunLiuPaiType(), dto.getSolarDate());
        dylnUtil.daYun(); // 计算大运数据
        dylnUtil.liuNian(dto.getDaYunLun()); // 计算流年数据
        dylnUtil.xiaoYun(dto.getDaYunLun()); // 计算小运数据
        Date daYunSolarDate;
        try {
            daYunSolarDate = DateParseUtil.parseDate(dto.getDaYunSolarDate()); // 大运公历日期
        } catch (Exception e) {
            throw new RuntimeException("大运公历日期解析失败: " + dto.getDaYunSolarDate(), e);
        }
        dylnUtil.liuYue(dto.getDaYunLun(), dto.getLiuNianLun(), daYunSolarDate.getYear() + 1900); // 计算流月数据
        List<List<String>> liuNian = dylnUtil.getLiuNian(); // 获取流年数据
        List<List<String>> xiaoYun = dylnUtil.getXiaoYun(); // 获取小运数据
        List<List<String>> liuYue = dylnUtil.getLiuYue(); // 获取流月数据
        // 2.2、数据封装
        List<TimeDataVo> liuNianPackage = TimeDataVo.liuNianEncapsulation(liuNian); // 流年封装
        List<TimeDataVo> xiaoYunPackage = TimeDataVo.xiaoYunEncapsulation(xiaoYun); // 小运封装
        List<TimeDataVo> liuYuePackage = TimeDataVo.liuYueEncapsulation(liuYue); // 流月封装

        // 3、处理流年干支
        String liuNianGan = ""; // 流年干
        String liuNianZhi = ""; // 流年支
        String liuNianGanZhi = liuNian.get(dto.getLiuNianLun() - 1).get(0); // 流年干支
        if (StringUtils.isNotBlank(liuNianGanZhi) && liuNianGanZhi.length() >= 1) liuNianGan = liuNianGanZhi.substring(0, 1); // 流年干
        if (StringUtils.isNotBlank(liuNianGanZhi) && liuNianGanZhi.length() >= 2) liuNianZhi = liuNianGanZhi.substring(1, 2); // 流年支

        // 4、计算命盘
        // 4.1、计算主星
        String daYunZhuXing = shiShenMap.get(dto.getDayGan() + dto.getDaYunGan()); // 大运主星
        String liuNianZhuXing = shiShenMap.get(dto.getDayGan() + liuNianGan); // 流年主星
        // 4.2、计算藏干
        List<String> daYunCangGan = diZhiCangGanMap.get(dto.getDaYunZhi()); // 大运藏干
        List<String> liuNianCangGan = diZhiCangGanMap.get(liuNianZhi); // 流年藏干
        // 4.3、计算副星
        List<String> daYunFuXing = new ArrayList<>();
        List<String> liuNianFuXing = new ArrayList<>();
        if (daYunCangGan != null) {
            for (String cangGan : daYunCangGan) {
                daYunFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 大运副星
            }
        }
        if (liuNianCangGan != null) {
            for (String cangGan : liuNianCangGan) {
                liuNianFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 流年副星
            }
        }


        // 4.4、计算自坐
        String daYunZiZuo = shiErZhangShengMap.get(dto.getDaYunGan() + dto.getDaYunZhi()); // 大运自坐
        String liuNianZiZuo = shiErZhangShengMap.get(liuNianGan + liuNianZhi); // 流年自坐
        // 4.5、计算星运
        String daYunXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getDaYunZhi()); // 大运星运
        String liuNianXingYun = shiErZhangShengMap.get(dto.getDayGan() + liuNianZhi); // 流年星运
        // 4.6、计算空亡
        String daYunKongWang = kongWangMap.get(daYunGanZhi); // 大运空亡
        String liuNianKongWang = kongWangMap.get(liuNianGanZhi); // 流年空亡
        // 4.7、计算纳音
        String daYunNaYin = naYinMap.get(daYunGanZhi); // 大运纳音
        String liuNianNaYin = naYinMap.get(liuNianGanZhi); // 流年纳音
        // 4.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                daYunGanZhi,                                 // 大运干支
                liuNianGanZhi,                              // 流年干支
                CommonUtil.EMPTY,                            // 流月干支（暂时为空）
                CommonUtil.EMPTY,                            // 流日干支（暂时为空）
                CommonUtil.EMPTY                             // 流时干支（暂时为空）
        );

        List<String> daYunShenSha = ssUtil.getDaYunGanZhiShenSha(); // 大运神煞
        List<String> liuNianShenSha = ssUtil.getLiuNianGanZhiShenSha(); // 流年神煞

        // 5、计算干支留意
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                dto.getDaYunGan(),                      // 第6个参数：大运干
                liuNianGan,                             // 第7个参数：流年干
                CommonUtil.EMPTY,                       // 第8个参数：流月干（暂时为空）
                CommonUtil.EMPTY,                       // 第9个参数：流日干（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时干（暂时为空）
        );
        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                dto.getDaYunZhi(),                      // 第6个参数：大运支
                liuNianZhi,                             // 第7个参数：流年支
                CommonUtil.EMPTY,                       // 第8个参数：流月支（暂时为空）
                CommonUtil.EMPTY,                       // 第9个参数：流日支（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时支（暂时为空）
        );

        // 6、封装数据
        DaYunVo vo = new DaYunVo();
        vo.setLiuNian(liuNianPackage); // 流年
        vo.setXiaoYun(xiaoYunPackage); // 小运
        vo.setLiuYue(liuYuePackage); // 流月
        vo.setDaYunZhuXing(daYunZhuXing); // 大运主星
        vo.setLiuNianZhuXing(liuNianZhuXing); // 流年主星
        vo.setDaYunGanZhi(daYunGanZhi); // 大运干支
        vo.setLiuNianGanZhi(liuNianGanZhi); // 流年干支
        vo.setDaYunCangGan(daYunCangGan); // 大运藏干
        vo.setLiuNianCangGan(liuNianCangGan); // 流年藏干
        vo.setDaYunFuXing(daYunFuXing); // 大运副星
        vo.setLiuNianFuXing(liuNianFuXing); // 流年副星
        vo.setDaYunZiZuo(daYunZiZuo); // 大运自坐
        vo.setLiuNianZiZuo(liuNianZiZuo); // 流年自坐
        vo.setDaYunXingYun(daYunXingYun); // 大运星运
        vo.setLiuNianXingYun(liuNianXingYun); // 流年星运
        vo.setDaYunKongWang(daYunKongWang); // 大运空亡
        vo.setLiuNianKongWang(liuNianKongWang); // 流年空亡
        vo.setDaYunNaYin(daYunNaYin); // 大运纳音
        vo.setLiuNianNaYin(liuNianNaYin); // 流年纳音
        vo.setDaYunShenSha(daYunShenSha); // 大运神煞
        vo.setLiuNianShenSha(liuNianShenSha); // 流年神煞
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意

        return vo;
    }

    @Override
    public LiuNianVo liuNian(LiuNianDto dto) {
        // 1、处理数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支
        String daYunGanZhi = dto.getDaYunGan() + dto.getDaYunZhi(); // 大运干支
        String liuNianGanZhi = dto.getLiuNianGan() + dto.getLiuNianZhi(); // 流年干支

        // 2.1、计算流月数据
        BaZiDaYunLiuNianUtil dylnUtil = new BaZiDaYunLiuNianUtil(dto.getDayGan(), dto.getSex(), dto.getQiYunLiuPai(), dto.getSolarDate());
        dylnUtil.daYun(); // 计算大运数据
        dylnUtil.liuNian(dto.getDaYunLun()); // 计算流年数据
        Date liuNianXiaoYunSolarDate;
        try {
            liuNianXiaoYunSolarDate = DateParseUtil.parseDate(dto.getLiuNianSolarDate()); // 流年公历日期
        } catch (Exception e) {
            throw new RuntimeException("流年公历日期解析失败: " + dto.getLiuNianSolarDate(), e);
        }
        dylnUtil.liuYue(dto.getDaYunLun(), dto.getLiuNianLun(), liuNianXiaoYunSolarDate.getYear() + 1900); // 计算流月数据
        List<List<String>> liuYue = dylnUtil.getLiuYue(); // 获取流月数据
        // 2.2、数据封装
        List<TimeDataVo> liuYuePackage =TimeDataVo.liuYueEncapsulation(liuYue); // 流月封装

        // 3、计算命盘数据
        // 3.1、计算主星
        String daYunZhuXing = shiShenMap.get(dto.getDayGan() + dto.getDaYunGan()); // 大运主星
        String liuNianZhuXing = shiShenMap.get(dto.getDayGan() + dto.getLiuNianGan()); // 流年主星
        // 3.2、计算藏干
        List<String> daYunCangGan = diZhiCangGanMap.get(dto.getDaYunZhi()); // 大运藏干
        List<String> liuNianCangGan = diZhiCangGanMap.get(dto.getLiuNianZhi()); // 流年藏干
        // 3.3、计算副星
        List<String> daYunFuXing = new ArrayList<>();
        List<String> liuNianFuXing = new ArrayList<>();
        if (daYunCangGan != null) {
            for (String cangGan : daYunCangGan) {
                daYunFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 大运副星
            }
        }
        if (liuNianCangGan != null) {
            for (String cangGan : liuNianCangGan) {
                liuNianFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 流年副星
            }
        }

        // 3.4、计算自坐
        String daYunZiZuo = shiErZhangShengMap.get(dto.getDaYunGan() + dto.getDaYunZhi()); // 大运自坐
        String liuNianZiZuo = shiErZhangShengMap.get(dto.getLiuNianGan() + dto.getLiuNianZhi()); // 流年自坐
        // 3.5、计算星运
        String daYunXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getDaYunZhi()); // 大运星运
        String liuNianXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getLiuNianZhi()); // 流年星运
        // 3.6、计算空亡
        String daYunKongWang = kongWangMap.get(daYunGanZhi); // 大运空亡
        String liuNianKongWang = kongWangMap.get(liuNianGanZhi); // 流年空亡
        // 3.7、计算纳音
        String daYunNaYin = naYinMap.get(daYunGanZhi); // 大运纳音
        String liuNianNaYin = naYinMap.get(liuNianGanZhi); // 流年纳音
        // 3.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                daYunGanZhi,                                 // 大运干支
                liuNianGanZhi,                              // 流年干支
                CommonUtil.EMPTY,                            // 流月干支（暂时为空）
                CommonUtil.EMPTY,                            // 流日干支（暂时为空）
                CommonUtil.EMPTY                             // 流时干支（暂时为空）
        );
        List<String> daYunShenSha = ssUtil.getDaYunGanZhiShenSha(); // 大运神煞
        List<String> liuNianShenSha = ssUtil.getLiuNianGanZhiShenSha(); // 流年神煞

        // 4、计算干支留意
        BaZiJiChuSetting setting = new BaZiJiChuSetting();
        BeanUtils.copyProperties(dto, setting);
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                dto.getDaYunGan(),                      // 第6个参数：大运干
                dto.getLiuNianGan(),                    // 第7个参数：流年干
                CommonUtil.EMPTY,                       // 第8个参数：流月干（暂时为空）
                CommonUtil.EMPTY,                       // 第9个参数：流日干（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时干（暂时为空）
        );
        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                dto.getDaYunZhi(),                      // 第6个参数：大运支
                dto.getLiuNianZhi(),                    // 第7个参数：流年支
                CommonUtil.EMPTY,                       // 第8个参数：流月支（暂时为空）
                CommonUtil.EMPTY,                       // 第9个参数：流日支（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时支（暂时为空）
        );


        // 5、封装数据并返回
        LiuNianVo vo = new LiuNianVo();
        vo.setLiuYue(liuYuePackage); // 流月
        vo.setDaYunGanZhi(daYunGanZhi); // 大运干支
        vo.setLiuNianGanZhi(liuNianGanZhi); // 流年干支
        vo.setDaYunZhuXing(daYunZhuXing); // 大运主星
        vo.setLiuNianZhuXing(liuNianZhuXing); // 流年主星
        vo.setDaYunCangGan(daYunCangGan); // 大运藏干
        vo.setLiuNianCangGan(liuNianCangGan); // 流年藏干
        vo.setDaYunFuXing(daYunFuXing); // 大运副星
        vo.setLiuNianFuXing(liuNianFuXing); // 流年副星
        vo.setDaYunZiZuo(daYunZiZuo); // 大运自坐
        vo.setLiuNianZiZuo(liuNianZiZuo); // 流年自坐
        vo.setDaYunXingYun(daYunXingYun); // 大运星运
        vo.setLiuNianXingYun(liuNianXingYun); // 流年星运
        vo.setDaYunKongWang(daYunKongWang); // 大运空亡
        vo.setLiuNianKongWang(liuNianKongWang); // 流年空亡
        vo.setDaYunNaYin(daYunNaYin); // 大运纳音
        vo.setLiuNianNaYin(liuNianNaYin); // 流年纳音
        vo.setDaYunShenSha(daYunShenSha); // 大运神煞
        vo.setLiuNianShenSha(liuNianShenSha); // 流年神煞
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意

        return vo;
    }

    @Override
    public LiuYueVo liuYue(LiuYueDto dto) {
        // 1、获取基础数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支
        String daYunGanZhi = dto.getDaYunGan() + dto.getDaYunZhi(); // 大运干支
        String liuNianGanZhi = dto.getLiuNianGan() + dto.getLiuNianZhi(); // 流年干支
        String liuYueGanZhi = dto.getLiuYueGan() + dto.getLiuYueZhi(); // 流月干支

        // 2.1、计算流日数据
        BaZiDaYunLiuNianUtil dylnUtil = new BaZiDaYunLiuNianUtil(dto.getDayGan(), dto.getSex(), dto.getQiYunLiuPai(), dto.getSolarDate());
        Date liuYueSolarDate;
        try {
            liuYueSolarDate = DateParseUtil.parseDate(dto.getLiuYueSolarDate()); // 流月公历日期
        } catch (Exception e) {
            throw new RuntimeException("流月公历日期解析失败: " + dto.getLiuYueSolarDate(), e);
        }
        dylnUtil.liuRi(liuYueSolarDate.getYear() + 1900, liuYueSolarDate.getMonth() + 1, dto.getJieQiSet()); // 计算流日数据
        List<List<String>> liuRi = dylnUtil.getLiuRi(); // 获取流日数据
        // 2.2、数据封装
        List<TimeDataVo> liuRiPackage = TimeDataVo.liuRiEncapsulation(liuRi); // 流日封装
        // 3、计算命盘
        // 3.1、计算主星
        String liuYueZhuXing = shiShenMap.get(dto.getDayGan() + dto.getLiuYueGan()); // 流月主星
        // 3.2、计算藏干
        List<String> liuYueCangGan = diZhiCangGanMap.get(dto.getLiuYueZhi()); // 流月藏干
        // 3.3、计算副星
        List<String> liuYueFuXing = new ArrayList<>();
        if (liuYueCangGan != null) {
            for (String cangGan : liuYueCangGan) {
                liuYueFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 流月副星
            }
        }
        // 3.4、计算自坐
        String liuYueZiZuo = shiErZhangShengMap.get(dto.getLiuYueGan() + dto.getLiuYueZhi()); // 流月自坐
        // 3.5、计算星运
        String liuYueXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getLiuYueZhi()); // 流月星运
        // 3.6、计算空亡
        String liuYueKongWang = kongWangMap.get(liuYueGanZhi); // 流月空亡
        // 3.7、计算纳音
        String liuYueNaYin = naYinMap.get(liuYueGanZhi); // 流月纳音
        // 3.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                daYunGanZhi,                                 // 大运干支
                liuNianGanZhi,                              // 流年干支
                liuYueGanZhi,                               // 流月干支
                CommonUtil.EMPTY,                            // 流日干支（暂时为空）
                CommonUtil.EMPTY                             // 流时干支（暂时为空）
        );
        List<String> liuYueShenSha = ssUtil.getLiuYueGanZhiShenSha(); // 流月神煞

        // 4、计算干支留意
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                dto.getDaYunGan(),                      // 第6个参数：大运干
                dto.getLiuNianGan(),                    // 第7个参数：流年干
                dto.getLiuYueGan(),                     // 第8个参数：流月干
                CommonUtil.EMPTY,                       // 第9个参数：流日干（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时干（暂时为空）
        );
        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                dto.getDaYunZhi(),                      // 第6个参数：大运支
                dto.getLiuNianZhi(),                    // 第7个参数：流年支
                dto.getLiuYueZhi(),                     // 第8个参数：流月支
                CommonUtil.EMPTY,                       // 第9个参数：流日支（暂时为空）
                CommonUtil.EMPTY                        // 第10个参数：流时支（暂时为空）
        );

        // 5、封装数据并返回
        LiuYueVo vo = new LiuYueVo();
        vo.setLiuRiList(liuRiPackage); // 流日列表
        vo.setLiuYueGanZhi(liuYueGanZhi); // 流月干支
        vo.setLiuYueZhuXing(liuYueZhuXing); // 流月主星
        vo.setLiuYueCangGan(liuYueCangGan); // 流月藏干
        vo.setLiuYueFuXing(liuYueFuXing); // 流月副星
        vo.setLiuYueZiZuo(liuYueZiZuo); // 流月自坐
        vo.setLiuYueXingYun(liuYueXingYun); // 流月星运
        vo.setLiuYueKongWang(liuYueKongWang); // 流月空亡
        vo.setLiuYueNaYin(liuYueNaYin); // 流月纳音
        vo.setLiuYueShenSha(liuYueShenSha); // 流月神煞
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意

        return vo;
    }


    @Override
    public LiuRiVo liuRi(LiuRiDto dto) {
        // 1、处理数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支
        String liuRiGanZhi = dto.getLiuRiGan() + dto.getLiuRiZhi(); // 流日干支

        // 2.1、计算流时数据
        BaZiDaYunLiuNianUtil dylnUtil = new BaZiDaYunLiuNianUtil(dto.getDayGan(), dto.getSex(), dto.getQiYunLiuPai(), dto.getSolarDate());
        Date liuRiSolarDate;
        try {
            liuRiSolarDate = DateParseUtil.parseDate(dto.getLiuRiSolarDate()); // 流日公历日期
        } catch (Exception e) {
            throw new RuntimeException("流日公历日期解析失败: " + dto.getLiuRiSolarDate(), e);
        }
        dylnUtil.liuShi(liuRiSolarDate.getYear() + 1900, liuRiSolarDate.getMonth() + 1, liuRiSolarDate.getDate()); // 计算流时数据
        List<List<String>> liuShi = dylnUtil.getLiuShi(); // 获取流时数据
        // 2.2、数据封装
        List<TimeDataVo> liuShiPackage =TimeDataVo.liuShiEncapsulation(liuShi); // 流时封装
        // 3、计算命盘
        // 3.1、计算主星
        String liuRiZhuXing = shiShenMap.get(dto.getDayGan() + dto.getLiuRiGan()); // 流日主星
        // 3.2、计算藏干
        List<String> liuRiCangGan = diZhiCangGanMap.get(dto.getLiuRiZhi()); // 流日藏干
        // 3.3、计算副星
        List<String> liuRiFuXing = new ArrayList<>();
        if (liuRiCangGan != null) {
            for (String cangGan : liuRiCangGan) {
                liuRiFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 流日副星
            }
        }
        // 3.4、计算自坐
        String liuRiZiZuo = shiErZhangShengMap.get(dto.getLiuRiGan() + dto.getLiuRiZhi()); // 流日自坐
        // 3.5、计算星运
        String liuRiXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getLiuRiZhi()); // 流日星运
        // 3.6、计算空亡
        String liuRiKongWang = kongWangMap.get(liuRiGanZhi); // 流日空亡
        // 3.7、计算纳音
        String liuRiNaYin = naYinMap.get(liuRiGanZhi); // 流日纳音
        // 3.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                dto.getDaYunGan() + dto.getDaYunZhi(),      // 大运干支
                dto.getLiuNianGan() + dto.getLiuNianZhi(),  // 流年干支
                dto.getLiuYueGan() + dto.getLiuYueZhi(),    // 流月干支
                liuRiGanZhi,                                 // 流日干支
                CommonUtil.EMPTY                             // 流时干支（暂时为空）
        );
        List<String> liuRiShenSha = ssUtil.getLiuRiGanZhiShenSha(); // 流日神煞

        // 4、计算干支留意
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                dto.getDaYunGan(),                      // 第6个参数：大运干
                dto.getLiuNianGan(),                    // 第7个参数：流年干
                dto.getLiuYueGan(),                     // 第8个参数：流月干
                dto.getLiuRiGan(),                      // 第9个参数：流日干
                CommonUtil.EMPTY                        // 第10个参数：流时干（暂时为空）
        );
        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                dto.getDaYunZhi(),                      // 第6个参数：大运支
                dto.getLiuNianZhi(),                    // 第7个参数：流年支
                dto.getLiuYueZhi(),                     // 第8个参数：流月支
                dto.getLiuRiZhi(),                      // 第9个参数：流日支
                CommonUtil.EMPTY                        // 第10个参数：流时支（暂时为空）
        );

        // 5、封装数据并返回
        LiuRiVo vo = new LiuRiVo();
        vo.setLiuShiList(liuShiPackage); // 流时列表
        vo.setLiuRiZhuXing(liuRiZhuXing); // 流日主星
        vo.setLiuRiGanZhi(liuRiGanZhi); // 流日干支
        vo.setLiuRiCangGan(liuRiCangGan); // 流日藏干
        vo.setLiuRiFuXing(liuRiFuXing); // 流日副星
        vo.setLiuRiZiZuo(liuRiZiZuo); // 流日自坐
        vo.setLiuRiXingYun(liuRiXingYun); // 流日星运
        vo.setLiuRiKongWang(liuRiKongWang); // 流日空亡
        vo.setLiuRiNaYin(liuRiNaYin); // 流日纳音
        vo.setLiuRiShenSha(liuRiShenSha); // 流日神煞
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意

        return vo;
    }

    @Override
    public LiuShiVo liuShi(LiuShiDto dto) {

        // 1、处理数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支
        String liuShiGanZhi = dto.getLiuShiGan() + dto.getLiuShiZhi(); // 流时干支

        // 2、计算命盘
        // 2.1、计算主星
        String liuShiZhuXing = shiShenMap.get(dto.getDayGan() + dto.getLiuShiGan()); // 流时主星
        // 2.2、计算藏干
        List<String> liuShiCangGan = diZhiCangGanMap.get(dto.getLiuShiZhi()); // 流时藏干
        // 2.3、计算副星
        List<String> liuShiFuXing = new ArrayList<>();
        if (liuShiCangGan != null) {
            for (String cangGan : liuShiCangGan) {
                liuShiFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 流时副星
            }
        }
        // 2.4、计算自坐
        String liuShiZiZuo = shiErZhangShengMap.get(dto.getLiuShiGan() + dto.getLiuShiZhi()); // 流时自坐
        // 2.5、计算星运
        String liuShiXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getLiuShiZhi()); // 流时星运
        // 2.6、计算空亡
        String liuShiKongWang = kongWangMap.get(liuShiGanZhi); // 流时空亡
        // 2.7、计算纳音
        String liuShiNaYin = naYinMap.get(liuShiGanZhi); // 流时纳音

        // 2.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                dto.getDaYunGan() + dto.getDaYunZhi(),      // 大运干支
                dto.getLiuNianGan() + dto.getLiuNianZhi(),  // 流年干支
                dto.getLiuYueGan() + dto.getLiuYueZhi(),    // 流月干支
                dto.getLiuRiGan() + dto.getLiuRiZhi(),      // 流日干支
                liuShiGanZhi                                 // 流时干支
        );
        List<String> liuShiShenSha = ssUtil.getLiuShiGanZhiShenSha(); // 流时神煞

        // 3、计算干支留意
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                dto.getDaYunGan(),                      // 第6个参数：大运干
                dto.getLiuNianGan(),                    // 第7个参数：流年干
                dto.getLiuYueGan(),                     // 第8个参数：流月干
                dto.getLiuRiGan(),                      // 第9个参数：流日干
                dto.getLiuShiGan()                      // 第10个参数：流时干
        );
        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                dto.getDaYunZhi(),                      // 第6个参数：大运支
                dto.getLiuNianZhi(),                    // 第7个参数：流年支
                dto.getLiuYueZhi(),                     // 第8个参数：流月支
                dto.getLiuRiZhi(),                      // 第9个参数：流日支
                dto.getLiuShiZhi()                      // 第10个参数：流时支
        );


        // 4、封装数据并返回
        LiuShiVo vo = new LiuShiVo();
        vo.setLiuShiZhuXing(liuShiZhuXing); // 流时主星
        vo.setLiuShiGanZhi(liuShiGanZhi); // 流时干支
        vo.setLiuShiCangGan(liuShiCangGan); // 流时藏干
        vo.setLiuShiFuXing(liuShiFuXing); // 流时副星
        vo.setLiuShiZiZuo(liuShiZiZuo); // 流时自坐
        vo.setLiuShiXingYun(liuShiXingYun); // 流时星运
        vo.setLiuShiKongWang(liuShiKongWang); // 流时空亡
        vo.setLiuShiNaYin(liuShiNaYin); // 流时纳音
        vo.setLiuShiShenSha(liuShiShenSha); // 流时神煞
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意

        return vo;
    }
    @Override
    public TaiMingShenVo taiMingShen(TaiMingShenDto dto) {
        // 1、处理数据
        String yearGanZhi = dto.getYearGan() + dto.getYearZhi(); // 年干支
        String monthGanZhi = dto.getMonthGan() + dto.getMonthZhi(); // 月干支
        String dayGanZhi = dto.getDayGan() + dto.getDayZhi(); // 日干支
        String hourGanZhi = dto.getHourGan() + dto.getHourZhi(); // 时干支
        String tanYuanGanZhi = dto.getTaiYuanGan() + dto.getTaiYuanZhi(); // 胎元干支
        String tanXiGanZhi = dto.getTaiXiGan() + dto.getTaiXiZhi(); // 胎息干支
        String mingGongGanZhi = dto.getMingGongGan() + dto.getMingGongZhi(); // 命宫干支
        String shenGongGanZhi = dto.getShenGongGan() + dto.getShenGongZhi(); // 身宫干支

        // 2、计算胎元、胎息、命宫、身宫命盘数据
        // 2.1、计算主星
        String taiYuanZhuXing = shiShenMap.get(dto.getDayGan() + dto.getTaiYuanGan()); // 胎元主星
        String taiXiZhuXing = shiShenMap.get(dto.getDayGan() + dto.getTaiXiGan()); // 胎息主星
        String mingGongZhuXing = shiShenMap.get(dto.getDayGan() + dto.getMingGongGan()); // 命宫主星
        String shenGongZhuXing = shiShenMap.get(dto.getDayGan() + dto.getShenGongGan()); // 身宫主星
        // 2.2、计算藏干
        List<String> taiYuanCangGan = diZhiCangGanMap.get(dto.getTaiYuanZhi()); // 胎元藏干
        List<String> taiXiCangGan = diZhiCangGanMap.get(dto.getTaiXiZhi()); // 胎息藏干
        List<String> mingGongCangGan = diZhiCangGanMap.get(dto.getMingGongZhi()); // 命宫藏干
        List<String> shenGongCangGan = diZhiCangGanMap.get(dto.getShenGongZhi()); // 身宫藏干
        // 2.3、计算副星
        List<String> taiYuanFuXing = new ArrayList<>();
        List<String> taiXiFuXing = new ArrayList<>();
        List<String> mingGongFuXing = new ArrayList<>();
        List<String> shenGongFuXing = new ArrayList<>();
        if (taiYuanCangGan != null) {
            for (String cangGan : taiYuanCangGan) {
                taiYuanFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 胎元副星
            }
        }
        if (taiXiCangGan != null) {
            for (String cangGan : taiXiCangGan) {
                taiXiFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 胎息副星
            }
        }
        if (mingGongCangGan != null) {
            for (String cangGan : mingGongCangGan) {
                mingGongFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 命宫副星
            }
        }
        if (shenGongCangGan != null) {
            for (String cangGan : shenGongCangGan) {
                shenGongFuXing.add(shiShenMap.get(dto.getDayGan() + cangGan)); // 身宫副星
            }
        }
        // 2.4、计算自坐
        String taiYuanZiZuo = shiErZhangShengMap.get(dto.getTaiYuanGan() + dto.getTaiYuanZhi()); // 胎元自坐
        String taiXiZiZuo = shiErZhangShengMap.get(dto.getTaiXiGan() + dto.getTaiXiZhi()); // 胎息自坐
        String mingGongZiZuo = shiErZhangShengMap.get(dto.getMingGongGan() + dto.getMingGongZhi()); // 命宫自坐
        String shenGongZiZuo = shiErZhangShengMap.get(dto.getShenGongGan() + dto.getShenGongZhi()); // 身宫自坐
        // 2.5、计算星运
        String taiYuanXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getTaiYuanZhi()); // 胎元星运
        String taiXiXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getTaiXiZhi()); // 胎息星运
        String mingGongXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getMingGongZhi()); // 命宫星运
        String shenGongXingYun = shiErZhangShengMap.get(dto.getDayGan() + dto.getShenGongZhi()); // 身宫星运
        // 2.6、计算空亡
        String taiYuanKongWang = kongWangMap.get(tanYuanGanZhi); // 胎元空亡
        String taiXiKongWang = kongWangMap.get(tanXiGanZhi); // 胎息空亡
        String mingGongKongWang = kongWangMap.get(mingGongGanZhi); // 命宫空亡
        String shenGongKongWang = kongWangMap.get(shenGongGanZhi); // 身宫空亡
        // 2.7、计算纳音
        String taiYuanNaYin = naYinMap.get(tanYuanGanZhi); // 胎元纳音
        String taiXiNaYin = naYinMap.get(tanXiGanZhi); // 胎息纳音
        String mingGongNaYin = naYinMap.get(mingGongGanZhi); // 命宫纳音
        String shenGongNaYin = naYinMap.get(shenGongGanZhi); // 身宫纳音
        // 2.8、计算神煞
        BaZiShenShaUtil ssUtil = new BaZiShenShaUtil(
                new BaZiShenShaSetting(),                    // 神煞设置
                dto.getSex().equals("男") ? 1 : 0,          // 性别：1男0女
                dto.getSeason(),                             // 季节
                dto.getYearGanZhiNaYin(),                   // 年干支纳音
                yearGanZhi,                                  // 年干支
                monthGanZhi,                                 // 月干支
                dayGanZhi,                                   // 日干支
                hourGanZhi,                                  // 时干支
                tanYuanGanZhi,                              // 大运干支（胎元）
                tanXiGanZhi,                                // 流年干支（胎息）
                mingGongGanZhi,                             // 流月干支（命宫）
                shenGongGanZhi,                             // 流日干支（身宫）
                CommonUtil.EMPTY                             // 流时干支
        );
        List<String> taiYuanShenSha = ssUtil.getDaYunGanZhiShenSha(); // 胎元神煞
        List<String> taiXiShenSha = ssUtil.getLiuNianGanZhiShenSha(); // 胎息神煞
        List<String> mingGongShenSha = ssUtil.getLiuYueGanZhiShenSha(); // 命宫神煞
        List<String> shenGongShenSha = ssUtil.getLiuRiGanZhiShenSha(); // 身宫神煞

        // 3、计算干支留意
        List<String> tianGanLiuYi = BaZiGanZhiLiuYiUtil.tanGanLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearGan(),                       // 第2个参数：年干
                dto.getMonthGan(),                      // 第3个参数：月干
                dto.getDayGan(),                        // 第4个参数：日干
                dto.getHourGan(),                       // 第5个参数：时干
                dto.getTaiYuanGan(),                    // 第6个参数：胎元干
                dto.getTaiXiGan(),                      // 第7个参数：胎息干
                dto.getMingGongGan(),                   // 第8个参数：命宫干
                dto.getShenGongGan(),                   // 第9个参数：身宫干
                CommonUtil.EMPTY                        // 第10个参数：空
        );
        List<String> diZhiLiuYi = BaZiGanZhiLiuYiUtil.diZhiLiuYi(
                new BaZiGanZhiLiuYiSetting(),           // 第1个参数：干支留意设置
                dto.getYearZhi(),                       // 第2个参数：年支
                dto.getMonthZhi(),                      // 第3个参数：月支
                dto.getDayZhi(),                        // 第4个参数：日支
                dto.getHourZhi(),                       // 第5个参数：时支
                dto.getTaiYuanZhi(),                    // 第6个参数：胎元支
                dto.getTaiXiZhi(),                      // 第7个参数：胎息支
                dto.getMingGongZhi(),                   // 第8个参数：命宫支
                dto.getShenGongZhi(),                   // 第9个参数：身宫支
                CommonUtil.EMPTY                        // 第10个参数：空
        );

        // 4、封装数据并返回
        TaiMingShenVo vo = new TaiMingShenVo();
        vo.setTaiYuanZhuXing(taiYuanZhuXing); // 胎元主星
        vo.setTaiXiZhuXing(taiXiZhuXing); // 胎息主星
        vo.setMingGongZhuXing(mingGongZhuXing); // 命宫主星
        vo.setShenGongZhuXing(shenGongZhuXing); // 身宫主星
        vo.setTaiYuanCangGan(taiYuanCangGan); // 胎元藏干
        vo.setTaiXiCangGan(taiXiCangGan); // 胎息藏干
        vo.setMingGongCangGan(mingGongCangGan); // 命宫藏干
        vo.setShenGongCangGan(shenGongCangGan); // 身宫藏干
        vo.setTaiYuanFuXing(taiYuanFuXing); // 胎元副星
        vo.setTaiXiFuXing(taiXiFuXing); // 胎息副星
        vo.setMingGongFuXing(mingGongFuXing); // 命宫副星
        vo.setShenGongFuXing(shenGongFuXing); // 身宫副星
        vo.setTaiYuanZiZuo(taiYuanZiZuo); // 胎元自坐
        vo.setTaiXiZiZuo(taiXiZiZuo); // 胎息自坐
        vo.setMingGongZiZuo(mingGongZiZuo); // 命宫自坐
        vo.setShenGongZiZuo(shenGongZiZuo); // 身宫自坐
        vo.setTaiYuanXingYun(taiYuanXingYun); // 胎元星运
        vo.setTaiXiXingYun(taiXiXingYun); // 胎息星运
        vo.setMingGongXingYun(mingGongXingYun); // 命宫星运
        vo.setShenGongXingYun(shenGongXingYun); // 身宫星运
        vo.setTaiYuanKongWang(taiYuanKongWang); // 胎元空亡
        vo.setTaiXiKongWang(taiXiKongWang); // 胎息空亡
        vo.setMingGongKongWang(mingGongKongWang); // 命宫空亡
        vo.setShenGongKongWang(shenGongKongWang); // 身宫空亡
        vo.setTaiYuanNaYin(taiYuanNaYin); // 胎元纳音
        vo.setTaiXiNaYin(taiXiNaYin); // 胎息纳音
        vo.setMingGongNaYin(mingGongNaYin); // 命宫纳音
        vo.setTaiYuanShenSha(taiYuanShenSha); // 胎元神煞
        vo.setShenGongNaYin(shenGongNaYin); // 身宫纳音
        vo.setTaiXiShenSha(taiXiShenSha); // 胎息神煞
        vo.setMingGongShenSha(mingGongShenSha); // 命宫神煞
        vo.setShenGongShenSha(shenGongShenSha); // 身宫神煞
        vo.setTaiYuanGanZhi(tanYuanGanZhi); // 胎元干支
        vo.setTaiXiGanZhi(tanXiGanZhi); // 胎息干支
        vo.setMingGongGanZhi(mingGongGanZhi); // 命宫干支
        vo.setShenGongGanZhi(shenGongGanZhi); // 身宫干支
        vo.setTianGanLiuYi(tianGanLiuYi); // 天干留意
        vo.setDiZhiLiuYi(diZhiLiuYi); // 地支留意

        return vo;
    }






}
