<script setup lang="ts">
import StudentCard from '@/components/StudentCard.vue'
import ValidatedInputVue from '@/components/ValidatedInput.vue'
import apiClient from '@/services/AxiosClient'
import RegistryService from '@/services/RegistryService'
import { useMessageStore } from '@/stores/message'
import type { Student, Advisor } from '@/types'
import { ref, watchEffect } from 'vue'
import { useRouter } from 'vue-router'
const props = defineProps<{
  advisor: Advisor
}>()
const students = ref<Student[]>()
const students_display = ref<Student[]>([])
RegistryService.getStudents(-1).then((res) => {
  students.value = res.data
  students.value = students.value.filter(
    (x) => x.teacher === null || x.teacher?.id === props.advisor.id
  )
})
const other_kw = ref<string>('')
const other_pgn = ref<number>(1)
const router = useRouter()

watchEffect(() => {
  if (students.value) {
    students_display.value = students.value.filter(
      (ss) =>
        !props.advisor.students?.map((x) => x.id).includes(ss.id) &&
        (ss.fname.toLowerCase().includes(other_kw.value.toLowerCase()) ||
          ss.lname.toLowerCase().includes(other_kw.value.toLowerCase()))
    )
    console.log(students_display.value.length)
    if ((other_pgn.value - 1) * 6 >= students_display.value.length || other_pgn.value < 1) {
      other_pgn.value = Math.ceil(students_display.value.length / 6)
    }
  }
})

function addStudent(id: number) {
  // eslint-disable-next-line vue/no-mutating-props
  props.advisor.students?.push(students.value?.find((x) => x.id === id)!)
}
function removeStudent(id: number) {
  // eslint-disable-next-line vue/no-mutating-props
  props.advisor.students = props.advisor.students?.filter((x) => x.id !== id)
}
function updateAssoc() {
  const out = props.advisor.students!.map((x) => {
    return { id: x.id }
  })
  apiClient
    .put(`/teachers/${props.advisor.id}/assign`, {
      students: out
    })
    .then(() => {
      router.push({})
      useMessageStore().flashMessage('Successfully Updated!')
    })
    .catch(() => {
      useMessageStore().flashMessage('Failed to update.')
    })
}
</script>

<template>
  <div
    class="w-full p-8 flex flex-col gap-4 transition-opacity relative"
    :class="{ 'pointer-events-none': !students }"
  >
    <div class="flex flex-row justify-between">
      <p class="text-3xl font-bold">My Students ({{ advisor.students?.length }})</p>
      <button class="bg-green-600 px-2 hover:brightness-75" @click="updateAssoc()">Confirm</button>
    </div>
    <div class="grid grid-cols-2 gap-4">
      <div
        v-for="student of props.advisor.students"
        :key="student.id"
        class="cursor-pointer"
        @click.stop="removeStudent(student.id)"
      >
        <StudentCard :student="student" class="pointer-events-none" />
      </div>
    </div>
    <p class="text-3xl font-bold">Other Students ({{ students_display.length }})</p>
    <ValidatedInputVue v-model="other_kw" label="Search" />
    <div class="grid grid-cols-2 gap-4">
      <div
        v-for="student of students_display.slice((other_pgn - 1) * 6, other_pgn * 6)"
        :key="student.id"
        class="cursor-pointer"
        @click.stop="addStudent(student.id)"
      >
        <StudentCard :student="student" class="pointer-events-none" />
      </div>
      <div class="flex flex-row justify-between col-span-2 font-sans">
        <button
          class="px-2 py-1 bg-amber-700 hover:brightness-75"
          @click="other_pgn = other_pgn - 1"
          :class="{ invisible: other_pgn <= 1 }"
        >
          Previous
        </button>
        <button
          class="px-2 py-1 bg-amber-700 hover:brightness-75"
          @click="other_pgn = other_pgn + 1"
          :class="{ invisible: students_display.length <= other_pgn * 6 }"
        >
          Next
        </button>
      </div>
    </div>
    <div class="absolute top-full w-full flex justify-center items-center" v-if="!students">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="36"
        height="36"
        fill="#fff"
        viewBox="0 0 24 24"
        class="w-16 h-16 animate-spin"
      >
        <path
          d="M10.14,1.16a11,11,0,0,0-9,8.92A1.59,1.59,0,0,0,2.46,12,1.52,1.52,0,0,0,4.11,10.7a8,8,0,0,1,6.66-6.61A1.42,1.42,0,0,0,12,2.69h0A1.57,1.57,0,0,0,10.14,1.16Z"
          class="spinner_P7sC"
        />
      </svg>
    </div>
  </div>
</template>
