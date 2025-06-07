/**
 * Vue Router 配置文件
 */
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import LoginPage from '@/views/auth/LoginPage.vue';
import RegisterPage from '@/views/auth/RegisterPage.vue';
import ChangePasswordPage from '@/views/auth/ChangePasswordPage.vue';
import ProfilePage from '@/views/auth/ProfilePage.vue';
import BaZiView from '@/views/BaZiView.vue';
import ComingSoon from '@/views/ComingSoon.vue';
import RecordsView from '@/views/RecordsView.vue';

// 导入 useAuth 来访问认证状态
import { useAuth } from '@/composables/useAuth.js';

const routes = [
  // [!] 确认首页路由，用于"排盘首页"按钮
  { path: '/', name: 'Home', component: HomeView },
  
  { path: '/bazi', name: 'BaZi', component: BaZiView },
  { path: '/login', name: 'Login', component: LoginPage },
  { path: '/register', name: 'Register', component: RegisterPage },
  { path: '/change-password', name: 'ChangePassword', component: ChangePasswordPage, meta: { requiresAuth: true } },
  { path: '/profile', name: 'Profile', component: ProfilePage, meta: { requiresAuth: true } },
  
  // Header 其他导航占位路由
  { path: '/records', name: 'Records', component: RecordsView, meta: { requiresAuth: true } },
  { path: '/ziwei', name: 'ZiWei', component: ComingSoon },
  { path: '/qimen', name: 'QiMen', component: ComingSoon },
  { path: '/liuyao', name: 'LiuYao', component: ComingSoon },
  { path: '/meihua', name: 'MeiHua', component: ComingSoon },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

// 全局前置守卫
router.beforeEach((to, from, next) => {
  const { isLoggedIn } = useAuth();
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

  if (requiresAuth && !isLoggedIn.value) {
    // 如果路由需要认证但用户未登录，则重定向到登录页
    next({
      path: '/login',
      query: { redirect: to.fullPath } // 保存用户想访问的原始页面路径
    });
  } else {
    // 否则，正常放行
    next();
  }
});

export default router;
