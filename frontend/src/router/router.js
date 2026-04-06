import { createRouter, createWebHistory } from 'vue-router'
import PaginaInicialView from '@/views/PaginaInicialView.vue'
import GerenciarNotaView from '@/views/GerenciarNotaView.vue'
import AboutView from '@/views/AboutView.vue'
import LoginView from '@/views/LoginView.vue'

const routes = [
  { path: '/login', name: 'login', component: LoginView },
  { path: '/', name: 'home', component: PaginaInicialView },
  { path: '/gerenciar/:id?', name: 'gerenciar', component: GerenciarNotaView, props: true },
  { path: '/about', name: 'about', component: AboutView }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('token');
  if (to.name !== 'login' && !isAuthenticated) {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router
