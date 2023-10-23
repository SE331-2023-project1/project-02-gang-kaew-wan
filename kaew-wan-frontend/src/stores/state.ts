import { defineStore } from 'pinia'

export const useStateStore = defineStore('state', {
  state: () => ({
    emotePaneId: 0 as number
  }),
  actions: {
    setEmotePane(n: number) {
      this.emotePaneId = n
    }
  }
})
