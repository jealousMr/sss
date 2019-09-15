import Vue from 'vue'
import Router from 'vue-router'
import Index from "../components/index/index"
import Photo from "../components/photo/photo"
import Deep_photo from "../components/photo/Deep_photo"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:'index',
      component:Index
    },
    {
      path: '/photo',
      name: 'photo',
      component: Photo
    },
    {
      path: '/deep_photo',
      name: 'Deep_photo',
      component: Deep_photo
    }
  ]
})
