window.bricks = {}
window.bricks.token = localStorage.getItem('token')
window.parent.postMessage({
    token: localStorage.getItem('token')
}, "*")

// // 检测参数 有没有redirectUrl
// var arr = window.location.search.substr(1).split('=')
// var temp = {}
// for(var i = 0; i < arr.length; i+=2) {
//     temp[arr[i]] = arr[i+1]
// }
// if (temp.redirectUrl) {
//     // 表示有回调地址，记录一下回调地址
//     localStorage.setItem('redirectUrl', tem.redirectUrl)
    
//     // 有两种情况
//     // 1. 当有回调地址，但是没有 token 的情况，这种情况下，重新定向到 登录页面
//     window.location.href = window.location.origin + '/login'
    
// } else {
//     // 没有回调地址，应该默认跳转到一个地址上去。。
//     //这里先不做处理
// }