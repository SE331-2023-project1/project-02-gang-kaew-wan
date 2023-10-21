<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useAdvisorStore } from '@/stores/advisor'

const advisorStore = useAdvisorStore()
const advisor = storeToRefs(advisorStore).advisor
</script>

<template>
  <main v-if="advisor" class="flex flex-row w-full max-w-6xl gap-8">
    <div class="flex flex-col gap-2 flex-1 max-w-[16rem]">
      <img :src="advisor.image" class="w-64 aspect-square object-cover shadow-lg" />
      <div class="flex flex-col">
        <span class="opacity-50">
          {{ advisor.position }}
        </span>
        <span class="text-2xl"> {{ advisor.fname }} {{ advisor.lname }} </span>
      </div>
      <div class="flex flex-col shadow-lg font-sans">
        <RouterLink
          class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
          :to="{ name: 'advisor-information' }"
          >Information</RouterLink
        >
        <RouterLink
          class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
          :to="{ name: 'assign-student' }"
          >Assign Student</RouterLink
        >
      </div>
    </div>
    <div class="w-full flex-1">
      <RouterView :advisor="advisor" />
    </div>
  </main>
  <!--  TODO: implement advisor -->
</template>
