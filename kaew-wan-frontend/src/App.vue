<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { ref } from 'vue'
import { useMessageStore } from './stores/message';
import { storeToRefs } from 'pinia';
const menuOpened = ref<boolean>(true)
const { message } = storeToRefs(useMessageStore())

function toggleMenuOpen() {
  menuOpened.value = !menuOpened.value
  console.log(menuOpened.value)
}
</script>

<template>
  <div class="flex flex-col sm:flex-row sm:min-h-screen">
    <div class="flex flex-col sm:flex-row sticky top-0 z-10 max-h-screen">
      <nav
        class="z-50 flex flex-col font-sans gap-4 text-white items-center bg-stone-800 p-4 overflow-hidden transition-all duration-300 shadow-xl"
        :class="{ 'sm:max-w-0 sm:px-0 sm:max-h-fit sm:py-4 max-h-0 py-0': !menuOpened, 'sm:max-w-screen-sm max-h-[720px] sm:max-h-full': menuOpened }">
        <div class="flex flex-col w-full items-center">
          <RouterLink :to="{ name: 'student-list', query: { page: 1 } }" class="min-w-fit text-2xl font-bold">STUDENT<span
              class="bg-clip-text bg-gradient-to-r from-lime-400 via-emerald-400 to-sky-400 text-transparent">CONNECT</span>
          </RouterLink>
          <p class="font-serif text-xs text-transparent bg-clip-text bg-gradient-to-b from-amber-400 to-red-500 font-bold">#1 in Registration Office Industry</p>
        </div>
        <div class="flex flex-col w-full items-end">
          <RouterLink :to="{ name: 'student-list', query: { page: 1 } }"
            class="min-w-fit flex flex-row gap-2 items-center text-lg font-semibold [&.router-link-active]:bg-gradient-to-r from-emerald-400 to-sky-500 hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md">
            <svg class="w-6 h-6" fill="currentColor" height="64px" width="64px" version="1.1" id="Capa_1"
              xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 408.191 408.191"
              xml:space="preserve" stroke="currentColor" stroke-width="2" transform="rotate(0)">
              <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
              <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round" stroke="#CCCCCC"
                stroke-width="3.2655279999999998"></g>
              <g id="SVGRepo_iconCarrier">
                <g>
                  <g>
                    <path
                      d="M363.663,294.916c0-18.823-13.48-34.545-31.289-38.05v-25.655c0-2.333-1.086-4.534-2.938-5.953 c-1.853-1.42-4.26-1.895-6.514-1.289l-8.355,2.25c-22.41-37.422-57.92-48.108-75.395-51.131v-19.483 c5.289-4.545,10.184-10.16,14.557-16.779c1.775-2.688,3.422-5.512,4.965-8.434c0.021,0.008,0.039,0.017,0.061,0.023 c1.959,0.635,3.953,0.936,5.932,0.936c9.098,0,18.565-6.189,21.023-16.258c5.732-23.485-13.5-28.398-13.83-28.458 c0.689-5.758,1.059-11.639,1.059-17.602C272.938,7.156,233.647,0,204.093,0c-29.551,0-68.838,7.156-68.838,69.035 c0,5.963,0.37,11.844,1.059,17.602c-0.329,0.06-19.763,5.184-13.831,28.461c2.559,10.043,11.927,16.255,21.025,16.255 c1.977,0,3.972-0.302,5.931-0.936c0.021-0.007,0.04-0.016,0.061-0.023c1.543,2.922,3.189,5.746,4.965,8.434 c4.373,6.619,9.267,12.234,14.555,16.779v19.483c-17.476,3.023-52.983,13.713-75.394,51.131l-8.354-2.25 c-2.254-0.604-4.661-0.13-6.513,1.289c-1.853,1.419-2.938,3.62-2.938,5.953v25.654c-17.812,3.504-31.291,19.227-31.291,38.051 c0,18.822,13.479,34.549,31.291,38.053v32.199c0,3.326,2.191,6.256,5.383,7.195l120.7,35.523c0.695,0.204,1.408,0.305,2.117,0.305 c0.025,0,0.052,0,0.077,0c0.718,0.002,1.472-0.093,2.192-0.305l120.701-35.508c3.191-0.94,5.383-3.868,5.383-7.195v-32.215 C350.184,329.465,363.663,313.738,363.663,294.916z M269.264,101.639c2.336,1.52,3.334,5.254,2.176,8.834 c-1.103,3.402-3.871,5.766-6.535,5.869C266.626,111.619,268.085,106.707,269.264,101.639z M136.752,110.474 c-1.158-3.579-0.16-7.313,2.177-8.834c1.18,5.067,2.638,9.979,4.358,14.702C140.625,116.236,137.855,113.875,136.752,110.474z M204.093,15c31.995,0,53.399,9.029,53.825,52.721c-26.49,5.475-50.976,0.613-72.852-14.498 c-12.249-8.462-20.517-18.125-24.52-23.389C169.911,18.174,185.141,15,204.093,15z M166.98,130.559 c-10.785-16.326-16.725-38.175-16.725-61.523c0-9.746,1.02-17.805,2.942-24.455c5.171,5.98,12.778,13.604,22.817,20.618 c13.961,9.756,34.738,19.657,61.55,19.657c6.219,0,12.766-0.541,19.625-1.732c-1.896,17.955-7.396,34.447-15.977,47.436 c-10.152,15.367-23.336,23.83-37.12,23.83C190.312,154.389,177.131,145.926,166.98,130.559z M177.259,189.085 c3.837-0.381,6.76-3.608,6.76-7.464v-16.475c6.406,2.791,13.149,4.242,20.074,4.242c6.927,0,13.671-1.451,20.079-4.243v16.476 c0,3.855,2.924,7.083,6.76,7.464c3.428,0.34,9.418,1.203,16.707,3.348c-3.697,11.395-22.129,20.362-43.542,20.362 c-21.414,0-39.847-8.968-43.544-20.362C167.842,190.288,173.831,189.425,177.259,189.085z M59.529,294.916 c0-10.499,6.836-19.432,16.291-22.58v45.156C66.365,314.345,59.529,305.414,59.529,294.916z M196.52,390.666l-105.7-31.108 v-118.56l105.7,28.468V390.666z M204.096,255.973L108.778,230.3c10.99-16.63,25.02-26.6,37.757-32.561 c6.21,17.444,29.267,30.056,57.563,30.056s51.345-12.613,57.552-30.06c12.738,5.96,26.773,15.931,37.764,32.565L204.096,255.973z M317.374,359.572l-105.701,31.095V269.466l105.701-28.468V359.572z M332.374,317.492v-45.156 c9.453,3.149,16.289,12.081,16.289,22.58C348.663,305.413,341.827,314.344,332.374,317.492z">
                    </path>
                  </g>
                </g>
              </g>
            </svg>
            <span>
              Student
            </span>
          </RouterLink>
          <RouterLink :to="{ name: 'advisor-list', query: { page: 1 } }"
            class="min-w-fit flex flex-row gap-2 items-center text-lg font-semibold [&.router-link-active]:bg-gradient-to-r from-emerald-400 to-sky-500  hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md">
            <svg class="w-6 h-6" fill="currentColor" height="64px" width="64px" version="1.1" id="Capa_1"
              xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 469.603 469.603"
              xml:space="preserve" stroke="currentColor" stroke-width="6">
              <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
              <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
              <g id="SVGRepo_iconCarrier">
                <path
                  d="M136.724,137.131c5.413,21.661,17.365,38.875,34.745,49.199c10.719,6.368,22.736,9.552,34.754,9.552 c12.018,0,24.036-3.184,34.755-9.552c17.38-10.326,29.331-27.54,34.744-49.202c27.657-10.408,42.039-31.192,38.694-56.487 c-4.773-36.097-46.413-75.94-101.806-80.51c-1.995-0.165-10.543-0.177-12.439-0.023c-32.012,2.605-62.586,17.55-82.687,40.644 C103.037,57.352,96.054,75.88,97.82,92.926C99.047,104.755,105.836,125.619,136.724,137.131z M230.762,169.136 c-15.136,8.992-33.942,8.992-49.078,0c-19.007-11.291-29.273-34.717-28.168-64.27c0.151-4.033,0.685-7.723,1.513-11.1 c13.888,5.771,30.147,9.286,47.11,10.12c1.273,0.063,6.574,0.074,7.69,0.023c15.71-0.726,30.827-3.751,44.035-8.737 c1.199-0.453,2.383-0.921,3.55-1.406c0.828,3.377,1.362,7.066,1.513,11.099C260.034,134.418,249.768,157.844,230.762,169.136z M132.57,53.883c17.647-20.275,45.159-33.009,73.651-34.105c47.389,1.819,84.582,34.863,88.367,63.486 c1.959,14.813-5.699,24.267-15.66,30.287c0.104-3.089,0.106-6.233-0.014-9.434c-0.381-10.156-2.667-19.583-6.682-28.007 c-0.03-0.071-0.068-0.14-0.099-0.21c-5.444-11.315-14.025-20.806-25.363-27.782c-4.705-2.896-10.863-1.427-13.757,3.276 c-2.894,4.704-1.427,10.863,3.277,13.757c4.041,2.486,8.427,5.937,12.257,10.638c-12.329,4.94-26.972,7.826-42.324,8.239 c-15.352-0.414-29.994-3.299-42.322-8.24c3.829-4.7,8.215-8.15,12.256-10.635c4.704-2.895,6.171-9.054,3.277-13.758 c-2.894-4.704-9.054-6.171-13.757-3.277c-11.383,7.002-19.986,16.541-25.426,27.915c-0.008,0.019-0.018,0.037-0.026,0.055 c-4.022,8.43-6.312,17.864-6.693,28.03c-0.119,3.192-0.118,6.328-0.014,9.41c-8.027-4.873-14.704-12.072-15.802-22.664 C116.542,79.556,121.957,66.077,132.57,53.883z M212.217,409.937c0,5.522-4.477,10-10,10H103.93c-2.971,0-5.788-1.134-7.688-3.418 s-2.686-5.108-2.145-8.029l23.568-127.233c5.553-29.985,28.519-52.9,61.434-61.294c17.63-4.496,37.032-4.463,54.632,0.097 c19.055,4.937,34.94,14.691,45.937,28.22c3.483,4.286,2.833,10.572-1.452,14.056c-4.285,3.482-10.583,2.811-14.067-1.476 c-16.645-20.479-49.585-29.388-80.107-21.607c-25.529,6.511-42.555,23.021-46.71,45.462l-21.378,115.224h86.265 C207.74,399.937,212.217,404.414,212.217,409.937z M365.415,289.937H254.356c-5.523,0-9.683,4.145-9.683,9.667v134.01 c0,5.522,4.16,10.323,9.683,10.323h7.317v15.666c0,5.522,4.477,10,10,10s10-4.478,10-10v-15.666h83.742 c5.523,0,10.258-4.801,10.258-10.323v-134.01C375.672,294.081,370.938,289.937,365.415,289.937z M355.672,423.937h-91v-114h91 V423.937z M280.688,345.811c0-5.522,4.985-10.125,9.985-10.125v0.25h38.395c5.523,0,9.803,4.353,9.803,9.875 s-4.197,9.875-10.197,9.875v0.25h-37.97C285.18,355.937,280.688,351.334,280.688,345.811z M327.612,374.937c0,5.522-4.477,10-10,10 h-15.455c-5.523,0-10-4.478-10-10s4.477-10,10-10h15.455C323.135,364.937,327.612,369.414,327.612,374.937z">
                </path>
              </g>
            </svg>
            <span>Advisor</span>
          </RouterLink>
          <RouterLink :to="{ name: 'course-list', query: { page: 1 } }"
            class="min-w-fit flex flex-row gap-2 items-center text-lg font-semibold [&.router-link-active]:bg-gradient-to-r from-emerald-400 to-sky-500  hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
              stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round"
                d="M12 6.042A8.967 8.967 0 006 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 016 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 016-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0018 18a8.967 8.967 0 00-6 2.292m0-14.25v14.25" />
            </svg>
            <span>Course</span>
          </RouterLink>
          <RouterLink :to="{ name: 'add-person', query: { page: 1 } }"
            class="min-w-fit flex flex-row gap-2 items-center text-lg font-semibold [&.router-link-active]:bg-gradient-to-r from-emerald-400 to-sky-500  hover:brightness-75 w-full hover:text-emerald-400 [&.router-link-active]:text-white px-2 py-1 rounded-md">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
              stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round"
                d="M19 7.5v3m0 0v3m0-3h3m-3 0h-3m-2.25-4.125a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zM4 19.235v-.11a6.375 6.375 0 0112.75 0v.109A12.318 12.318 0 0110.374 21c-2.331 0-4.512-.645-6.374-1.766z" />
            </svg>
            <span>Add Person</span>
          </RouterLink>
        </div>
      </nav>
      <div class="absolute w-full top-full sm:top-4 sm:w-fit sm:left-full flex justify-center z-20">
        <button
          class="p-1 sm:mb-0 w-16 h-fit sm:h-16 sm:w-fit flex justify-center items-center shadow-xl overflow-hidden text-white bg-stone-800 rounded-bl-2xl rounded-br-2xl sm:rounded-bl-none sm:rounded-tr-2xl transition-all group"
          @click="toggleMenuOpen">
          <span class="hidden sm:block overflow-hidden max-w-0 transition-all duration-500"
            :class="{ 'group-hover:max-w-[16rem]': !menuOpened }">Menu</span>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
            class="w-6 h-6 transition-all hidden sm:block group-hover:text-emerald-400"
            :class="{ 'rotate-180': !menuOpened }">
            <path stroke-linecap="round" stroke-linejoin="round" d="M18.75 19.5l-7.5-7.5 7.5-7.5m-6 15L5.25 12l7.5-7.5" />
          </svg>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
            class="w-6 h-6 transition-all block sm:hidden group-hover:text-emerald-400  "
            :class="{ 'rotate-180': !menuOpened }">
            <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12.75l7.5-7.5 7.5 7.5m-15 6l7.5-7.5 7.5 7.5" />
          </svg>
        </button>
      </div>
    </div>
    <div class="flex-1 font-serif text-white flex flex-row justify-center items-start relative p-4 sm:p-8">
      <div class="absolute top-0 w-full text-center transition-all bg-red-500 opacity-0 z-30 shadow-lg"
        :class="{ '-translate-y-full': !message, 'animate-flashMessage': message }">&nbsp;{{ message }}</div>
      <RouterView />
    </div>
  </div>
</template>
