import type { Comment } from '@/types'
import { defineStore } from 'pinia'

export const useCommentStore = defineStore('comment', {
  state: () => ({
    comments: null as Comment[] | null
  }),
  actions: {
    setComments(comments: Comment) {
      this.comments = comments
    },
    clear() {
      this.comments = null
    }
  }
})
