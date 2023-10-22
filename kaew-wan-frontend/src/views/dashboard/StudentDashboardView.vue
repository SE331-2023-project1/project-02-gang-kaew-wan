<script setup lang="ts">
import RegistryService from '@/services/RegistryService'
import { useAuthStore } from '@/stores/auth'
import { ref } from 'vue'
import type { Announcement } from '@/types'
import { useRouter } from 'vue-router'
import AnnouncementCard from '@/components/AnnouncementCard.vue'

const authStore = useAuthStore()
const router = useRouter()

const id = authStore.currentID
const announcements = ref<Announcement[]>([])

RegistryService.getStudent(id).then((res) => {
  if (res.data.teacher) {
    RegistryService.getAnnouncementsByPerson(res.data.teacher.id)
      .then((response) => {
        announcements.value = response.data as Announcement[]
      })
      .catch((err) => {
        console.log(err)
        router.push({ name: 'network-error' })
      })
  }
})
</script>

<template>
  <main class="w-full max-w-7xl flex flex-col">
    <div class="flex flex-col w-full gap-4">
      <div class="flex flex-row items-center gap-4">
        <p class="text-4xl text-white">Your Announcement</p>
        <hr class="flex-1 border-0 border-b border-stone-700" />
      </div>
      <div
        v-if="announcements.length > 0"
        class="flex flex-col gap-4 h-96 overflow-y-scroll scrollbar-thin scrollbar-track-transparent scrollbar-thumb-stone-700 hover:scrollbar-thumb-stone-500 scrollbar-thumb-rounded-full"
      >
        <AnnouncementCard
          v-for="announcement in announcements"
          :key="announcement.id"
          :announcement="announcement"
        />
      </div>
      <div v-else>
        <p
          class="text-lg mt-2 w-full border p-4 rounded-xl border-white border-dashed opacity-50 font-sans tracking-wide"
        >
          Yippie! You have no announcement.
        </p>
      </div>
    </div>
  </main>
</template>

<style scoped></style>
