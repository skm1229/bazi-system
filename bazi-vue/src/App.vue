<template>
  <div id="app-container">
    <Header />
    <main class="main-content">
      <router-view />
    </main>
    <Footer />
  </div>
</template>

<script setup>
import Header from '@/components/layout/Header.vue';
import Footer from '@/components/layout/Footer.vue';
</script>

<style scoped>
#app-container { display: flex; flex-direction: column; min-height: 100vh; }
.main-content { flex: 1; padding: var(--spacing-xl) 0; }
</style>
