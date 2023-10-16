<script setup lang="ts">

import AddAdvisorForm from "@/components/AddAdvisorForm.vue";
import AddStudentForm from "@/components/AddStudentForm.vue";
import { ref } from "vue";

const isStudentView = ref<Boolean>(true)

</script>

<template>
  <main class="bg-stone-100 flex flex-col w-full mx-8 lg:w-2/3 lg:m-0 items-center">
    <header class="p-3 flex flex-row items-center gap-3">
      <span class="text-sm font-medium text-black ">Add Advisor</span>
      <label class="relative inline-flex items-center cursor-pointer">
        <input type="checkbox" v-model="isStudentView" class="sr-only peer">
        <div
            class="w-11 h-6 bg-stone-700 peer-focus:outline-none peer-focus:ring-2 peer-focus:ring-stone-500 rounded-sm peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-sm after:h-5 after:w-5 after:transition-all peer-checked:bg-emerald-400"></div>
      </label>
      <span class=" text-sm font-medium text-black ">Add Student</span>
    </header>
    <AddStudentForm v-if="isStudentView" />
    <AddAdvisorForm v-else />
  </main>


</template>
