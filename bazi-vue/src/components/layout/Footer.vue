<template>
  <el-footer class="app-footer">
    <p>&copy; {{ new Date().getFullYear() }} 周易排盘系统. All Rights Reserved.</p>
  </el-footer>
</template>

<style scoped>
.app-footer {
  padding: 20px 0;
  text-align: center;
  background-color: var(--color-light-2);
  border-top: var(--border-base);
  color: var(--color-dark-text-secondary);
  font-size: var(--font-size-sm);
}
.app-footer p { margin: 0; }
</style>
