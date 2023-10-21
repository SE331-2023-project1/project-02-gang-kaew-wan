<script setup lang="ts">

import type {PropType} from "vue";
import type {Comment} from "@/types";
import ReactionPanel from "@/components/ReactionPanel.vue";

defineProps( {
  comment: {
    type: Object as PropType<Comment>,
    required: true
  }
})

</script>

<template>
  <div class="bg-stone-800 p-4">
    <p>Sender: {{comment.sender.fname}}</p>
    <p class="text-xl font-bold">{{ comment.message }}</p>
    <ReactionPanel :reactable="comment" :key="comment.id"></ReactionPanel>
  </div>
</template>

<style scoped>

</style>