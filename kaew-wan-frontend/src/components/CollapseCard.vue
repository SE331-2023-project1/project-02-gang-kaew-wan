<script setup lang="ts">
import { computed, ref } from 'vue'
import { Collapse } from 'vue-collapsed'

const props = defineProps({
  title: {
    type: String,
    required: false
  },
  active: {
    type: Boolean,
    required: false
  }
})

const isExpanded = ref<boolean>(false)
isExpanded.value = props.active || false

function handleCollapse() {
  isExpanded.value = !isExpanded.value
}

const TOGGLE_ID = 'toggle'
const COLLAPSE_ID = 'collapse'

const toggleAttrs = computed(() => ({
  id: TOGGLE_ID,
  'aria-controls': COLLAPSE_ID,
  'aria-expanded': isExpanded.value
}))

const collapseAttrs = {
  'aria-labelledby': TOGGLE_ID,
  id: COLLAPSE_ID,
  role: 'region'
}
</script>

<template>
  <article class="w-full">
    <div class="Section">
      <button
        v-bind="toggleAttrs"
        @click="handleCollapse"
        @click.prevent
        :class="[
          'Panel',
          { 'Active bg-stone-700 hover:bg-stone-600': isExpanded },
          { 'rounded-b-lg bg-stone-800 hover:bg-stone-700': !isExpanded }
        ]"
        class="flex items-center justify-between w-full p-5 font-medium text-left border-0 rounded-t-lg transition-all"
      >
        <span class="text-xl font-semibold">{{ title }}</span>
        <svg
          :class="{ 'rotate-180': isExpanded }"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-6 h-6 transition-transform"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M19.5 5.25l-7.5 7.5-7.5-7.5m15 6l-7.5 7.5-7.5-7.5"
          />
        </svg>
      </button>
      <Collapse
        v-bind="collapseAttrs"
        :when="isExpanded"
        :class="{ 'opacity-0': !isExpanded }"
        class="rounded-b-lg bg-stone-800 transition-all duration-500"
      >
        <slot />
      </Collapse>
    </div>
  </article>
</template>
