<template>
  <el-card v-if="data" class="bazi-page-card" shadow="never">
    <div class="chart-header">
      <div class="header-content-left">
        <span class="case-name">{{ data.name || '案例' }}</span>
        <div class="date-info">
          <div>阴历：{{ data.lunarStr }} <span class="gender-info">({{ data.zao }})</span></div>
          <div>阳历：{{ data.solarStr }}</div>
        </div>
      </div>
      <div class="header-actions">
        <el-dropdown trigger="click" @command="handleViewChange">
          <el-button type="primary">
            {{ activeView }}<el-icon class="el-icon--right"><ArrowDown /></el-icon>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item v-for="item in menuItems" :key="item" :command="item">
                {{ item }}
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <div class="content-area">
      <keep-alive>
        <component :is="currentViewComponent" :data="data" />
      </keep-alive>
    </div>
  </el-card>
</template>

<script setup>
import { ref, computed } from 'vue';
import BaZiInfo from './BaZiInfo.vue';
import BaZiChart from './BaZiChart.vue';
import BaZiAnalysis from './BaZiAnalysis.vue';
import BaZiMoreInfo from './BaZiMoreInfo.vue';

defineProps({
  data: { type: Object, required: true },
});

const menuItems = ['基本排盘', '基本信息', '更多信息', '专业细盘'];
const activeView = ref('基本排盘');

const viewComponents = {
  '基本信息': BaZiInfo,
  '基本排盘': BaZiChart,
  '专业细盘': BaZiAnalysis,
  '更多信息': BaZiMoreInfo,
};

const currentViewComponent = computed(() => {
  return viewComponents[activeView.value] || BaZiChart;
});

const handleViewChange = (command) => {
  activeView.value = command;
};
</script>

<style scoped>
.bazi-page-card { border: var(--border-base); }
:deep(.el-card__body) { padding: 0; }
.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #212529;
  color: #E0E0E0;
  padding: 16px 20px;
  border-bottom: 1px solid #444;
}
.header-content-left {
  display: flex;
  align-items: center;
}
.case-name { 
  font-size: 1.6em; 
  color: var(--color-brand-gold);
}
.date-info { 
  font-size: 0.9em; 
  margin-left: 20px; 
  color: #CCC; 
}
.gender-info { margin-left: 8px; }

.header-actions .el-button {
  background-color: #495057;
  border-color: #6c757d;
  color: #f8f9fa;
}
.header-actions .el-button:hover,
.header-actions .el-button:focus {
  background-color: #5a6268;
  border-color: #8a9299;
  color: #fff;
}

.content-area {
  padding: 0;
  background-color: #fff;
}
</style>
