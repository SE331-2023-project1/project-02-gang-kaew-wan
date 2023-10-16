<script setup lang="ts">
import { useStudentStore } from '@/stores/student';
import { storeToRefs } from 'pinia';
import { computed, ref } from "vue";
import { useMessageStore } from '@/stores/message';
import RegistryService from '@/services/RegistryService';


const { student } = storeToRefs(useStudentStore())
const comments = computed(() => {
  return student.value?.comments.slice().reverse()
})
const comment = ref<string>("")
const submitHandler = async () => {
  if (comment.value.trim().length <= 0) return
  if (!student.value?.id) return;
  student.value.comments.push(comment.value)
  RegistryService.updateStudent(student.value.id, student.value)
  comment.value = ""
  useMessageStore().flashMessage("Added a comment successfully.")
}

</script>

<template>
  <main v-if="student" class="flex flex-col gap-4">
    <div class="flex flex-col">
      <textarea class="w-full p-2 text-white bg-stone-900 placeholder-stone-500 resize-none" placeholder="Enter Comment"
        v-model="comment"></textarea>
      <button type="submit" class="bg-emerald-400 hover:brightness-75 text-black font-sans"
        @click="submitHandler">Comment</button>
    </div>


    <div class="flex flex-col">
      <div v-for="comment of comments" :key="comment"
        class="px-2 py-4 border-t last-of-type:border-b border-stone-700 flex gap-8">
        <span class="font-bold">Admin</span> {{ comment }}
      </div>
    </div>
  </main>
  <main v-else>
    <img src="/svg/loading.svg" class="w-16">
  </main>
</template>


