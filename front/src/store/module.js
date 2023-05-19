import http from '@/util/http-common.js';
import router from '@/router/index.js';

const state = {
    token: null,
    email: null,
    name: null,
}

const getters = {
    'token': state => state.token,
    'email': state => state.email,
    'name': state => state.name,
}

const mutations = {
    'login': (state, payload) => {
        state.token = payload.headers['accesstoken']
        state.email = payload.data['email']
        state.name = payload.data['name']
    },
    'logout': (state) => {
        state.token = null
        state.email = null
        state.name = null
    }
}

const actions = {
    login({commit}, {email, password}) {
        const params = {
            "email": email,
            "password": password
        }
        http.post('/user/login', JSON.stringify(params), {
            headers: { 'Content-Type': 'application/json' }
        }).then(response => {
            commit('login', response)
            console.log(response.data)
            sessionStorage.setItem('token', response.data);
            // router.replace('/');
        }).catch(error => {
            console.log(error)
            alert('로그인에 실패하였습니다:module.js')
        })
    },
    logout({commit}) {
        commit('logout')
        router.push('/login')
    }
}

export default {
    state,
    getters,
    mutations,
    actions
}