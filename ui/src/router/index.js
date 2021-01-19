import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Registration from '@/components/Registration'

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
    }
  ]
})
