import Vue from "vue"
import Router from "vue-router"
Vue.use(Router)

const router = new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "Home",
            meta: {
                title: "首页",
            },
            // component: () => import("@/pages/home/Home"),
        },
        {
            path: "/login",
            name: "Login",
            meta: {
                title: "登录",
            },
            component: () => import("@/pages/login/Login"),
        },
        {
            path: "/register",
            name: "Register",
            meta: {
                title: "注册",
            },
            component: () => import("@/pages/register/Register"),
        },
        {
            path: "/userhome",
            name: "UserHome",
            meta: {
                title: "个人中心",
            },
            component: () => import("@/pages/userHome/UserHome"),
        },
    ],
})

// 白名单
let whiteList = ["/login", "/register"]

// 跳转前，走这里
router.beforeEach((to, from, next) => {
    let token = localStorage.getItem("token")
    if (whiteList.indexOf(to.path) !== -1) {
        if (token) {
            if (to.path == "/login" || to.path == "/register") {
                next({ path: "/" })
            } else {
                next()
            }
        } else {
            next()
        }
    } else {
        if (!token) {
            next({ path: "/login" })
        } else {
            next()
        }
    }

    if (to.meta.title) {
        document.title = to.meta.title
    }

    // 解析rediect url
})

export default router
