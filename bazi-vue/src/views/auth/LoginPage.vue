<template>
  <AuthLayout>
    <template #header-title>欢迎登录</template>
    <el-form ref="loginFormRef" :model="formState" :rules="rules" @submit.prevent="handleLogin" label-position="top">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formState.username" size="large" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="formState.password" type="password" size="large" show-password />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" class="auth-button" :loading="isLoading">
          {{ isLoading ? '登录中...' : '登 录' }}
        </el-button>
      </el-form-item>
    </el-form>
    <template #footer-link>
      还没有账户？ <router-link to="/register" class="link">立即注册</router-link>
    </template>
  </AuthLayout>
</template>

<script setup>
// Script部分无需改动
import { ref, reactive } from 'vue';
import AuthLayout from '@/components/AuthLayout.vue';
import { useAuth } from '@/composables/useAuth.js';
const { login, isLoading } = useAuth();
const loginFormRef = ref(null);
const formState = reactive({ username: '', password: '' });
const rules = reactive({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
});
const handleLogin = async () => {
  if (!loginFormRef.value) return;
  const valid = await loginFormRef.value.validate();
  if (valid) await login(formState);
};
</script>

<style scoped>
/* 应用新的统一按钮样式 */
.auth-button {
    width: 100%;
    height: 50px;
    font-size: 1.2rem;
    font-weight: 500;
    border-radius: var(--border-radius-pill);
    background-color: var(--color-dark-1);
    border: none;
    margin-top: 20px;
}
.el-form-item { margin-bottom: 22px; }
</style>
