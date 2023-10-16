<script setup lang="ts">
import { storeToRefs } from "pinia";
import { useAdvisorStore } from "@/stores/advisor";

const store = useAdvisorStore()
const { advisor } = storeToRefs(store)

</script>

<template>
  <main class="flex flex-col gap-4" v-if="advisor">
    <div class="flex flex-col">
      <p class="text-sm opacity-50">Faculty</p>
      <p class="text-xl">{{ advisor.faculty }}</p>
    </div>
    <div class="flex flex-col">
      <p class="text-sm opacity-50">Email</p>
      <p class="text-xl">{{ `${advisor.first_name.toLowerCase()}.${advisor.last_name.toLowerCase()[0]}@cmu.ac.th` }}</p>
    </div>
  </main>
  <main v-else>
    <img src="/svg/loading.svg" class="w-16">
  </main>
</template>

<style scoped>
</style>
