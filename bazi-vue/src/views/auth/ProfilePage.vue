<template>
  <AuthLayout>
    <template #header-title>个人中心</template>
    <el-form
      v-if="userInfo"
      ref="formRef"
      :model="formState"
      :rules="rules"
      label-position="top"
      @submit.prevent="handleSubmit"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formState.username" size="large" disabled />
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="formState.nickname" size="large" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="formState.email" type="email" size="large" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" class="auth-button" :loading="isLoading">
          {{ isLoading ? '保存中...' : '保 存' }}
        </el-button>
      </el-form-item>
    </el-form>
     <div v-else class="loading-state">
      <el-icon class="is-loading" :size="30"><Loading /></el-icon>
    </div>
  </AuthLayout>
</template>

<script setup>
import { ref, reactive, onMounted, watch } from 'vue';
import AuthLayout from '@/components/AuthLayout.vue';
import { useAuth } from '@/composables/useAuth.js';
import { useUser } from '@/composables/useUser.js';

const { userInfo } = useAuth();
const { fetchUserInfo, updateUserInfo, isLoading } = useUser();
const formRef = ref(null);

// formState 现在是一个由 userInfo 驱动的响应式对象
const formState = reactive({
  username: '',
  nickname: '',
  email: '',
});

const rules = {
  nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  email: [
    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
    { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] },
  ],
};

// 监视 userInfo 的变化，并用其更新表单
watch(userInfo, (newUser) => {
  if (newUser) {
    formState.username = newUser.username;
    formState.nickname = newUser.nickname;
    formState.email = newUser.email;
  }
}, { immediate: true }); // immediate: true 确保组件加载时立即执行一次

onMounted(() => {
  // 页面加载时，调用 fetchUserInfo 来确保获取到最新的用户信息
  fetchUserInfo();
});

const handleSubmit = async () => {
  if (!formRef.value) return;
  const valid = await formRef.value.validate();
  if (valid) {
    await updateUserInfo({
      nickname: formState.nickname,
      email: formState.email,
    });
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
.loading-state {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 200px;
}
</style> 
 