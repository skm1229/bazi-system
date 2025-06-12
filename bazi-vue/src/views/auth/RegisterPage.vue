<template>
  <AuthLayout>
    <template #header-title>创建您的账户</template>
    <el-form ref="registerFormRef" :model="formState" :rules="rules" @submit.prevent="handleRegister" label-position="top">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formState.username" size="large" />
      </el-form-item>
      <el-form-item label="昵称 (可选)" prop="nickname">
        <el-input v-model="formState.nickname" size="large" />
      </el-form-item>
      <el-form-item label="邮箱 (可选)" prop="email">
        <el-input v-model="formState.email" type="email" size="large" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="formState.password" type="password" size="large" show-password />
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="formState.confirmPassword" type="password" size="large" show-password />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" class="auth-button" :loading="isLoading">
          {{ isLoading ? '注册中...' : '注 册' }}
        </el-button>
      </el-form-item>
    </el-form>
    <template #footer-link>
      已有账户？ <router-link to="/login" class="link">立即登录</router-link>
    </template>
  </AuthLayout>
</template>

<script setup>
import { ref, reactive } from 'vue';
import AuthLayout from '@/components/AuthLayout.vue';
import { useAuth } from '@/composables/useAuth.js';

const { register, isLoading } = useAuth();
const registerFormRef = ref(null);
const formState = reactive({ username: '', nickname: '', email: '', password: '', confirmPassword: '' });
const validateConfirmPassword = (rule, value, callback) => {
  if (value !== formState.password) callback(new Error('两次输入的密码不一致'));
  else callback();
};
const rules = reactive({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  confirmPassword: [{ required: true, validator: validateConfirmPassword, trigger: 'blur' }],
  email: [{ type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] }],
});
const handleRegister = async () => {
  if (!registerFormRef.value) return;
  const valid = await registerFormRef.value.validate();
  if (valid) {
    const userData = { ...formState };
    if (userData.email === '') {
      userData.email = null;
    }
    await register(userData);
  }
};
</script>

<style scoped>
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
