<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { useAuthStore } from './stores/auth'
import { useMessageStore } from './stores/message'
import HyperLink from '@/components/HyperLink.vue'

const menuOpened = ref<boolean>(true)
const store = useMessageStore()
const { message } = storeToRefs(store)
const authStore = useAuthStore()
const router = useRouter()

function logout() {
  authStore.logout()
  router.push({ name: 'login' })
}

const token = localStorage.getItem('access_token')
const cookie_user = localStorage.getItem('user')
if (token && cookie_user) {
  authStore.save(token, JSON.parse(cookie_user))
} else {
  logout()
}

function toggleMenuOpen() {
  menuOpened.value = !menuOpened.value
  console.log(menuOpened.value)
}

</script>

<template>
  <div class="flex flex-col sm:flex-row sm:min-h-screen">
    <div class="flex flex-col sm:flex-row sticky top-0 z-10 max-h-screen">
      <nav
        class="z-50 flex flex-col font-sans gap-4 text-white items-center bg-stone-800 p-4 overflow-hidden transition-all duration-300"
        :class="{
          'sm:max-w-0 sm:px-0 sm:max-h-fit sm:py-4 max-h-0 py-0': !menuOpened,
          'sm:max-w-screen-sm max-h-[720px] sm:max-h-full': menuOpened
        }"
      >
        <div class="flex flex-col w-full items-center">
          <RouterLink
            :to="{ name: 'dashboard' }"
            class="min-w-fit text-2xl font-bold"
            >STUDENT<span
              class="bg-clip-text bg-gradient-to-r from-lime-400 via-emerald-400 to-sky-400 text-transparent"
              >CONNECT</span
            >
          </RouterLink>
          <p
            class="whitespace-nowrap font-serif text-xs text-transparent bg-clip-text bg-gradient-to-b from-amber-400 to-red-500 font-bold"
          >
            #1 in Registration Office Industry
          </p>
        </div>
        <!-- TODO: change link to dashboard -->
        <div class="flex flex-col w-full items-end">
          <div v-if="authStore.currentID" class="flex flex-col w-full items-end">
            <RouterLink
              :to="{ name: 'profile-detail', params: { id: authStore.currentID } }"
              class="min-w-fit flex flex-row gap-2 items-center text-lg hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md"
            >
              <div v-if="authStore.hasPerson" class="flex flex-row gap-2 items-center">
                <img
                  class="w-12 h-12 object-cover rounded-full border-2 border-black border-opacity-40"
                  :src="authStore.currentImage.toString()"
                />
                <div>
                  <p class="whitespace-nowrap">{{ authStore.currentName }}</p>
                  <p class="whitespace-nowrap text-xs opacity-50">
                    {{ authStore.currentRole.split('_')[1] }} @{{ authStore.currentUsername }}
                  </p>
                </div>
              </div>
              <div v-else>
                <p class="whitespace-nowrap">{{ authStore.currentUsername }}</p>
                <p class="whitespace-nowrap text-xs opacity-50">
                  {{ authStore.currentRole.split('_')[1] }}
                </p>
              </div>
            </RouterLink>
            <button class="flex gap-1 items-center hover:brightness-75" @click="logout()">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-4 h-4"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15M12 9l-3 3m0 0l3 3m-3-3h12.75"
                />
              </svg>
              Log out
            </button>
          </div>
          <div v-else class="flex flex-col w-full items-end">
            <RouterLink
              :to="{ name: 'login' }"
              class="min-w-fit flex flex-row gap-2 items-center text-lg font-semibold [&.router-link-active]:bg-gradient-to-r from-emerald-400 to-sky-500 hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-6 h-6"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15m3 0l3-3m0 0l-3-3m3 3H9"
                />
              </svg>
              <span class="whitespace-nowrap"> Login </span>
            </RouterLink>
            <RouterLink
              :to="{ name: 'register' }"
              class="min-w-fit flex flex-row gap-2 items-center text-lg font-semibold [&.router-link-active]:bg-gradient-to-r from-emerald-400 to-sky-500 hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="w-6 h-6"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M19 7.5v3m0 0v3m0-3h3m-3 0h-3m-2.25-4.125a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zM4 19.235v-.11a6.375 6.375 0 0112.75 0v.109A12.318 12.318 0 0110.374 21c-2.331 0-4.512-.645-6.374-1.766z"
                />
              </svg>

              <span class="whitespace-nowrap"> Register </span>
            </RouterLink>
          </div>
          <hr class="border-0 border-b border-stone-50 w-full opacity-25 my-2" />

          <HyperLink :title="'Dashboard'" :name="'dashboard'" />
          <HyperLink v-if="authStore.isTeacher" :title="'My Student'" :name="'student-list'" />
          <HyperLink v-if="authStore.isAdmin" :title="'Add Teacher'" :name="'add-advisor'" />
          <HyperLink v-if="authStore.isAdmin" :title="'Teacher List'" :name="'advisor-list'" />
          <HyperLink v-if="authStore.isAdmin" :title="'Student List'" :name="'student-list'" />
        </div>
      </nav>
      <div class="absolute w-full top-full sm:top-4 sm:w-fit sm:left-full flex justify-center z-20">
        <button
          class="p-1 sm:mb-0 w-16 h-fit sm:h-16 sm:w-fit flex justify-center items-center shadow-xl overflow-hidden text-white bg-stone-800 rounded-bl-2xl rounded-br-2xl sm:rounded-bl-none sm:rounded-tr-2xl transition-all group"
          @click="toggleMenuOpen"
        >
          <span
            class="hidden sm:block overflow-hidden max-w-0 transition-all duration-500"
            :class="{ 'group-hover:max-w-[16rem]': !menuOpened }"
            >Menu</span
          >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6 transition-all hidden sm:block group-hover:text-emerald-400"
            :class="{ 'rotate-180': !menuOpened }"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M18.75 19.5l-7.5-7.5 7.5-7.5m-6 15L5.25 12l7.5-7.5"
            />
          </svg>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-6 h-6 transition-all block sm:hidden group-hover:text-emerald-400"
            :class="{ 'rotate-180': !menuOpened }"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M4.5 12.75l7.5-7.5 7.5 7.5m-15 6l7.5-7.5 7.5 7.5"
            />
          </svg>
        </button>
      </div>
    </div>
    <div
      class="flex-1 font-serif text-white flex flex-row justify-center items-start relative p-4 sm:p-8"
    >
      <div
        class="absolute top-0 w-full text-center transition-all bg-red-500 opacity-0 z-30 shadow-lg"
        :class="{ '-translate-y-full': !message, 'animate-flashMessage': message }"
      >
        &nbsp;{{ message }}
      </div>
      <RouterView />
    </div>
  </div>
</template>
