/**
 * 奇门遁甲记录的增删改查API
 */
import request from '@/services/request.js';

export function saveQiMenRecordApi(userId, qiMenDto) {
  return request.post('/records/qimen/save', qiMenDto, { params: { userId } });
}

export function getQiMenRecordsByUserIdApi(userId) {
  return request.get(`/records/qimen/user/${userId}`);
}

export function getQiMenRecordApi(id, userId) {
  return request.get(`/records/qimen/${id}`, { params: { userId } });
}

export function updateQiMenRecordApi(id, userId, qiMenDto) {
  return request.put(`/records/qimen/${id}`, qiMenDto, { params: { userId } });
}

export function deleteQiMenRecordApi(id, userId) {
  return request.delete(`/records/qimen/${id}`, { params: { userId } });
} 
