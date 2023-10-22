export interface Student extends Person {
  studentId: string
  teacher?: Advisor
}

export interface Advisor extends Person {
  position: string
  announcements: Announcement[]
  students?: Student[]
}

export interface Department {
  id: number
  name: string
}

export interface Announcement extends Reactable {
  message: string
  file: string
  teacher: Advisor
}

export interface OutgoingAnnouncement extends Partial<Reactable> {
  message: string
  file: string
  teacher: Partial<Advisor>
}

export interface Comment extends Reactable {
  message: string
  edited: boolean
  parent?: Comment
  sender: Person
  receiver: Person
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
export interface Reaction {
  id: number
  emote: string
}
