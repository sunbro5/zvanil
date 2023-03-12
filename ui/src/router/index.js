/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Registration from '@/components/Registration'
import Chat from '@/components/Chat'
import Login from '@/components/Login'
import HexagonMap from '@/components/HexagonMap'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/registration',
      name: 'Registration',
      component: Registration
    },
    {
      path: '/chat',
      name: 'Chat',
      component: Chat
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/map',
      name: 'HexagonMap',
      component: HexagonMap
    }
  ]
})
