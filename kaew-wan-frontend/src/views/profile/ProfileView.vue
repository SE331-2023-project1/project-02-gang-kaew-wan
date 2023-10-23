<script setup lang="ts">
import { usePersonStore } from '@/stores/person'
import { storeToRefs } from 'pinia'

const store = usePersonStore()
const person = storeToRefs(store).person
</script>

<template>
  <div v-if="person" class="w-full">
    <div class="flow-root">
      <dl class="text-sm divide-y divide-stone-900 flex flex-col">
        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">First name</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            {{ person.fname }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">Last name</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            {{ person.lname }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">Department</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            {{ person.department.name }}
          </dd>
        </div>
      </dl>
    </div>
  </div>
</template>

<style scoped></style>
