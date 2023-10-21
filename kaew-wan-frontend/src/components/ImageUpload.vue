<script setup lang="ts">
import { computed, ref } from 'vue'
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
const compressing = ref<boolean>(false)
const uploading = ref<boolean>(false)
const compress_progress = ref<number>(0)

function openFileUpload() {
  document.getElementById('fileUpload')?.click()
}
function onProgress(progress: number) {
  compress_progress.value = progress
}
async function onFileUpload(e: Event) {
  const files = (e.target as HTMLInputElement).files
  console.log(files)
  if (files && files.length > 0) {
    image.value = ''
    compressing.value = true
    const compressed_image: Blob = await imageCompression(files[0], {
      maxSizeMB: 5,
      alwaysKeepResolution: true,
      useWebWorker: true,
      fileType: 'image/jpeg',
      onProgress: onProgress
    })
    compressing.value = false
    console.log(compressed_image)
    uploading.value = true
    apiClient
      .post(
        '/uploadfile',
        {
          file: new File([compressed_image], compressed_image.name)
        },
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      )
      .then((res) => {
        image.value = res.data.url
      })
      .catch((err) => {
        console.error(err)
      })
      .finally(() => {
        uploading.value = false
      })
  }
}
</script>
<template>
  <div class="flex flex-col items-center">
    <div
      class="w-64 h-64 border rounded-lg flex justify-center group hover:border-blue-500 hover:bg-blue-500 hover:bg-opacity-20 transition-colors"
      :class="{
        'text-red-700 placeholder:text-red-400 border-red-600': error,
        'border-stone-500': !error,
        'pointer-events-none': uploading || compressing
      }"
      @click="openFileUpload"
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
        <span v-if="uploading">Uploading</span>
        <span v-else-if="compressing">Compressing {{ compress_progress }}%</span>
        <span v-else>Upload a file</span>
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
