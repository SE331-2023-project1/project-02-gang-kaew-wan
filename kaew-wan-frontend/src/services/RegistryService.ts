import type { Advisor, Announcement, Department, Student } from '@/types'
import { type AxiosResponse } from 'axios'
import apiClient from './AxiosClient'

export default {
  getStudents(
    pgSize: number,
    pgN: number = 1,
    keyword?: string,
    advisorId?: number
  ): Promise<AxiosResponse<Student[]>> {
    let queryParams = '?'
    if (pgSize > 0) {
      queryParams += `_limit=${pgSize}&_page=${pgN}&`
    }
    if (keyword && keyword !== '') {
      queryParams += `keyword=${keyword}&`
    }
    if (advisorId && advisorId >= 0) {
      queryParams += `advisor=${advisorId}&`
    }

    return apiClient.get<Student[]>(`/students${queryParams}`)
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
  },
  getDepartments(): Promise<AxiosResponse<Department[]>> {
    return apiClient.get<Department[]>(`/departments`)
  },
  getAnnouncementsByPerson(id: number): Promise<AxiosResponse<Announcement[]>> {
    return apiClient.get<Announcement[]>(`/announcements/${id}`)
  }
}
