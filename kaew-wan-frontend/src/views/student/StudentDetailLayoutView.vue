<script setup lang="ts">
import { useStudentStore } from '@/stores/student'
import { storeToRefs } from 'pinia'

const studentStore = useStudentStore()
const student = storeToRefs(studentStore).student
</script>

<template>
  <div v-if="student" class="flex flex-row w-full max-w-7xl gap-8">
    <div class="flex flex-col gap-2 flex-1 max-w-[16rem]">
      <div class="w-full aspect-square">
        <img :src="student.image" class="aspect-square object-cover" />
      </div>
      <div class="flex flex-col">
        <span class="opacity-50">
          {{ student.studentId }}
        </span>
        <span class="text-2xl"> {{ student.fname }} {{ student.lname }} </span>
      </div>
      <div class="flex flex-col shadow-lg font-sans flex-1">
        <RouterLink
          class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
          :to="{ name: 'student-information', params: { id: student.id } }"
        >
          Detail
        </RouterLink>
      </div>
    </div>
    <div class="w-full flex-1">
      <RouterView :student="student" />
    </div>
  </div>
</template>
