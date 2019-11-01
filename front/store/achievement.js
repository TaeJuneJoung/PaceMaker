export const state = () => ({
  userAchieve: {},
  // 업적 데이터
  coinAchieve: [
    { id: 1, number: 100, name: '천리 길도 한 걸음부터', img: 'achieve/coin01.svg' },
    { id: 2, number: 500, name: '떡상각', img: 'achieve/coin02.svg' },
    { id: 3, number: 1000, name: '떡상중', img: 'achieve/coin03.svg' },
    { id: 4, number: 10000, name: '보라코인 달성', img: 'achieve/coin04.svg' },
    { id: 5, number: 100000000, name: '당신은 운영자?!!', img: 'achieve/coin05.svg' }
  ],
  makerAchieve: [
    { id: 1, number: 1, name: '시작이 반이다', img: 'achieve/maker01.svg' },
    { id: 2, number: 10, name: '멘토의 시작', img: 'achieve/maker02.svg' },
    { id: 3, number: 100, name: '운영자가 되어줘', img: 'achieve/maker03.svg' },
    { id: 4, number: 10000, name: '당신은 Pack Maker!!', img: 'achieve/maker04.svg' }
  ],
  commentAchieve: [
    { id: 1, number: 1, name: '인싸의 시작', img: 'achieve/comment01.svg' },
    { id: 2, number: 10, name: '뉴스타', img: 'achieve/comment02.svg' },
    { id: 3, number: 100, name: '인싸각', img: 'achieve/comment03.svg' },
    { id: 4, number: 1000, name: 'PaceMaker 인싸', img: 'achieve/comment04.svg' },
    { id: 5, number: 100000000, name: '당신은 댓글왕', img: 'achieve/comment05.svg' }
  ],
  roomAchieve: [
    { id: 1, number: 1, name: 'One피스', img: 'achieve/room01.svg' },
    { id: 2, number: 10, name: '도전 성공자', img: 'achieve/room02.svg' },
    { id: 3, number: 100, name: '목표 달성의 달인', img: 'achieve/room03.svg' },
    { id: 4, number: 1000, name: '마스터피스', img: 'achieve/room04.svg' },
    { id: 5, number: 100000000, name: 'God of God', img: 'achieve/room05.svg' }
  ],
  showModal: false
})

export const getters = {
  getCoinAchieve: (state) => {
    return state.coinAchieve
  },
  getMakerAchieve: (state) => {
    return state.makerAchieve
  },
  getCommentAchieve: (state) => {
    return state.commentAchieve
  },
  getRoomAchieve: (state) => {
    return state.roomAchieve
  }
}

export const mutations = {
  setShowModal(state, value) {
    state.showModal = value
  }
}
