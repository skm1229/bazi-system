/**
 * 用户管理相关API
 */
import request from '@/services/request.js';

export function getUserInfoApi(userId) {
  return request.get('/user/info', { params: { userId } });
}

export function updateUserInfoApi(userId, profileData) {
  return request.put('/user/info', profileData, { params: { userId } });
}

export function changePasswordApi(userId, oldPassword, newPassword) {
  const params = new URLSearchParams();
  params.append('userId', userId);
  params.append('oldPassword', oldPassword);
  params.append('newPassword', newPassword);
  return request.post('/user/changePassword', params);
}
 