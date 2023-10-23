<script setup lang="ts">
import CommentCard from '@/components/CommentCard.vue'
import type { Comment } from '@/types'

const emit = defineEmits(['updateComment'])
defineProps<{
  comments: Comment[]
}>()
</script>

<template>
  <div>
    <div :key="comment.id" v-for="comment in comments.filter((x) => x.parent === null)">
      <CommentCard :comment="comment" :comments="comments" @replied="emit('updateComment')" />
    </div>
  </div>
</template>
