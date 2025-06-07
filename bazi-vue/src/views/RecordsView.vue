<template>
  <div class="records-view-container">
    <div class="page-header">
      <h1 class="page-title">我的案例记录</h1>
      <p class="page-description">这里保存了您所有的术数排盘记录，方便随时查阅。</p>
    </div>

    <div class="record-types-nav">
      <el-button 
        v-for="recordType in recordTypes" 
        :key="recordType.key"
        :type="activeTab === recordType.key ? 'primary' : ''"
        round
        @click="activeTab = recordType.key"
      >
        {{ recordType.label }}
      </el-button>
    </div>

    <div class="records-content">
      <div v-if="activeTab === 'bazi'">
        <div v-if="state.isLoading" class="loading-state">
          <el-icon class="is-loading" :size="30"><Loading /></el-icon>
          <span style="margin-left: 10px;">正在加载记录...</span>
        </div>
        
        <el-empty v-else-if="!state.records.length" description="暂无八字排盘记录" />

        <div v-else class="records-list">
          <BaZiRecordItem 
            v-for="record in state.records" 
            :key="record.id" 
            :data="record"
            @delete="deleteRecord"
            @view-details="handleViewDetails"
            @edit="handleEdit"
            class="record-item"
          />
        </div>
      </div>

      <!-- 其他记录类型的占位符 -->
      <div v-if="activeTab === 'ziwei'" class="placeholder-content">紫微斗数记录将显示在此处</div>
      <div v-if="activeTab === 'qimen'" class="placeholder-content">奇门遁甲记录将显示在此处</div>
      <div v-if="activeTab === 'meihua'" class="placeholder-content">梅花易数记录将显示在此处</div>
      <div v-if="activeTab === 'liuyao'" class="placeholder-content">六爻记录将显示在此处</div>
    </div>

    <!-- 统一的详情/编辑弹窗 -->
    <el-dialog
      v-model="isDialogVisible"
      :title="dialogTitle"
      width="80%"
      top="5vh"
      destroy-on-close
      @close="resetDialogState"
    >
      <div v-if="isLoadingDetails" class="dialog-loading-state">
        <el-icon class="is-loading" :size="30"><Loading /></el-icon>
        <span style="margin-left: 10px;">正在加载数据...</span>
      </div>

      <el-row v-else-if="chartDetails" :gutter="20">
        <!-- 左侧排盘结果 -->
        <el-col :span="isEditMode ? 12 : 24">
          <BaZiPage :data="chartDetails" />
        </el-col>

        <!-- 右侧编辑表单 (仅在编辑模式下显示) -->
        <el-col :span="12" v-if="isEditMode && currentEditingRecord">
           <BaZiInputForm
              :modelValue="currentEditingRecord"
              @update:modelValue="currentEditingRecord = $event"
              :extraOptions="{ saveRecord: true }" 
              @submit="handleUpdateAndRepan"
            />
        </el-col>
      </el-row>

      <el-empty v-else description="无法加载排盘数据" />
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useBaZiRecord } from '@/composables/useBaZiRecord.js';
import { useDialog } from '@/composables/useDialog.js';
import BaZiRecordItem from '@/components/records/BaZiRecordItem.vue';
import BaZiPage from '@/components/bazi/BaZiPage.vue';
import BaZiInputForm from '@/components/bazi/BaZiInputForm.vue';
import { baZiPaiPanApi } from '@/api/paipan/bazi-api.js';
import { updateBaZiRecordApi } from '@/api/records/bazi-record-api.js';
import { getUserIdFromStorage } from '@/utils/idUtils.js';
import { notify } from '@/services/notify';

const { state, deleteRecord, updateLocalRecord } = useBaZiRecord();

const activeTab = ref('bazi');

const { isVisible: isDialogVisible, open: openDialog, close: closeDialog } = useDialog();

// --- 弹窗相关状态 ---
const isLoadingDetails = ref(false);
const chartDetails = ref(null);
const isEditMode = ref(false);
const currentEditingRecord = ref(null);
// ---

const dialogTitle = computed(() => isEditMode.value ? `编辑案例：${currentEditingRecord.value?.name}` : '排盘详情');

const recordTypes = [
  { key: 'bazi', label: '八字记录' },
  { key: 'ziwei', label: '紫微斗数' },
  { key: 'qimen', label: '奇门遁甲' },
  { key: 'meihua', label: '梅花易数' },
  { key: 'liuyao', label: '六爻记录' }
];

/**
 * 处理查看详情事件
 */
const handleViewDetails = async (record) => {
  isEditMode.value = false;
  chartDetails.value = null;
  isLoadingDetails.value = true;
  openDialog();
  
  try {
    const result = await baZiPaiPanApi(record);
    chartDetails.value = { ...result, sex: record.sex };
  } finally {
    isLoadingDetails.value = false;
  }
};

/**
 * 处理编辑事件
 */
const handleEdit = async (record) => {
  isEditMode.value = true;
  isLoadingDetails.value = true;
  // 深拷贝一份记录数据用于编辑，避免直接修改列表中的原始数据
  currentEditingRecord.value = JSON.parse(JSON.stringify(record));
  openDialog();

  try {
     const result = await baZiPaiPanApi(record);
     chartDetails.value = { ...result, sex: record.sex };
  } finally {
     isLoadingDetails.value = false;
  }
};

/**
 * 处理编辑模式下的表单提交（更新并重排）
 */
const handleUpdateAndRepan = async () => {
  if (!currentEditingRecord.value) return;
  
  isLoadingDetails.value = true;
  const userId = getUserIdFromStorage();

  try {
    // 1. 更新数据库中的记录
    const updatedRecord = await updateBaZiRecordApi(currentEditingRecord.value.id, userId, currentEditingRecord.value);
    
    // 2. 使用更新后的信息重新排盘
    const newChartResult = await baZiPaiPanApi(updatedRecord);
    chartDetails.value = { ...newChartResult, sex: updatedRecord.sex };
    
    // 3. 更新本地列表中的数据
    updateLocalRecord(updatedRecord);
    
    notify.success('记录更新成功！');
    isEditMode.value = false; // 切换回查看模式
  } finally {
    isLoadingDetails.value = false;
  }
};

/**
 * 关闭弹窗时重置所有状态
 */
const resetDialogState = () => {
  chartDetails.value = null;
  currentEditingRecord.value = null;
  isEditMode.value = false;
  isLoadingDetails.value = false;
}
</script>

<style scoped>
.records-view-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: var(--spacing-xl) 20px;
}
.page-header {
  margin-bottom: 2rem;
  text-align: center;
}
.page-title {
  font-size: 2.2rem;
  font-weight: 500;
}
.page-description {
  font-size: 1rem;
  color: #6c757d;
  margin-top: 0.5rem;
}
.dialog-loading-state {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
  font-size: 1.2rem;
  color: var(--color-dark-text-secondary);
}
.loading-state {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
  font-size: 1.2rem;
  color: var(--color-dark-text-secondary);
  background-color: var(--color-light-2);
  border-radius: var(--border-radius-lg);
}
.records-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(32%, 1fr));
  gap: var(--spacing-lg);
}
.record-item {
  transition: all 0.3s ease;
}
.el-empty {
  grid-column: 1 / -1; /* 让空状态占据整行 */
}
.record-types-nav {
  display: flex;
  justify-content: center;
  gap: var(--spacing-md);
  margin-bottom: 2rem;
}
.records-content {
  margin-top: 1rem;
}
.placeholder-content {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
  font-size: 1.2rem;
  color: var(--color-dark-text-secondary);
  background-color: var(--color-light-2);
  border-radius: var(--border-radius-lg);
}
</style> 
