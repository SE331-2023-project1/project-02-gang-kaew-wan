<script setup lang="ts">
import { useCommentStore } from '@/stores/comment'
import { storeToRefs } from 'pinia'
import CommentCard from "@/components/CommentCard.vue";

const commentStore = useCommentStore()
const comments = storeToRefs(commentStore).comments
</script>

<template>

<CommentCard v-for="comment in comments" :comment="comment" :key="comment.id"/>

</template>

<style scoped></style>
