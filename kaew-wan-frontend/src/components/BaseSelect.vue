<template>
  <div class="flex flex-col">
    <label v-if="label" class="text-xs">{{ label }}</label>
    <select
      class="form-select bg-transparent focus:ring-0 border-0 border-b focus:bg-blue-500 focus:bg-opacity-20 transition-colors"
      :class="{
        'text-red-700 placeholder:text-red-400 border-red-600': error,
        'border-stone-500': !error
      }"
      :value="modelValue"
      v-bind="{
        ...$attrs,
        onChange: ($event) => {
          $emit('update:modelValue', ($event.target as HTMLSelectElement)?.value)
        }
      }"
    >
      <option class="bg-stone-500 text-white" :value="null" disabled selected>{{ label }}</option>
      <option
        class="bg-stone-700 text-white"
        v-for="option in options"
        :value="valueExtractor(option)"
        :key="keyExtractor(option)"
        :selected="valueExtractor(option) === modelValue"
      >
        {{ textExtractor(option) }}
      </option>
    </select>
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

<script setup lang="ts" generic="T">
import UUID from '@/features/UniqueID'
import ErrorMessage from './ErrorMessage.vue'
defineProps<{
  label: string
  modelValue: T[keyof T]
  options: T[]
  keyExtractor: (x: T) => string | number | symbol | undefined
  valueExtractor: (x: T) => T[keyof T]
  textExtractor: (x: T) => T[keyof T]
  error?: string
}>()
const uuid = UUID().getID()
</script>
