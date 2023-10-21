<script setup lang="ts">
import StudentCard from '@/components/StudentCard.vue'
import RegistryService from '@/services/RegistryService'
import {computed, ref, watchEffect} from 'vue'
import {useRouter} from 'vue-router'
import {type Student} from '@/types'
import {useAuthStore} from "@/stores/auth";

const props = defineProps({
  page: {
    type: Number,
    required: true
  }
})
const router = useRouter()
const authStore = useAuthStore();
let teacherId: number;
if(authStore.isAdmin){
  teacherId = -1
}else{
  teacherId =  authStore.currentID;
}


const students = ref<Student[]>()
const students_count = ref<number>(0)
const maxPage = computed(() => {
  return Math.ceil(students_count.value / 10)
})

const hasNextPage = computed(() => {
  return props.page.valueOf() < maxPage.value
})

watchEffect(() => {
  changePage(props.page)
})

function changePage(page: number) {
  RegistryService.getStudents(10, page,"",teacherId)
      .then((res) => {
        res.data.sort((a, b) => {
          if (a.id && b.id) {
            return a.id - b.id
          } else {
            return 0
          }
        })
        students.value = res.data
        students_count.value = res.headers['x-total-count']
      })
      .catch(() => {
        router.push({name: 'NetworkError'})
      })
}
</script>

<template>
  <main class="w-full px-4 sm:p-0 sm:w-2/3 flex flex-col items-center gap-4" v-if="students">
    <div class="grid gap-4 w-full lg:grid-cols-2 grid-cols-1" v-if="students.length > 0">
      <StudentCard :student="student" v-for="student in students" :key="student.id"></StudentCard>
    </div>
    <div v-else>
      Opsss, you have no student. Please contact the admin!
    </div>


    <div class="flex justify-between w-full items-center">
      <RouterLink
          class="px-2 py-1 bg-emerald-400 text-black hover:shadow-md hover:brightness-75 flex group transition-all"
          :to="{ name: 'student-list', query: { page: props.page - 1 } }"
          rel="prev"
          :class="{ invisible: props.page <= 1 }"
      >
        <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
        >
          <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M15.75 19.5L8.25 12l7.5-7.5"
          />
        </svg
        >
        <span
            class="group overflow-hidden whitespace-nowrap max-w-0 opacity-0 group-hover:max-w-[10rem] group-hover:opacity-100 group-hover:ml-1 transition-all duration-500"
        >Previous Page</span
        >
      </RouterLink>
      <RouterLink
          class="px-2 py-1 bg-emerald-400 text-black hover:shadow-md hover:brightness-75 flex group transition-all"
          :to="{ name: 'student-list', query: { page: props.page + 1 } }"
          rel="next"
          :class="{ invisible: !hasNextPage }"
      >
        <span
            class="group overflow-hidden whitespace-nowrap opacity-0 max-w-0 group-hover:max-w-[10rem] group-hover:mr-1 group-hover:opacity-100 transition-all duration-500"
        >
          Next Page </span
        >
        <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5"/>
        </svg>
      </RouterLink>
    </div>
  </main>
</template>
