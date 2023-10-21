<template>
  <main class="flex max-w-6xl w-full bg-stone-800 p-8" v-if="student">
    <div class="flex md:flex-row w-full gap-4 flex-col">
      <div class="flex flex-col gap-4 flex-shrink-0">
        <p class="text-3xl opacity-75">Student</p>
        <div class="flex flex-row md:flex-col gap-4">
          <img
            :src="student.image"
            class="p-1 border border-white aspect-square w-48 object-cover"
          />
          <div class="flex flex-col flex-1">
            <RouterLink
              :to="{ name: 'student-information' }"
              class="[&.router-link-exact-active]:border-emerald-400 text-lg [&.router-link-exact-active]:text-emerald-400 px-2 py-1 border-l-2 hover:border-l-4 transition-all"
            >
              Information</RouterLink
            >
            <RouterLink
              :to="{ name: 'student-comments' }"
              class="[&.router-link-exact-active]:border-emerald-400 text-lg [&.router-link-exact-active]:text-emerald-400 px-2 py-1 border-l-2 hover:border-l-4 transition-all"
            >
              Comments</RouterLink
            >
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-4 flex-1">
        <p class="text-3xl">{{ `${student.first_name} ${student.last_name}` }}</p>
        <hr />
        <RouterView />
      </div>
    </div>
  </main>
  <main v-else>
    <img src="/svg/loading.svg" class="w-16" />
  </main>
</template>

<script setup lang="ts">
import { useStudentStore } from '@/stores/student'
import { storeToRefs } from 'pinia'
const { student } = storeToRefs(useStudentStore())
</script>
