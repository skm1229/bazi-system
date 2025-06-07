/**
 * 统一的通知服务
 */
import { ElNotification } from 'element-plus';

const defaultOptions = {
  offset: 80,
  position: 'top-right',
  duration: 3000,
};

export const notify = {
  success: (message, title = '成功') => ElNotification({ ...defaultOptions, title, message, type: 'success' }),
  error: (message, title = '失败') => ElNotification({ ...defaultOptions, title, message, type: 'error' }),
  warning: (message, title = '警告') => ElNotification({ ...defaultOptions, title, message, type: 'warning' }),
  info: (message, title = '提示') => ElNotification({ ...defaultOptions, title, message, type: 'info' }),
};
