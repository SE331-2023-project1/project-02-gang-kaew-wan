<script setup lang="ts">
import { useCourseStore } from "@/stores/course";
import { storeToRefs } from "pinia";

const store = useCourseStore()
const course = storeToRefs(store).course
const lecturer = storeToRefs(store).lecturer
</script>

<template>
  <main class="w-full max-w-6xl bg-stone-800 p-4 sm:p-8" v-if="course && lecturer">
    <p class="text-sm opacity-50">#{{ course.id }}</p>
    <p class="text-3xl">{{ course.course_name }}</p>
    <hr class="my-4" />
    <div class="flex flex-col gap-4">

      <p class="text-emerald-400">Course Description:
        <span class="text-white"> {{ course.course_desc }}</span>
      </p>
      <p class="text-emerald-400"> Taught by: <RouterLink :to="{ name: 'advisor-information', params: { id: lecturer.id } }"
          class="text-white underline hover:brightness-75">{{ lecturer.prefix ? lecturer.prefix + ' ' : '' }}{{
            lecturer.first_name }}
          {{ lecturer.last_name }}</RouterLink>
      </p>
    </div>
  </main>
</template>
