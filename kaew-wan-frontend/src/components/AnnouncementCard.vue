<script setup lang="ts">
import { computed } from 'vue'
import type { Announcement } from '@/types'
import ReactionPanel from '@/components/ReactionPanel.vue'
import VueMarkdown from 'vue-markdown-render'

const props = defineProps<{
  announcement: Announcement
}>()

const fileName = computed(() => {
  const list = props.announcement.file.split('/')
  return list[list.length - 1].split('?')[0]
})

const isImage = computed(() => {
  return (
    fileName.value.endsWith('.png') ||
    fileName.value.endsWith('.jpg') ||
    fileName.value.endsWith('.jpeg') ||
    fileName.value.endsWith('.gif')
  )
})
</script>

<template>
  <div class="flex flex-row gap-4">
    <div class="py-2">
      <img :src="announcement.teacher.image" class="aspect-square w-16 rounded-full object-cover" />
    </div>
    <div class="bg-stone-800 p-4 shadow-lg flex flex-col font-sans flex-1 rounded-lg">
      <p class="font-bold">{{ `${announcement.teacher.fname} ${announcement.teacher.lname}` }}</p>
      <VueMarkdown :source="announcement.message" />
      <img v-if="isImage" :src="announcement.file" class="max-w-xs border border-stone-700 m-2" />
      <a
        v-else
        :href="announcement.file"
        target="_blank"
        class="rounded-lg p-4 bg-stone-700 max-w-sm hover:brightness-125 flex flex-row items-center gap-4 my-2"
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
        {{ fileName }}
      </a>
      <ReactionPanel :reactable="announcement" :key="announcement.id"></ReactionPanel>
    </div>
  </div>
</template>
