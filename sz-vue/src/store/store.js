// 初始化一个vuex的实例（数据仓库） 导出即可
import Vuex from 'vuex'
import Vue from 'vue'
// 使用安装
Vue.use(Vuex)
// 初始化
const store = new Vuex.Store({
// 配置（state|mutations|actions）
    state: {
        a:
            {teams: [], 卡密: ''},
        count: 'xxxxxxxx',
    },
    mutations: {
        add(state, team) {
            state.a.teams.push(team)
        },
        修改卡密(state, 卡密) {
            state.a.卡密 = 卡密
        }

    },
    actions: {}
})
export default store
