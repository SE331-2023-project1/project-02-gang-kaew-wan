import type { Advisor } from '@/types'
import { defineStore } from 'pinia'

export const useAdvisorStore = defineStore('advisor', {
  state: () => ({
    advisor: null as Advisor | null
  }),
  actions: {
    setAdvisor(advisor: Advisor) {
      this.advisor = advisor
    },
    clear() {
      this.advisor = null
    }
  }
})
