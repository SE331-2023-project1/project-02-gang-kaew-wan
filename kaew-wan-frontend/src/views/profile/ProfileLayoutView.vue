<script setup lang="ts">
import { usePersonStore } from '@/stores/person'
import { storeToRefs } from 'pinia'
import { useAuthStore } from '@/stores/auth'

const store = usePersonStore()
const person = storeToRefs(store).person
const authStore = useAuthStore()

const isOwner = person.value.id == authStore.currentID

console.log(person.value)
</script>

<template>
  <div v-if="person" class="mx-5 my-2 w-2/3">
    <h2 class="text-gray-400">#{{ person.id }}</h2>
    <h1 class="text-2xl text-white">{{ person.fname }} {{ person.lname }}</h1>

    <div id="nav" class="p-3 font-bold text-green-300">
      <RouterLink :to="{ name: 'profile-detail', params: { id: person.id } }">Detail</RouterLink>

      <RouterLink
        v-if="isOwner && authStore.isStudent"
        :to="{ name: 'profile-edit', params: { id: person.id } }"
      >
        | Edit</RouterLink
      >
    </div>

    <RouterView :person="person" />
  </div>
</template>

<style scoped></style>
