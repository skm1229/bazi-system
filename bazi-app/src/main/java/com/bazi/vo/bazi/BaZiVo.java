package com.bazi.vo.bazi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 八字结果VO - 基于参考代码完整结构设计
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "八字结果")
public class BaZiVo {

    // 基本信息
    @Schema(description = "姓名", example = "张三")
    private String name;

    @Schema(description = "性别", example = "男")
    private String sex;

    @Schema(description = "乾造/坤造", example = "乾造")
    private String zao;

    @Schema(description = "占事", example = "事业")
    private String occupy;

    @Schema(description = "地区", example = "北京市")
    private String address;

    // 日期时间信息
    @Schema(description = "公历日期字符串", example = "2003年1月15日00时00分")
    private String solarStr;

    @Schema(description = "农历日期字符串", example = "壬午年十二月初十 子时")
    private String lunarStr;

    @Schema(description = "公历日期")
    private Date solarDate;

    @Schema(description = "农历日期")
    private Date lunarDate;

    @Schema(description = "星期", example = "星期日")
    private String xingQi;

    @Schema(description = "季节", example = "冬")
    private String jiJie;

    @Schema(description = "生肖", example = "兔")
    private String shengXiao;

    @Schema(description = "星座", example = "摩羯座")
    private String xingZuo;

    @Schema(description = "月相", example = "朔月")
    private String yueXiang;

    @Schema(description = "月将", example = "登明")
    private String yueJiang;

    @Schema(description = "月将神", example = "亥将")
    private String yueJiangShen;

    @Schema(description = "五不遇时", example = "甲不遇申")
    private String wuBuYuShi;

    // 九星信息
    @Schema(description = "值年九星五行", example = "木")
    private String yearJiuXingWuXing;

    @Schema(description = "值月九星五行", example = "火")
    private String monthJiuXingWuXing;

    @Schema(description = "值日九星五行", example = "土")
    private String dayJiuXingWuXing;

    @Schema(description = "值时九星五行", example = "金")
    private String hourJiuXingWuXing;

    @Schema(description = "值年九星方位", example = "震")
    private String yearJiuXingFangWei;

    @Schema(description = "值月九星方位", example = "巽")
    private String monthJiuXingFangWei;

    @Schema(description = "值日九星方位", example = "坤")
    private String dayJiuXingFangWei;

    @Schema(description = "值时九星方位", example = "乾")
    private String hourJiuXingFangWei;

    @Schema(description = "值年九星方位描述", example = "正东")
    private String yearJiuXingFangWeiMiaoShu;

    @Schema(description = "值月九星方位描述", example = "东南")
    private String monthJiuXingFangWeiMiaoShu;

    @Schema(description = "值日九星方位描述", example = "西南")
    private String dayJiuXingFangWeiMiaoShu;

    @Schema(description = "值时九星方位描述", example = "西北")
    private String hourJiuXingFangWeiMiaoShu;

    // 冲煞信息
    @Schema(description = "年冲生肖", example = "鸡")
    private String yearChongShengXiao;

    @Schema(description = "月冲生肖", example = "马")
    private String monthChongShengXiao;

    @Schema(description = "日冲生肖", example = "猴")
    private String dayChongShengXiao;

    @Schema(description = "时冲生肖", example = "鼠")
    private String hourChongShengXiao;

    @Schema(description = "太岁类型", example = "顺")
    private String taiSuiType;

    // 八字信息
    @Schema(description = "八字", example = "[\"壬午\", \"癸丑\", \"戊子\", \"壬子\"]")
    private List<String> baZi;

    @Schema(description = "八字五行", example = "[\"水火\", \"水土\", \"土水\", \"水水\"]")
    private List<String> baZiWuXing;

    @Schema(description = "八字空亡", example = "[\"戌亥\", \"戌亥\", \"戌亥\", \"戌亥\"]")
    private List<String> baZiKongWang;

    @Schema(description = "八字纳音", example = "[\"杨柳木\", \"桑柘木\", \"霹雳火\", \"桑柘木\"]")
    private List<String> baZiNaYin;

    // 干支信息
    @Schema(description = "年干", example = "壬")
    private String yearGan;

    @Schema(description = "月干", example = "癸")
    private String monthGan;

    @Schema(description = "日干", example = "戊")
    private String dayGan;

    @Schema(description = "时干", example = "壬")
    private String hourGan;

    @Schema(description = "年干支主星", example = "正财")
    private String yearGanZhiZhuXing;

    @Schema(description = "月干支主星", example = "偏印")
    private String monthGanZhiZhuXing;

    @Schema(description = "日干支主星", example = "比肩")
    private String dayGanZhiZhuXing;

    @Schema(description = "时干支主星", example = "正财")
    private String hourGanZhiZhuXing;

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

    @Schema(description = "年干五行")
    private String yearGanWuXing;

    @Schema(description = "月干五行")
    private String monthGanWuXing;

    @Schema(description = "日干五行")
    private String dayGanWuXing;

    @Schema(description = "时干五行")
    private String hourGanWuXing;

    @Schema(description = "年支五行")
    private String yearZhiWuXing;

    @Schema(description = "月支五行")
    private String monthZhiWuXing;

    @Schema(description = "日支五行")
    private String dayZhiWuXing;

    @Schema(description = "时支五行")
    private String hourZhiWuXing;

    @Schema(description = "年干支五行")
    private String yearGanZhiWuXing;

    @Schema(description = "月干支五行")
    private String monthGanZhiWuXing;

    @Schema(description = "日干支五行")
    private String dayGanZhiWuXing;

    @Schema(description = "时干支五行")
    private String hourGanZhiWuXing;

    // 藏干信息
    @Schema(description = "年支藏干")
    private List<String> yearZhiCangGan;

    @Schema(description = "月支藏干")
    private List<String> monthZhiCangGan;

    @Schema(description = "日支藏干")
    private List<String> dayZhiCangGan;

    @Schema(description = "时支藏干")
    private List<String> hourZhiCangGan;

    // 十神信息
    @Schema(description = "年干支副星")
    private List<String> yearGanZhiFuXing;

    @Schema(description = "月干支副星")
    private List<String> monthGanZhiFuXing;

    @Schema(description = "日干支副星")
    private List<String> dayGanZhiFuXing;

    @Schema(description = "时干支副星")
    private List<String> hourGanZhiFuXing;

    // 自坐
    @Schema(description = "年干支自坐")
    private String yearGanZhiZiZuo;

    @Schema(description = "月干支自坐")
    private String monthGanZhiZiZuo;

    @Schema(description = "日干支自坐")
    private String dayGanZhiZiZuo;

    @Schema(description = "时干支自坐")
    private String hourGanZhiZiZuo;

    // 星运
    @Schema(description = "年干支星运")
    private String yearGanZhiXingYun;

    @Schema(description = "月干支星运")
    private String monthGanZhiXingYun;

    @Schema(description = "日干支星运")
    private String dayGanZhiXingYun;

    @Schema(description = "时干支星运")
    private String hourGanZhiXingYun;

    // 纳音
    @Schema(description = "年干支纳音")
    private String yearGanZhiNaYin;

    @Schema(description = "月干支纳音")
    private String monthGanZhiNaYin;

    @Schema(description = "日干支纳音")
    private String dayGanZhiNaYin;

    @Schema(description = "时干支纳音")
    private String hourGanZhiNaYin;

    // 空亡
    @Schema(description = "年干支空亡")
    private String yearGanZhiKongWang;

    @Schema(description = "月干支空亡")
    private String monthGanZhiKongWang;

    @Schema(description = "日干支空亡")
    private String dayGanZhiKongWang;

    @Schema(description = "时干支空亡")
    private String hourGanZhiKongWang;

    // 神煞信息
    @Schema(description = "年干支神煞")
    private List<String> yearGanZhiShenSha;

    @Schema(description = "月干支神煞")
    private List<String> monthGanZhiShenSha;

    @Schema(description = "日干支神煞")
    private List<String> dayGanZhiShenSha;

    @Schema(description = "时干支神煞")
    private List<String> hourGanZhiShenSha;

    @Schema(description = "小儿关煞")
    private List<String> xiaoErGuanSha;

    // 五行统计
    @Schema(description = "五行木数量（天干）")
    private int ganMuCount;

    @Schema(description = "五行火数量（天干）")
    private int ganHuoCount;

    @Schema(description = "五行土数量（天干）")
    private int ganTuCount;

    @Schema(description = "五行金数量（天干）")
    private int ganJinCount;

    @Schema(description = "五行水数量（天干）")
    private int ganShuiCount;

    @Schema(description = "五行木数量（地支）")
    private int zhiMuCount;

    @Schema(description = "五行火数量（地支）")
    private int zhiHuoCount;

    @Schema(description = "五行土数量（地支）")
    private int zhiTuCount;

    @Schema(description = "五行金数量（地支）")
    private int zhiJinCount;

    @Schema(description = "五行水数量（地支）")
    private int zhiShuiCount;

    @Schema(description = "五行木数量（藏干）")
    private int cangGanMuCount;

    @Schema(description = "五行火数量（藏干）")
    private int cangGanHuoCount;

    @Schema(description = "五行土数量（藏干）")
    private int cangGanTuCount;

    @Schema(description = "五行金数量（藏干）")
    private int cangGanJinCount;

    @Schema(description = "五行水数量（藏干）")
    private int cangGanShuiCount;

    // 留意信息
    @Schema(description = "天干留意")
    private List<String> tianGanLiuYi;

    @Schema(description = "地支留意")
    private List<String> diZhiLiuYi;

    // 节气信息
    @Schema(description = "人元司令分野")
    private String renYuan;

    @Schema(description = "出生节气")
    private String birthSolarTerms;

    @Schema(description = "上一节")
    private String prevJie;

    @Schema(description = "上一节日期")
    private String prevJieDateStr;

    @Schema(description = "距上一节天数")
    private int prevJieDayNumber;

    @Schema(description = "下一节")
    private String nextJie;

    @Schema(description = "下一节日期")
    private String nextJieDateStr;

    @Schema(description = "距下一节天数")
    private int nextJieDayNumber;

    @Schema(description = "出生气")
    private String birthQi;

    @Schema(description = "上一气")
    private String prevQi;

    @Schema(description = "上一气日期")
    private String prevQiDateStr;

    @Schema(description = "距上一气天数")
    private int prevQiDayNumber;

    @Schema(description = "下一气")
    private String nextQi;

    @Schema(description = "下一气日期")
    private String nextQiDateStr;

    @Schema(description = "距下一气天数")
    private int nextQiDayNumber;

    // 星宿信息
    @Schema(description = "星宿")
    private String xingXiu;

    @Schema(description = "星宿吉凶")
    private String xiuJiXiong;

    @Schema(description = "星宿吉凶歌诀")
    private String xiuJiXiongGeJue;

    @Schema(description = "彭祖百忌", example = "戊不受田田主不祥，子不问卜自惹祸殃。")
    private String pengZuBaiJi;

    // 胎命身信息
    @Schema(description = "胎元")
    private String taiYuan;

    @Schema(description = "胎息")
    private String taiXi;

    @Schema(description = "命宫")
    private String mingGong;

    @Schema(description = "身宫")
    private String shenGong;

    @Schema(description = "命卦")
    private String mingGua;

    @Schema(description = "命卦分析")
    private List<String> mingGuaFenXi;

    // 五行分析
    @Schema(description = "五行缺失")
    private List<String> wuXingQueShi;

    @Schema(description = "五行数量")
    private List<String> wuXingCount;

    @Schema(description = "五行旺衰")
    private List<String> wuXingWangShuai;

    @Schema(description = "身体强弱")
    private String shenTiQiangRuo;

    @Schema(description = "喜用神")
    private List<String> xiYongShen;

    @Schema(description = "喜用神方位")
    private List<String> xiYongShenFangWei;

    // 命理分析
    @Schema(description = "骨重")
    private String guZhong;

    @Schema(description = "骨重歌诀")
    private String guZhongGeJue;

    @Schema(description = "八字格局", example = "正官格")
    private String geJu;

    @Schema(description = "日柱论命")
    private String dayZhuLunMing;

    @Schema(description = "姻缘分析")
    private String yinYuanFenXi;

    @Schema(description = "五行分析")
    private String wuXingFenXi;

    // 运势信息
    @Schema(description = "大运")
    private List<List<String>> daYun;

    @Schema(description = "流年")
    private List<List<String>> liuNian;

    @Schema(description = "正财运年份、年龄、干支")
    private List<List<String>> zhengCaiYun;

    @Schema(description = "偏财运年份、年龄、干支")
    private List<List<String>> pianCaiYun;

    @Schema(description = "正桃花年份、年龄、干支")
    private List<List<String>> zhengTaoHua;

    @Schema(description = "偏桃花年份、年龄、干支")
    private List<List<String>> pianTaoHua;

    @Schema(description = "起运")
    private String qiYun;

    @Schema(description = "起运日期")
    private String qiYunDateStr;

    @Schema(description = "交运")
    private String jiaoYun;
}
