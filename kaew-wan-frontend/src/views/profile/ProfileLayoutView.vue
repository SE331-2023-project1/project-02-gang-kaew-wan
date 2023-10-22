<script setup lang="ts">
import {usePersonStore} from '@/stores/person'
import {storeToRefs} from 'pinia'
import {useAuthStore} from '@/stores/auth'

const store = usePersonStore()
const person = storeToRefs(store).person
const authStore = useAuthStore()

const isOwner = person.value?.id == authStore.currentID

// console.log(person.value)
</script>

<template>
  <div v-if="person" class="mx-5 my-2 w-2/3">


    <div class="flex flex-col gap-2 flex-1 max-w-[16rem]">
      <h2 class="text-gray-400">#{{ person.id }}</h2>

      <div class="flex flex-col">
        <span class="opacity-50">
          {{ person.fname }} {{ person.lname }}
        </span>
      </div>


      <div class="flex flex-col shadow-lg font-sans">
        <RouterLink
            class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
            :to="{ name: 'profile-detail', params: { id: person.id } }">
          Detail
        </RouterLink>

        <RouterLink
            v-if="isOwner && authStore.isStudent"
            class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
            :to="{ name: 'profile-edit', params: { id: person.id } }"
        >
          Edit
        </RouterLink
        >
      </div>
    </div>
    <div class="w-full flex-1">
      <RouterView :person="person"/>
    </div>

  </div>
</template>

<style scoped></style>
