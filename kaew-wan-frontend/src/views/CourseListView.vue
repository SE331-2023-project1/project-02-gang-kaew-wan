<script setup lang="ts">
import { computed, ref, watchEffect } from "vue";
import CourseCard from "@/components/CourseCard.vue";
import { useRouter } from "vue-router";
import type { Course } from "@/types";
import RegistryService from "@/services/RegistryService";

const props = defineProps({
  page: {
    type: Number,
    required: true
  }
})
const router = useRouter()
const courses = ref<Course[]>()
const courses_count = ref<number>(0)
const maxPage = computed(() => {
  return Math.ceil(courses_count.value / 6)
})

const hasNextPage = computed(() => {
  return props.page.valueOf() < maxPage.value
})

watchEffect(() => {
  changePage(props.page)
})

function changePage(page: number) {
  RegistryService.getCourses(6, page).then((res) => {
    courses.value = res.data
    courses_count.value = res.headers['x-total-count']
  }).catch(() => {
    router.push({ name: 'NetworkError' })
  })
}

</script>

<template>
  <main class="w-full max-w-6xl flex flex-col items-center gap-4">
    <div class="flex flex-col gap-4">
      <CourseCard :course="course" v-for="course in courses" :key="course.id" />
    </div>
    <div class="flex justify-between w-full items-center">
      <RouterLink
        class="px-2 py-1 bg-emerald-400 text-black hover:shadow-md hover:brightness-75 flex group transition-all"
        :to="{ name: 'course-list', query: { page: props.page - 1 } }" rel="prev"
        :class="{ 'invisible': props.page <= 1 }">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
          class="w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5" />
        </svg><span
          class="group overflow-hidden whitespace-nowrap max-w-0 opacity-0 group-hover:max-w-[10rem] group-hover:opacity-100 group-hover:ml-1 transition-all duration-500">Previous
          Page</span>
      </RouterLink>
      <RouterLink
        class="px-2 py-1 bg-emerald-400 text-black hover:shadow-md hover:brightness-75 flex group transition-all "
        :to="{ name: 'course-list', query: { page: props.page + 1 } }" rel="next" :class="{ 'invisible': !hasNextPage }">
        <span
          class="group overflow-hidden whitespace-nowrap opacity-0 max-w-0 group-hover:max-w-[10rem] group-hover:mr-1 group-hover:opacity-100 transition-all duration-500">
          Next Page
        </span><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
          stroke="currentColor" class="w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5" />
        </svg>
      </RouterLink>
    </div>
  </main>
</template>
