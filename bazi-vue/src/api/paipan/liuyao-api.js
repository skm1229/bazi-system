/**
 * 六爻排盘计算相关API
 */
import request from '@/services/request.js';

export function liuyaoPaiPanApi(data) {
  return request.post('/liuyao/paiPan', data);
}
