<template>
  <div class="bazi-view-container">
    <el-row :gutter="30">
      <el-col :xs="24" :sm="24" :md="12">
        <div v-if="state.isLoading" class="loading-state d-flex justify-content-center align-items-center">
          <el-icon class="is-loading" :size="30"><Loading /></el-icon>
          <span style="margin-left: 10px;">正在排盘中...</span>
        </div>
        <BaZiPage v-else-if="state.baZiVo" :data="state.baZiVo" />
        <el-empty v-else description="暂无排盘结果，请在右侧输入信息后点击排盘" />
      </el-col>
      
      <el-col :xs="24" :sm="24" :md="12">
        <BaZiInputForm
          :modelValue="state.baZiDto"
          @update:modelValue="state.baZiDto = $event"
          :extraOptions="state.extra"
          @update:extraOptions="state.extra = $event"
          @submit="handleFormSubmit"
          @reset="handleResetForm"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { useBaZi } from '@/composables/useBaZi.js';
import BaZiInputForm from '@/components/bazi/BaZiInputForm.vue';
import BaZiPage from '@/components/bazi/BaZiPage.vue';

const { state, handleFormSubmit, handleResetForm } = useBaZi();
</script>

<style scoped>
@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.bazi-view-container {
  padding: var(--spacing-xl);
  max-width: 1800px;
  margin: 0 auto;
  animation: fadeIn 0.5s ease-out;
}
.loading-state {
  min-height: 400px;
  font-size: 1.2rem;
  color: var(--color-dark-text-secondary);
}
.el-col {
  margin-bottom: var(--spacing-lg);
}
</style>
