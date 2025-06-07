/**
 * 应用主入口文件
 */
import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';

// 导入我们定义的全局样式
import '@/assets/styles/main.scss'; 

import App from './App.vue';
import router from './router/router.js';

const app = createApp(App);

// 全局注册 Element Plus 图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

app.use(router);
app.use(ElementPlus);

app.mount('#app');
