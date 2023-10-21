import 'nprogress/nprogress.css'
import './assets/main.css'

import nProgress from 'nprogress'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(createPinia())
app.use(router)

nProgress.configure({ trickle: false })
app.mount('#app')
