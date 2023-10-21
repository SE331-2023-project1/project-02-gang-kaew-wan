<script setup lang="ts">
import type { PropType } from 'vue'
import type { Announcement } from '@/types'

defineProps({
  announcement: {
    type: Object as PropType<Announcement>,
    required: true
  }
})
</script>

<!--TODO: Pai tam hai soui-->
<template>
  <div>
    <h5 class="text-white">#{{ announcement.id }}</h5>
    <h1>{{ announcement.message }}</h1>
    <h3>{{ announcement.file }}</h3>
  </div>
</template>

<style scoped></style>
