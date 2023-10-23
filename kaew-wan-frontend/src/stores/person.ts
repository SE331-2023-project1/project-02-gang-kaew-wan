import type { Person } from '@/types'
import { defineStore } from 'pinia'

export const usePersonStore = defineStore('person', {
  state: () => ({
    person: null as Person | null
  }),
  actions: {
    setPerson(person: Person) {
      this.person = person
    },
    clear() {
      this.person = null
    }
  }
})
