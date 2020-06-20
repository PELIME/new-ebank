import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'

const getDefaultState = () => {
  return {
    token: getToken(),
    username: ''
  }
}

const state = getDefaultState()
const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.username = username
  },
  SET_PHOMNE: (state,phone)=>{
    state.phone=phone
  },
  SET_NUMBER: (state,number)=>{
    state.number=number
  },
  SET_EMAIL: (state,email)=>{
    state.email=email
  },
  SET_DESC: (state,description)=>{
    state.description=description
  },
  SET_REMARK: (state,remark)=>{
    state.remark=remark
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { body } = response
        console.log(body)
        console.log(body.token)
        commit('SET_TOKEN', body.token)
        setToken(body.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        console.log('getInfo')
        console.log(response)
        const { body } = response
        if (!body) {
          reject('Verification failed, please Login again.')
        }
        
        const { username, phone,number,email,description,remark } = body

        commit('SET_NAME', username)
        commit('SET_PHOMNE', phone)
        commit('SET_NUMBER', number)
        commit('SET_EMAIL', email)
        commit('SET_DESC', description)
        commit('SET_REMARK', remark)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

