<script setup lang="ts">
import type { Advisor } from '@/types'
defineProps<{
  advisor: Advisor
}>()
</script>

<template>
  <div class="w-full p-8 flex flex-col gap-4">
    <p class="text-3xl font-bold">Annoucements ({{ advisor.announcements.length }})</p>
    <hr class="border-0 border-b border-stone-700" />
    <div class="flex flex-col gap-4">
      <div
        v-for="announcement in advisor.announcements"
        class="bg-stone-800 p-4 shadow-lg flex flex-col gap-2 font-sans"
      >
        <p class="text-xl font-bold">{{ announcement.message }}</p>
        <a
          :href="announcement.file"
          class="rounded-lg p-4 bg-stone-700 max-w-xs hover:brightness-125 flex flex-row items-center gap-4"
          ><svg
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
          Attached File</a
        >
        <div class="flex flex-row relative items-center h-8 gap-2">
          <div
            v-for="reaction of announcement.reactions
              .reduce((prev, curr) => {
                if (prev.has(curr.emote)) {
                  prev.set(curr.emote, prev.get(curr.emote)! + 1)
                } else {
                  prev.set(curr.emote, 1)
                }
                return prev
              }, new Map<string, number>())
              .entries()"
            class="p-1 border rounded-lg border-opacity-25 flex flex-row gap-1"
          >
            <p>{{ reaction[0] }}</p>
            <p>{{ reaction[1] }}</p>
          </div>
          <button
            class="h-6 aspect-square border rounded-full flex justify-center items-center opacity-25 border-dashed hover:opacity-100 hover:border-solid"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="w-4 h-4"
            >
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 6v12m6-6H6" />
            </svg>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
