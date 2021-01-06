
// // 定义全局变量
// window.bricks = {};
// // 检测query，保存 rediectUrl
// import Query from '@/utils/query'
// import jwt from 'jwt-decode'

// let query = new Query(window.location.url);
// 这里等待以后优化.  目前先做成 进行登录页面就行
// let token = localStorage.getItem('token')
// if (query.getItem('redirectUrl') && token) {
//     // 查看 token 是否过期
//     // 解析 token ...
//     console.log(jwt(token));
// } 



import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import './store'
import '@/config'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/styles/reset.css'
import '@/assets/iconfont/iconfont.css'
import '@/request/index'

Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
    router,
    // store,
    render: h => h(App),
}).$mount('#app')