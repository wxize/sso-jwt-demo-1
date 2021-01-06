class WindowConnector {
    static emitToken() {
        window.parent.postMessage({
            token: localStorage.getItem('token')
        }, "*")
    }
}

export default WindowConnector