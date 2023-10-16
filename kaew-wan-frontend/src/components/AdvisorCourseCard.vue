<template>
  <RouterLink :to="{ name: 'course-detail', params: { id: course.id } }">
    <div class="bg-stone-700 shadow-md p-4 flex flex-col gap-1 hover:border-l-2 border-emerald-400 group">
      <p class="text-xs opacity-50">
        #{{ course.id }}
      </p>
      <div class="flex sm:flex-row flex-col items-baseline gap-2">
        <p class="text-lg sm:text-3xl font-bold group-hover:text-emerald-400 ">{{ course.course_name }}</p>
        <p class="text-xs opacity-75">{{ `Professor ${advisor.first_name} ${advisor.last_name}` }}</p>
      </div>
    </div>
  </RouterLink>
</template>

<script setup lang="ts">
import type { Advisor, Course } from '@/types';
import type { PropType } from 'vue';

defineProps({
  course: {
    type: Object as PropType<Course>,
    required: true
  },
  advisor: {
    type: Object as PropType<Advisor>,
    required: true
  },
})
</script>
