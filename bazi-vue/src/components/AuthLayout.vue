<template>
  <div class="auth-page-container">
    <div class="auth-card">
      <div class="card-header text-center">
        <h1><slot name="header-title"></slot></h1>
      </div>
      <div class="card-body">
        <slot></slot>
      </div>
      <div class="auth-footer text-center">
        <slot name="footer-link"></slot>
      </div>
    </div>
  </div>
</template>

<style scoped>
.auth-page-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 60px);
  padding: 40px 20px;
  background-color: #f7f8fa;
}
/* 应用新的卡片样式 */
.auth-card {
  width: 100%;
  max-width: 420px;
  background-color: var(--color-light-1);
  padding: 30px 40px;
  border-radius: var(--border-radius-lg);
  box-shadow: 0 8px 24px rgba(0,0,0,0.08);
}
.card-header h1 {
  font-size: 1.8rem;
  font-weight: 500;
  margin: 0 0 30px 0;
}
.auth-footer { margin-top: var(--spacing-xl); font-size: var(--font-size-sm); }
:global(.link) { color: var(--color-primary); text-decoration: none; font-weight: 500; }
</style>
