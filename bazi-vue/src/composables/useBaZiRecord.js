/**
 * 八字排盘记录管理的组合式函数
 * 封装了与用户排盘记录相关的获取、删除等业务逻辑
 */
import { reactive, onMounted } from 'vue';
import { useAuth } from '@/composables/useAuth.js';
import { getBaZiRecordsByUserIdApi, deleteBaZiRecordApi } from '@/api/records/bazi-record-api.js';
import { notify } from '@/services/notify.js';
import { ElMessageBox } from 'element-plus';

export function useBaZiRecord() {
  const { userInfo } = useAuth();

  // 响应式状态，用于存储记录列表和加载状态
  const state = reactive({
    records: [],
    isLoading: true,
  });

  /**
   * 根据用户ID获取排盘记录列表
   */
  async function fetchRecords() {
    if (!userInfo.value?.id) {
      notify.error('无法获取用户信息，请重新登录。');
      state.isLoading = false;
      return;
    }
    state.isLoading = true;
    try {
      const data = await getBaZiRecordsByUserIdApi(userInfo.value.id);
      // 按创建时间倒序排列
      state.records = data.sort((a, b) => new Date(b.createTime) - new Date(a.createTime));
    } finally {
      state.isLoading = false;
    }
  }

  /**
   * 根据记录ID删除一条排盘记录
   * @param {string|number} recordId - 要删除的记录ID
   */
  async function deleteRecord(recordId) {
    if (!userInfo.value?.id) {
      notify.error('用户未登录，无法执行删除操作。');
      return;
    }

    try {
      await ElMessageBox.confirm('此操作将永久删除该条记录，是否继续？', '确认删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      });
      
      await deleteBaZiRecordApi(recordId, userInfo.value.id);
      // 从本地状态中移除已删除的记录，避免重新请求
      state.records = state.records.filter(r => r.id !== recordId);
      notify.success('记录已成功删除');

    } catch (error) {
      if (error !== 'cancel') {
        // API请求的错误会由request拦截器统一处理，这里只处理取消操作
        notify.info('已取消删除');
      }
    }
  }

  /**
   * 新增：在本地更新一条记录
   * @param {object} updatedRecord - 已更新的记录数据
   */
  const updateLocalRecord = (updatedRecord) => {
    const index = state.records.findIndex(r => r.id === updatedRecord.id);
    if (index !== -1) {
      state.records.splice(index, 1, updatedRecord);
    }
  };

  // 组件挂载时自动获取记录
  onMounted(fetchRecords);

  return {
    state,
    fetchRecords,
    deleteRecord,
    updateLocalRecord,
  };
} 
