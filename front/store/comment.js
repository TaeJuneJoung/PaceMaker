import { getComment } from '../api/comment.js'

export const state = () => ({
	commentList: {},
	commentDate: {},
})

export const getters = {
	getCommentList: state => {
		return state.commentList
	},
	getCommentDate: state => {
		return state.commentDate
	}
}

export const mutations = {
	setCommentList: (state, commentList) => {
		state.commentList = commentList
	},
	setFormateDate: (state, commentDate) => {
		state.commentDate = commentDate
	}
}

export const actions = {
	setCommentList(context, modelRoomId) {
		getComment(modelRoomId)
			.then(({ data }) => {
				let commentDate = []
				for (let i = 0; i < data.length; i++) {
					let dateTime = new Date(data[i].date)
					commentDate.push(dateTime.getFullYear() + '/' + (dateTime.getMonth()+1) + '/' + dateTime.getDate() + ' ' + dateTime.getHours() + ':' + dateTime.getMinutes())
				}
				context.commit('setFormateDate', commentDate)
				context.commit('setCommentList', data)
			})
			.catch(error => {
				console.error(error)
			})
	}
}