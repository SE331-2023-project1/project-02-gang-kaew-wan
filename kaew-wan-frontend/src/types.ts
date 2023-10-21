export interface Student extends Person {
  studentId: string
}

export interface Advisor extends Person {
  position: string
  announcements: Announcements[]
  students?: Student[]
}

export interface Department {
  id: number
  name: string
}

export interface Announcements extends Reactable {
  message: string
  file: string
}

export interface Reactable {
  id: number
  reactions: Reaction[]
}

export interface Reaction {
  id: number
  emote: string
}

export interface User {
  id: number
  username: string
  email: string
  role: string
  person?: Student | Advisor
}

export interface Person {
  id: number
  fname: string
  lname: string
  image: string
  department: Department
}

export interface Course {
  id?: number
  advisorId: number
  course_name: string
  course_desc: string
}

export interface Comment {
  id: number
  message: string
  edited: boolean
  parent?: Comment
  student: Student
  teacher: Advisor
}
