<script setup lang="ts">

import {boolean, string} from "yup";
import {computed, ref} from "vue";
import {Collapse} from "vue-collapsed";

const props = defineProps({
  title: string,
  active: {
    type: boolean
  }
})

const isExpanded = ref<boolean>(false)
isExpanded.value = <boolean>props.active || false

function handleCollapse() {
  isExpanded.value = !isExpanded.value
}

const TOGGLE_ID = 'toggle'
const COLLAPSE_ID = 'collapse'

const toggleAttrs = computed(() => ({
  id: TOGGLE_ID,
  'aria-controls': COLLAPSE_ID,
  'aria-expanded': isExpanded.value,
}))

const collapseAttrs = {
  'aria-labelledby': TOGGLE_ID,
  id: COLLAPSE_ID,
  role: 'region',
}
</script>

<template>
  <article class="w-full">
    <div class="Section">
      <button
          v-bind="toggleAttrs"
          @click="handleCollapse"
          @click.prevent
          :class="['Panel', { Active: isExpanded }]"
          class="flex items-center bg-stone-800 justify-between w-full p-5 font-medium text-left border border-b-0 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 border-gray-700 text-gray-400 hover:bg-gray-800"
      >
        <span class="text-xl font-semibold">{{ title }}</span>
        <svg v-if="!isExpanded" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" class="w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 5.25l-7.5 7.5-7.5-7.5m15 6l-7.5 7.5-7.5-7.5"/>
        </svg>

        <svg v-else xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" class="w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12.75l7.5-7.5 7.5 7.5m-15 6l7.5-7.5 7.5 7.5"/>
        </svg>


      </button>
      <Collapse v-bind="collapseAttrs" :when="isExpanded" class="p-5 border border-b-0 border-gray-700 bg-stone-800 ">
        <slot class="CollapseContent">
        </slot>
      </Collapse>
    </div>

  </article>
</template>

<style scoped>

</style>