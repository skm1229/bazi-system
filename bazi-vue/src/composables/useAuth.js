/**
 * 认证相关的组合式函数
 * 封装了全局用户状态和所有认证操作（登录、注册、登出）
 */
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { 
  loginApi, 
  registerApi, 
  logoutApi,
} from '@/api/auth-api.js';
import { notify } from '@/services/notify.js';

// 模块级单例状态，确保全局唯一
const userInfo = ref(null);
const authToken = ref(null);

// 内部函数，用于设置和清除用户会话
const _setUserSession = (sessionData) => {
  // 如果传入 null 或 undefined，则视为登出
  if (!sessionData || !sessionData.token) {
    userInfo.value = null;
    authToken.value = null;
    localStorage.removeItem('userInfo');
    localStorage.removeItem('authToken');
    return;
  }

  const userData = { 
    id: sessionData.id, 
    username: sessionData.username,
    nickname: sessionData.nickname,
    email: sessionData.email,
  };
  userInfo.value = userData;
  authToken.value = sessionData.token;
  localStorage.setItem('userInfo', JSON.stringify(userData));
  localStorage.setItem('authToken', sessionData.token);
};

// 应用加载时，立即尝试从 localStorage 初始化状态
try {
  const storedUser = localStorage.getItem('userInfo');
  const storedToken = localStorage.getItem('authToken');
  if (storedUser && storedToken) {
    userInfo.value = JSON.parse(storedUser);
    authToken.value = storedToken;
  }
} catch (e) {
  _setUserSession(null); // 清理可能已损坏的数据
}

export function useAuth() {
  const router = useRouter();
  const isLoading = ref(false);

  // 计算属性，用于在UI中方便地判断用户是否登录
  const isLoggedIn = computed(() => !!authToken.value);

  /**
   * 登录方法
   * @param {object} credentials - 包含 username 和 password
   */
  const login = async (credentials) => {
    if (isLoading.value) return;
    isLoading.value = true;
    try {
      const data = await loginApi(credentials.username, credentials.password);
      _setUserSession(data);
      notify.success(`欢迎回来, ${data.nickname || data.username || '用户'}！`);
      router.push('/');
    } finally {
      isLoading.value = false;
    }
  };

  /**
   * 注册方法
   * @param {object} userData - 包含 username, password, 及可选的 nickname, email
   */
  const register = async (userData) => {
    if (isLoading.value) return;
    isLoading.value = true;
    try {
      const payload = {
        username: userData.username,
        password: userData.password,
      };
      if (userData.nickname) payload.nickname = userData.nickname;
      if (userData.email) payload.email = userData.email;
      
      await registerApi(payload);
      notify.success('账户已成功创建，请登录。');
      router.push('/login');
    } finally {
      isLoading.value = false;
    }
  };

  /**
   * 登出方法
   * @param {object} options - 可选参数，例如 { silent: true }
   */
  const logout = async (options = {}) => {
    try {
      if (userInfo.value?.id) {
        await logoutApi(userInfo.value.id);
      }
    } finally {
      _setUserSession(null);
      if (!options.silent) {
      notify.success('已成功退出登录');
      }
      router.replace('/login');
    }
  };

  /**
   * 安全地更新当前用户会话信息。
   * @param {object} newProfile - 包含要更新的用户信息
   */
  const updateUserSession = (newProfile) => {
    if (!authToken.value) return;
    const updatedSession = {
      ...userInfo.value,
      ...newProfile,
      token: authToken.value,
    };
    _setUserSession(updatedSession);
  };

  return { 
    userInfo,
    isLoggedIn,
    isLoading,
    login,
    register,
    logout,
    updateUserSession, // 暴露给 useUser 使用
  };
}
