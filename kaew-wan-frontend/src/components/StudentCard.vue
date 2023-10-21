<script setup lang="ts">
import type { Student } from '@/types'
import { type PropType, computed } from 'vue'
const intl = new Intl.DisplayNames(['en'], { type: 'region' })

const props = defineProps({
  student: {
    type: Object as PropType<Student>,
    required: true
  }
})
</script>

<template>
  <RouterLink :to="{ name: 'student-information', params: { id: student.id } }">
    <div
      class="relative font-serif bg-stone-800 shadow-md flex flex-row gap-4 hover:border-l-4 hover:pl-[2px] transition-all border-emerald-400 duration-100 group"
    >
      <img :src="student.image" class="w-24 aspect-square object-cover" />
      <div class="flex flex-col py-2 justify-start">
        <p class="text-xs opacity-50">{{ student.studentId }}</p>
        <p class="text-xl group-hover:text-emerald-400 transition-colors duration-100">
          {{ `${student.fname} ${student.lname}` }}
        </p>
        <p class="text-sm group-hover:text-emerald-400 transition-colors duration-100 opacity-50">
          {{ student.department.name }}
        </p>
      </div>
    </div>
  </RouterLink>
</template>
