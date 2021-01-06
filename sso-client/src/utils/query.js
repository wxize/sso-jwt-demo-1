export default class Query {
    constructor(url) {
        let search = url.split('?')[1];
        let queryArr = search.split('=');
        for(let i = 0; i < queryArr.length; i += 2) {
            this[queryArr[i]] = queryArr[i+1];
        }
    }

    getItem(key) {
        return this[key];
    }


}