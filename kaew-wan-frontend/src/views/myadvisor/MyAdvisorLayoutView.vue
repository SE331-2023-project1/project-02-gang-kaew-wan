<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useAdvisorStore } from '@/stores/advisor'

const advisorStore = useAdvisorStore()
const advisor = storeToRefs(advisorStore).advisor
</script>

<template>
  <div v-if="advisor" class="mx-5 my-2 w-2/3">
    <div class="flex flex-col gap-2 flex-1 max-w-[16rem]">
      <h2 class="text-gray-400">#{{ advisor.id }}</h2>

      <div class="flex flex-col">
        <span class="opacity-50"> {{ advisor.fname }} {{ advisor.lname }} </span>
      </div>

      <div class="flex flex-col shadow-lg font-sans">
        <RouterLink
          class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
          :to="{ name: 'my-advisor-detail' }"
        >
          Detail
        </RouterLink>
        <!--        For review advisor feature -->
        <!--        <RouterLink-->
        <!--            class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"-->
        <!--            :to="{ name: 'my-advisor-detail'}">-->
        <!--          Detail-->
        <!--        </RouterLink>-->
      </div>
    </div>
    <div class="w-full flex-1">
      <RouterView :advisor="advisor" />
    </div>
  </div>
</template>

<style scoped></style>
