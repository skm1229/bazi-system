/**
 * 梅花易数记录的增删改查API
 */
import request from '@/services/request.js';

export function saveMeiHuaRecordApi(userId, meiHuaDto) {
  return request.post('/records/meihua/save', meiHuaDto, { params: { userId } });
}

export function getMeiHuaRecordsByUserIdApi(userId) {
  return request.get(`/records/meihua/user/${userId}`);
}

export function getMeiHuaRecordApi(id, userId) {
  return request.get(`/records/meihua/${id}`, { params: { userId } });
}

export function updateMeiHuaRecordApi(id, userId, meiHuaDto) {
  return request.put(`/records/meihua/${id}`, meiHuaDto, { params: { userId } });
}

export function deleteMeiHuaRecordApi(id, userId) {
  return request.delete(`/records/meihua/${id}`, { params: { userId } });
} 
