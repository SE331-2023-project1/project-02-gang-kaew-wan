<script setup lang="ts">
import UUID from '@/features/UniqueID'
import ErrorMessage from './ErrorMessage.vue'
withDefaults(
  defineProps<{
    label?: string
    modelValue?: any
    error?: string
    required?: boolean
    type?: string
    autocomplete?: boolean
  }>(),
  {
    label: '',
    modelValue: '',
    error: '',
    required: false,
    type: 'text',
    autocomplete: true
  }
)
const uuid = UUID().getID()
</script>
<template>
  <div class="flex flex-col gap-1">
    <label :for="label" class="text-xs">{{ label }}</label>
    <input
      :type="type"
      :id="uuid"
      class="form-input bg-transparent border-0 border-b p-0 shadow-sm placeholder:text-gray-400 placeholder:text-opacity-25 focus:placeholder:text-opacity-25 focus:ring-0 focus:placeholder:text-blue-500 focus:bg-blue-500 focus:bg-opacity-20 transition-colors"
      :class="{
        'text-red-700 placeholder:text-red-400 border-red-600': error,
        'border-stone-500': !error
      }"
      :value="modelValue"
      :placeholder="label"
      @input="$emit('update:modelValue', ($event.target as HTMLInputElement)?.value)"
      v-bind="$attrs"
      :aria-describedby="error ? `${uuid}-error` : undefined"
      :aria-invalid="error ? true : false"
      :required="required"
      :autocomplete="`${!autocomplete ? 'new-' : ''}${label}`"
    />
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
