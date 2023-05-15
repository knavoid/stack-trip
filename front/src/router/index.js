import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import(/* webpackChunkName: "about" */ '@/views/SignupView.vue')
  },
  {
    path: '/trip',
    name: 'trip',
    component: () => import(/* webpackChunkName: "about" */ '@/views/TriplistView.vue')
  },
  {
    path: '/board',
    name: 'board',
    component: () => import(/* webpackChunkName: "about" */ '@/views/BoardView.vue'),

  },
  {
    path: '/board/posting',
    name: 'posting',
    component: () => import('@/components/BoardPost.vue')
  },
  {
    path: '/bookmark',
    name: 'bookmark',
    component: () => import(/* webpackChunkName: "about" */ '../views/BookmarkView.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
