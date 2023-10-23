<script setup lang="ts">
import { useStudentStore } from '@/stores/student'
import { storeToRefs } from 'pinia'

const studentStore = useStudentStore()
const student = storeToRefs(studentStore).student
</script>

<template>
  <!--<h1>This is profile view</h1>-->
  <div v-if="student" class="w-full">
    <div class="flow-root">
      <dl class="text-sm divide-y divide-stone-900 flex flex-col">
        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">First name</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            {{ student.fname }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">Last name</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            {{ student.lname }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">Department</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            {{ student.department.name }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 bg-stone-800">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">Bio</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Et facilis debitis explicabo
            doloremque impedit nesciunt dolorem facere, dolor quasi veritatis quia fugit aperiam
            aspernatur neque molestiae labore aliquam soluta architecto?
          </dd>
        </div>
        <div class="grid grid-cols-1 gap-1 sm:grid-cols-5 sm:gap-4 mt-4 bg-stone-800" v-if="student.teacher">
          <dt class="text-white font-semibold p-2 bg-stone-700 h-full">Advisor</dt>
          <dd class="text-gray-100 sm:col-span-4 py-2">
            ({{ student.teacher.position }}) {{ student.teacher.fname }} {{ student.teacher.lname }}
          </dd>
        </div>
      </dl>
    </div>
  </div>
</template>

<style scoped></style>
