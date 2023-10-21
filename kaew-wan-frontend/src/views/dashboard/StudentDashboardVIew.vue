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
        // console.log(response.data)
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
  <h1 class="text-4xl text-amber-500 mb-4">
    Your
    <span
      class="bg-clip-text bg-gradient-to-r from-lime-400 via-emerald-400 to-sky-400 text-transparent"
      >Announcement</span
    >
  </h1>

  <div v-if="announcements.length > 0">
    <AnnouncementCard
      v-for="announcement in announcements"
      :key="announcement.id"
      :announcement="announcement"
    />
  </div>
  <div v-else>
    <span class="text-green-300 text-lg mt-2"> Yippy! You have no announcement </span>
  </div>
</template>

<style scoped></style>
