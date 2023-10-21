<script setup lang="ts">
import type {Advisor} from '@/types'
import AnnouncementCard from "@/components/AnnouncementCard.vue";

defineProps<{
  advisor: Advisor
}>()

</script>

<template>
  <div class="w-full p-8 flex flex-col gap-4">
    <p class="text-3xl font-bold">Announcements ({{ advisor.announcements.length }})</p>
    <hr class="border-0 border-b border-stone-700"/>
    <div class="flex flex-col gap-4">
      <AnnouncementCard v-for="announcement in advisor.announcements" :announcement="announcement"
                        :key="announcement.id"/>

    </div>
  </div>
</template>
