export const state = () => ({
  room: {
    title: '',
    public: true,
    sprint: [
      [
        // sprint
        [
          // day
          { todo: '', flag: false },
          { todo: '', flag: false },
          { todo: '', flag: false }
        ],
        [{ todo: '', flag: false }],
        [{ todo: '', flag: false }],
        [{ todo: '', flag: false }],
        [{ todo: '', flag: false }],
        [{ todo: '', flag: false }],
        [{ todo: '', flag: false }]
      ]
    ]
  }
})

export const getters = {
  getAddRoom: (state) => {
    return state.room
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
