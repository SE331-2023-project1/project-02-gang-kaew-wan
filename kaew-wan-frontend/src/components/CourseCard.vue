<script setup lang="ts">
import type { PropType } from "vue";
import type { Course } from "@/types";

defineProps({
  course: {
    required: true,
    type: Object as PropType<Course>
  }
})
</script>

<template>
  <RouterLink :to="{ name: 'course-detail', params: { id: course.id } }"
    class="relative p-6 shadow-md bg-stone-800 flex flex-col gap-2 group border-l-4 border-transparent hover:border-emerald-400 transition-all">
    <div class="flex flex-col gap-2">
      <div class="flex flex-col md:flex-row items-center gap-2 md:gap-8">
        <p class="opacity-50">#{{ course.id }}</p>
        <p class="sm:text-2xl text-1xl font-bold text-white group-hover:text-emerald-400 transition-colors">{{
          course.course_name }}</p>
      </div>
      <p
        class="opacity-50 text-justify max-h-64 text-ellipsis overflow-hidden sm:text-white text-transparent bg-clip-text bg-gradient-to-b from-white from-80% to-95% to-transparent">
        {{ course.course_desc }}
      </p>

    </div>
  </RouterLink></template>
