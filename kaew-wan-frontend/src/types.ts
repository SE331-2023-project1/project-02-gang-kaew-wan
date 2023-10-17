export interface Student {
  id?: number
  fname: string
  lname: string
  image: string
}

export interface Advisor {
  id?: number
  fname: string
  lname: string
  image: string
  prefix: string
  faculty: string
  student?: Student[]
  course?: Course[]
}

export interface Course {
  id?: number
  advisorId: number
  course_name: string
  course_desc: string
}
