;(function () {
    let token = localStorage.getItem("token")
    if (token) {
    } else {
        var serverUrl = "http://www.sso.com:3003"
        window.addEventListener("message", (e) => {
            if (e.data.token) {
                console.log("有令牌")
                localStorage.setItem('token', e.data.token)
            } else {
                console.log("没有令牌")
                window.location.href =
                    serverUrl + "/login?redirectUrl=" + window.location.href
            }

            // localStorage.removeItem('token');
            // if (e.data.token) {
            //     console.log(e)
            //     localStorage.setItem('token', e.data.token)
            // } else {
            //     // 跳转登录页面
            //     window.location.href = server + '?redirectUrl=' + window.location.href;
            // }
        })

        var iframe = document.createElement("iframe")
        iframe.src = serverUrl
        iframe.width = 0
        iframe.height = 0
        iframe.style.display = "none"
        document.body.append(iframe)
    }
})()
