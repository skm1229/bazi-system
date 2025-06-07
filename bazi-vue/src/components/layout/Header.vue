<template>
  <el-header class="app-header">
    <div class="header-left">
      <router-link to="/" class="logo-container">
        <div class="logo-box">周易</div>
        <span class="system-title">周易排盘</span>
      </router-link>
    </div>

    <div class="header-center">
      <el-menu :default-active="activeMenu" class="header-menu" mode="horizontal" router :ellipsis="false">
        <el-menu-item index="/">排盘首页</el-menu-item>
        <el-menu-item index="/bazi">八字排盘</el-menu-item>
        <el-menu-item index="/records">记录案例</el-menu-item>
        <el-menu-item index="/ziwei">紫微斗数</el-menu-item>
        <el-menu-item index="/qimen">奇门遁甲</el-menu-item>
        <el-menu-item index="/liuyao">六爻排盘</el-menu-item>
        <el-menu-item index="/meihua">梅花易数</el-menu-item>
      </el-menu>
    </div>

    <div class="header-right">
      <el-dropdown v-if="isLoggedIn" @command="handleUserCommand" trigger="click">
        <span class="el-dropdown-link d-flex align-items-center">
          <el-avatar :icon="UserFilled" :size="32" />
          <span class="username">{{ userInfo.username }}</span>
          <el-icon><arrow-down /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="profile">个人中心</el-dropdown-item>
            <el-dropdown-item command="settings">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <div v-else>
        <router-link to="/login" class="login-link">登录 / 注册</router-link>
      </div>
    </div>
  </el-header>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useAuth } from '@/composables/useAuth';
import { UserFilled } from '@element-plus/icons-vue';

const route = useRoute();
const router = useRouter();
const { userInfo, isLoggedIn, logout } = useAuth();
const activeMenu = computed(() => route.path);

const handleUserCommand = async (command) => {
  if (command === 'logout') {
    await logout();
  } else if (command === 'settings') {
    router.push('/change-password');
  } else if (command === 'profile') {
    router.push('/profile');
  }
};
</script>

<style scoped>
.app-header {
  height: var(--header-height);
  display: flex;
  align-items: center;
  background-color: var(--color-dark-1);
  color: var(--color-light-text-primary);
  padding: 0 var(--spacing-xl);
  border-bottom: 1px solid var(--color-dark-2);
}
.header-left { 
  flex: 1;
  display: flex;
  justify-content: flex-start;
}
.logo-container { display: flex; align-items: center; text-decoration: none; }
.logo-box {
  width: auto;
  padding: 0 10px;
  height: 40px;
  background-color: var(--color-dark-2);
  color: var(--color-light-1);
  display: flex; align-items: center; justify-content: center;
  font-weight: bold; border-radius: var(--border-radius-md);
  margin-right: 12px;
}
.system-title { font-size: 1.2rem; font-weight: 500; color: white; }

.header-center {
  flex: 2;
  display: flex;
  justify-content: center;
  /* 确保菜单不会被压缩 */
  min-width: 0; 
}
.header-menu { border-bottom: none; background-color: transparent !important; }
.header-menu .el-menu-item {
  font-size: 1rem;
  color: var(--color-light-text-primary) !important;
  background-color: transparent !important;
  border-bottom: 3px solid transparent !important;
  padding: 0 15px;
  transition: all 0.2s ease-in-out;
}
.header-menu .el-menu-item:hover { color: var(--color-light-1) !important; }
.header-menu .el-menu-item.is-active {
  color: var(--color-brand-gold) !important;
  border-bottom-color: var(--color-brand-gold) !important;
}

.header-right {
  flex: 1;
  display: flex;
  justify-content: flex-end;
}
.el-dropdown-link { cursor: pointer; color: var(--color-light-text-primary); }
.username { margin: 0 var(--spacing-sm); }
.login-link { color: var(--color-light-text-primary); text-decoration: none; }
</style>
