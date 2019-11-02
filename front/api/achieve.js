import axios from 'axios'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/usersAchieve'
}

/**
 * 가입시, 업적데이터 생성
 * param {} userId 
 * return Object
 */
function createAchieve(userId) {
	return axios.post(`${config.baseUrl}`, {"userId": userId})
}

/**
 * Event, 업적데이터 수정
 * param {} achieveData
 * return Object
 */
function putAchieve(achieveData) {
	return axios.put(`${config.baseUrl}`, achieveData)
}

/**
 * 해당 유저 업적데이터
 * param {} userId
 * return Object
 */
function getAchieve(userId) {
	return axios.get(`${config.baseUrl}/${userId}`)
}

export {
	createAchieve,
	putAchieve,
	getAchieve
}