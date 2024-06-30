import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import LeaderBoardView from '@/views/LeaderBoardView.vue'
import AddLapTimeView from '@/views/AddLapTimeView.vue'
import RegisterView from '@/views/RegisterView.vue'


import { useAuthStore } from '@/stores/auth'



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/tracks/:id/leaderboard',
      name: 'track-leaderboard',
      component: LeaderBoardView
    },
    {
      path: '/laptime',
      name: 'add-laptime',
      component: AddLapTimeView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
  ]
})

router.beforeEach(async (to) => {
  const publicPages = ['/login', '/register', '/', '/tracks/:id/leaderboard']
  const authRequired = !publicPages.includes(to.path)
  const auth = useAuthStore()

  if (authRequired && !auth.user) {
    return '/login'
  }
})

export default router
