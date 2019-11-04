import axios from 'axios'

const config = {
	baseUrl: 'http://0.0.0.0:8080/api/v1/comments'
}

/**
 * 룸 id 가져오기
 * param {} modelRoomId
 * return Object
 */
function getComment(modelRoomId) {
	return axios.get(`${config.baseUrl}/model/${modelRoomId}`)
}

/**
 * 댓글 생성시
 * param {} commentData
 * return Object
 */
function createComment(commentData) {
	return axios.post(`${config.baseUrl}`, commentData)
}

export {
	getComment,
	createComment,
}