/**
 * 通用的对话框状态管理组合式函数
 */
import { ref } from 'vue';

/**
 * 提供管理对话框显示/隐藏状态的基础功能
 * @returns {object} 包含 isVisible, open, close 的对象
 */
export function useDialog() {
  const isVisible = ref(false);

  const open = () => {
    isVisible.value = true;
  };

  const close = () => {
    isVisible.value = false;
  };

  return {
    isVisible,
    open,
    close,
  };
} 
