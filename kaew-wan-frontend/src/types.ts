export interface Student {
  id?: number
  advisorId: number
  address: string
  nationality: string
  birthday: number
  first_name: string
  last_name: string
  image: string
  courseId: Array<number>
  comments: Array<string>
}

export interface Advisor {
  id?: number
  first_name: string
  last_name: string
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
