<template>
  <div class="home-view-container d-flex justify-content-center align-items-center">
    <div class="content-wrapper text-center" :class="{ 'transition-out': isTransitioning }">
      <svg viewBox="-15 -15 30 30" width="350" height="350" xmlns="http://www.w3.org/2000/svg">
        <defs>
          <g id="taiji">
            <circle r="6" />
            <path d="M0 6
                A6 6 0 0 1 0-6
                A3 3 0 0 1 0 0
                A3 3 0 0 0 0 6
                M0 2
                A1 1 0 0 1 0 4
                A1 1 0 0 1 0 2" fill="#fff" />
            <circle r="1" cy="-3" />
          </g>
        </defs>

        <g id="shadow" opacity="0">
          <use href="#taiji" />
          <animate attributeName="opacity" from="0.2" to="-0.05" dur="4s" begin="2s" repeatCount="indefinite" fill="remove" />
          <animateTransform attributeName="transform" type="scale" from="1" to="3" dur="4s" begin="2s" repeatCount="indefinite" fill="remove" additive="sum" />
          <animateTransform attributeName="transform" type="rotate" from="0 0 0" to="540 0 0" dur="4s" begin="2s" repeatCount="indefinite" fill="remove" additive="sum" />
        </g>

        <g id="main">
          <use href="#taiji" />
          <animateTransform attributeName="transform" type="rotate" from="0 0 0" to="360 0 0" dur="2s" begin="0s" repeatCount="indefinite" />
        </g>
      </svg>
      <h1 class="welcome-text" style="font-size: 2.8rem; margin-bottom: 24px; font-family: 'Source Han Serif', serif; margin-top: -80px;">欢迎来到 周易排盘</h1>
      <p class="description-text" style="font-size: 1.1rem; color: #666; max-width: 600px; margin: auto; font-family: 'Source Han Serif', serif; font-style: italic;">
        精准排盘，洞悉命理玄机。通过输入您的生辰信息，开启一段富有洞察的自我探索之旅。
      </p>
      <a href="#" @click.prevent="goToBaziPage" class="action-button">开始排盘</a>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const isTransitioning = ref(false);
const router = useRouter();

const goToBaziPage = () => {
  isTransitioning.value = true;
  setTimeout(() => {
    router.push('/bazi');
  }, 1000); // Match animation duration
};
</script>

<style scoped>
.home-view-container { flex-grow: 1; }

@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.taijitu {
  animation: spin 10s linear infinite, fadeInUp 1.5s ease-out;
}

.welcome-text {
  animation: fadeInUp 1s ease-out 0.5s;
  animation-fill-mode: both;
}

.description-text {
  animation: fadeInUp 1s ease-out 1s;
  animation-fill-mode: both;
}

.action-button {
  display: inline-block;
  margin-top: 40px;
  padding: 14px 38px;
  font-size: 1.2rem;
  color: var(--color-brand-gold);
  text-decoration: none;
  background-color: var(--color-dark-1);
  border-radius: var(--border-radius-pill);
  font-family: 'Source Han Serif', serif;
  transition: all 0.3s ease;
  animation: fadeInUp 1s ease-out 1.5s;
  animation-fill-mode: both;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.action-button:hover {
  transform: translateY(-4px);
  background-color: var(--color-dark-2);
  color: var(--color-brand-gold);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
}

/* Transition Animations */
@keyframes scaleAndFadeOut {
  from {
    transform: scale(1);
    opacity: 1;
  }
  to {
    transform: scale(50);
    opacity: 0;
  }
}

@keyframes fadeOut {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}

.content-wrapper.transition-out svg {
  animation: scaleAndFadeOut 1s cubic-bezier(0.215, 0.610, 0.355, 1.000) forwards;
  will-change: transform, opacity;
}

.content-wrapper.transition-out .welcome-text,
.content-wrapper.transition-out .description-text,
.content-wrapper.transition-out .action-button {
  animation: fadeOut 0.4s ease-in forwards;
}
</style>
