import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations.js'
Vue.use(Vuex)
export default new Vuex.Store({
    state: {
        displayMainNav: true,
        forget: {
            phoneNum: '15011021101'
        },
        user: {
            avatar: '',
            nickname: localStorage.getItem("nickName")
        }
    },
    mutations
})