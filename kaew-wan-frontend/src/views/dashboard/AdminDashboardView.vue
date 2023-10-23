<script setup lang="ts">
import ValidatedInput from '@/components/ValidatedInput.vue';
import AdvisorListView from '@/views/AdvisorListView.vue'
import StudentEmbeddedListView from '@/views/StudentEmbeddedListView.vue'
import { ref } from 'vue'
const teacher_pgn = ref<number>(1)
const student_pgn = ref<number>(1)
const keyword = ref<string>('')
</script>

<template>
  <main class="flex flex-col max-w-7xl gap-4 w-full">
    <div class="flex flex-col gap-2 flex-1">
      <div class="flex flex-row gap-4 items-center">
        <p class="text-2xl">Teachers</p>
        <hr class="px-2 flex-1 border-0 border-b border-stone-700" />
        <RouterLink
          class="transition-colors flex flex-row items-center px-2 py-1 rounded-full font-sans border border-green-600 hover:border-transparent hover:bg-green-600 text-green-600 hover:text-white"
          :to="{ name: 'add-advisor' }"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path stroke-linecap="round" stroke-linejoin="round" d="M12 6v12m6-6H6" />
          </svg>
          <p class="px-1">Add</p>
        </RouterLink>
      </div>
      <AdvisorListView
        :page="teacher_pgn"
        @next-page="teacher_pgn = teacher_pgn + 1"
        @prev-page="teacher_pgn = teacher_pgn - 1"
      />
    </div>
    <div class="flex flex-col gap-2 flex-1">
      <div class="flex flex-row gap-4 items-center">
        <p class="text-2xl">Students</p>
        <hr class="px-2 flex-1 border-0 border-b border-stone-700" />
      </div>
      <ValidatedInput v-model="keyword" label="Search" />
      <StudentEmbeddedListView
        :page="student_pgn"
        :keyword="keyword"
        @next-page="student_pgn = student_pgn + 1"
        @prev-page="student_pgn = student_pgn - 1"
      />
    </div>
  </main>
</template>

<style scoped></style>
