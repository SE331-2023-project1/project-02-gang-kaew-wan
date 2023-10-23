<template>
  <p aria-live="assertive" :id="id">
    <slot />
  </p>
</template>

<script setup lang="ts">
defineProps<{
  id: string
}>()
</script>
