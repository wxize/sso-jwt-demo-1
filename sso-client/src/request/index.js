import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import qs from 'qs'
import conf from '@/config'

Vue.use(VueAxios, axios)

if (conf.server) 
    axios.defaults.baseURL = conf.server;

axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';


// 添加请求拦截器
axios.interceptors.request.use(function(config) {
    // 在发送请求之前做些什么
    let token = localStorage.getItem('token')
    if (token) {
        config.headers.token = token
    }
    
    if (config.method == "post") {
        config.data = qs.stringify({...config.data})
    }
    return config;
}, function(error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

axios.interceptors.response.use(
    response => {
        if (response.status === 200) {
            const res = response.data
            if (res.error !== 0) {
                console.log(res.message)
            }
            return res.data
        } else {
            return Promise.reject(response)
        }
    },
    error => console.log(error)
)

export default axios