<script setup lang="ts">
import AnnouncementCard from '@/components/AnnouncementCard.vue'
import ImageUpload from '@/components/ImageUpload.vue'
import apiClient from '@/services/AxiosClient'
import RegistryService from '@/services/RegistryService'
import { useAuthStore } from '@/stores/auth'
import { useMessageStore } from '@/stores/message'
import type { Announcement, Comment, OutgoingAnnouncement } from '@/types'
import { ref, type VNodeRef } from 'vue'
import VueMarkdown from 'vue-markdown-render'
import StudentEmbeddedListView from '../StudentEmbeddedListView.vue'
import CommentView from '../CommentView.vue'

const student_pgn = ref<number>(1)
const authStore = useAuthStore()
const announcements = ref<Announcement[]>([])
const comments = ref<Comment[]>([])
const openPreview = ref<boolean>(false)
const announcementBody = ref<OutgoingAnnouncement>({
  message: '',
  file: '',
  teacher: {
    id: authStore.currentPersonID
  }
})

function updateAnnouncements() {
  if (authStore.currentPersonID) {
    RegistryService.getAnnouncementsByPerson(authStore.currentPersonID).then((res) => {
      announcements.value = res.data as Announcement[]
    })
  }
}
function updateComments() {
  if (authStore.currentPersonID) {
    RegistryService.getComments(authStore.currentPersonID).then((res) => {
      comments.value = (res.data as Comment[]).sort((a, b) => a.id - b.id)
    })
  }
}
function sendAnnouncement() {
  apiClient
    .post('/announcements', announcementBody.value)
    .then(() => {
      useMessageStore().flashMessage('Posted Announcement.')
      updateAnnouncements()
      announcementBody.value = {
        message: '',
        file: '',
        teacher: {
          id: authStore.currentPersonID
        }
      }
    })
    .catch(() => {
      useMessageStore().flashMessage('Failed to post announcement.')
    })
}
updateAnnouncements()
updateComments()
</script>

<template>
  <main class="flex flex-col max-w-7xl gap-4 w-full">
    <div class="flex flex-col gap-2 flex-1">
      <div class="flex flex-row gap-4 items-center">
        <p class="text-2xl">My Students</p>
        <hr class="px-2 flex-1 border-0 border-b border-stone-700" />
      </div>
      <StudentEmbeddedListView
        :page="student_pgn"
        @next-page="student_pgn = student_pgn + 1"
        @prev-page="student_pgn = student_pgn - 1"
      />
    </div>
    <div class="flex flex-row items-center gap-4">
      <p class="text-2xl text-white">My Announcements</p>
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
    <div class="bg-stone-800 rounded-lg pt-2 pb-1 font-sans flex flex-col">
      <div class="flex flex-row gap-4 items-baseline px-4">
        <p
          @click="openPreview = false"
          class="w-fit cursor-pointer p-1"
          :class="{ 'border-b-2 border-green-500  font-bold': !openPreview }"
        >
          Compose
        </p>
        <p
          @click="openPreview = true"
          class="w-fit cursor-pointer p-1"
          :class="{ 'border-b-2 border-green-500  font-bold': openPreview }"
        >
          Preview
        </p>
      </div>
      <div v-if="!openPreview" class="w-full min-h-fit">
        <textarea
          class="h-32 form-textarea bg-stone-700 resize-none border-0 w-full focus:ring-0 focus:border-b-2 placeholder:text-stone-400"
          v-model="announcementBody.message"
          placeholder="Compose your announcement here..."
        />
      </div>
      <div v-else class="w-full min-h-[6rem] p-4">
        <VueMarkdown :source="announcementBody.message" />
      </div>
      <div class="flex flex-row justify-between px-1 items-center">
        <ImageUpload v-model="announcementBody.file" :image-upload="false" />
        <button
          class="flex flex-row rounded-full transition-colors items-center text-stone-400 border border-stone-400 hover:bg-green-600 hover:border-transparent hover:text-white px-2 py-1"
          @click="sendAnnouncement()"
        >
          <p class="px-1">Send</p>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-4 h-4"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5"
            />
          </svg>
        </button>
      </div>
    </div>
    <div class="flex flex-col gap-2 flex-1">
      <div class="flex flex-row gap-4 items-center">
        <p class="text-2xl">My Conversations</p>
        <hr class="px-2 flex-1 border-0 border-b border-stone-700" />
      </div>
      <CommentView @update-comment="updateComments()" :comments="comments" />
    </div>
  </main>
</template>
