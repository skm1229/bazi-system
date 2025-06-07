/**
 * 提供基于五行的颜色映射
 */

// 核心数据：五行与颜色的映射
const WUXING_COLOR_MAP = {
  '木': '#27AE60', // 绿色
  '火': '#E74C3C', // 红色
  '土': 'saddlebrown',
  '金': '#F1C40F', // 黄色/金色
  '水': '#3498DB', // 蓝色
  'default': '#303133' // 默认颜色
};

// 核心数据：天干与五行的映射
const GAN_TO_WUXING_MAP = {
  '甲': '木', '乙': '木', '丙': '火', '丁': '火', '戊': '土', '己': '土',
  '庚': '金', '辛': '金', '壬': '水', '癸': '水'
};

// 核心数据：地支与五行的映射
const ZHI_TO_WUXING_MAP = {
  '子': '水', '亥': '水',
  '寅': '木', '卯': '木',
  '巳': '火', '午': '火',
  '申': '金', '酉': '金',
  '辰': '土', '戌': '土', '丑': '土', '未': '土'
};

/**
 * 新增：统一的五行颜色获取函数
 * @param {string} char - 单个字符，可以是天干、地支或五行本身
 * @returns {string} 颜色HEX值
 */
export const getWuxingColor = (char) => {
  if (!char) return WUXING_COLOR_MAP.default;
  
  // 1. 直接检查是否为五行字符
  if (WUXING_COLOR_MAP[char]) {
    return WUXING_COLOR_MAP[char];
  }

  // 2. 检查是否为天干
  const ganWuxing = GAN_TO_WUXING_MAP[char];
  if (ganWuxing) {
    return WUXING_COLOR_MAP[ganWuxing];
  }
  
  // 3. 检查是否为地支
  const zhiWuxing = ZHI_TO_WUXING_MAP[char];
  if (zhiWuxing) {
    return WUXING_COLOR_MAP[zhiWuxing];
  }
  
  // 4. 返回默认颜色
  return WUXING_COLOR_MAP.default;
};
