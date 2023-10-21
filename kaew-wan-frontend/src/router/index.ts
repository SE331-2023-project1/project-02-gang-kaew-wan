import RegistryService from '@/services/RegistryService'
import { useAdvisorStore } from '@/stores/advisor'
import { useStudentStore } from '@/stores/student'
import AdvisorListView from '@/views/AdvisorListView.vue'
import LoginViewVue from '@/views/LoginView.vue'
import NetworkErrorView from '@/views/NetworkErrorView.vue'
import NotFoundErrorView from '@/views/NotFoundErrorView.vue'
import StudentDetailView from '@/views/student/StudentDetailLayoutView.vue'
import StudentListView from '@/views/StudentListView.vue'

import nProgress from 'nprogress'
import { createRouter, createWebHistory } from 'vue-router'
import RegisterViewVue from '@/views/RegisterView.vue'
import DashboardLayout from '@/views/dashboard/DashboardLayoutView.vue'
import { useAuthStore } from '@/stores/auth'
import ProfileView from '@/views/profile/ProfileView.vue'
import { usePersonStore } from '@/stores/person'
import StudentCommentView from '@/views/student/StudentCommentView.vue'
import StudentInformationView from '@/views/student/StudentInformationView.vue'
import type { Comment, Student } from '@/types'
import AdvisorDetailLayoutView from '@/views/advisor/AdvisorDetailLayoutView.vue'
import AdvisorInformationView from '@/views/advisor/AdvisorInformationView.vue'
import AdvisorAssignStudentView from '@/views/advisor/AdvisorAssignStudentView.vue'
import ForbiddenView from '@/views/ForbiddenView.vue'
import RegisterTeacherView from '@/views/RegisterTeacherView.vue'
import MyCommentView from '@/views/MyCommentView.vue'
import { useCommentStore } from '@/stores/comment'
import ProfileLayoutView from '@/views/profile/ProfileLayoutView.vue'
import EditProfileView from '@/views/profile/EditProfileView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/advisors',
      name: 'advisor-list',
      component: AdvisorListView,
      props: (route) => ({ page: parseInt(route.query?.page as string) }),
      beforeEnter: (to, _, next) => {
        const authStore = useAuthStore()
        if (!authStore.isAdmin) {
          next({ name: 'forbidden-page' })
        }
        if (
          !to.query?.page ||
          parseInt(to.query?.page as string) < 1 ||
          isNaN(parseInt(to.query?.page as string))
        ) {
          next({ name: 'advisor-list', query: { page: 1 } })
        } else {
          next()
        }
      }
    },
    {
      path: '/advisor/:id(\\d+)',
      name: 'advisor-detail',
      component: AdvisorDetailLayoutView,
      props: true,
      beforeEnter: async (to) => {
        const id: number = parseInt(to.params.id as string)
        const advisorStore = useAdvisorStore()
        advisorStore.clear()
        advisorStore.setAdvisor(
          await RegistryService.getAdvisorExpanded(id).then((res) => {
            return res.data
          })
        )
      },
      children: [
        {
          path: '',
          alias: 'information',
          name: 'advisor-information',
          component: AdvisorInformationView
        },
        {
          path: 'assign',
          alias: 'assign-student',
          name: 'assign-student',
          component: AdvisorAssignStudentView
        }
      ]
    },
    {
      path: '/students',
      name: 'student-list',
      component: StudentListView,
      props: (route) => ({ page: parseInt(route.query?.page as string) }),
      beforeEnter: (to, _, next) => {
        const authStore = useAuthStore()
        if (authStore.isStudent) {
          next({ name: 'forbidden-page' })
        }
        if (
          !to.query?.page ||
          parseInt(to.query?.page as string) < 1 ||
          isNaN(parseInt(to.query?.page as string))
        ) {
          next({ name: 'student-list', query: { page: 1 } })
        } else {
          next()
        }
      }
    },
    {
      path: '/student/:id(\\d+)',
      name: 'student-detail',
      component: StudentDetailView,
      props: true,
      beforeEnter: (to) => {
        const id: number = parseInt(to.params.id as string)
        const studentStore = useStudentStore()
        studentStore.clear()
        return RegistryService.getStudent(id).then((res) => {
          studentStore.setStudent(res.data as Student)
        })
      },
      children: [
        {
          path: '',
          alias: 'information',
          name: 'student-information',
          component: StudentInformationView
        },
        {
          path: 'comment',
          alias: 'comment',
          component: StudentCommentView
        }
      ]
    },
    {
      name: 'login',
      path: '/login',
      component: LoginViewVue
    },
    {
      name: 'register',
      path: '/register',
      component: RegisterViewVue
    },
    {
      name: 'add-advisor',
      path: '/add-advisor',
      component: RegisterTeacherView,
      beforeEnter: (to, _, next) => {
        const authStore = useAuthStore()
        if (!authStore.isAdmin) {
          next({ name: 'forbidden-page' })
        }
        next()
      }
    },
    {
      name: 'dashboard',
      path: '/',
      component: DashboardLayout,
      beforeEnter: (to, from, next) => {
        const authStore = useAuthStore()
        if (authStore.user !== null) {
          next()
        } else {
          next({ name: 'login' })
        }
      }
    },
    {
      name: 'profile-layout',
      path: '/profile/:id',
      component: ProfileLayoutView,
      props: true,
      beforeEnter: (to) => {
        const id = parseInt(to.params.id as string)
        const store = usePersonStore()
        const authStore = useAuthStore()
        if (authStore.currentRole === 'ROLE_STUDENT') {
          return RegistryService.getStudent(id)
            .then((res) => {
              store.setPerson({
                id: res.data.id,
                fname: res.data.fname,
                lname: res.data.lname,
                image: res.data.image,
                department: res.data.department
              })
            })
            .catch((err) => {
              console.log(err)
              if (err.response && err.response.status === 404) {
                router.push({ name: '404-resource', params: { resource: 'event' } })
              } else {
                router.push({ name: 'network-error' })
              }
            })
        } else {
          return RegistryService.getAdvisor(id)
            .then((res) => {
              store.setPerson({
                id: res.data.id,
                fname: res.data.fname,
                lname: res.data.lname,
                image: res.data.image,
                department: res.data.department
              })
            })
            .catch((err) => {
              console.log(err)
              if (err.response && err.response.status === 404) {
                router.push({ name: '404-resource', params: { resource: 'event' } })
              } else {
                router.push({ name: 'network-error' })
              }
            })
        }
      },
      children: [
        {
          path: '',
          alias: 'ProfileDetail',
          name: 'profile-detail',
          component: ProfileView,
          props: true
        },
        {
          path: 'edit',
          alias: 'Edit Profile',
          name: 'profile-edit',
          component: EditProfileView,
          props: true
        }
      ]
    },
    {
      name: 'my-comment',
      path: '/my-comment',
      component: MyCommentView,
      props: true,
      beforeEnter: () => {
        const authStore = useAuthStore()
        const commentStore = useCommentStore()
        const id = authStore.currentID

        return RegistryService.getComments(id)
          .then((res) => {
            commentStore.setComments(res.data as Comment[])
          })
          .catch((err) => {
            console.log(err)
            if (err.response && err.response.status === 404) {
              router.push({ name: '404-resource', params: { resource: 'comment' } })
            } else {
              router.push({ name: 'network-error' })
            }
          })
      }
    },
    {
      name: 'network-error',
      path: '/network-error',
      component: NetworkErrorView
    },
    {
      path: '/404',
      name: '404-resource',
      component: NotFoundErrorView
    },
    {
      path: '/403',
      name: 'forbidden-page',
      component: ForbiddenView
    },
    {
      path: '/:catchAll(.*)',
      name: 'not-found',
      component: NotFoundErrorView
    }
  ],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) return savedPosition
    else return { top: 0 }
  }
})

router.beforeEach(() => {
  nProgress.start()
})

router.afterEach(() => {
  nProgress.done()
})

export default router
