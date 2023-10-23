import RegistryService from '@/services/RegistryService'
import type { Advisor } from '@/types'
import { defineStore } from 'pinia'

export const useAdvisorStore = defineStore('advisor', {
  state: () => ({
    advisor: null as Advisor | null
  }),
  actions: {
    updateAdvisor() {
      if (this.advisor) {
        RegistryService.getAdvisor(this.advisor.id).then((res) => {
          this.advisor = res.data
        })
      }
    },
    setAdvisor(advisor: Advisor) {
      this.advisor = advisor
    },
    clear() {
      this.advisor = null
    }
  }
})
