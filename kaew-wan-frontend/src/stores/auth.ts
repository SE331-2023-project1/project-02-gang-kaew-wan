import apiClient from '@/services/AxiosClient'
import type { User } from '@/types'
import axios from 'axios'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: null as string | null,
    user: null as User | null
  }),
  getters: {
    currentName(): string {
      return this.user?.person ? `${this.user.person.fname} ${this.user.person.lname}` : ''
    },
    currentUsername(): string {
      return this.user ? this.user.username : ''
    },
    currentID(): number {
      return this.user?.id || 0
    },
    currentRole(): string {
      return this.user?.role || ''
    },
    currentImage(): string {
      return this.user?.person?.image || ''
    },
    hasPerson(): boolean {
      return this.user?.person ? true : false || false
    },
    isAdmin(): boolean {
      return this.user?.role === 'ROLE_ADMIN'
    },
    isTeacher(): boolean {
      return this.user?.role === 'ROLE_TEACHER'
    },
    isStudent(): boolean {
      return this.user?.role === 'ROLE_STUDENT'
    }
  },
  actions: {
    login(username: string, password: string) {
      return apiClient
        .post('/api/v1/auth/authenticate', {
          username,
          password
        })
        .then((res) => {
          this.save(res.data.access_token, res.data.user)
          console.log(this.token, this.user)
          return res
        })
    },
    save(token: string, user: User) {
      if (!(token && user)) {
        return
      }
      this.token = token
      this.user = user
      localStorage.setItem('access_token', this.token as string)
      localStorage.setItem('user', JSON.stringify(this.user))
      axios.defaults.headers.common['Authorization'] = `Bearer ${this.token}`
    },
    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem('access_token')
      localStorage.removeItem('user')
    }
  }
})
