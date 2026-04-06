import './assets/main.css'
import Header from './components/Header.vue' 
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from './router/router'

import App from './App.vue'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component('Header', Header)

app.mount('#app')
