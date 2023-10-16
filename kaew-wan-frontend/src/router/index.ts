import RegistryService from '@/services/RegistryService'
import { useCourseStore } from '@/stores/course'
import { useStudentStore } from '@/stores/student'
import { useAdvisorStore } from '@/stores/advisor'
import AddPersonLayoutVue from '@/views/AddPersonLayout.vue'
import AdvisorDetailView from '@/views/AdvisorDetailView.vue'
import AdvisorInformationView from '@/views/advisor/AdvisorInformationView.vue'
import AdvisorCourseView from '@/views/advisor/AdvisorCourseView.vue'
import AdvisorStudentView from '@/views/advisor/AdvisorStudentView.vue'
import AdvisorListView from '@/views/AdvisorListView.vue'
import CourseDetail from '@/views/CourseDetail.vue'
import CourseListView from '@/views/CourseListView.vue'
import NetworkErrorView from '@/views/NetworkErrorView.vue'
import NotFoundErrorView from '@/views/NotFoundErrorView.vue'
import StudentDetailView from '@/views/StudentDetailView.vue'
import StudentListView from '@/views/StudentListView.vue'
import StudentCommentView from '@/views/student/StudentCommentView.vue'
import StudentCoursesView from '@/views/student/StudentCoursesView.vue'
import StudentInformationView from '@/views/student/StudentInformationView.vue'
import nProgress from 'nprogress'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/advisor',
      name: 'advisor-list',
      component: AdvisorListView,
      props: (route) => ({ page: parseInt(route.query?.page as string) }),
      beforeEnter: (to, _, next) => {
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
      component: AdvisorDetailView,
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
          path: 'courses',
          alias: 'courses',
          name: 'advisor-courses',
          component: AdvisorCourseView
        },
        {
          path: 'advisor',
          alias: 'advisor',
          name: 'advisor-student',
          component: AdvisorStudentView
        }
      ]
    },
    {
      path: '/',
      name: 'student-list',
      component: StudentListView,
      props: (route) => ({ page: parseInt(route.query?.page as string) }),
      beforeEnter: (to, _, next) => {
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
      beforeEnter: async (to) => {
        const id: number = parseInt(to.params.id as string)
        const studentStore = useStudentStore()
        studentStore.clear()
        studentStore.setStudent(
          await RegistryService.getStudent(id).then((res) => {
            return res.data
          })
        )
      },
      children: [
        {
          path: '',
          alias: 'information',
          name: 'student-information',
          component: StudentInformationView
        },
        {
          path: 'courses',
          alias: 'courses',
          name: 'student-courses',
          component: StudentCoursesView
        },
        {
          path: 'comments',
          name: 'student-comments',
          component: StudentCommentView
        }
      ]
    },
    {
      path: '/course',
      name: 'course-list',
      component: CourseListView,
      props: (route) => ({ page: parseInt(route.query?.page as string) }),
      beforeEnter: (to, _, next) => {
        if (
          !to.query?.page ||
          parseInt(to.query?.page as string) < 1 ||
          isNaN(parseInt(to.query?.page as string))
        ) {
          next({ name: 'course-list', query: { page: 1 } })
        } else {
          next()
        }
      }
    },
    {
      path: '/course/:id(\\d+)',
      name: 'course-detail',
      component: CourseDetail,
      props: true,
      beforeEnter: async (to) => {
        const id: number = parseInt(to.params.id as string)
        const courseStore = useCourseStore()
        courseStore.clear()
        courseStore.setCourse(
          await RegistryService.getCourse(id).then((res) => {
            return res.data
          })
        )
      }
    },
    {
      name: 'add-person',
      path: '/add',
      component: AddPersonLayoutVue
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
