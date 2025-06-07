const wuxingColors = {
  '木': '#4CAF50', // Green
  '火': '#F44336', // Red
  '土': '#A0522D', // Sienna - a more earthy brown
  '金': '#FFC107', // Amber - a more readable gold
  '水': '#2196F3', // Blue
};

const ganWuxing = {
  '甲': '木', '乙': '木', '丙': '火', '丁': '火', '戊': '土', '己': '土',
  '庚': '金', '辛': '金', '壬': '水', '癸': '水'
};

const zhiWuxing = {
  '子': '水', '丑': '土', '寅': '木', '卯': '木', '辰': '土', '巳': '火',
  '午': '火', '未': '土', '申': '金', '酉': '金', '戌': '土', '亥': '水'
};

const GUA_WUXING = { '乾': '金', '兑': '金', '离': '火', '震': '木', '巽': '木', '坎': '水', '坤': '土', '艮': '土' };
const JIJIE_WUXING_KEYWORDS = { '春': '木', '夏': '火', '秋': '金', '冬': '水' };

const wuxingChars = Object.keys(wuxingColors);
const ganZhiChars = [...Object.keys(ganWuxing), ...Object.keys(zhiWuxing)];
export const ALL_WUXING_RELATED_CHARS = [...wuxingChars, ...ganZhiChars];

/**
 * 智能获取五行颜色
 * @param {string} text 输入的文字，如: "木", "丙", "杨柳木", "离", "仲夏", "火旺"
 * @returns {string | undefined} 十六进制颜色代码
 */
export const getWuxingColor = (text) => {
  if (!text) {
    return undefined;
  }

  // 优先级1: 直接匹配五行、天干、地支、八卦 (单个字符)
  if (text.length === 1) {
    if (wuxingColors[text]) return wuxingColors[text];
    const ganWuxingVal = ganWuxing[text];
    if (ganWuxingVal) return wuxingColors[ganWuxingVal];
    const zhiWuxingVal = zhiWuxing[text];
    if (zhiWuxingVal) return wuxingColors[zhiWuxingVal];
    if (GUA_WUXING[text]) return wuxingColors[GUA_WUXING[text]];
  }

  // 优先级2: 检查字符串开头是否为五行 (处理 "火旺")
  const firstChar = text.charAt(0);
  if (wuxingColors[firstChar]) {
    return wuxingColors[firstChar];
  }

  // 优先级3: 检查字符串末尾是否为五行 (处理纳音 "杨柳木")
  const lastChar = text.slice(-1);
  if (wuxingColors[lastChar]) {
    return wuxingColors[lastChar];
  }

  // 优先级4: 检查是否包含天干或地支 (处理 "丙火")
  for (const char of ganZhiChars) {
    if (text.includes(char)) {
      const wuxing = ganWuxing[char] || zhiWuxing[char];
      return wuxingColors[wuxing];
    }
  }

  // 优先级5: 检查是否包含季节关键字 (处理 "仲夏")
  for (const keyword in JIJIE_WUXING_KEYWORDS) {
    if (text.includes(keyword)) {
      return wuxingColors[JIJIE_WUXING_KEYWORDS[keyword]];
    }
  }

  return undefined;
}; 
 