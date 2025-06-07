/**
 * 认证相关API (登录、注册、登出)
 */
import request from '@/services/request.js';

export function loginApi(username, password) {
  const formData = new URLSearchParams();
  formData.append('username', username);
  formData.append('password', password);
  return request({
    url: '/auth/login',
    method: 'post',
    data: formData,
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  });
}

export function registerApi(userData) {
  return request.post('/auth/register', userData);
}

export function logoutApi(userId) {
  return request.post(`/auth/logout?userId=${userId}`);
}
