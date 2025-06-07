/**
 * 六爻排盘记录的增删改查API
 */
import request from '@/services/request.js';

export function saveLiuYaoRecordApi(userId, liuYaoDto) {
  return request.post('/records/liuyao/save', liuYaoDto, { params: { userId } });
}

export function getLiuYaoRecordsByUserIdApi(userId) {
  return request.get(`/records/liuyao/user/${userId}`);
}

export function getLiuYaoRecordApi(id, userId) {
  return request.get(`/records/liuyao/${id}`, { params: { userId } });
}

export function updateLiuYaoRecordApi(id, userId, liuYaoDto) {
  return request.put(`/records/liuyao/${id}`, liuYaoDto, { params: { userId } });
}

export function deleteLiuYaoRecordApi(id, userId) {
  return request.delete(`/records/liuyao/${id}`, { params: { userId } });
} 
