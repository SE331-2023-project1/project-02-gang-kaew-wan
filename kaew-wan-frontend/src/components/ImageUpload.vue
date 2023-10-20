<script setup lang="ts">
import { computed } from 'vue'
import ErrorMessage from './ErrorMessage.vue'
import UniqueID from '@/features/UniqueID'
import apiClient from '@/services/AxiosClient'
import imageCompression from 'browser-image-compression'
const emit = defineEmits(['update:modelValue'])
const props = defineProps<{
  modelValue: string
  error?: string
}>()
const uuid = UniqueID().getID()
const image = computed({
  get() {
    return props.modelValue
  },
  set(value: string) {
    emit('update:modelValue', value)
  }
})

function openFileUpload() {
  document.getElementById('fileUpload')?.click()
}
async function onFileUpload(e: Event) {
  const files = (e.target as HTMLInputElement).files
  if (files && files.length > 0) {
    const compressed_image: Blob = await imageCompression(files[0], {
      maxSizeMB: 1,
      maxWidthOrHeight: 256,
      useWebWorker: true,
      fileType: 'image/jpeg'
    })
    console.log(compressed_image)
    const form = new FormData()
    form.append('file', new File([compressed_image], compressed_image.name))
    apiClient
      .post('/uploadfile', form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((res) => {
        image.value = res.data.url
      })
      .catch((err) => {
        console.error(err)
      })
  }
}
</script>
<template>
  <div class="flex flex-col items-center" @click="openFileUpload">
    <div
      class="w-64 h-64 border rounded-lg flex justify-center group hover:border-blue-500 hover:bg-blue-500 hover:bg-opacity-20 transition-colors"
      :class="{
        'text-red-700 placeholder:text-red-400 border-red-600': error,
        'border-stone-500': !error
      }"
    >
      <input hidden type="file" id="fileUpload" @change="onFileUpload" />
      <img v-if="modelValue" class="w-full h-full object-cover rounded-lg" :src="modelValue" />
      <div
        v-else
        class="place-self-center flex flex-col items-center text-stone-500 group-hover:text-blue-500 group-hover:opacity-100"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="3"
          stroke="currentColor"
          class="w-12 h-12"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
        </svg>
        Upload a file
      </div>
    </div>
    <ErrorMessage
      class="flex text-xs text-red-700 items-center gap-2"
      :class="{ invisible: !error }"
      :id="`${uuid}-error`"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="2"
        stroke="currentColor"
        class="w-4 h-4"
      >
        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
      </svg>
      <p>
        {{ error }}
      </p>
    </ErrorMessage>
  </div>
</template>
