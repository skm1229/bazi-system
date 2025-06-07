/**
 * 将Date对象或可被解析的日期字符串格式化为 'YYYY-MM-DD HH:mm:ss'
 * @param {Date | string} dateInput - 日期输入
 * @returns {string | null} 格式化后的日期字符串，或在输入无效时返回null
 */
export const formatDateTime = (dateInput) => {
  const dateObj = dateInput instanceof Date ? dateInput : new Date(dateInput);
  
  if (isNaN(dateObj.getTime())) {
    console.error('formatDateTime: Invalid date input:', dateInput);
    return null;
  }

  const YYYY = dateObj.getFullYear();
  const MM = String(dateObj.getMonth() + 1).padStart(2, '0');
  const DD = String(dateObj.getDate()).padStart(2, '0');
  const HH = String(dateObj.getHours()).padStart(2, '0');
  const mm = String(dateObj.getMinutes()).padStart(2, '0');
  const ss = String(dateObj.getSeconds()).padStart(2, '0');

  return `${YYYY}-${MM}-${DD} ${HH}:${mm}:${ss}`;
};

/**
 * 将Date对象或可被解析的日期字符串格式化为 'YYYY年MM月DD日'
 * @param {Date | string} dateInput - 日期输入
 * @returns {string | null} 格式化后的日期字符串，或在输入无效时返回null
 */
export const formatDateToChinese = (dateInput) => {
  const dateObj = dateInput instanceof Date ? dateInput : new Date(dateInput);

  if (isNaN(dateObj.getTime())) {
    console.error('formatDateToChinese: Invalid date input:', dateInput);
    return null;
  }

  const YYYY = dateObj.getFullYear();
  const MM = String(dateObj.getMonth() + 1).padStart(2, '0');
  const DD = String(dateObj.getDate()).padStart(2, '0');

  return `${YYYY}年${MM}月${DD}日`;
};
