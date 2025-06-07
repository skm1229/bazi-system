<template>
  <div class="bazi-input-container">
    <h2 class="form-title text-center">八字排盘信息录入</h2>
    <el-form
      ref="formRef"
      :model="formData"
      label-position="top"
      @submit.prevent="$emit('submit')"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="命主姓名" prop="name"><el-input v-model="formData.name" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="formData.sex">
              <el-radio :value="1">男</el-radio>
              <el-radio :value="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="历法类型" prop="dateType">
             <el-radio-group v-model="formData.dateType">
              <el-radio-button :value="0">公历</el-radio-button>
              <el-radio-button :value="1">农历</el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12" v-if="formData.dateType === 1">
          <el-form-item label="是否闰月" prop="leapMonthType">
            <el-radio-group v-model="formData.leapMonthType">
              <el-radio :value="0">非闰月</el-radio>
              <el-radio :value="1">是闰月</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="出生时间" prop="date">
            <el-date-picker
              v-model="formData.date"
              type="datetime"
              style="width: 100%;"
              format="YYYY-MM-DD HH:mm:ss"
              value-format="YYYY-MM-DD HH:mm:ss"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="人元司令分野" prop="renYuanType">
             <el-select v-model="formData.renYuanType" style="width: 100%;">
              <el-option label="子平真诠法诀" :value="0" />
              <el-option label="渊海子平法诀" :value="1" />
              <el-option label="星平会海法诀" :value="2" />
              <el-option label="三命通会法诀" :value="3" />
              <el-option label="神峰通考法诀" :value="4" />
              <el-option label="万育吾之法诀" :value="5" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生地点" prop="address">
            <el-input v-model="formData.address" placeholder="例如：北京市" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占事分类" prop="occupy">
            <el-input v-model="formData.occupy" placeholder="如：事业、财运" />
          </el-form-item>
        </el-col>
      </el-row>

      <div class="advanced-options-container">
        <a @click="showAdvanced = !showAdvanced" class="advanced-options-toggle">
          排盘高级选项 <el-icon><ArrowDown v-if="!showAdvanced" /><ArrowUp v-if="showAdvanced" /></el-icon>
        </a>
        <el-collapse-transition>
          <div v-show="showAdvanced" class="advanced-options-content">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="起运流派" prop="qiYunLiuPaiType">
                  <el-select v-model="formData.qiYunLiuPaiType" style="width: 100%;">
                    <el-option label="按分钟数计算" :value="1" />
                    <el-option label="按天数和时辰数计算" :value="0" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="节气排法" prop="jieQiType">
                  <el-select v-model="formData.jieQiType" style="width: 100%;">
                    <el-option label="按分钟计算" :value="1" />
                    <el-option label="按天计算" :value="0" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="年柱设置" prop="yearGanZhiType">
                  <el-select v-model="formData.yearGanZhiType" style="width: 100%;">
                    <el-option label="以立春交接时刻为准" :value="2" />
                    <el-option label="以立春当天为准" :value="1" />
                    <el-option label="以正月初一为准" :value="0" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="月柱设置" prop="monthGanZhiType">
                  <el-select v-model="formData.monthGanZhiType" style="width: 100%;">
                    <el-option label="以节交接时刻起算" :value="1" />
                    <el-option label="以节交接当天起算" :value="0" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="日柱设置" prop="dayGanZhiType">
                  <el-select v-model="formData.dayGanZhiType" style="width: 100%;">
                    <el-option label="晚子时日柱按当天" :value="0" />
                    <el-option label="晚子时日柱按明天" :value="1" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="时柱设置" prop="hourGanZhiType">
                  <el-select v-model="formData.hourGanZhiType" style="width: 100%;">
                    <el-option label="早子时" :value="0" />
                    <el-option label="晚子时" :value="1" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </div>
        </el-collapse-transition>
      </div>

      <el-form-item label="保存记录">
        <el-switch v-model="extraOptions.saveRecord" />
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" native-type="submit" class="submit-button">开始排盘</el-button>
        <el-button @click="resetForm" class="reset-button" text bg>清空重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const props = defineProps({
  modelValue: { type: Object, required: true },
  extraOptions: { type: Object, required: true },
});
const emit = defineEmits(['update:modelValue', 'update:extraOptions', 'submit', 'reset']);

const showAdvanced = ref(false);

const formData = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value),
});
const extraOptions = computed({
  get: () => props.extraOptions,
  set: (value) => emit('update:extraOptions', value),
});

const formRef = ref(null);

const resetForm = () => {
    if (formRef.value) {
        emit('reset', formRef.value);
    }
};
</script>

<style scoped>
.bazi-input-container {
  background-color: var(--color-light-1);
  padding: 30px 40px;
  border-radius: var(--border-radius-lg);
  box-shadow: 0 8px 24px rgba(0,0,0,0.08);
}
.form-title {
    font-weight: 500;
    font-size: 1.8rem;
    margin-bottom: 30px;
}
.el-form-item { margin-bottom: 18px; }
.advanced-options-container {
    border-top: 1px solid #f0f0f0;
    border-bottom: 1px solid #f0f0f0;
    padding: 10px 0;
    margin-bottom: 20px;
}
.advanced-options-toggle {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  color: var(--el-color-primary);
  cursor: pointer;
  font-size: 14px;
}
.advanced-options-content {
  padding-top: 20px;
}
.action-buttons {
  margin-top: 20px;
}
.action-buttons :deep(.el-form-item__content) {
    justify-content: center;
    gap: 10px;
}
.submit-button, .reset-button {
    flex-grow: 2;
    height: 45px;
    font-size: 1.1rem;
    border-radius: var(--border-radius-pill);
}
.submit-button {
    background-color: var(--color-dark-1);
    border: none;
}
.reset-button {
    flex-grow: 1;
}
</style>
