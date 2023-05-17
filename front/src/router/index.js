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
    path: '/board/detail/',
    name: 'detail',
    component: () => import('@/components/BoardDetail.vue')
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
  },
  {
    path: '/qna',
    name: 'qna',
    component: () => import(/* webpackChunkName: "about" */ '../views/QnABoard.vue')
  },
  {
    path: '/qna/qnaposting',
    name: 'qnaposting',
    component: () => import('@/components/QnAPost.vue')
  },
  {
    path: '/qna/detail/:articleno',
    name: 'qnadetail',
    component: () => import('@/components/QnADetail.vue')
  },
  {
    path: '/qna/modify/:articleno',
    name: 'qnamodify',
    component: () => import('@/components/QnAModify.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
