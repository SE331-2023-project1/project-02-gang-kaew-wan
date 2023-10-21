<script setup lang="ts">
import RegistryService from '@/services/RegistryService'
import { useMessageStore } from '@/stores/message'
import { type Course, type Advisor, type Student } from '@/types'
import { computed, ref } from 'vue'
const advisors = ref<Advisor[]>([])
RegistryService.getAdvisors(-1).then((res) => (advisors.value = res.data))
const courses = ref<Course[]>([])
RegistryService.getCourses(-1).then((res) => (courses.value = res.data))
const searchWord = ref<string>('')
const student = ref<Student>({
  fname: '',
  lname: '',
  image: '',
  courseId: [],
  advisorId: -1,
  comments: [],
  nationality: '',
  birthday: 0,
  address: ''
})
const courseAfterSearch = computed(() => {
  return courses.value.filter((x) =>
    x.course_name.toLowerCase().startsWith(searchWord.value.toLowerCase())
  )
})
const birthdayWrapper = computed({
  get() {
    const date = new Date(student.value.birthday)
    return date.toISOString().split('T')[0]
  },
  set(newValue: string) {
    student.value.birthday = Date.parse(newValue)
  }
})
const coursesDisplay = computed(() => {
  return student.value.courseId
    .map((v) => courses.value.find((x) => x.id === v))
    .filter((x) => x !== undefined) as Course[]
})
function addCourseToStudent(id: number) {
  if (!student.value.courseId.includes(id)) student.value.courseId.push(id)
  ;(document.activeElement as HTMLElement | null)?.blur()
}
function removeCourseFromStudent(id: number) {
  student.value.courseId = student.value.courseId.filter((x) => x !== id)
}
function submitForm() {
  RegistryService.insertStudent(student.value).then((res) => {
    student.value = {
      fname: '',
      lname: '',
      image: '',
      courseId: [],
      advisorId: -1,
      comments: [],
      nationality: '',
      birthday: 0,
      address: ''
    }
    console.log(res.data)
    useMessageStore().flashMessage('Added a student successfully.')
  })
}
</script>

<template>
  <main class="w-full flex flex-col h-auto pb-4 items-center">
    <header class="text-black text-lg lg:text-2xl">Student Form</header>

    <form class="grid grid-cols-2 gap-4 max-w-xl" @submit.prevent="submitForm">
      <div class="col-span-2 lg:col-span-1">
        <label class="text-xs text-stone-700">First Name</label>
        <input
          type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder=""
          v-model="student.first_name"
          required
        />
      </div>

      <div class="col-span-2 lg:col-span-1">
        <label class="text-xs text-stone-700">Last Name</label>
        <input
          type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder=""
          v-model="student.last_name"
          required
        />
      </div>
      <div class="col-span-2">
        <label class="text-xs text-stone-700">Address</label>
        <input
          type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder=""
          v-model="student.address"
          required
        />
      </div>

      <div class="col-span-2 lg:col-span-1">
        <label class="text-xs text-stone-700">Nationality</label>
        <input
          type="text"
          class="md:text-xl w-full h-8 text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder=""
          v-model="student.nationality"
          required
        />
      </div>
      <div class="col-span-2 lg:col-span-1">
        <label class="text-xs text-stone-700">Birthday</label>
        <input
          type="date"
          class="md:text-xl w-full h-8 text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          placeholder=""
          v-model="birthdayWrapper"
          required
        />
      </div>

      <div class="col-span-2">
        <label class="text-xs text-stone-700">Profile Image URL</label>
        <input
          type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors"
          v-model="student.image"
          placeholder=""
          required
        />
        <div class="flex flex-col items-center mt-4">
          <div class="w-[128px] h-[128px] border border-dashed border-stone-400">
            <img
              class="h-full w-full object-cover"
              :class="{ invisible: !student.image }"
              :src="student.image"
            />
          </div>
          <p class="mx-auto text-black opacity-40 text-xs">Preview</p>
        </div>
      </div>

      <div class="col-span-2">
        <label class="text-xs text-stone-700">Advisor</label>
        <select id="advisor" class="w-full text-black bg-transparent" v-model="student.advisorId">
          <option v-for="advisor in advisors" :value="advisor.id" :key="advisor.id">
            {{
              `${advisor.first_name}
                      ${advisor.last_name}`
            }}
          </option>
        </select>
      </div>

      <div class="col-span-2 relative">
        <label class="text-xs text-stone-700">Enrolled Course</label>
        <div
          class="flex flex-row flex-wrap gap-1 [&>*:nth-child(4n+1)]:bg-emerald-500 [&>*:nth-child(4n+2)]:bg-teal-500 [&>*:nth-child(4n+3)]:bg-sky-500 [&>*:nth-child(4n+4)]:bg-blue-500"
        >
          <div
            v-for="course in coursesDisplay"
            class="rounded-r-full rounded-l-full px-2 py-1 text-xs flex flex-row items-center gap-1"
            :key="course.id"
          >
            <button type="button" @click="removeCourseFromStudent(course.id!)">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-4 h-4"
              >
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
            <p>{{ course.course_name }}</p>
          </div>
        </div>
        <input
          type="text"
          class="md:text-xl w-full text-black bg-transparent border-b border-stone-400 focus:outline-none focus:border-stone-700 transition-colors peer"
          v-model="searchWord"
          placeholder=""
        />
        <div
          class="text-black hidden peer-focus:flex overflow-hidden transition-all absolute bg-white border border-stone-700 w-full flex-col shadow-lg"
        >
          <button
            type="button"
            class="hover:bg-emerald-400 hover:text-white px-1"
            v-for="course in courseAfterSearch"
            :key="course.id"
            @mousedown.prevent=""
            @click="addCourseToStudent(course.id!)"
          >
            {{ course.course_name }}
          </button>
        </div>
      </div>
      <button class="bg-emerald-400 col-span-2 mx-[40%] px-2 py-1">Add</button>
    </form>
  </main>
</template>
