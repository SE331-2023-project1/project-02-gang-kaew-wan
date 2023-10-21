<script setup lang="ts">
import type { PropType } from 'vue'
import type { Announcement } from '@/types'
import ReactionPanel from '@/components/ReactionPanel.vue'

defineProps({
  announcement: {
    type: Object as PropType<Announcement>,
    required: true
  }
})
</script>

<template>
  <div class="bg-stone-800 p-4 shadow-lg flex flex-col gap-2 font-sans">
    <p class="text-xl font-bold">{{ announcement.message }}</p>
    <a
      :href="announcement.file"
      class="rounded-lg p-4 bg-stone-700 max-w-xs hover:brightness-125 flex flex-row items-center gap-4"
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
          d="M13.19 8.688a4.5 4.5 0 011.242 7.244l-4.5 4.5a4.5 4.5 0 01-6.364-6.364l1.757-1.757m13.35-.622l1.757-1.757a4.5 4.5 0 00-6.364-6.364l-4.5 4.5a4.5 4.5 0 001.242 7.244"
        />
      </svg>
      Attached File
    </a>
    <ReactionPanel :reactable="announcement" :key="announcement.id"></ReactionPanel>
  </div>
</template>

<style scoped></style>
