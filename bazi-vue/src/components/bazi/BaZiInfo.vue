<template>
  <el-card shadow="never" class="bazi-info-card">
    <div class="info-grid">
      <!-- Row 1 -->
      <div class="info-item"><span class="label">年命信息</span>
        <span class="value">
          <span>{{ data.baZiNaYin ? data.baZiNaYin[0].slice(0, -1) : '' }}</span>
          <span :style="{ color: getWuxingColor(data.baZiNaYin ? data.baZiNaYin[0] : '') }">{{ data.baZiNaYin ? data.baZiNaYin[0].slice(-1) : '' }}</span>
        </span>
      </div>
      <div class="info-item"><span class="label">命卦信息</span><span class="value" :style="{ color: getWuxingColor(data.mingGua) }">{{ data.mingGua }}</span></div>
      <div class="info-item"><span class="label">生肖星座</span><span class="value">{{ data.shengXiao }}、{{ data.xingZuo }}</span></div>
      <div class="info-item"><span class="label">星宿信息</span><span class="value">{{ data.xingXiu }} {{ data.xiuJiXiong }}</span></div>

      <!-- Row 2 -->
      <div class="info-item"><span class="label">身体强弱</span><span class="value">{{ data.shenTiQiangRuo }}</span></div>
      <div class="info-item">
        <span class="label">人元司令</span>
        <span class="value">
          <span :style="{ color: getWuxingColor(renYuanParts.colored) }">{{ renYuanParts.colored }}</span>
          <span>{{ renYuanParts.uncolored }}</span>
        </span>
      </div>
      <div class="info-item"><span class="label">胎元胎息</span><span class="value">{{ data.taiYuan }}、{{ data.taiXi }}</span></div>
      <div class="info-item"><span class="label">命宫身宫</span><span class="value">{{ data.mingGong }}、{{ data.shenGong }}</span></div>

      <!-- Row 3 -->
      <div class="info-item"><span class="label">生辰八字</span>
        <span class="value">
          <span v-for="(item, index) in data.baZi" :key="index" class="pillar">
            <span :style="{ color: getWuxingColor(item[0]) }">{{ item[0] }}</span>
            <span :style="{ color: getWuxingColor(item[1]) }">{{ item[1] }}</span>
          </span>
        </span>
      </div>
      <div class="info-item"><span class="label">八字五行</span>
        <span class="value">
          <span v-for="(item, itemIndex) in data.baZiWuXing" :key="itemIndex" style="margin-right: 6px;">
            <span v-for="(char, charIndex) in item.split('')" :key="charIndex" :style="{ color: getWuxingColor(char) }">
              {{ char }}
            </span>
          </span>
        </span>
      </div>
      <div class="info-item"><span class="label">五行个数</span>
        <span class="value">
           <span v-for="item in data.wuXingCount" :key="item" style="margin-right: 6px;">
            <span :style="{ color: getWuxingColor(item) }">{{ item.charAt(0) }}</span>
            <span>{{ item.substring(1) }}</span>
          </span>
        </span>
      </div>
      <div class="info-item"><span class="label">五行旺衰</span>
        <span class="value">
          <span v-for="item in data.wuXingWangShuai" :key="item" :style="{ color: getWuxingColor(item), marginRight: '4px' }">{{ item }}</span>
        </span>
      </div>

      <!-- Row 4 -->
      <div class="info-item"><span class="label">八字空亡</span><span class="value">{{ data.baZiKongWang?.join(' ') }}</span></div>
      <div class="info-item"><span class="label">八字纳音</span>
        <span class="value">
          <span v-for="item in data.baZiNaYin" :key="item" style="margin-right: 6px;">
            <span>{{ item.slice(0, -1) }}</span>
            <span :style="{ color: getWuxingColor(item) }">{{ item.slice(-1) }}</span>
          </span>
        </span>
      </div>
      <div class="info-item"><span class="label">五行缺失</span>
        <span class="value">
           <span v-for="item in data.wuXingQueShi" :key="item" :style="{ color: getWuxingColor(item), marginRight: '6px' }">
            {{ item }}
          </span>
          <span v-if="!data.wuXingQueShi?.length">无</span>
        </span>
      </div>
      <div class="info-item"><span class="label">月相</span><span class="value">{{ data.yueXiang }}</span></div>
      
      <!-- Row 5 -->
      <div class="info-item"><span class="label">月将</span><span class="value">{{ data.yueJiang }}</span></div>
      <div class="info-item"><span class="label">月将神</span><span class="value">{{ data.yueJiangShen }}</span></div>
      <div class="info-item"><span class="label">喜用神</span>
        <span class="value">
           <span v-for="item in data.xiYongShen" :key="item" :style="{ color: getWuxingColor(item), marginRight: '4px' }">
            {{ item }}
          </span>
        </span>
      </div>
      <div class="info-item"><span class="label">喜用神位</span><span class="value">{{ data.xiYongShenFangWei?.join('、') }}</span></div>

      <!-- Row 6 -->
      <div class="info-item" style="grid-column: span 2;"><span class="label">格局</span><span class="value">{{ data.geJu }}</span></div>
      <div class="info-item"><span class="label">日主属性</span><span class="value" :style="{ color: getWuxingColor(data.dayGanWuXing) }">{{ data.dayGanWuXing }}</span></div>
      <div class="info-item"><span class="label">季节</span><span class="value" :style="{ color: getWuxingColor(data.jiJie) }">{{ data.jiJie }}</span></div>

      <!-- Row 7 -->
      <div class="info-item"><span class="label">五不遇时</span><span class="value">{{ data.wuBuYuShi || '无' }}</span></div>
      <div class="info-item"><span class="label">值年九星</span><span class="value" :style="{ color: getWuxingColor(data.yearJiuXingWuXing) }">{{ data.yearJiuXingWuXing }}</span></div>
      <div class="info-item"><span class="label">星期</span><span class="value">{{ data.xingQi }}</span></div>
      <div class="info-item"><span class="label"></span><span class="value"></span></div> <!-- Placeholder for alignment -->

      <!-- Full-width Rows -->
      <div class="info-item full-width"><span class="label">起运信息</span><span class="value">{{ data.qiYun }}, 阳历 {{ data.qiYunDateStr }}</span></div>
      <div class="info-item full-width"><span class="label">节气日期</span><span class="value">{{ data.prevJie }}: {{ data.prevJieDateStr }}、{{ data.nextJie }}: {{ data.nextJieDateStr }}</span></div>
      <div class="info-item full-width"><span class="label">出生信息</span><span class="value">出生于 {{ data.prevJie }} 后...</span></div>
    </div>
  </el-card>
</template>

<script setup>
import { computed } from 'vue';
import { getWuxingColor, ALL_WUXING_RELATED_CHARS } from '@/composables/wuxingColors.js';

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});

const renYuanParts = computed(() => {
  const text = props.data.renYuan;
  if (!text) {
    return { colored: '', uncolored: '' };
  }

  let splitIndex = text.length;
  for (let i = 0; i < text.length; i++) {
    const char = text[i];
    if (!ALL_WUXING_RELATED_CHARS.includes(char)) {
      splitIndex = i;
      break;
    }
  }

  return {
    colored: text.substring(0, splitIndex),
    uncolored: text.substring(splitIndex),
  };
});
</script>

<style scoped>
.bazi-info-card {
  margin-top: 16px;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
  font-size: 14px;
}

.info-item {
  display: flex;
  align-items: center;
  padding: 8px;
  background-color: #f7f7f7;
  border-radius: 4px;
  min-height: 38px; /* Ensure consistent height */
}

.pillar {
  margin-right: 8px;
}

.pillar span {
  display: inline-block;
  width: 1.2em;
  text-align: center;
}

.label {
  color: #666;
  margin-right: 12px;
  flex-shrink: 0;
  width: 60px;
}

.value {
  color: #333;
  font-weight: normal;
  word-break: break-all;
}

.full-width {
  grid-column: span 4;
}
</style>
