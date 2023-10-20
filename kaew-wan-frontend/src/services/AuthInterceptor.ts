import router from '@/router'
import type { AxiosError } from 'axios'
import apiClient from './AxiosClient'

apiClient.interceptors.request.use(
  (req) => {
    const token = localStorage.getItem('access_token')
    if (token) {
      req.headers['Authorization'] = `Bearer ${token}`
    }
    return req
  },
  (err) => {
    return Promise.reject(err)
  }
)

apiClient.interceptors.response.use(
  (res) => {
    return res
  },
  (err: AxiosError) => {
    if (err.response?.status === 403) {
      return router.push({ name: 'login' })
    }
    if (err.response?.status === 404) {
      return err
    }
    return router.push({ name: 'network-error' })
  }
)
