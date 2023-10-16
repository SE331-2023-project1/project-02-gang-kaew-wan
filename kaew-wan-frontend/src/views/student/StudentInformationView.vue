<script setup lang="ts">
import { storeToRefs } from "pinia";
import { useStudentStore } from "@/stores/student";
import { computed } from "vue";

const store = useStudentStore()
const { student, advisor } = storeToRefs(store)
const intl = new Intl.DisplayNames(['en'], { type: 'region' })
const nationality = computed(() => {
  if (student.value?.nationality) {
    try {
      return intl.of(student.value.nationality)
    } catch {
      return student.value?.nationality
    }
  }
  return student.value?.nationality
})

</script>

<template>
  <main class="flex flex-col gap-4" v-if="student && advisor">
    <div class="flex flex-col">
      <p class="text-sm opacity-50">Advisor</p>
      <div class="flex flex-col lg:flex-row p-4 bg-stone-700 shadow-md gap-4 border-emerald-400">
        <img :src="advisor.image" class="w-32 aspect-square object-cover shadow-lg" />
        <div class="flex flex-col justify-between">
          <RouterLink :to="{ name: 'advisor-information', params: { id: advisor.id } }" class="group">
            <p class="text-3xl group-hover:text-emerald-400 transition-colors">{{ advisor.prefix ?
              advisor.prefix + ' ' : '' }}{{ advisor.first_name }} {{
    advisor.last_name }}</p>
            <p class="font-light opacity-75 group-hover:text-emerald-400 transition-colors">{{ advisor.faculty }}</p>
          </RouterLink>
          <div class="flex flex-row">
            <p class="text-md">Contact Advisor:</p>
            <a :href="`mailto:${advisor.first_name.toLowerCase()}.${advisor.last_name.toLowerCase()[0]}@cmu.ac.th`"
              class="text-emerald-400 hover:brightness-75 underline ml-2">{{
                `${advisor.first_name.toLowerCase()}.${advisor.last_name.toLowerCase()[0]}@cmu.ac.th` }} </a>
          </div>
        </div>
      </div>
    </div>
    <div class="flex flex-col">
      <p class="text-sm opacity-50">Address</p>
      <p class="text-xl">{{ student.address }}</p>
    </div>
    <div class="flex flex-col">
      <p class="text-sm opacity-50">Birthday</p>
      <p class="text-xl">{{ new Date(student.birthday / 1).toLocaleDateString() }}</p>
    </div>
    <div class="flex flex-col">
      <p class="text-sm opacity-50">Nationality</p>
      <p class="text-xl">{{ nationality }}</p>
    </div>
  </main>
  <main v-else>
    <img src="/svg/loading.svg" class="w-16">
  </main>
</template>

<style scoped>
</style>
