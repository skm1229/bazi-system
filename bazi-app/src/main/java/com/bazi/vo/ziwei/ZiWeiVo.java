package com.bazi.vo.ziwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 紫微斗数排盘结果VO
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "紫微斗数排盘结果")
public class ZiWeiVo {

    // 基本信息
    @Schema(description = "姓名", example = "张三")
    private String name;

    @Schema(description = "性别", example = "男")
    private String sex;

    @Schema(description = "乾造/坤造", example = "乾造")
    private String zao;

    @Schema(description = "占事", example = "求财运")
    private String occupy;

    // 日期时间信息
    @Schema(description = "公历日期字符串", example = "2003年1月15日0时0分")
    private String solarStr;

    @Schema(description = "农历日期字符串", example = "二〇〇二年腊月十三日子时")
    private String lunarStr;

    @Schema(description = "公历日期")
    private Date solarDate;

    @Schema(description = "农历日期")
    private Date lunarDate;

    @Schema(description = "星期", example = "星期三")
    private String xingQi;

    @Schema(description = "季节", example = "冬季")
    private String jiJie;

    @Schema(description = "生肖", example = "马")
    private String shengXiao;

    @Schema(description = "星座", example = "摩羯座")
    private String xingZuo;

    @Schema(description = "月相", example = "上弦月")
    private String yueXiang;

    @Schema(description = "月将", example = "丑将")
    private String yueJiang;

    @Schema(description = "月将神", example = "太常")
    private String yueJiangShen;

    @Schema(description = "五不遇时", example = "甲不遇申")
    private String wuBuYuShi;

    @Schema(description = "太岁类型", example = "顺行")
    private String taiSuiType;

    @Schema(description = "男女阴阳", example = "阳男")
    private String nanNvYinYang;

    // 天干地支
    @Schema(description = "年干", example = "壬")
    private String yearGan;

    @Schema(description = "月干", example = "癸")
    private String monthGan;

    @Schema(description = "日干", example = "戊")
    private String dayGan;

    @Schema(description = "时干", example = "壬")
    private String hourGan;

    @Schema(description = "年支", example = "午")
    private String yearZhi;

    @Schema(description = "月支", example = "丑")
    private String monthZhi;

    @Schema(description = "日支", example = "子")
    private String dayZhi;

    @Schema(description = "时支", example = "子")
    private String hourZhi;

    @Schema(description = "年干支", example = "壬午")
    private String yearGanZhi;

    @Schema(description = "月干支", example = "癸丑")
    private String monthGanZhi;

    @Schema(description = "日干支", example = "戊子")
    private String dayGanZhi;

    @Schema(description = "时干支", example = "壬子")
    private String hourGanZhi;

    // 紫微斗数核心信息
    @Schema(description = "命宫宫位", example = "1")
    private int mingGongGongWei;

    @Schema(description = "身宫宫位", example = "7")
    private int shenGongGongWei;

    @Schema(description = "五行局", example = "水二局")
    private String wuXingJu;

    @Schema(description = "命宫地支", example = "子")
    private String mingGongDiZhi;

    @Schema(description = "身宫地支", example = "午")
    private String shenGongDiZhi;

    @Schema(description = "命主", example = "贪狼")
    private String mingZhu;

    @Schema(description = "身主", example = "天相")
    private String shenZhu;

    // 十二宫信息
    @Schema(description = "十二宫名称")
    private List<String> shiErMingGong;

    @Schema(description = "十二宫地支")
    private List<String> shiErGongDiZhi;

    @Schema(description = "十二宫诸星")
    private List<List<String>> shiErGongZhuXing;

    @Schema(description = "十二宫诸星（带标识）")
    private List<List<String>> shiErGongZhuXingMark;

    // 重要星曜宫位
    @Schema(description = "紫微星宫位")
    private Integer ziWeiXingGongWei;

    @Schema(description = "天府星宫位")
    private Integer tianFuXingGongWei;

    @Schema(description = "紫微星宫位地支")
    private String ziWeiXingGongDiZhi;

    @Schema(description = "天府星宫位地支")
    private String tianFuXingGongDiZhi;

    // 四化星信息
    @Schema(description = "化禄星宫位")
    private Integer huaLuXingGongWei;

    @Schema(description = "化权星宫位")
    private Integer huaQuanXingGongWei;

    @Schema(description = "化科星宫位")
    private Integer huaKeXingGongWei;

    @Schema(description = "化忌星宫位")
    private Integer huaJiXingGongWei;

    @Schema(description = "化禄星宫位略解")
    private String huaLuXingGongWeiLueJie;

    @Schema(description = "化权星宫位略解")
    private String huaQuanXingGongWeiLueJie;

    @Schema(description = "化科星宫位略解")
    private String huaKeXingGongWeiLueJie;

    @Schema(description = "化忌星宫位略解")
    private String huaJiXingGongWeiLueJie;

    // 年干诸星
    @Schema(description = "年干诸星")
    private List<String> nianGanZhuXing;

    // 年支诸星
    @Schema(description = "年支诸星")
    private List<String> nianZhiZhuXing;

    // 时系诸星
    @Schema(description = "时系诸星")
    private List<String> shiXiZhuXing;

    // 小限
    @Schema(description = "小限")
    private List<String> xiaoXian;

    // 十二宫博士
    @Schema(description = "十二宫博士")
    private List<String> shiErGongBoShi;

    // 十二宫将军
    @Schema(description = "十二宫将军")
    private List<String> shiErGongJiangJun;

    // 十二宫岁建
    @Schema(description = "十二宫岁建")
    private List<String> shiErGongSuiJian;

    // 十二宫晦气
    @Schema(description = "十二宫晦气")
    private List<String> shiErGongHuiQi;

    // 十二宫丧门
    @Schema(description = "十二宫丧门")
    private List<String> shiErGongSangMen;

    // 十二宫贯索
    @Schema(description = "十二宫贯索")
    private List<String> shiErGongGuanSuo;

    // 十二宫官符
    @Schema(description = "十二宫官符")
    private List<String> shiErGongGuanFu;

    // 十二宫小耗
    @Schema(description = "十二宫小耗")
    private List<String> shiErGongXiaoHao;

    // 十二宫大耗
    @Schema(description = "十二宫大耗")
    private List<String> shiErGongDaHao;

    // 十二宫龙德
    @Schema(description = "十二宫龙德")
    private List<String> shiErGongLongDe;

    // 十二宫白虎
    @Schema(description = "十二宫白虎")
    private List<String> shiErGongBaiHu;

    // 十二宫天德
    @Schema(description = "十二宫天德")
    private List<String> shiErGongTianDe;

    // 十二宫吊客
    @Schema(description = "十二宫吊客")
    private List<String> shiErGongDiaoKe;

    // 十二宫病符
    @Schema(description = "十二宫病符")
    private List<String> shiErGongBingFu;

    // 十二宫四化星
    @Schema(description = "十二宫四化星")
    private List<List<String>> shiErGongSiHuaXing;

    // 流年信息
    @Schema(description = "流年支")
    private String liuNianZhi;

    @Schema(description = "流年太岁")
    private String liuNianTaiSui;

    @Schema(description = "流年岁驿")
    private String liuNianSuiYi;

    @Schema(description = "流年息神")
    private String liuNianXiShen;

    @Schema(description = "流年华盖")
    private String liuNianHuaGai;

    @Schema(description = "流年劫煞")
    private String liuNianJieSha;

    @Schema(description = "流年灾煞")
    private String liuNianZaiSha;

    @Schema(description = "流年天煞")
    private String liuNianTianSha;

    @Schema(description = "流年指背")
    private String liuNianZhiBei;

    @Schema(description = "流年咸池")
    private String liuNianXianChi;

    @Schema(description = "流年月煞")
    private String liuNianYueSha;

    @Schema(description = "流年亡神")
    private String liuNianWangShen;
}
