package skm.core.bazi.utils;

import com.nlf.calendar.JieQi;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.eightchar.DaYun;
import com.nlf.calendar.eightchar.LiuNian;
import com.nlf.calendar.eightchar.LiuYue;
import com.nlf.calendar.eightchar.XiaoYun;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import skm.core.bazi.maps.BaZiDaYunLiuNianMap;
import skm.core.bazi.maps.BaZiJiChuMap;
import skm.utils.DateUtil;

import java.util.*;

/**
 * 八字-大运流年工具
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
public class BaZiDaYunLiuNianUtil {

    /**
     * 日干
     */
    private String dayGan;
    /**
     * 性别
     */
    private String sex;
    /**
     * 起运流派
     */
    private int qiYunLiuPai;
    /**
     * 公历日期
     */
    private Solar solar;
    /**
     * 农历日期
     */
    private Lunar lunar;

    /**
     * 大运
     */
    private List<List<String>> daYun;
    /**
     * 流年
     */
    private List<List<String>> liuNian;
    /**
     * 小运
     */
    private List<List<String>> xiaoYun;
    /**
     * 流月
     */
    private List<List<String>> liuYue;
    /**
     * 流日
     */
    private List<List<String>> liuRi;
    /**
     * 流时
     */
    private List<List<String>> liuShi;

    /**
     * 大运原数据
     */
    protected DaYun[] daYunSource;
    /**
     * 流年原数据
     */
    protected LiuNian[] liuNianSource;
    /**
     * 小运持续年数
     */
    protected int xiaoYunYearSum;

    /**
     * 初始化
     *
     * @param dayGan      日干
     * @param sex         性别（男、女）
     * @param qiYunLiuPai 起运流派（0:按天数和时辰数计算：3天1年、1天4个月、1时辰10天。1:按分钟数计算：4320分=1年、360分=1月、12分=1天、1分=2小时）
     * @param solarDate   公历日期
     */
    public BaZiDaYunLiuNianUtil(String dayGan, String sex, int qiYunLiuPai, Date solarDate) {
        this.dayGan = dayGan; // 日干
        this.sex = sex; // 性别
        this.qiYunLiuPai = qiYunLiuPai; // 起运流派
        this.solar = new Solar(solarDate); // 初始化公历日期
        this.lunar = getSolar().getLunar(); // 初始化农历日期
    }

    /**
     * 大运数据（干支、天干十神、地支十神、公历日期、公历年、年龄）
     */
    public void daYun() {
        List<List<String>> daYun = new ArrayList<>(); // 大运

        // 1、添加全部大运数据
        DaYun[] daYunSource = getLunar().getEightChar().getYun(("男".equals(getSex()) ? 1 : 0), getQiYunLiuPai()).getDaYun(13); // 获取大运原数据
        String ganShen = ""; // 天干十神
        String zhiShen = ""; // 地支十神
        for (DaYun dy : daYunSource) {
            String ganZhi = dy.getGanZhi(); // 干支
            if (StringUtils.isNotBlank(ganZhi)) {
                List<String> shiShen = getShiShen(ganZhi);
                ganShen = shiShen.get(0); // 天干十神
                zhiShen = shiShen.get(1); // 地支十神
            }
            String solarDate = DateUtil.getDateStr(dy.getStartYear(), 1, 1, 0, 0, 0); // 公历日期
            daYun.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, String.valueOf(dy.getStartYear()), String.valueOf(dy.getStartAge()))); // 干支、天干十神、地支十神、公历日期、公历年、年龄
        }

        // 2、计算并设置小运持续年数（根据公历年计算）
        this.xiaoYunYearSum = Integer.parseInt(daYun.get(1).get(4)) - Integer.parseInt(daYun.get(0).get(4));

        // 3、处理第一轮大运的年龄数据
        if (getXiaoYunYearSum() < 1) {
            // 3.1、若小运持续年数小于1年，则将大运的第一轮数据的岁数重置为：1~10
            daYun.set(0, daYun.get(0)).set(5, "1~10");
        } else if (getXiaoYunYearSum() == 1) {
            // 3.2、若小运持续年数等于1年，则将大运的第一轮数据的岁数重置为：1
            daYun.set(0, daYun.get(0)).set(5, String.valueOf(getXiaoYunYearSum()));
        } else {
            // 3.3、若小运持续年数大于1年，则将大运的第一轮数据的岁数重置为：1~?
            daYun.set(0, daYun.get(0)).set(5, "1~" + getXiaoYunYearSum());
        }

        // 4、处理全部大运岁数
        for (int i = 1; i < daYun.size() - 1; i++) {
            int startAge = Integer.parseInt(daYun.get(i).get(5));
            int endAge = startAge + 9;
            daYun.set(i, daYun.get(i)).set(5, startAge + "~" + endAge);
        }

        // 5、设置数据
        this.daYun = daYun; // 大运
        this.daYunSource = daYunSource; // 大运原数据
    }

    /**
     * 流年数据（干支、天干十神、地支十神、公历日期、公历年、年龄）
     *
     * @param daYunLun 大运轮
     */
    public void liuNian(int daYunLun) {
        // 1、添加全部流年原数据
        LiuNian[] liuNianSource = null;
        List<List<String>> liuNianSourceTemporary = new ArrayList<>(); // 临时存储流年原数据
        for (DaYun dy : getDaYunSource()) {
            liuNianSource = dy.getLiuNian(10); // 获取流年
            for (LiuNian ln : liuNianSource) {
                liuNianSourceTemporary.add(Arrays.asList(ln.getGanZhi(), String.valueOf(ln.getYear()), String.valueOf(ln.getAge()))); // 干支、公历年、年龄
            }
        }

        // 2、计算开始索引与结束索引
        int start = 0; // 开始索引
        int end; // 结束索引
        if (daYunLun == 1) {
            if (getXiaoYunYearSum() == 0) {
                end = 10; // 结束索引
            } else {
                end = getXiaoYunYearSum(); // 结束索引
            }
        } else {
            start = getXiaoYunYearSum() + 10 * (daYunLun - 2); // 开始索引
            end = start + 10; // 结束索引
        }

        // 3、添加流年数据
        String ganShen = ""; // 天干十神
        String zhiShen = ""; // 地支十神
        List<List<String>> liuNian = new ArrayList<>(); // 流年
        for (int i = start; i < end; i++) {
            List<String> ln = liuNianSourceTemporary.get(i);
            String ganZhi = ln.get(0); // 干支
            if (StringUtils.isNotBlank(ganZhi)) {
                List<String> shiShen = getShiShen(ganZhi);
                ganShen = shiShen.get(0); // 天干十神
                zhiShen = shiShen.get(1); // 地支十神
            }
            String solarDate = DateUtil.getDateStr(Integer.parseInt(ln.get(1)), 1, 1, 0, 0, 0); // 公历日期
            liuNian.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, ln.get(1), ln.get(2))); // 干支、天干十神、地支十神、公历日期、公历年、年龄
        }

        // 4、设置数据
        this.liuNian = liuNian; // 流年
        this.liuNianSource = liuNianSource; // 流年原数据
    }

    /**
     * 小运数据（干支、天干十神、地支十神、公历日期、公历年、年龄）
     *
     * @param daYunLun 大运轮
     */
    public void xiaoYun(int daYunLun) {
        // 1、添加全部小运数据
        List<List<String>> xiaoYunSourceTemporary = new ArrayList<>(); // 临时存储小运原数据
        for (DaYun dy : getDaYunSource()) {
            XiaoYun[] xiaoYunSource = dy.getXiaoYun(10); // 获取小运
            for (XiaoYun xy : xiaoYunSource) {
                xiaoYunSourceTemporary.add(Arrays.asList(xy.getGanZhi(), String.valueOf(xy.getYear()), String.valueOf(xy.getAge()))); // 干支、公历年、年龄
            }
        }

        // 2、计算开始索引与结束索引
        int start = 0; // 开始索引
        int end; // 结束索引
        if (daYunLun == 1) {
            if (getXiaoYunYearSum() == 0) {
                end = 10; // 结束索引
            } else {
                end = getXiaoYunYearSum(); // 结束索引
            }
        } else {
            start = getXiaoYunYearSum() + 10 * (daYunLun - 2); // 开始索引
            end = start + 10; // 结束索引
        }

        // 3、添加小运数据
        String ganShen = ""; // 天干十神
        String zhiShen = ""; // 地支十神
        List<List<String>> xiaoYun = new ArrayList<>(); // 小运
        for (int i = start; i < end; i++) {
            List<String> xy = xiaoYunSourceTemporary.get(i);
            String ganZhi = xy.get(0); // 干支
            if (StringUtils.isNotBlank(ganZhi)) {
                List<String> shiShen = getShiShen(ganZhi);
                ganShen = shiShen.get(0); // 天干十神
                zhiShen = shiShen.get(1); // 地支十神
            }
            String solarDate = DateUtil.getDateStr(Integer.parseInt(xy.get(1)), 1, 1, 0, 0, 0); // 公历日期
            xiaoYun.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, xy.get(1), xy.get(2))); // 干支、天干十神、地支十神、公历日期、公历年、年龄
        }

        // 4、设置数据
        this.xiaoYun = xiaoYun; // 小运
    }

    /**
     * 计算并返回十神（天干十神、地支十神）
     *
     * @param ganZhi 干支
     * @return 十神（天干十神、地支十神）
     */
    private List<String> getShiShen(String ganZhi) {
        Map<String, String> shiShenMap = BaZiJiChuMap.SHI_SHEN; // 十神（日干+其他干支为键）

        List<String> shiShen = new ArrayList<>();
        String ganShiShen = shiShenMap.get(getDayGan() + ganZhi.charAt(0)); // 天干十神
        String zhiShiShen = shiShenMap.get(getDayGan() + ganZhi.charAt(1)); // 地支十神
        shiShen.add(ganShiShen);
        shiShen.add(zhiShiShen);

        return shiShen;
    }

    /**
     * 流月数据（干支、天干十神、地支十神、公历日期、公历几月几日、十二节）
     *
     * @param daYunLun   大运轮
     * @param liuNianLun 流年轮
     * @param solarYear  公历年
     */
    public void liuYue(int daYunLun, int liuNianLun, int solarYear) {
        // 1、添加全部大运流月数据
        Map<String, List<String>> liuYueShiErJieSolarMonthDay = getLiuYueShiErJieSolarMonthDay(solarYear);// 计算流月的十二节公历月日、农历月、十二节
        List<String> solarMonthDay = liuYueShiErJieSolarMonthDay.get("solarMonthDay"); // 十二节公历月日
        List<String> lunarMonth = liuYueShiErJieSolarMonthDay.get("lunarMonth"); // 十二节农历月
        List<String> jie = liuYueShiErJieSolarMonthDay.get("jie"); // 十二节
        List<List<String>> daYunLiuYueTemporary = new ArrayList<>(); // 临时存储全部大运流月
        for (LiuNian ln : getLiuNianSource()) {
            LiuYue[] liuYueSource = ln.getLiuYue(); // 获取流月
            for (int i = 0; i < liuYueSource.length; i++) {
                daYunLiuYueTemporary.add(Arrays.asList(liuYueSource[i].getGanZhi(), BaZiDaYunLiuNianMap.SHI_ER_SOLAR_MONTH[i], solarMonthDay.get(i), lunarMonth.get(i), jie.get(i))); // 干支、公历月、公历几月几日、十二节农历月、十二节
            }
        }

        // 2、遍历大运流月，查找月干支所处的索引
        String liChunMonthGanZhi; // 立春当月的干支
        int monthGanZhiIndex = 0; // 月干支所处的索引
        Lunar lunar = new Solar(getSolar().getYear(), 2, 10).getLunar();
        int dayOffset = 0; // 天数偏移量
        if ("小寒".equals(lunar.getPrevJie().toString())) {
            // 2.1、上一节为[小寒]时，则向后推算日期，直至当天节为[立春]停止
            while (true) {
                dayOffset++; // 依次向后判断当月是否处于立春节
                lunar = lunar.next(dayOffset); // 向后n天
                if (!"小寒".equals(lunar.getPrevJie().toString())) {
                    liChunMonthGanZhi = lunar.getMonthInGanZhiExact(); // 立春当月的干支（以节交接时刻起算）
                    break;
                }
            }
        } else if ("惊蛰".equals(lunar.getNextJie().toString())) {
            // 2.2、下一节为[惊蛰]时，则向前推算日期，直至当天节为[立春]停止
            while (true) {
                dayOffset++; // 依次向前判断当月是否处于立春节
                lunar = lunar.next(-dayOffset); // 向前n天
                if (!"惊蛰".equals(lunar.getPrevJie().toString())) {
                    liChunMonthGanZhi = lunar.getMonthInGanZhiExact(); // 立春当月的干支（以节交接时刻起算）
                    break;
                }
            }
        } else {
            // 2.3、上一节为[立春]时，则说明此日在立春当月
            liChunMonthGanZhi = lunar.getMonthInGanZhiExact(); // 立春当月的干支（以节交接时刻起算）
        }
        for (int i = 0; i < daYunLiuYueTemporary.size(); i++) {
            String ganZhi = daYunLiuYueTemporary.get(i).get(0);
            if (ganZhi.equals(liChunMonthGanZhi)) {
                monthGanZhiIndex = i;
                break;
            }
        }
        if (monthGanZhiIndex == 1 || getXiaoYunYearSum() == 10) monthGanZhiIndex = 0;

        // 3、从月干支索引处向后依次添加流月数据
        List<List<String>> xiaoYunLiuYueTemporary = new ArrayList<>(); // 临时存储全部小运流月
        int xiaoYunLiuYueCount = getXiaoYunYearSum() * 12;
        for (int i = 0; i < xiaoYunLiuYueCount; i++) {
            int count = i + monthGanZhiIndex;
            if (count > 119) {
                i = 0;
                count = 59;
                if (xiaoYunLiuYueCount > 59) xiaoYunLiuYueCount -= 59;
            } else {
                count = i + monthGanZhiIndex;
            }
            List<String> ly = daYunLiuYueTemporary.get(count);
            xiaoYunLiuYueTemporary.add(Arrays.asList(ly.get(0), ly.get(1), ly.get(2), ly.get(3), ly.get(4))); // 干支、公历月、公历几月几日、十二节农历月、十二节
        }

        String ganShen = ""; // 天干十神
        String zhiShen = ""; // 地支十神
        List<List<String>> liuYue = new ArrayList<>(); // 流月

        // 4、添加流月数据
        int start; // 开始索引
        int end; // 结束索引
        if (daYunLun == 1) {
            if (getXiaoYunYearSum() == 0) {
                // 4.1、第一轮大运，若小运持续的年数为0，则获取第一轮大运中第一到十二轮流月数据（说明：当起运时间小于1年时才会出现这种情况）
                for (int i = 0; i < 12; i++) {
                    List<String> ly = daYunLiuYueTemporary.get(i);
                    String ganZhi = ly.get(0); // 干支
                    if (StringUtils.isNotBlank(ganZhi)) {
                        List<String> shiShen = getShiShen(ganZhi);
                        ganShen = shiShen.get(0); // 天干十神
                        zhiShen = shiShen.get(1); // 地支十神
                    }
                    if (i == 11) solarYear++;
                    String solarDate = DateUtil.getDateStr(solarYear, Integer.parseInt(ly.get(1)), 1, 0, 0, 0); // 公历日期
                    liuYue.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, ly.get(2), ly.get(3), ly.get(4))); // 干支、天干十神、地支十神、公历日期、公历几月几日、十二节
                }
            } else {
                // 4.2、第一轮大运，小运持续的年数不为0，则获取小运的十二轮流月数据
                start = 12 * (liuNianLun - 1); // 开始索引
                end = start + 12; // 结束索引
                for (int i = start; i < end; i++) {
                    List<String> ly = xiaoYunLiuYueTemporary.get(i);
                    String ganZhi = ly.get(0); // 干支
                    if (StringUtils.isNotBlank(ganZhi)) {
                        List<String> shiShen = getShiShen(ganZhi);
                        ganShen = shiShen.get(0); // 天干十神
                        zhiShen = shiShen.get(1); // 地支十神
                    }
                    if (i == end - 1) solarYear++;
                    String solarDate = DateUtil.getDateStr(solarYear, Integer.parseInt(ly.get(1)), 1, 0, 0, 0); // 公历日期
                    liuYue.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, ly.get(2), ly.get(3), ly.get(4))); // 干支、天干十神、地支十神、公历日期、公历几月几日、十二节
                }
            }
        } else {
            // 4.3、非第一轮大运
            start = (12 * (liuNianLun - 1)) == 120 ? 0 : 12 * (liuNianLun - 1); // 开始索引
            end = start + 12; // 结束索引
            for (int i = start; i < end; i++) {
                List<String> ly = daYunLiuYueTemporary.get(i);
                String ganZhi = ly.get(0); // 干支
                if (StringUtils.isNotBlank(ganZhi)) {
                    List<String> shiShen = getShiShen(ganZhi);
                    ganShen = shiShen.get(0); // 天干十神
                    zhiShen = shiShen.get(1); // 地支十神
                }
                if (i == end - 1) solarYear++;
                String solarDate = DateUtil.getDateStr(solarYear, Integer.parseInt(ly.get(1)), 1, 0, 0, 0); // 公历日期
                liuYue.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, ly.get(2), ly.get(3), ly.get(4))); // 干支、天干十神、地支十神、公历日期、公历几月几日、十二节
            }
        }

        // 5、设置数据
        this.liuYue = liuYue;
    }

    /**
     * 流日数据（干支、天干十神、地支十神、公历日期、公历日、农历日）
     *
     * @param solarYear  公历年
     * @param solarMonth 公历月
     * @param jieQiSet   节气排法（0:按天计算。1:按分钟计算）
     */
    public void liuRi(int solarYear, int solarMonth, int jieQiSet) {
        // 1、根据公历年、公历月初始化日期
        Lunar lunar = new Lunar(new Solar(solarYear, solarMonth, 15));

        // 2、获取上一节、下一节、上一节农历日期
        JieQi prevJie = lunar.getPrevJie(jieQiSet == 0); // 上一节
        JieQi nextJie = lunar.getNextJie(jieQiSet == 0); // 下一节
        Lunar startLunar = prevJie.getSolar().getLunar(); // 上一节农历日期

        String ganShen = ""; // 天干十神
        String zhiShen = ""; // 地支十神
        List<List<String>> liuRi = new ArrayList<>(); // 流日

        // 3、添加流日数据
        for (int i = 0; i < 60; i++) {
            Solar nextSolar = startLunar.next(i).getSolar(); // 向后推第n天的公历日期
            Lunar nextLunar = startLunar.next(i); // 向后推第n天的农历日期
            // 若未到下一节则继续添加
            if (!nextLunar.getJie().equals(nextJie.toString())) {
                String ganZhi = nextLunar.getDayInGanZhiExact2(); // 获取日干支（晚子时日柱算当天）
                if (StringUtils.isNotBlank(ganZhi)) {
                    List<String> shiShen = getShiShen(ganZhi);
                    ganShen = shiShen.get(0); // 天干十神
                    zhiShen = shiShen.get(1); // 地支十神
                }
                String solarDate = DateUtil.getDateStr(nextSolar.getYear(), nextSolar.getMonth(), nextSolar.getDay(), 0, 0, 0); // 公历日期
                liuRi.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, nextSolar.getDay() + "日", nextLunar.getDayInChinese())); // 干支、天干十神、地支十神、公历日期、公历日、农历日
            } else break;
        }

        // 4、设置数据
        this.liuRi = liuRi;
    }

    /**
     * 流时数据（干支、天干十神、地支十神、公历日期、公历时、汉代命名）
     *
     * @param solarYear  公历年
     * @param solarMonth 公历月
     * @param solarDay   公历日
     */
    public void liuShi(int solarYear, int solarMonth, int solarDay) {
        String ganShen = ""; // 天干十神
        String zhiShen = ""; // 地支十神
        List<List<String>> liuShi = new ArrayList<>(); // 流时

        // 1、添加流时数据
        int hour = 0; // 小时数
        Solar solar = new Solar(solarYear, solarMonth, solarDay);
        for (int i = 0; i < 12; i++) {
            // 通过公历日期初始化
            Calendar cal = Calendar.getInstance();
            cal.set(solar.getYear(), solar.getMonth() - 1, solar.getDay(), hour, 0, 0);
            Lunar lunar = new Lunar(cal.getTime());
            String ganZhi = lunar.getTimeInGanZhi(); // 干支
            if (StringUtils.isNotBlank(ganZhi)) {
                List<String> shiShen = getShiShen(lunar.getTimeInGanZhi());
                ganShen = shiShen.get(0); // 天干十神
                zhiShen = shiShen.get(1); // 地支十神
            }
            String solarDate = DateUtil.getDateStr(solarYear, solarMonth, solarDay, BaZiDaYunLiuNianMap.SHI_ER_SHI_2[i], 0, 0); // 公历日期
            liuShi.add(Arrays.asList(ganZhi, ganShen, zhiShen, solarDate, BaZiDaYunLiuNianMap.SHI_ER_SHI[i], BaZiDaYunLiuNianMap.DI_ZHI_HAN_MING[i])); // 干支、天干十神、地支十神、公历日期、公历时、汉代命名
            hour += 2;
        }

        // 2、设置数据
        this.liuShi = liuShi;
    }

    /**
     * 计算流月的十二节公历月日、农历月、十二节
     *
     * @param solarYear 公历年
     * @return 流月的十二节公历月日、农历月、十二节
     */
    private Map<String, List<String>> getLiuYueShiErJieSolarMonthDay(int solarYear) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> solarMonthDay = new ArrayList<>(); // 十二节公历月日
        List<String> lunarMonth = new ArrayList<>(); // 十二节农历月
        List<String> jie = new ArrayList<>(); // 十二节

        Lunar lunar = new Solar(solarYear, 1, 1).getLunar();

        int i = 0; // 十二节索引
        int day = 0; // 向后查询的天数
        while (true) {
            Lunar nextLunar = lunar.next(day); // 向后第n天的农历日期
            if (nextLunar.getJie().equals(BaZiDaYunLiuNianMap.SHI_ER_JIE[i])) {
                Solar nextSolar = nextLunar.getSolar(); // 向后第n天的公历日期
                solarMonthDay.add(nextSolar.getMonth() + "月" + nextSolar.getDay() + "日"); // 十二节公历月日
                lunarMonth.add(nextLunar.getMonthInChinese() + "月"); // 十二节农历月
                jie.add(BaZiDaYunLiuNianMap.SHI_ER_JIE[i]); // 十二节
                map.put("solarMonthDay", solarMonthDay);
                map.put("lunarMonth", lunarMonth);
                map.put("jie", jie);
                i++;
                if (i > 11) break;
                day += 26;
            }
            day++;
        }

        return map;
    }



}
