export const state = () => ({
  roomId: '',
  userCount: '',
  roomFlag: false,
  summary: '',
  completeFlag: '',
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
  }
}