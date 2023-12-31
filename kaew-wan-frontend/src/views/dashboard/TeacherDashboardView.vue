<script setup lang="ts">
import AnnouncementCard from '@/components/AnnouncementCard.vue'
import ImageUpload from '@/components/ImageUpload.vue'
import apiClient from '@/services/AxiosClient'
import RegistryService from '@/services/RegistryService'
import { useAuthStore } from '@/stores/auth'
import { useMessageStore } from '@/stores/message'
import {
  type OutgoingComment,
  type Announcement,
  type Comment,
  type OutgoingAnnouncement,
  type Student
} from '@/types'
import { ref } from 'vue'
import VueMarkdown from 'vue-markdown-render'
import StudentEmbeddedListView from '../StudentEmbeddedListView.vue'
import CommentView from '../CommentView.vue'
import BaseSelect from '@/components/BaseSelect.vue'

const student_pgn = ref<number>(1)
const authStore = useAuthStore()
const announcements = ref<Announcement[]>([])
const students = ref<Student[]>([])
const comments = ref<Comment[]>([])
const openPreview = ref<boolean>(false)
const openCommentPreview = ref<boolean>(false)
const openComments = ref<boolean>(true)
const openAnnouncements = ref<boolean>(true)
const announcementBody = ref<OutgoingAnnouncement>({
  message: '',
  file: '',
  teacher: {
    id: authStore.currentPersonID
  }
})
const commentBody = ref<OutgoingComment>({
  message: '',
  sender: { id: authStore.currentPersonID },
  receiver: { id: 0 }
})

function updateStudentList() {
  if (authStore.currentPersonID) {
    RegistryService.getStudents(-1, -1, undefined, authStore.currentPersonID).then((res) => {
      students.value = res.data as Student[]
    })
  }
}
function updateAnnouncements() {
  if (authStore.currentPersonID) {
    RegistryService.getAnnouncementsByPerson(authStore.currentPersonID).then((res) => {
      announcements.value = (res.data as Announcement[]).sort((a, b) => b.id - a.id)
    })
  }
}
function updateComments() {
  if (authStore.currentPersonID) {
    RegistryService.getComments(authStore.currentPersonID).then((res) => {
      comments.value = (res.data as Comment[]).sort((a, b) => b.id - a.id)
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
function submitComment() {
  apiClient
    .post(`/comments`, commentBody.value)
    .then(() => {
      useMessageStore().flashMessage('Replied!')
      updateComments()
      commentBody.value = {
        message: '',
        sender: { id: authStore.currentPersonID },
        receiver: { id: 0 }
      }
    })
    .catch(() => {
      useMessageStore().flashMessage('Failed to reply.')
    })
}
updateAnnouncements()
updateComments()
updateStudentList()
</script>

<template>
  <main class="flex flex-col max-w-7xl gap-4 w-full">
    <div class="flex flex-col gap-2 flex-1">
      <div class="flex flex-row gap-4 items-center">
        <p class="text-2xl">My Students</p>
        <hr class="px-2 flex-1 border-0 border-b border-stone-700" />
      </div>
      <StudentEmbeddedListView
        keyword=""
        :page="student_pgn"
        @next-page="student_pgn = student_pgn + 1"
        @prev-page="student_pgn = student_pgn - 1"
      />
    </div>
    <div
      class="flex flex-row items-center gap-4 group cursor-pointer"
      @click="openAnnouncements = !openAnnouncements"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="w-6 h-6 text-stone-700 group-hover:text-stone-100 transition-transform"
        :class="{ 'rotate-180': openAnnouncements }"
      >
        <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
      </svg>
      <p class="text-2xl text-white">My Announcements</p>
      <hr class="flex-1 border-0 border-b border-stone-700" />
    </div>
    <div v-if="openAnnouncements" class="bg-stone-800 rounded-lg pt-2 pb-1 font-sans flex flex-col">
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
          class="flex flex-row rounded-full transition-colors items-center text-stone-500 border border-stone-500 hover:bg-green-600 hover:border-transparent hover:text-white px-2 py-1"
          :class="{ 'pointer-events-none': announcementBody.message.trim().length === 0 }"
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
    <div v-if="announcements.length > 0 && openAnnouncements" class="flex flex-col gap-4">
      <AnnouncementCard
        v-for="announcement in announcements"
        :key="announcement.id"
        :announcement="announcement"
      />
    </div>

    <div class="flex flex-col gap-2 flex-1">
      <div
        class="flex flex-row gap-4 items-center group cursor-pointer"
        @click="openComments = !openComments"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-6 h-6 text-stone-700 group-hover:text-stone-100 transition-transform"
          :class="{ 'rotate-180': openComments }"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
        </svg>
        <p class="text-2xl">My Conversations</p>
        <hr class="px-2 flex-1 border-0 border-b border-stone-700" />
      </div>
      <div class="bg-stone-800 rounded-lg pt-2 pb-1 font-sans flex flex-col" v-if="openComments">
        <div class="flex flex-row gap-4 items-baseline px-4">
          <p
            @click="openCommentPreview = false"
            class="w-fit cursor-pointer p-1"
            :class="{ 'border-b-2 border-green-500  font-bold': !openCommentPreview }"
          >
            Compose
          </p>
          <p
            @click="openCommentPreview = true"
            class="w-fit cursor-pointer p-1"
            :class="{ 'border-b-2 border-green-500  font-bold': openCommentPreview }"
          >
            Preview
          </p>
        </div>
        <div v-if="!openCommentPreview" class="w-full min-h-fit">
          <textarea
            class="h-32 form-textarea bg-stone-700 resize-none border-0 w-full focus:ring-0 focus:border-b-2 placeholder:text-stone-400"
            v-model="commentBody.message"
            placeholder="Compose your comment here..."
          />
        </div>
        <div v-else class="w-full min-h-[6rem] p-4">
          <VueMarkdown :source="commentBody.message" />
        </div>
        <div class="flex flex-row justify-between px-4 items-center">
          <BaseSelect
            label="Receiver"
            v-model="commentBody.receiver.id"
            :options="students"
            :key-extractor="(x) => x.id"
            :value-extractor="(x) => x.id"
            :text-extractor="(x) => `${x.fname} ${x.lname}`"
          />
          <button
            class="flex flex-row rounded-full transition-colors items-center text-stone-500 border border-stone-500 hover:bg-green-600 hover:border-transparent hover:text-white px-2 py-1"
            :class="{
              'pointer-events-none':
                commentBody.receiver.id === 0 || commentBody.message.trim().length === 0
            }"
            @click="submitComment()"
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
      <div v-if="openComments">
        <CommentView @update-comment="updateComments()" :comments="comments" />
      </div>
    </div>
  </main>
</template>
