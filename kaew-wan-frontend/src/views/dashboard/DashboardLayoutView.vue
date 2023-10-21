<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import AdminDashboardView from '@/views/dashboard/AdminDashboardView.vue'
import StudentDashboardVIew from '@/views/dashboard/StudentDashboardVIew.vue'
import TeacherDashboardView from '@/views/dashboard/TeacherDashboardView.vue'

const authStore = useAuthStore()
</script>

<template>
  <div v-if="authStore.user !== null">
    <AdminDashboardView v-if="authStore.currentRole === 'ROLE_ADMIN'" />
    <StudentDashboardVIew v-if="authStore.currentRole === 'ROLE_STUDENT'" />
    <TeacherDashboardView v-if="authStore.currentRole === 'ROLE_TEACHER'" />
  </div>
</template>

<style scoped></style>
