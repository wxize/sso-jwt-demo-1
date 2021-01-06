

// 动态加载有点问题，暂时先换成静态加载
import dev from './env/dev.config'
import pro from './env/pro.config'


let conf = process.env.NODE_ENV == "development"
    ? dev
    : pro

export default conf;