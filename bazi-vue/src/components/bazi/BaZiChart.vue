<template>
  <div class="bazi-chart-container">
    <div class="chart-body">
      <table class="sishu-table">
        <thead>
          <tr>
            <th class="row-header-label">日期</th>
            <th>年柱</th><th>月柱</th><th>日柱</th><th>时柱</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="row-header">主星</td>
            <td>{{ data.yearGanZhiZhuXing }}</td>
            <td>{{ data.monthGanZhiZhuXing }}</td>
            <td class="day-master-label" :style="{ color: getWuxingColor(data.dayGan) }">
              {{ data.sex === 1 ? '元男' : '元女' }}
            </td>
            <td>{{ data.hourGanZhiZhuXing }}</td>
          </tr>
          <tr>
            <td class="row-header">天干</td>
            <td class="gan-char" :style="{ color: getWuxingColor(data.yearGan) }">{{ data.yearGan }}</td>
            <td class="gan-char" :style="{ color: getWuxingColor(data.monthGan) }">{{ data.monthGan }}</td>
            <td class="gan-char" :style="{ color: getWuxingColor(data.dayGan) }">{{ data.dayGan }}</td>
            <td class="gan-char" :style="{ color: getWuxingColor(data.hourGan) }">{{ data.hourGan }}</td>
          </tr>
          <tr>
            <td class="row-header">地支</td>
            <td class="zhi-char" :style="{ color: getWuxingColor(data.yearZhi) }">{{ data.yearZhi }}</td>
            <td class="zhi-char" :style="{ color: getWuxingColor(data.monthZhi) }">{{ data.monthZhi }}</td>
            <td class="zhi-char" :style="{ color: getWuxingColor(data.dayZhi) }">{{ data.dayZhi }}</td>
            <td class="zhi-char" :style="{ color: getWuxingColor(data.hourZhi) }">{{ data.hourZhi }}</td>
          </tr>
          <tr>
            <td class="row-header">藏干</td>
            <td><div v-for="item in data.yearZhiCangGan" :key="item" :style="{ color: getWuxingColor(item.charAt(0)) }">{{ item }}</div></td>
            <td><div v-for="item in data.monthZhiCangGan" :key="item" :style="{ color: getWuxingColor(item.charAt(0)) }">{{ item }}</div></td>
            <td><div v-for="item in data.dayZhiCangGan" :key="item" :style="{ color: getWuxingColor(item.charAt(0)) }">{{ item }}</div></td>
            <td><div v-for="item in data.hourZhiCangGan" :key="item" :style="{ color: getWuxingColor(item.charAt(0)) }">{{ item }}</div></td>
          </tr>
          <tr>
            <td class="row-header">副星</td>
            <td><div v-for="item in data.yearGanZhiFuXing" :key="item">{{ item }}</div></td>
            <td><div v-for="item in data.monthGanZhiFuXing" :key="item">{{ item }}</div></td>
            <td><div v-for="item in data.dayGanZhiFuXing" :key="item">{{ item }}</div></td>
            <td><div v-for="item in data.hourGanZhiFuXing" :key="item">{{ item }}</div></td>
          </tr>
          <tr>
            <td class="row-header">纳音</td>
            <td>{{ data.yearGanZhiNaYin }}</td>
            <td>{{ data.monthGanZhiNaYin }}</td>
            <td>{{ data.dayGanZhiNaYin }}</td>
            <td>{{ data.hourGanZhiNaYin }}</td>
          </tr>
          <tr>
            <td class="row-header">神煞</td>
            <td><div v-for="item in data.yearGanZhiShenSha" :key="item" class="shensha-item">{{ item }}</div></td>
            <td><div v-for="item in data.monthGanZhiShenSha" :key="item" class="shensha-item">{{ item }}</div></td>
            <td><div v-for="item in data.dayGanZhiShenSha" :key="item" class="shensha-item">{{ item }}</div></td>
            <td><div v-for="item in data.hourGanZhiShenSha" :key="item" class="shensha-item">{{ item }}</div></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="chart-footer">
      <div class="liuyi-item"><strong>天干留意：</strong><span>{{ data.tianGanLiuYi?.join('，') || '无' }}</span></div>
      <div class="liuyi-item"><strong>地支留意：</strong><span>{{ data.diZhiLiuYi?.join('，') || '无' }}</span></div>
    </div>
  </div>
</template>

<script setup>
import { getWuxingColor } from '@/utils/wuxingColors.js';
defineProps({
  data: { type: Object, required: true },
});
</script>

<style scoped>
.bazi-view-container {
  padding: var(--spacing-xl);
  max-width: 1800px;
  margin: 0 auto;
}
.loading-state {
  min-height: 400px;
  font-size: 1.2rem;
  color: var(--color-dark-text-secondary);
}
.el-col {
  margin-bottom: var(--spacing-lg);
}
.sishu-table { 
  width: 100%; 
  border-collapse: collapse; 
  table-layout: fixed;
}
.sishu-table th, .sishu-table td {
  border: 1px solid var(--color-light-border);
  padding: 8px 12px; 
  text-align: center; 
  vertical-align: middle;
}
.sishu-table th, .row-header { 
  background-color: #f8f9fa; 
  font-weight: bold; 
}
.row-header-label {
  width: 80px;
}
.day-master-label { 
  font-weight: bold; 
}
.gan-char, .zhi-char { 
  font-size: 1.8em; 
  font-weight: bold; 
  font-family: 'KaiTi', 'SimSun', serif;
}
td > div:not(.shensha-list) {
  margin-bottom: 2px;
}
td > div:not(.shensha-list):last-child {
  margin-bottom: 0;
}
.shensha-list { 
  display: contents; /* This will make the container effectively disappear, letting children be direct descendants of the cell */
}
.shensha-item { 
  color: var(--color-dark-text-secondary);
  line-height: 1.6;
}
.chart-footer { 
  padding: 12px 20px;
  background-color: #f8f9fa; 
  border-top: 1px solid var(--color-light-border); 
  line-height: 1.8;
  font-size: 0.9em;
}
.liuyi-item span { 
  color: var(--color-dark-text-secondary); 
}
.liuyi-item strong { 
  color: var(--color-dark-text-primary); 
}
</style>
