<script setup lang="ts">
import { usePersonStore } from '@/stores/person'
import { storeToRefs } from 'pinia'

const store = usePersonStore()
const person = storeToRefs(store).person
</script>

<template>
  <!--<h1>This is profile view</h1>-->
  <div v-if="person">
    <p>
      {{ person.id }}
      {{ person.department.name }}
    </p>
    <hr />

    {{ person.fname }} {{ person.lname }}
    <img :src="person.image" />
  </div>
</template>

<style scoped></style>
