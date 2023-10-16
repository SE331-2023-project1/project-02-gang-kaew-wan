<template>
    <main class="flex flex-col gap-4" v-if="courses.length">
        <StudentCourseCard v-for="course_tuple of course_tuples" :key="course_tuple[0].id" :course="course_tuple[0]"
            :advisor="course_tuple[1]" />
    </main>
    <main class="flex flex-row justify-center" v-else>
        <img src="/svg/loading.svg" class="w-16">
    </main>
</template>

<script setup lang="ts">
import StudentCourseCard from '@/components/StudentCourseCard.vue'
import { useStudentStore } from '@/stores/student';
import type { Advisor, Course } from '@/types';
import { storeToRefs } from 'pinia';
import { computed, type Ref } from 'vue'
const { courses, course_advisors } = storeToRefs(useStudentStore())
let course_tuples: Ref<Array<[Course, Advisor]>> = computed(() => {
    let tuples: Array<[Course, Advisor]> = Array(courses.value.length)
    courses.value.forEach((v, i) => {
        tuples[i] = [courses.value[i], course_advisors.value[i]]
    })
    return tuples
})
</script>
