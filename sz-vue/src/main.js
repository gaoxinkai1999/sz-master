import Vue from 'vue'
import App from './App.vue'
import axios from "axios";
import router from "@/router/index";
import store from "@/store/store";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'mint-ui/lib/style.css'

Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(ElementUI)

new Vue({
  render: h => h(App),
  router: router,
  store
}).$mount('#app')
