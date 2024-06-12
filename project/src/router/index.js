import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [

  
  {
    path: '/',
    name: 'Hello',
    component: () => import('../views/HelloView.vue')
  },
  {
    path: '/themain',
    name: 'Main',
    component: () => import('../views/TheMain.vue')
  },
  {
    path: '/info',
    name: 'Infomation',
    component: () => import('../views/PersonInfo.vue')
  },
  {
    path: '/student',
    name: 'Student',
    component: () => import('../views/StuInfomation.vue')
  },
  {
    path: '/score',
    name: 'Thescore',
    component: () => import('../views/TheScore.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/UserLogin.vue')
  },
  {
    path: '/regist',
    name: 'Regist',
    component: () => import('../views/UserRegist.vue')
  }
  
]

const router = new VueRouter({
  routes
})

export default router
