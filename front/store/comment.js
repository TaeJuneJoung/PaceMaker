import { getComment } from '../api/comment.js'

export const state = () => ({
	commentList: {},
	commentDate: {},
	modelRoomId: 0,
})

export const getters = {
	getCommentList: state => {
		return state.commentList
	},
	getCommentDate: state => {
		return state.commentDate
	},
	getModelRoomId: state => {
		return state.modelRoomId
	},
}

export const mutations = {
	setCommentList: (state, commentList) => {
		state.commentList = commentList
	},
	setFormateDate: (state, commentDate) => {
		state.commentDate = commentDate
	},
	setModelRoomId: (state, modelRoomId) => {
		state.modelRoomId = modelRoomId
	}
}

export const actions = {
	setCommentList(context, modelRoomId) {
		getComment(modelRoomId)
			.then(({ data }) => {
				let commentDate = []
				for (let i = 0; i < data.length; i++) {
					let dateTime = new Date(data[i].date)
					commentDate.push(dateTime.getFullYear() + '.' + (dateTime.getMonth()+1) + '.' + dateTime.getDate() + ' ' + dateTime.getHours() + ':' + dateTime.getMinutes())
				}
				context.commit('setModelRoomId', modelRoomId)
				context.commit('setFormateDate', commentDate)
				context.commit('setCommentList', data)
			})
			.catch(error => {
				console.error(error)
			})
	}
}