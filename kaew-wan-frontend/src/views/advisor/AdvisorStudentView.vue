<script setup lang="ts">
import { storeToRefs } from "pinia";
import { useAdvisorStore } from "@/stores/advisor";

const store = useAdvisorStore()
const advisor = storeToRefs(store).advisor
const students = advisor.value?.student
const intl = new Intl.DisplayNames(['en'], { type: 'region' })


</script>

<template>
  <main class="flex flex-col gap-4">
    <!-- TODO: Change to RouterLink -->
    <RouterLink
      class="relative font-serif bg-stone-700 shadow-md flex flex-row gap-4 hover:border-l-4 hover:pl-[2px] transition-all border-emerald-400 duration-100 group"
      v-for="student in students" :key="student.id" :to="{ name: 'student-information', params: { id: student.id } }">
      <img :src="student.image" class="w-32 aspect-square object-cover" />
      <div class="flex flex-col py-2 justify-start">
        <p class="text-xs opacity-50">#{{ student.id }}</p>
        <p class="text-xl group-hover:text-emerald-400 transition-colors duration-100">{{ `${student.first_name}
                  ${student.last_name}` }}</p>
        <p class="text-sm transition-colors duration-100 opacity-75">{{ intl.of(student.nationality) }}</p>
      </div>
    </RouterLink>
  </main>
  <!-- <main v-if="null">
    <img src="/svg/loading.svg" class="w-16">
  </main> -->
</template>

<style scoped>
</style>
