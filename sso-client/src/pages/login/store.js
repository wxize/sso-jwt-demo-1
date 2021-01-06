
let store = {}
let queryArr = window.location.search.substr(1).split('=')
for(let i = 0; i < queryArr.length; i += 2) {
    if (queryArr[i] == "redirectUrl") {
        store.redirectUrl = queryArr[i+1]
        break;
    }
}

export default store;