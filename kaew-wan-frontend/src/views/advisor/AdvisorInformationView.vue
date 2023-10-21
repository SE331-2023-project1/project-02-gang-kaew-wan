<script setup lang="ts">
import ValidatedInput from '@/components/ValidatedInput.vue'
import apiClient from '@/services/AxiosClient'
import { useMessageStore } from '@/stores/message'
import type { Advisor } from '@/types'
import { useField, useForm } from 'vee-validate'
import { ref } from 'vue'
import * as yup from 'yup'
const props = defineProps<{
  advisor: Advisor
}>()
const inputPane = ref<number>(0)
const validationSchema = yup.object({
  emote: yup
    .string()
    .required('Emote required.')
    .test('single-emote', 'Must be 1 character.', (x) => {
      return [...x].length === 1
    })
})
const { errors, handleSubmit } = useForm({
  validationSchema,
  initialValues: {
    emote: ''
  }
})

const { value: emote } = useField<string>('emote')
function addReaction(id: number, emote: string) {
  props.advisor.announcements
    .find((x) => x.id === id)
    ?.reactions.push({
      id: 0,
      emote: emote
    })
  apiClient
    .post(`/reactions/${id}`, {
      emote
    })
    .catch(() => {
      useMessageStore().flashMessage('Error adding reaction')
    })
}

function openPane(id: number) {
  inputPane.value = inputPane.value === id ? 0 : id
}

const onSubmit = handleSubmit((values) => {
  addReaction(inputPane.value, values.emote)
  openPane(inputPane.value)
})
</script>

<template>
  <div class="w-full p-8 flex flex-col gap-4" @click="openPane(inputPane)">
    <p class="text-3xl font-bold">Announcements ({{ advisor.announcements.length }})</p>
    <hr class="border-0 border-b border-stone-700" />
    <div class="flex flex-col gap-4">
      <div
        v-for="announcement in advisor.announcements"
        class="bg-stone-800 p-4 shadow-lg flex flex-col gap-2 font-sans"
      >
        <p class="text-xl font-bold">{{ announcement.message }}</p>
        <a
          :href="announcement.file"
          class="rounded-lg p-4 bg-stone-700 max-w-xs hover:brightness-125 flex flex-row items-center gap-4"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M13.19 8.688a4.5 4.5 0 011.242 7.244l-4.5 4.5a4.5 4.5 0 01-6.364-6.364l1.757-1.757m13.35-.622l1.757-1.757a4.5 4.5 0 00-6.364-6.364l-4.5 4.5a4.5 4.5 0 001.242 7.244"
            />
          </svg>
          Attached File</a
        >
        <div class="flex flex-row relative items-center h-8 gap-2">
          <button
            v-for="reaction of announcement.reactions
              .reduce((prev, curr) => {
                if (prev.has(curr.emote)) {
                  prev.set(curr.emote, prev.get(curr.emote)! + 1)
                } else {
                  prev.set(curr.emote, 1)
                }
                return prev
              }, new Map<string, number>())
              .entries()"
            class="p-1 border border-white border-opacity-25 hover:border-opacity-100 rounded-lg flex flex-row gap-1"
            @click="addReaction(announcement.id, reaction[0])"
          >
            <div class="aspect-square w-6 h-6 font-bold">
              <p>{{ reaction[0] }}</p>
            </div>
            <p>{{ reaction[1] }}</p>
          </button>
          <div class="relative flex justify-center items-center h-full" @click.stop="">
            <button
              class="h-6 aspect-square border rounded-full flex justify-center items-center opacity-25 border-dashed hover:opacity-100 hover:border-solid"
              @click.stop="openPane(announcement.id)"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-4 h-4"
              >
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 6v12m6-6H6" />
              </svg>
            </button>
            <form
              class="absolute flex flex-col items-end bg-stone-800 -top-32 border border-white border-opacity-25 p-2 rounded-lg"
              :class="{ hidden: !(inputPane === announcement.id) }"
              @submit.prevent="onSubmit"
            >
              <ValidatedInput label="Emote" v-model="emote" :error="errors.emote" />
              <button class="rounded-lg px-2 py-1 w-full bg-green-600" type="submit">Add</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
