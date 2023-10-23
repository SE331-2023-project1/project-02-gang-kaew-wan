<script setup lang="ts">
import CommentCard from '@/components/CommentCard.vue'
import { useAuthStore } from '@/stores/auth'
import RegistryService from '@/services/RegistryService'
import { ref } from 'vue'
import type { Comment } from '@/types'

const authStore = useAuthStore()
const comments = ref<Comment[]>([])
function updateComments() {
  if (authStore.currentPersonID) {
    RegistryService.getComments(authStore.currentPersonID).then((res) => {
      comments.value = (res.data as Comment[]).sort((a, b) => b.id - a.id)
    })
  }
}
updateComments()
</script>

<template>
  <main class="w-full max-w-7xl flex flex-col gap-2">
    <div class="text-2xl">Comments</div>
    <CommentCard
      @replied="updateComments()"
      v-for="comment in comments?.filter((comment2) => comment2.parent === null)"
      :comment="comment"
      :comments="comments!"
      :key="comment.id"
    />
  </main>
</template>

<style scoped></style>
