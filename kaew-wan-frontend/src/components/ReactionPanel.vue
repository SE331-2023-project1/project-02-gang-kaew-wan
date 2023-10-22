<script setup lang="ts">
import ValidatedInput from '@/components/ValidatedInput.vue'
import type { PropType } from 'vue'
import type { Reactable } from '@/types'
import apiClient from '@/services/AxiosClient'
import { useMessageStore } from '@/stores/message'
import * as yup from 'yup'
import { useField, useForm } from 'vee-validate'
import { ref } from 'vue'

const props = defineProps({
  reactable: {
    type: Object as PropType<Reactable>,
    required: true
  }
})

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
  console.log(id, emote)
  // eslint-disable-next-line vue/no-mutating-props
  props.reactable?.reactions.push( {
    id: props.reactable?.id || 0,
    emote: emote
  })

  // console.log(
  //   props.reactable?.reactions.push({
  //     id: props.reactable?.id || 0,
  //     emote: emote
  //   })
  // )

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
  console.log("submit emote")
  openPane(inputPane.value)
  addReaction(props.reactable?.id || 0, values.emote)
})
</script>

<template>
  <div class="flex flex-row relative items-center h-8 gap-2">
    <button
      v-for="reaction of reactable.reactions
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
      @click="addReaction(reactable.id, reaction[0])"
    >
      <div class="aspect-square w-6 h-6 font-bold">
        <p>{{ reaction[0] }}</p>
      </div>
      <p>{{ reaction[1] }}</p>
    </button>
    <div class="relative flex justify-center items-center h-full" @click.stop="">
      <button
        class="h-6 aspect-square border rounded-full flex justify-center items-center opacity-25 border-dashed hover:opacity-100 hover:border-solid"
        @click.stop="openPane(reactable.id)"
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
        :class="{ hidden: !(inputPane === reactable.id) }"
        @submit.prevent="onSubmit"
      >
        <ValidatedInput label="Emote" v-model="emote" :error="errors.emote" />
        <button class="rounded-lg px-2 py-1 w-full bg-green-600" type="submit">Add</button>
      </form>
    </div>
  </div>
</template>

<style scoped></style>
