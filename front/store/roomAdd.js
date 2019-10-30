export const state = () => ({
  room: {
    title: '',
    public: true,
    sprint: [
      [
        // sprint
        [
          // day
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
  },
  setAddDay(state, value) {
    const obj = { todo: '', flag: false }
    state.room.sprint[value.sprint][value.day].push(obj)
  },
  setInsertDay(state, value) {
    state.room.sprint[value.sprint][value.day][value.todo].todo = value.text
  },
  setCheckDay(state, value) {
    state.room.sprint[value.sprint][value.day][value.todo].flag = value.flag
  },
  removeDay(state, value) {
    state.room.sprint[value.sprint][value.day].splice(value.todo, 1)
  }
}

export const actions = {
  setRoomList(context, list) {
    context.commit('setRoomList', list)
  }
}
