/**
 * 紫微斗数记录的增删改查API
 */
import request from '@/services/request.js';

export function saveZiWeiRecordApi(userId, ziWeiDto) {
  return request.post('/records/ziwei/save', ziWeiDto, { params: { userId } });
}

export function getZiWeiRecordsByUserIdApi(userId) {
  return request.get(`/records/ziwei/user/${userId}`);
}

export function getZiWeiRecordApi(id, userId) {
  return request.get(`/records/ziwei/${id}`, { params: { userId } });
}

export function updateZiWeiRecordApi(id, userId, ziWeiDto) {
  return request.put(`/records/ziwei/${id}`, ziWeiDto, { params: { userId } });
}

export function deleteZiWeiRecordApi(id, userId) {
  return request.delete(`/records/ziwei/${id}`, { params: { userId } });
} 
