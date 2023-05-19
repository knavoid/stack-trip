import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import modules from '@/store/module.js'

Vue.use(Vuex)

const persistedState = createPersistedState({
  paths: ['token', 'email', 'name']
})

export default new Vuex.Store({
  state: modules.state,
  getters: modules.getters,
  mutations: modules.mutations,
  actions: modules.actions,
  plugins: [persistedState]
})
