/**
 * 八字排盘页面的组合式函数
 * 封装了该页面的所有状态和业务逻辑
 */
import { reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { baZiPaiPanApi } from '@/api/paipan/bazi-api.js';
import { saveBaZiRecordApi } from '@/api/records/bazi-record-api.js';
import { getUserIdFromStorage } from '@/utils/idUtils.js';
import { formatDateTime } from '@/utils/dateUtils.js';
import { notify } from '@/services/notify.js';
import { useAuth } from './useAuth';

/**
 * 创建一个与后端 BaZiDto 完全对应的默认输入对象
 * @param {boolean} isInitial - 是否为页面首次加载
 * @returns {object} BaZiDto 对象
 */
function createDefaultBaZiDto(isInitial = false) {
  return {
    name: isInitial ? '默认排盘' : '',
    occupy: '',
    date: formatDateTime(new Date()),
    address: '',
    leapMonthType: 0,
    dateType: 0,
    sex: 1,
    // 以下为高级设置，采用后端推荐的默认值
    qiYunLiuPaiType: 1,
    jieQiType: 1,
    renYuanType: 0,
    yearGanZhiType: 2,
    monthGanZhiType: 1,
    dayGanZhiType: 0,
    hourGanZhiType: 0,
  };
}

// 页面响应式状态
const state = reactive({
  baZiVo: null,
  baZiDto: createDefaultBaZiDto(),
  extra: { saveRecord: false }, // 仅保留与UI相关的附加选项
  isLoading: true,
});

export function useBaZi() {
  const router = useRouter();
  const { isLoggedIn } = useAuth();

  async function handleFormSubmit() {
    // 如果需要保存记录但用户未登录，则跳转到登录页
    if (state.extra.saveRecord && !isLoggedIn.value) {
      notify.warning('请先登录后再保存记录');
      router.push({ path: '/login', query: { redirect: '/bazi' } });
      return;
    }

    state.isLoading = true;
    state.baZiVo = null;
    try {
      // 提交时，DTO已包含所有表单数据
      const payload = { ...state.baZiDto, ...state.extra };
      const chartResult = await baZiPaiPanApi(payload);
      
      // 修复：将输入表单中的性别信息，手动合并到API返回的结果中
      state.baZiVo = {
        ...chartResult,
        sex: payload.sex 
      };

      let successMsg = '排盘成功！';
      if (state.extra.saveRecord) {
        const userId = getUserIdFromStorage();
        if (userId) {
          await saveBaZiRecordApi(userId, payload);
          successMsg = '排盘成功，并已为您保存记录！';
        } else {
          // 此情况理论上已被上面的检查覆盖，作为安全措施保留
          notify.warning('无法获取用户信息，记录未保存。');
        }
      }
      notify.success(successMsg);

    } finally {
      state.isLoading = false;
    }
  }

  function handleResetForm(formInstance) {
    state.baZiDto = createDefaultBaZiDto();
    state.extra = { saveRecord: false };
    state.baZiVo = null;
    if (formInstance?.clearValidate) formInstance.clearValidate();
    notify.info('表单及结果已清空。');
  }

  async function loadDefaultChart() {
    state.isLoading = true;
    try {
      const defaultDto = createDefaultBaZiDto(true);
      const result = await baZiPaiPanApi(defaultDto);
      state.baZiVo = {
        ...result,
        sex: defaultDto.sex 
      };
      state.baZiDto = defaultDto;
    } finally {
      state.isLoading = false;
    }
  }
  
  onMounted(() => {
    if (!state.baZiVo) loadDefaultChart();
    else state.isLoading = false;
  });

  return { state, handleFormSubmit, handleResetForm };
}
