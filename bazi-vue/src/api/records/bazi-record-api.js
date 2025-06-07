/**
 * 八字排盘记录的增删改查API
 */
import request from '@/services/request.js';

export function saveBaZiRecordApi(userId, baziDto) {
  return request.post('/records/bazi/save', baziDto, { params: { userId } });
}

export function getBaZiRecordsByUserIdApi(userId) {
  return request.get(`/records/bazi/user/${userId}`);
}

export function getBaZiRecordApi(id, userId) {
  return request.get(`/records/bazi/${id}`, { params: { userId } });
}

export function updateBaZiRecordApi(id, userId, baziDto) {
  return request.put(`/records/bazi/${id}`, baziDto, { params: { userId } });
}

export function deleteBaZiRecordApi(id, userId) {
  return request.delete(`/records/bazi/${id}`, { params: { userId } });
}
