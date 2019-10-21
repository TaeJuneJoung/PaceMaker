export const state = () => ({
  userEmail: '',
  nickName: 'testuser',
  userIcon: 'mdi-account-circle',
  userPoint: 0,
  userRoomList: [],
  userAlarm: false
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
  },
  getUserPoint: state => {
    return state.userPoint
  },
  getUserRoomList: state => {
    return state.userRoomList
  },
  getUserAlarm: state => {
    return state.userAlarm
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
  },
  setUserPoint (state, point) {
    state.userPoint = point
  },
  addUserPoint (state, point) {
    state.userPoint += point
  },
  setUserRoomList (state, list) {
    state.userRoomList = list
  },
  setUserAlarm (state, alarm) {
    state.userAlarm = alarm
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
  },
  setUserPoint (context, point) {
    context.commit('setUserPoint' , point)
  },
  addUserPoint (context, point) {
    context.commit('addUserPoint' , point)
  },
  setUserRoomList (context, list) {
    context.commit('setUserRoomList' , list)
  },
  setUserAlarm (context, alarm) {
    context.commit('setUserAlarm', alarm)
  }
}