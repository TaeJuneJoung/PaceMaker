export const state = () => ({
  roomId: '',
  userCount: '',
  roomFlag: false,
  summary: '',
  completeFlag: '',
  sprint: [
    [
      [ { todo : "aaa0", flag : false } , { todo : "bbb0", flag : true } ],
      [ { todo : "aaa1", flag : false } , { todo : "bbb1", flag : true } ],
      [ { todo : "aaa2", flag : false } , { todo : "bbb2", flag : true } ],
      [ { todo : "aaa3", flag : false } , { todo : "bbb3", flag : true } ],
      [ { todo : "aaa4", flag : false } , { todo : "bbb4", flag : true } ],
      [ { todo : "aaa5", flag : false } , { todo : "bbb5", flag : true } ],
      [ { todo : "aaa6", flag : false } , { todo : "bbb6", flag : true } ]
    ],
    [
      [ { todo : "aaa00", flag : false } , { todo : "bbb00", flag : true } ],
      [ { todo : "aaa11", flag : false } , { todo : "bbb11", flag : true } ],
      [ { todo : "aaa22", flag : false } , { todo : "bbb22", flag : true } ],
      [ { todo : "aaa33", flag : false } , { todo : "bbb33", flag : true } ],
      [ { todo : "aaa44", flag : false } , { todo : "bbb44", flag : true } ],
      [ { todo : "aaa55", flag : false } , { todo : "bbb55", flag : true } ],
      [ { todo : "aaa66", flag : false } , { todo : "bbb66", flag : true } ]
    ],
    [
      [ { todo : "aaa000", flag : false } , { todo : "bbb000", flag : true } ],
      [ { todo : "ccc111", flag : false } , { todo : "ddd111", flag : true } ],
      [ { todo : "aaa222", flag : false } , { todo : "bbb222", flag : true } ],
      [ { todo : "aaa333", flag : false } , { todo : "bbb333", flag : true } ],
      [ { todo : "aaa444", flag : false } , { todo : "bbb444", flag : true } ],
      [ { todo : "aaa555", flag : false } , { todo : "bbb555", flag : true } ],
      [ { todo : "aaa666", flag : false } , { todo : "bbb666", flag : true } ]
    ]
  ],
  roomList: [
		{
			roomId: '1',
			userCount: '2',
			roomFlag: true,
			summary: '테스트 룸',
      completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=33'
		},
		{
			roomId: '2',
			userCount: '3',
			roomFlag: true,
			summary: '테스트 룸2',
      completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=23'
		},
		{
      roomId: '3',
			userCount: '2',
			roomFlag: true,
			summary: '테스트 룸3',
			completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=27'
		},
		{
      roomId: '4',
			userCount: '3',
			roomFlag: true,
			summary: '테스트 룸4',
			completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=45'
    },
    {
      roomId: '5',
			userCount: '2',
			roomFlag: true,
			summary: '테스트 룸5',
			completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=55'
		},
		{
      roomId: '6',
			userCount: '3',
			roomFlag: true,
			summary: '테스트 룸6',
			completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=10'
		},
		{
      roomId: '7',
			userCount: '3',
			roomFlag: true,
			summary: '테스트 룸7',
			completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=15'
		},
		{
      roomId: '8',
			userCount: '8',
			roomFlag: true,
			summary: '테스트 룸8',
			completedFlag: false,
      roomImg: 'https://picsum.photos/500/300?image=20'
		}
	]
})

export const getters = {
  getRoomList: state => {
    return state.roomList
  },
  getRoomById: (state) => {
    return (id) => {
      let size = state.roomList.length;
      return (0 <= id && id < size) ? state.roomList[id] : "null";
    }
  },
  getRoomId: state => {
    return state.roomId
  },
  getUserCount: state => {
    return state.userCount
  },
  getRoomFlag: state => {
    return state.roomFlag
  },
  getSummary: state => {
    return state.summary
  },
  getCompleteFlage: state => {
    return state.completeFlag
  },
  getSprints: (state) => {
    return state.sprint
  }
}

export const mutations = {
  setRoomList (state, list){
    state.roomList = list
  },
  setRoomId (state, id){
    state.roomId = id
  },
  setUserCount (state, count) {
    state.userCount = count
  },
  setRoomFlag (state, flag) {
    state.roomFlag = flag
  },
  setSummary (state, summary) {
    state.summary = summary
  },
  setCompleteFlag (state, flag) {
    state.completeFlag = flag
  },
  setTodoFlag (state, idx, day, flag) {
    state.sprint[idx][day].flag = flag
  }
}

export const actions = {
  setRoomList (context, list){
    context.commit('setRoomList' , list)
  },
  setRoomId (context, id) {
    context.commit('setRoomId' , id)
  },
  setUserCount (context, count) {
    context.commit('setUserCount' , count)
  },
  setRoomFlag (context, flag) {
    context.commit('setRoomFlag' , flag)
  },
  setSummary (context, summary) {
    context.commit('setSummary' , summary) 
  },
  setCompleteFlag (state, flag) {
    context.commit('setCompleteFlag' , flag)
  },
  setTodoFlag (context, idx, n, value){
    context.commit('setTodoFlag', idx, n, value)
  }
}