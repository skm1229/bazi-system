/**
 * 奇门遁甲排盘计算相关API
 */
import request from '@/services/request.js';

export function qimenPaiPanApi(data) {
  return request.post('/qimen/paiPan', data);
}
