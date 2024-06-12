import Vue from 'vue'
import App from './App.vue'
import './assets/bootstrap/css/bootstrap.min.css'
import ElementUI from 'element-ui';
import router from './router'
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import moment from 'moment';
import * as echarts from "echarts";
import JsonExcel from 'vue-json-excel'


Vue.component('downloadExcel',JsonExcel)
Vue.prototype.$echarts = echarts;
Vue.prototype.$moment = moment
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$axios=axios
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
