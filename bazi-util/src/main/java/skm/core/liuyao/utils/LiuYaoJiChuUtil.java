package skm.core.liuyao.utils;

import java.util.*;

import skm.utils.CommonUtil;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import skm.core.liuyao.maps.LiuYaoJiChuMap;
import skm.core.liuyao.settings.LiuYaoJiChuSetting;

/**
 * 六爻 - 基础工具
 *
 * @author skm1229
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class LiuYaoJiChuUtil {

    /**
     * 获取日期
     *
     * @param liuYaoJiChuSetting 六爻 - 基础设置
     * @return 日期
     */
    public static Map<String, Object> getDate(LiuYaoJiChuSetting liuYaoJiChuSetting) {

        Solar solar;
        Lunar lunar;

        // 1、判断日期类型
        Date date = liuYaoJiChuSetting.getDate(); // 日期
        if (liuYaoJiChuSetting.getDateType() == 0) {
            // 1.1、按公历日期计算
            solar = new Solar(date); // 公历日期
            lunar = solar.getLunar(); // 农历日期
        } else {
            // 1.2、按农历日期计算
            try {
                lunar = new Lunar(date.getYear() + 1900, liuYaoJiChuSetting.getLeapMonthType() == 0 ? (date.getMonth() + 1) : -(date.getMonth() + 1), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()); // 农历日期
                solar = lunar.getSolar(); // 公历日期
            } catch (IllegalArgumentException e) {
//                lunar = new Lunar(); // 当前农历日期
//                solar = lunar.getSolar(); // 当前公历日期
                throw new IllegalArgumentException(e);
            }
        }

        // 2、添加日期并返回
        Map<String, Object> map = new HashMap<>();
        map.put("solar", solar); // 公历日期
        map.put("lunar", lunar); // 农历日期
        return map;

    }

    /**
     * 获取干支
     *
     * @param liuYaoJiChuSetting 六爻 - 基础设置
     * @param lunar              农历日期
     * @return 干支
     */
    public static Map<String, List<String>> getGanZhi(LiuYaoJiChuSetting liuYaoJiChuSetting, Lunar lunar) {

        List<String> list;
        Map<String, List<String>> map = new HashMap<>();

        // 1、判断年干支类型
        if (liuYaoJiChuSetting.getYearGanZhiType() == 0) {
            list = Arrays.asList(lunar.getYearGan(), lunar.getYearZhi(), lunar.getYearInGanZhi()); // 以正月初一作为新年的开始
        } else if (liuYaoJiChuSetting.getYearGanZhiType() == 1) {
            list = Arrays.asList(lunar.getYearGanByLiChun(), lunar.getYearZhiByLiChun(), lunar.getYearInGanZhiByLiChun()); // 以立春当天作为新年的开始
        } else {
            list = Arrays.asList(lunar.getYearGanExact(), lunar.getYearZhiExact(), lunar.getYearInGanZhiExact()); // 以立春交接的时刻作为新年的开始
        }
        map.put("yearGanZhi", list);

        // 2、判断月干支类型
        if (liuYaoJiChuSetting.getMonthGanZhiType() == 0) {
            list = Arrays.asList(lunar.getMonthGan(), lunar.getMonthZhi(), lunar.getMonthInGanZhi()); // 以节交接当天起算
        } else {
            list = Arrays.asList(lunar.getMonthGanExact(), lunar.getMonthZhiExact(), lunar.getMonthInGanZhiExact()); // 以节交接时刻起算
        }
        map.put("monthGanZhi", list);

        // 3、判断日干支类型
        if (liuYaoJiChuSetting.getDayGanZhiType() == 0) {
            list = Arrays.asList(lunar.getDayGanExact(), lunar.getDayZhiExact(), lunar.getDayInGanZhiExact()); // 晚子时日干支算明天
        } else {
            list = Arrays.asList(lunar.getDayGanExact2(), lunar.getDayZhiExact2(), lunar.getDayInGanZhiExact2()); // 晚子时日干支算当天
        }
        map.put("dayGanZhi", list);

        // 4、支持早子时和晚子时
        map.put("hourGanZhi", Arrays.asList(lunar.getTimeGan(), lunar.getTimeZhi(), lunar.getTimeInGanZhi()));

        // 5、返回干支
        return map;

    }

    /**
     * 获取六爻爻象、六爻爻象标识、六爻爻象标识名称（★可用于排盘模式扩展）
     *
     * @param liuYaoJiChuSetting 六爻 - 基础设置
     * @param lunar              农历日期
     * @param yearZhi            年支
     * @param hourZhi            时支
     * @return 六爻爻象、六爻爻象标识、六爻爻象标识名称
     */
    public static Map<String, List<String>> getLiuYaoData(LiuYaoJiChuSetting liuYaoJiChuSetting, Lunar lunar, String yearZhi, String hourZhi) {

        Map<String, List<String>> map = new HashMap<>();

        List<String> liuYaoAs = new ArrayList<>(); // 保存六爻爻象（如：[—, - -, - -, - -, - -, —]）
        List<String> liuYaoAsMark = new ArrayList<>(); // 保存六爻爻象标识（如：[○, ×, , ×, , ]）
        List<String> liuYaoAsMarkName = new ArrayList<>(); // 保存六爻爻象标识名称（如：[老阳, 老阴, 少阴, 老阴, 少阴, 少阳]）

        if (liuYaoJiChuSetting.getPaiPanType() == 0) {
            // ★★★ 1.1、日期排盘模式
            Map<String, Integer> diZhiShuMap = LiuYaoJiChuMap.DI_ZHI_SHU; // 地支对应的数字（地支为键）
            int yearNumber = diZhiShuMap.get(yearZhi);  // 年数
            int monthNumber = lunar.getMonth(); // 月数
            int dayNumber = lunar.getDay(); // 日数
            int hourNumber = diZhiShuMap.get(hourZhi); // 时数
            // 1.1.1、计算上卦数：（年数+月数+日数）÷8得出余数，若除尽则统一用8表示
            int shangGuaNumber = (yearNumber + monthNumber + dayNumber) % 8;
            shangGuaNumber = shangGuaNumber == 0 ? 8 : shangGuaNumber;
            // 1.1.2、计算下卦数：（年数+月数+日数+时数）÷8得出余数，若除尽则统一用8表示
            int xiaGuaNumber = (yearNumber + monthNumber + dayNumber + hourNumber) % 8;
            xiaGuaNumber = xiaGuaNumber == 0 ? 8 : xiaGuaNumber;
            // 1.1.3、计算动爻数：（年数+月数+日数+时数）÷6得出余数，若除尽则统一用6表示
            int dongYaoNumber = (yearNumber + monthNumber + dayNumber + hourNumber) % 6;
            dongYaoNumber = dongYaoNumber == 0 ? 6 : dongYaoNumber;
            // 1.1.4、根据上卦数和下卦数获取本卦卦象
            String benGuaAs = LiuYaoJiChuMap.LIU_SHI_SI_GUA_AS.get(Arrays.asList(shangGuaNumber, xiaGuaNumber));
            // 1.1.5、根据本卦卦象获取六爻爻象
            liuYaoAs = LiuYaoJiChuMap.LIU_SHI_SI_GUA_LIU_YAO_AS.get(benGuaAs);
            // 1.1.6、根据动爻数计算六爻标识
            liuYaoAsMark = CommonUtil.addCharToList(6, "");
            if ("—".equals(liuYaoAs.get(dongYaoNumber - 1))) {
                liuYaoAsMark.set(dongYaoNumber - 1, "○"); // 该爻为阳爻，则在该爻位上添加标识：○
            } else {
                liuYaoAsMark.set(dongYaoNumber - 1, "×"); // 该爻为阴爻，则在该爻位上添加标识：×
            }
            // 1.1.7、计算六爻爻象标识名称
            for (int i = 0; i < 6; i++) {
                if ("—".equals(liuYaoAs.get(i)) && "".equals(liuYaoAsMark.get(i))) liuYaoAsMarkName.add("少阳");
                if ("--".equals(liuYaoAs.get(i)) && "".equals(liuYaoAsMark.get(i))) liuYaoAsMarkName.add("少阴");
                if ("—".equals(liuYaoAs.get(i)) && "○".equals(liuYaoAsMark.get(i))) liuYaoAsMarkName.add("老阳");
                if ("--".equals(liuYaoAs.get(i)) && "×".equals(liuYaoAsMark.get(i))) liuYaoAsMarkName.add("老阴");
            }
        } else if (liuYaoJiChuSetting.getPaiPanType() == 1) {
            // ★★★ 1.2、自动排盘模式
            Map<List<Integer>, List<String>> autoRandomYaoMap = LiuYaoJiChuMap.AUTO_RANDOM_YAO; // 【少阳、少阴、老阳、老阴】对应信息
            // 1.2.1、计算六爻（循环6次，分别对应：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
            for (int i = 0; i < 6; i++) {
                List<Integer> randomList = CommonUtil.randomList(3, 1); // 随机产生0~1中的3个数字
                liuYaoAs.add(autoRandomYaoMap.get(randomList).get(0)); // 保存六爻爻象（顺序：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
                liuYaoAsMark.add(autoRandomYaoMap.get(randomList).get(1)); // 保存六爻爻象标识（顺序：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
                liuYaoAsMarkName.add(autoRandomYaoMap.get(randomList).get(2)); // 保存六爻爻象标识名称（顺序：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
            }

        } else if (liuYaoJiChuSetting.getPaiPanType() == 2) {
            // ★★★ 1.3、手动排盘模式
            Map<Integer, List<String>> manualRandomYaoMap = LiuYaoJiChuMap.MANUAL_RANDOM_YAO; // 【少阳、少阴、老阳、老阴】对应信息
            // 1.3.1、封装六爻
            List<Integer> packageList = CommonUtil.packageList(liuYaoJiChuSetting.getYiYao(), liuYaoJiChuSetting.getErYao(), liuYaoJiChuSetting.getSanYao(), liuYaoJiChuSetting.getSiYao(), liuYaoJiChuSetting.getWuYao(), liuYaoJiChuSetting.getLiuYao());
            // 1.3.2、计算六爻【循环6次，分别对应：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)】
            for (int i = 0; i < 6; i++) {
                liuYaoAs.add(manualRandomYaoMap.get(packageList.get(i)).get(0)); // 保存六爻爻象（顺序：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
                liuYaoAsMark.add(manualRandomYaoMap.get(packageList.get(i)).get(1)); // 保存六爻爻象标识（顺序：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
                liuYaoAsMarkName.add(manualRandomYaoMap.get(packageList.get(i)).get(2)); // 保存六爻爻象标识名称（顺序：初爻(一爻)、二爻、三爻、四爻、五爻、上爻(六爻)）
            }
        } else if (liuYaoJiChuSetting.getPaiPanType() == 3) {
            // ★★★ 1.4、在此可扩展更多排盘模式（建议：将每种排盘模式封装为单独方法）
            System.out.println("======================");
            System.out.println("在此可扩展更多排盘模式");
            System.out.println("======================");
        }

        // 2、设置并返回数据
        map.put("liuYaoAs", liuYaoAs); // 六爻爻象
        map.put("liuYaoAsMark", liuYaoAsMark); // 保存六爻爻象标识
        map.put("liuYaoAsMarkName", liuYaoAsMarkName); // 保存六爻爻象标识名称
        return map;

    }


}


