// vite.config.js

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import path from 'path'; // 1. 引入 path 模块

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      // 2. 配置 @ 指向 src 目录
      '@': path.resolve(__dirname, './src'),
    },
  },
});
