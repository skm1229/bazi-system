/**
 * 内部函数：确保ID是字符串类型，避免JS精度问题
 * @param {number | string | null | undefined} id
 * @returns {string | null}
 */
function ensureStringId(id) {
  if (id === null || id === undefined) return null;
  return String(id);
}

/**
 * 从 localStorage 安全地获取用户ID字符串
 * @returns {string | null} 用户ID或null
 */
export function getUserIdFromStorage() {
  try {
    const userInfo = localStorage.getItem('userInfo');
    if (userInfo) {
      return ensureStringId(JSON.parse(userInfo).id);
    }
  } catch (error) {
    console.error('Error parsing user info from localStorage:', error);
  }
  return null;
}
