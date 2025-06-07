<template>
  <div class="record-item">
    <div class="main-content">
      <div class="info-section">
        <div class="name-info">
          <span class="name">{{ data.name }}</span>
          <el-tag size="small" :style="genderTagStyle" effect="light" round>
            {{ data.sex === 1 ? '男' : '女' }}
          </el-tag>
        </div>
        <div class="date-info">
          <el-icon><Calendar /></el-icon>
          <span>{{ formattedSolarDate }}</span>
        </div>
        <div class="occupy-info" v-if="data.occupy">
          <el-icon><ChatDotSquare /></el-icon>
          <span>{{ data.occupy }}</span>
        </div>
      </div>

      <div class="bazi-grid-section">
        <div class="bazi-grid">
          <div v-for="(column, index) in baziColumns" :key="index" class="bazi-column">
            <div class="header-text">{{ column.label }}</div>
            <div class="char gan" :style="{ color: column.ganColor }">{{ column.gan }}</div>
            <div class="char zhi" :style="{ color: column.zhiColor }">{{ column.zhi }}</div>
          </div>
        </div>
      </div>
    </div>

    <el-dropdown class="more-options" trigger="click" @command="handleCommand">
      <el-icon class="more-options-icon"><MoreFilled /></el-icon>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="view" :icon="View">查看详情</el-dropdown-item>
          <el-dropdown-item command="update" :icon="Edit">修改记录</el-dropdown-item>
          <el-dropdown-item command="delete" :icon="Delete" divided>删除</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { getWuxingColor } from '@/utils/wuxingColors';
import { formatDateToChinese } from '@/utils/dateUtils';
import { MoreFilled, Calendar, ChatDotSquare, View, Edit, Delete } from '@element-plus/icons-vue';

// 为了不修改wuxingColors.js，在此处复制一份映射
const GAN_TO_WUXING_MAP = {
  '甲': '木', '乙': '木', '丙': '火', '丁': '火', '戊': '土', '己': '土',
  '庚': '金', '辛': '金', '壬': '水', '癸': '水'
};

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['delete', 'view-details', 'edit']);

const router = useRouter();

const genderTagStyle = computed(() => {
  const dayGan = props.data.dayGan;
  if (!dayGan) {
    return {};
  }
  const backgroundColor = getWuxingColor(dayGan);
  const wuxing = GAN_TO_WUXING_MAP[dayGan];

  // 如果五行是金（黄色背景），字体用深色以保证可读性
  const color = wuxing === '金' ? '#303133' : '#ffffff';

  return {
    backgroundColor,
    color,
    border: 'none', // 移除el-tag的默认边框，让背景色更纯粹
  };
});

const handleCommand = (command) => {
  switch (command) {
    case 'view':
      emit('view-details', props.data);
      break;
    case 'update':
      emit('edit', props.data);
      break;
    case 'delete':
      emit('delete', props.data.id);
      break;
  }
};

const formattedSolarDate = computed(() => {
  if (!props.data.date) return '日期未提供';
  return formatDateToChinese(props.data.date);
});

const baziColumns = computed(() => {
  if (!props.data) return [];
  const safeData = props.data || {};
  return [
    { label: '年柱', gan: safeData.yearGan, zhi: safeData.yearZhi, ganColor: getWuxingColor(safeData.yearGan), zhiColor: getWuxingColor(safeData.yearZhi) },
    { label: '月柱', gan: safeData.monthGan, zhi: safeData.monthZhi, ganColor: getWuxingColor(safeData.monthGan), zhiColor: getWuxingColor(safeData.monthZhi) },
    { label: '日柱', gan: safeData.dayGan, zhi: safeData.dayZhi, ganColor: getWuxingColor(safeData.dayGan), zhiColor: getWuxingColor(safeData.dayZhi) },
    { label: '时柱', gan: safeData.hourGan, zhi: safeData.hourZhi, ganColor: getWuxingColor(safeData.hourGan), zhiColor: getWuxingColor(safeData.hourZhi) }
  ].map(col => ({
      ...col,
      gan: col.gan || '?',
      zhi: col.zhi || '?'
  }));
});
</script>

<style scoped lang="scss">
.record-item {
  background-color: #fcfcfc;
  color: #34495e;
  border-radius: var(--border-radius-lg);
  border-bottom: 3px solid #f0ebe5;
  padding: var(--spacing-lg);
  box-shadow: var(--shadow-1);
  transition: all 0.2s ease-in-out;
  display: flex;
  position: relative;
  min-height: 140px;

  &:hover {
    transform: translateY(-4px);
    box-shadow: var(--shadow-2);
    border-color: #e9e4de;
  }
}

.main-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.info-section {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-sm);
  flex-shrink: 0;
  margin-right: var(--spacing-xl);
}

.name-info {
  display: flex;
  align-items: center;
  gap: var(--spacing-sm);
}

.name {
  font-size: 1.5rem;
  font-weight: 500;
  color: #2c3e50;
}

.date-info, .occupy-info {
  display: flex;
  align-items: center;
  gap: var(--spacing-xs);
  font-size: 0.9rem;
  color: #7f8c8d;
}

.bazi-grid-section {
  flex-grow: 1;
}

.bazi-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: var(--spacing-md);
  text-align: center;
}

.bazi-column {
  .header-text {
    font-size: 0.8rem;
    color: #7f8c8d;
    margin-bottom: var(--spacing-xs);
  }
  .char {
    font-family: 'KaiTi', 'SimSun', serif;
    font-size: 2rem;
    line-height: 1.2;
    font-weight: bold;
  }
}

.more-options {
  position: absolute;
  top: var(--spacing-md);
  right: var(--spacing-md);
  z-index: 10;
}

.more-options-icon {
  cursor: pointer;
  font-size: 1.25rem;
  color: #7f8c8d;
  &:hover {
    color: #2c3e50;
  }
}
</style> 
