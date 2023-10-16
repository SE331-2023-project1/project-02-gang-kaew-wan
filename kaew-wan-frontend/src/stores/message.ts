import { defineStore } from 'pinia'

export const useMessageStore = defineStore('message', {
  state: () => ({
    message: '' as string,
    duration_ms: 3000 as number
  }),
  actions: {
    flashMessage(msg: string) {
      this.clear()
      this.message = msg
      setTimeout(() => this.clear(), this.duration_ms)
    },
    clear() {
      this.message = ''
    }
  }
})
