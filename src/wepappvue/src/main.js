import Vue from 'vue'
import VueRouter from 'vue-router';
import App from './App.vue'
import Login from './components/Login'
import Main from './components/Main'
import vuetify from './plugins/vuetify'
import 'vuetify/dist/vuetify.min.css';
import Vuetify from "vuetify";
Vue.config.productionTip = false

Vue.use(VueRouter);
Vue.use(Vuetify, {
  theme: {
    primary: '#7957d5',
  },
});

const router = new VueRouter({
  routes: [
    {
      path: '/login',
      component: Login,
    },
    {
      path: '/main',
      component: Main,
    }
  ],
});

Vue.config.productionTip = false;

new Vue({
  router: router,
  el: '#app',
  vuetify,
  render: h => h(App)
}).$mount('#app')

router.replace('/login')