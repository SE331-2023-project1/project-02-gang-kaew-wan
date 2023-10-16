import type { Student, Advisor, Course } from '@/types'
import axios, { type AxiosResponse } from 'axios'

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_URL,
  withCredentials: false,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export default {
  getStudents(pgSize: number, pgN: number = 1): Promise<AxiosResponse<Student[]>> {
    return apiClient.get<Student[]>(
      `/student?${pgSize > 0 ? `_limit=${pgSize}&_page=${pgN}` : ''}`
    )
  },
  getAdvisors(pgSize: number, pgN: number = 1): Promise<AxiosResponse<Advisor[]>> {
    return apiClient.get<Advisor[]>(
      `/advisor?${pgSize > 0 ? `_limit=${pgSize}&_page=${pgN}` : ''}`
    )
  },
  getCourses(pgSize: number, pgN: number = 1): Promise<AxiosResponse<Course[]>> {
    return apiClient.get<Course[]>(`/course?${pgSize > 0 ? `_limit=${pgSize}&_page=${pgN}` : ''}`)
  },
  getStudent(id: number): Promise<AxiosResponse<Student>> {
    return apiClient.get<Student>(`/student/${id}`)
  },
  getAdvisor(id: number): Promise<AxiosResponse<Advisor>> {
    return apiClient.get<Advisor>(`/advisor/${id}`)
  },
  getAdvisorExpanded(id: number): Promise<AxiosResponse<Advisor>> {
    return apiClient.get<Advisor>(`/advisor/${id}?_embed=course&_embed=student`)
  },
  getCourse(id: number): Promise<AxiosResponse<Course>> {
    return apiClient.get<Course>(`/course/${id}`)
  },
  updateStudent(id: number, student: Student): Promise<AxiosResponse<Student>> {
    return apiClient.put<Student>(`/student/${id}`, student)
  },
  insertStudent(student: Student): Promise<AxiosResponse<Student>> {
    return apiClient.post<Student>(`/student`, student)
  },
  insertAdvisor(advisor: Advisor): Promise<AxiosResponse<Advisor>> {
    return apiClient.post<Advisor>(`/advisor`, advisor)
  }
}
