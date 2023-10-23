<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useAdvisorStore } from '@/stores/advisor'
import { computed, ref, watchEffect } from 'vue'
import apiClient from '@/services/AxiosClient'
import type { Review } from '@/types'
import { useAuthStore } from '@/stores/auth'
import { useMessageStore } from '@/stores/message'

const authStore = useAuthStore()
const advisorStore = useAdvisorStore()
const advisor = storeToRefs(advisorStore).advisor
const ratingTemp = ref<number>(0)
const ratingAvg = computed(() => {
  if (advisor.value?.reviews) {
    return (
      advisor.value.reviews
        .map((x) => x.rating)
        .reduce((prev, curr) => {
          return prev + curr
        }, 0) / advisor.value.reviews.length
    )
  } else {
    return 0
  }
})
function rate(n: number) {
  apiClient
    .post('/reviews', {
      rating: n,
      student: { id: authStore.currentPersonID },
      teacher: { id: advisor.value?.id }
    } as Review)
    .then(() => {
      useMessageStore().flashMessage('Added Review!')
      advisorStore.updateAdvisor()
    })
    .catch(() => {
      useMessageStore().flashMessage('Failed to add review.')
    })
}
</script>

<template>
  <main v-if="advisor" class="flex flex-row w-full max-w-7xl gap-8">
    <div class="flex flex-col gap-2 flex-1 max-w-[16rem]">
      <img :src="advisor.image" class="w-64 aspect-square object-cover shadow-lg" />
      <div class="flex flex-col">
        <span class="opacity-50">
          {{ advisor.position }}
        </span>
        <span class="text-2xl"> {{ advisor.fname }} {{ advisor.lname }} </span>
        <div class="flex flex-row gap-2 items-center">
          <div class="flex flex-row">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="w-6 h-6"
              :class="{
                'text-stone-600': ratingAvg < n,
                '!text-blue-400': ratingTemp >= n,
                'text-yellow-400': ratingAvg >= n
              }"
              :key="`${n}-star`"
              v-for="n in [1, 2, 3, 4, 5]"
              @click="rate(n)"
              @mouseover="ratingTemp = n"
              @mouseleave="ratingTemp = 0"
            >
              <path
                fill-rule="evenodd"
                d="M10.788 3.21c.448-1.077 1.976-1.077 2.424 0l2.082 5.007 5.404.433c1.164.093 1.636 1.545.749 2.305l-4.117 3.527 1.257 5.273c.271 1.136-.964 2.033-1.96 1.425L12 18.354 7.373 21.18c-.996.608-2.231-.29-1.96-1.425l1.257-5.273-4.117-3.527c-.887-.76-.415-2.212.749-2.305l5.404-.433 2.082-5.006z"
                clip-rule="evenodd"
              />
            </svg>
          </div>
          <span>{{ ratingAvg.toFixed(2) }}({{ advisor.reviews?.length || 0 }})</span>
        </div>
      </div>
      <div class="flex flex-col shadow-lg font-sans">
        <RouterLink
          class="bg-stone-800 [&.router-link-exact-active]:bg-stone-700 hover:brightness-125 w-full p-4 text-lg"
          :to="{ name: 'my-advisor-detail' }"
        >
          Detail
        </RouterLink>
      </div>
    </div>
    <div class="w-full flex-1">
      <RouterView :advisor="advisor" />
    </div>
  </main>
</template>
