/**
 * 紫微斗数排盘计算相关API
 */
import request from '@/services/request.js';

export function ziweiPaiPanApi(data) {
  return request.post('/ziwei/paiPan', data);
}
