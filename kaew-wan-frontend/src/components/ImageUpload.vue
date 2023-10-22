<script setup lang="ts">
import { computed, ref } from 'vue'
import ErrorMessage from './ErrorMessage.vue'
import UniqueID from '@/features/UniqueID'
import apiClient from '@/services/AxiosClient'
import imageCompression from 'browser-image-compression'
import { useMessageStore } from '@/stores/message'
const emit = defineEmits(['update:modelValue'])
const props = withDefaults(
  defineProps<{
    modelValue: string
    error?: string
    imageUpload?: boolean
  }>(),
  {
    imageUpload: true
  }
)
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
const fileName = computed(() => {
  const list = props.modelValue.split('/')
  return list[list.length - 1]
})
async function onFileUpload(e: Event) {
  const files = (e.target as HTMLInputElement).files
  console.log(files)
  if (files && files.length > 0) {
    if (props.imageUpload) {
      image.value = ''
      compressing.value = true
      try {
        const file = await imageCompression(files[0], {
          maxSizeMB: 10,
          alwaysKeepResolution: true,
          useWebWorker: true,
          onProgress: onProgress
        })
        compressing.value = false
        console.log(file)
        uploading.value = true
        apiClient
          .post(
            '/uploadfile',
            {
              file: new File([file], file.name)
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
      } catch (error) {
        console.error(error)
        compressing.value = false
        uploading.value = false
        useMessageStore().flashMessage('File is not an image.')
      }
    } else {
      uploading.value = true
      apiClient
        .post(
          '/uploadfile',
          {
            file: files[0]
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
}
</script>
<template>
  <div class="flex flex-col items-center">
    <div
      class="w-64 border rounded-lg flex justify-center group hover:border-blue-500 hover:bg-blue-500 hover:bg-opacity-20 transition-colors"
      :class="{
        'text-red-700 placeholder:text-red-400 border-red-600': error,
        'border-stone-500': !error,
        'pointer-events-none': uploading || compressing,
        'h-64': imageUpload,
        'border-transparent': modelValue
      }"
      @click="openFileUpload"
    >
      <input hidden type="file" id="fileUpload" @change="onFileUpload" />
      <img
        v-if="modelValue && imageUpload"
        class="w-full h-full object-cover rounded-lg"
        :src="modelValue"
      />
      <div v-else-if="modelValue" class="w-full rounded-md py-2 px-4 bg-stone-700">
        {{ fileName }}
      </div>
      <div
        v-else
        class="place-self-center flex items-center text-stone-500 group-hover:text-blue-500 group-hover:opacity-100 gap-2"
        :class="{
          'flex-col': imageUpload
        }"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          :class="{
            'w-12 h-12': imageUpload,
            'w-6 h-6': !imageUpload
          }"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
        </svg>
        <span v-if="uploading">Uploading</span>
        <span v-else-if="compressing">Compressing {{ compress_progress }}%</span>
        <span v-else>Upload {{ imageUpload ? 'an image' : 'a file' }}</span>
      </div>
    </div>
    <ErrorMessage
      class="flex text-xs text-red-700 items-center gap-2"
      :class="{ hidden: !error }"
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
