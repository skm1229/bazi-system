<template>
  <div class="bazi-more-info-panel">
    <!-- 星宿 -->
    <section class="info-section">
      <h2 class="section-title"><span>星宿</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">星宿</span>
          <span class="value">{{ data.xingXiu }} {{ data.xiuJiXiong }}</span>
        </div>
        <div class="info-row">
          <span class="label">歌诀</span>
          <span class="value">{{ data.xiuJiXiongGeJue }}</span>
        </div>
      </div>
    </section>

    <!-- 命卦 -->
    <section class="info-section">
      <h2 class="section-title"><span>命卦</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">命卦</span>
          <span class="value">{{ data.mingGua }}</span>
        </div>
        <div class="info-row" v-if="data.mingGuaFenXi">
          <span class="label">分析</span>
          <span class="value">{{ data.mingGuaFenXi.join(' ') }}</span>
        </div>
      </div>
    </section>
    
    <!-- 日主论命 -->
    <section class="info-section" v-if="data.dayZhuLunMing">
      <h2 class="section-title"><span>日主论命</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">参考</span>
          <span class="value">{{ data.dayZhuLunMing }}</span>
        </div>
      </div>
    </section>
    
    <!-- 姻缘分析 -->
    <section class="info-section" v-if="data.yinYuanFenXi">
      <h2 class="section-title"><span>姻缘分析</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">参考</span>
          <span class="value">{{ data.yinYuanFenXi }}</span>
        </div>
      </div>
    </section>

    <!-- 五行分析 -->
    <section class="info-section" v-if="data.wuXingFenXi">
      <h2 class="section-title"><span>五行分析</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">参考</span>
          <span class="value">{{ data.wuXingFenXi }}</span>
        </div>
      </div>
    </section>

    <!-- 彭祖百忌 -->
    <section class="info-section">
      <h2 class="section-title"><span>彭祖百忌</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">参考</span>
          <span class="value">{{ data.pengZuBaiJi }}</span>
        </div>
      </div>
    </section>

    <!-- 小儿关煞 (假设数据存在) -->
    <section class="info-section" v-if="data.xiaoErGuanSha">
      <h2 class="section-title"><span>小儿关煞</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">参考</span>
          <span class="value">{{ data.xiaoErGuanSha.join('、') }}</span>
        </div>
      </div>
    </section>

    <!-- 袁天罡称骨 -->
    <section class="info-section">
      <h2 class="section-title"><span>袁天罡称骨</span></h2>
      <div class="info-content">
        <div class="info-row">
          <span class="label">骨重</span>
          <span class="value">{{ data.guZhong }}</span>
        </div>
        <div class="info-row">
          <span class="label">批注</span>
          <span class="value verse">{{ data.guZhongGeJue }}</span>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
defineProps({
  data: { type: Object, required: true },
});
</script>

<style scoped>
.bazi-more-info-panel {
  background-color: #fff;
}
.info-section {
  padding: 1.5rem 20px;
  border-bottom: 1px solid #f0f2f5;
}
.info-section:last-child {
  border-bottom: none;
}
.section-title {
  text-align: center;
  font-size: 1.2rem;
  font-weight: 500;
  color: #888;
  margin-bottom: 1.8rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.section-title span {
  margin: 0 15px;
}
.section-title::before,
.section-title::after {
  content: '';
  display: block;
  width: 40px;
  height: 1px;
  background-color: #ddd;
}
.info-row {
  display: flex;
  font-size: 0.95rem;
  line-height: 1.8;
  padding: 0.3rem 0;
}
.info-row .label {
  width: 80px;
  flex-shrink: 0;
  color: #888;
  font-weight: 500;
}
.info-row .value {
  flex-grow: 1;
  color: #333;
}
.info-row .value.verse {
  font-style: italic;
}
</style> 
