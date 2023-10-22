<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import AdminDashboardView from '@/views/dashboard/AdminDashboardView.vue'
import StudentDashboardVIew from '@/views/dashboard/StudentDashboardView.vue'
import TeacherDashboardView from '@/views/dashboard/TeacherDashboardView.vue'

const authStore = useAuthStore()
</script>

<template>
  <main v-if="authStore.user !== null">
    <AdminDashboardView v-if="authStore.currentRole === 'ROLE_ADMIN'" />
    <StudentDashboardVIew v-if="authStore.currentRole === 'ROLE_STUDENT'" />
    <TeacherDashboardView v-if="authStore.currentRole === 'ROLE_TEACHER'" />
  </main>
</template>

<style scoped></style>
