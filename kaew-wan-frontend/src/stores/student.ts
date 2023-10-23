import { defineStore } from 'pinia'
import type { Student } from '@/types'

export const useStudentStore = defineStore('student', {
  state: () => ({
    student: null as Student | null
  }),
  actions: {
    setStudent(stu: Student) {
      this.student = stu
    },
    clear() {
      this.student = null
    }
  }
})
