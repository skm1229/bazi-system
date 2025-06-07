/**
 * Axios 实例和拦截器配置
 */
import axios from 'axios';
import { ElNotification } from 'element-plus';

const service = axios.create({
  baseURL: 'http://localhost:9999/api/',
  timeout: 10000,
});

// 请求拦截器
service.interceptors.request.use(
  config => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token;
    }
    return config;
  },
  error => Promise.reject(error)
);

// 响应拦截器
service.interceptors.response.use(
  response => {
    const res = response.data;
    if (res?.code >= 200 && res?.code < 300) {
      return res.data;
    } else {
      ElNotification.error({ title: '操作失败', message: res?.msg || '系统处理出错' });
      return Promise.reject(new Error(res?.msg || '后端业务处理失败'));
    }
  },
  error => {
    let errorMessage = '网络请求异常，请稍后重试。';
    if (error.response?.data?.msg) {
      errorMessage = error.response.data.msg;
    }
    ElNotification.error({
      title: `请求错误 (${error.response?.status || 'Network Error'})`,
      message: errorMessage,
    });
    return Promise.reject(error);
  }
);

export default service;
