/**
 * 梅花易数排盘计算相关API
 */
import request from '@/services/request.js';

export function meihuaPaiPanApi(data) {
  return request.post('/meihua/paiPan', data);
}
