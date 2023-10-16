import { defineStore } from 'pinia'
import type { Advisor, Course } from '@/types'
import RegistryService from '@/services/RegistryService'

export const useCourseStore = defineStore('course', {
  state: () => ({
    course: null as Course | null,
    lecturer: null as Advisor | null
  }),
  actions: {
    async setCourse(course: Course) {
      this.course = course
      this.lecturer = await RegistryService.getAdvisor(course.advisorId).then((res) => res.data)
    },
    clear() {
      this.course = null
      this.lecturer = null
    }
  }
})
