/**
 * 八字排盘计算相关API
 */
import request from '@/services/request.js';

export function baZiPaiPanApi(data) {
  return request.post('/bazi/paiPan', data);
}

export function daYunInitApi(data) {
  return request.post('/bazi/daYunInit', data);
}

export function daYunApi(data) {
  return request.post('/bazi/daYun', data);
}

export function liuNianApi(data) {
  return request.post('/bazi/liuNian', data);
}

export function liuYueApi(data) {
  return request.post('/bazi/liuYue', data);
}

export function liuRiApi(data) {
  return request.post('/bazi/liuRi', data);
}

export function liuShiApi(data) {
  return request.post('/bazi/liuShi', data);
}

export function taiMingShenApi(data) {
  return request.post('/bazi/taiMingShen', data);
}

