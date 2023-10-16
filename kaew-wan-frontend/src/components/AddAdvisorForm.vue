<script setup lang="ts">
import RegistryService from "@/services/RegistryService";
import { useMessageStore } from "@/stores/message";
import { type Advisor } from "@/types";
import { ref } from "vue";
const advisor = ref<Advisor>({
  first_name: '',
  last_name: '',
  image: '',
  prefix: '',
  faculty: '',
})
function submitForm() {
  RegistryService.insertAdvisor(advisor.value).then((res) => {
    advisor.value = {
      first_name: '',
      last_name: '',
      image: '',
      prefix: '',
      faculty: '',
    }
    useMessageStore().flashMessage('Added an advisor successfully.')
  })
}
</script>

<template>
  <main class="w-full flex flex-col h-auto pb-4 items-center">

    <header class="text-black text-lg lg:text-2xl ">Student Form</header>

    <form class="grid grid-cols-7 gap-4 max-w-xl" @submit.prevent="submitForm">
      <div class="col-span-7 lg:col-span-1">
        <label class="text-xs text-stone-700">Prefix</label>
        <input type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder="" v-model="advisor.prefix" />
      </div>
      <div class="col-span-7 lg:col-span-3">
        <label class="text-xs text-stone-700">First Name</label>
        <input type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder="" v-model="advisor.first_name" required />
      </div>

      <div class="col-span-7 lg:col-span-3">
        <label class="text-xs text-stone-700">Last Name</label>
        <input type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder="" v-model="advisor.last_name" required />
      </div>
      <div class="col-span-7">
        <label class="text-xs text-stone-700">Faculty</label>
        <input type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder="" v-model="advisor.faculty" required />
      </div>

      <div class="col-span-7">
        <label class="text-xs text-stone-700">Profile Image URL</label>
        <input type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          v-model="advisor.image" placeholder="" required />
        <div class="flex flex-col items-center mt-4">
          <div class="w-[128px] h-[128px] border border-dashed border-stone-400">
            <img class="h-full w-full object-cover" :class="{ 'invisible': !advisor.image }" :src="advisor.image" />
          </div>
          <p class="mx-auto text-black opacity-40 text-xs">Preview</p>
        </div>
      </div>

      <button class="bg-emerald-400 col-span-7 mx-[40%] px-2 py-1">Add</button>
    </form>

  </main>
</template>
