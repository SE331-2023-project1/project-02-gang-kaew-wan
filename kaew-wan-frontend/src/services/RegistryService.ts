import type { Advisor, Course, Student } from '@/types'
import { type AxiosResponse } from 'axios'
import apiClient from './AxiosClient'

export default {
  getStudents(pgSize: number, pgN: number = 1): Promise<AxiosResponse<Student[]>> {
    return apiClient.get<Student[]>(
      `/students?${pgSize > 0 ? `_limit=${pgSize}&_page=${pgN}` : ''}`
    )
  },
  getAdvisors(pgSize: number, pgN: number = 1): Promise<AxiosResponse<Advisor[]>> {
    return apiClient.get<Advisor[]>(
      `/teachers?${pgSize > 0 ? `_limit=${pgSize}&_page=${pgN}` : ''}`
    )
  },
  getStudent(id: number): Promise<AxiosResponse<Student>> {
    return apiClient.get<Student>(`/students/${id}`)
  },
  getAdvisor(id: number): Promise<AxiosResponse<Advisor>> {
    return apiClient.get<Advisor>(`/teachers/${id}`)
  },
  getAdvisorExpanded(id: number): Promise<AxiosResponse<Advisor>> {
    return apiClient.get<Advisor>(`/teachers/${id}?_embed=course&_embed=student`)
  },
  updateStudent(id: number, student: Student): Promise<AxiosResponse<Student>> {
    return apiClient.put<Student>(`/students/${id}`, student)
  },
  insertStudent(student: Student): Promise<AxiosResponse<Student>> {
    return apiClient.post<Student>(`/students`, student)
  },
  insertAdvisor(teacher: Advisor): Promise<AxiosResponse<Advisor>> {
    return apiClient.post<Advisor>(`/teachers`, teacher)
  }
}
