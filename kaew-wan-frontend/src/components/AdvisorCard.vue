<script setup lang="ts">
import type { PropType } from 'vue'
import type { Advisor } from '@/types'

defineProps({
  advisor: {
    required: true,
    type: Object as PropType<Advisor>
  }
})
</script>

<template>
  <RouterLink :to="{ name: 'advisor-information', params: { id: advisor.id } }">
    <div
      class="flex flex-row h-full sm:flex-col overflow-hidden shadow-md bg-stone-800 hover:border-emerald-400 transition-all border-r-2 sm:border-r-0 sm:border-b-2 border-transparent duration-100 group"
    >
      <div>
        <img
          :src="advisor.image"
          class="w-full aspect-square object-cover"
          :alt="`(${advisor.position}) ${advisor.fname} ${advisor.lname}`"
        />
      </div>
      <div class="px-6 py-4">
        <p class="text-xs opacity-50">#{{ advisor.id }}</p>
        <p class="text-lg group-hover:text-emerald-400 transition-colors text-ellipsis">
          {{`(${advisor.position}) ${advisor.fname} ${advisor.lname}`}}
        </p>
        <p class="text-sm font-light opacity-75">{{ advisor.department.name }}</p>
      </div>
    </div>
  </RouterLink>
</template>

<style scoped></style>
