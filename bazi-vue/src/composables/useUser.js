/**
 * 用户管理相关的组合式函数
 * 封装了用户资料获取、更新、密码修改等操作
 */
import { ref } from 'vue';
import { useAuth } from './useAuth.js';
import {
  getUserInfoApi,
  updateUserInfoApi,
  changePasswordApi,
} from '@/api/user-api.js';
import { notify } from '@/services/notify.js';

/**
 * 提供管理当前已登录用户相关信息和操作的功能
 * @returns {object} 包含用户管理方法的对象
 */
export function useUser() {
  const { userInfo, logout, updateUserSession } = useAuth();
  const isLoading = ref(false);

  /**
   * 获取当前登录用户的完整个人资料
   * @returns {Promise<object|null>} 返回用户资料对象，失败则返回null
   */
  const fetchUserInfo = async () => {
    if (!userInfo.value?.id) return null;
    isLoading.value = true;
    try {
      const userProfile = await getUserInfoApi(userInfo.value.id);
      updateUserSession(userProfile); // 使用 useAuth 的方法更新全局状态
      return userProfile;
    } catch (error) {
      // 错误通知已在 request.js 中统一处理
      return null;
    } finally {
      isLoading.value = false;
    }
  };

  /**
   * 更新当前用户的个人资料
   * @param {object} profileData - 包含要更新的字段，如 nickname, email
   * @returns {Promise<void>}
   */
  const updateUserInfo = async (profileData) => {
    if (!userInfo.value?.id) {
      notify.error('用户未登录，无法更新信息。');
      return;
    }
    isLoading.value = true;
    try {
      const updatedUser = await updateUserInfoApi(userInfo.value.id, profileData);
      updateUserSession(updatedUser); // 使用 useAuth 的方法更新全局状态
      notify.success('个人信息更新成功！');
    } finally {
      isLoading.value = false;
    }
  };

  /**
   * 修改当前用户的密码
   * @param {object} passwordData - 包含 oldPassword 和 newPassword
   * @returns {Promise<void>}
   */
  const changePassword = async (passwordData) => {
    if (!userInfo.value?.id) {
      notify.error('用户未登录，无法修改密码。');
      return;
    }
    isLoading.value = true;
    try {
      await changePasswordApi(
        userInfo.value.id,
        passwordData.oldPassword,
        passwordData.newPassword
      );
      notify.success('密码修改成功，请重新登录。');
      await logout({ silent: true }); // 使用 useAuth 的登出方法
    } finally {
      isLoading.value = false;
    }
  };

  return {
    isLoading,
    fetchUserInfo,
    updateUserInfo,
    changePassword,
  };
} 
 