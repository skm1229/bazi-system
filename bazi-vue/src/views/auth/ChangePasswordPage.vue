<template>
  <AuthLayout>
    <template #header-title>修改密码</template>
    <el-form
      ref="formRef"
      :model="formState"
      :rules="rules"
      label-position="top"
      @submit.prevent="handleSubmit"
    >
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input v-model="formState.oldPassword" type="password" size="large" show-password />
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="formState.newPassword" type="password" size="large" show-password />
      </el-form-item>
      <el-form-item label="确认新密码" prop="confirmPassword">
        <el-input v-model="formState.confirmPassword" type="password" size="large" show-password />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" class="auth-button" :loading="isLoading">
          {{ isLoading ? '提交中...' : '确认修改' }}
        </el-button>
      </el-form-item>
    </el-form>
  </AuthLayout>
</template>

<script setup>
import { ref, reactive } from 'vue';
import AuthLayout from '@/components/AuthLayout.vue';
import { useUser } from '@/composables/useUser.js';

const { changePassword, isLoading } = useUser();
const formRef = ref(null);

const formState = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: '',
});

const validateConfirmPassword = (rule, value, callback) => {
  if (value !== formState.newPassword) {
    callback(new Error('两次输入的密码不一致！'));
  } else {
    callback();
  }
};

const rules = reactive({
  oldPassword: [{ required: true, message: '请输入旧密码', trigger: 'blur' }],
  newPassword: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能少于6位', trigger: 'blur' },
  ],
  confirmPassword: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { validator: validateConfirmPassword, trigger: 'blur' },
  ],
});

const handleSubmit = async () => {
  if (!formRef.value) return;
  const valid = await formRef.value.validate();
  if (valid) {
    await changePassword({
      oldPassword: formState.oldPassword,
      newPassword: formState.newPassword,
    });
    // 成功后，useAuth中的logout会自动跳转，这里可以不用额外操作
    formRef.value.resetFields();
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
 