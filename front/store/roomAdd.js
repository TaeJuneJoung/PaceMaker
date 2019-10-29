export const state = () => ({
  room: {
    title: '',
    public: true,
    sprint: [
      {
        0: [{ todo: '', flag: false }],
        1: [{ todo: '', flag: false }],
        2: [{ todo: '', flag: false }],
        3: [{ todo: '', flag: false }],
        4: [{ todo: '', flag: false }],
        5: [{ todo: '', flag: false }],
        6: [{ todo: '', flag: false }]
      }
    ]
  }
})

export const getters = {
  getAddRoom: (state) => {
    return state.addRoom
  }
}

export const mutations = {
  setRoom(state, value) {
    state.room.title = value.title
    state.room.public = value.public
    state.room.sprint = value.sprint
  }
}

export const actions = {
  setRoomList(context, list) {
    context.commit('setRoomList', list)
  }
}
