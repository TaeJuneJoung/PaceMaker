export const state = () => ({
  userEmail: '',
  nickName: 'testuser',
  userIcon: 'mdi-account-circle'
})

export const getters = {
  getUserEmail: state => {
    return state.userEmail
  },
  getUserName: state => {
    return state.nickName
  },
  getUserIcon: state => {
    return state.userIcon
  }
}
export const mutations = {
  setUserEmail (state, email) {
    state.userEmail = email
  },
  setUserName (state, name) {
    state.nickName = name
  },
  setUserIcon (state, icon) {
    state.userIcon = icon
  }
}

export const actions = {
  setUserEmail (context, email) {
    context.commit('setUserEmail' , email)
  },
  setUserName (context, name) {
    context.commit('setUserName' , name)
  },
  setUserIcon (context, icon) {
    context.commit('setUserIcon' , icon)
  }
}