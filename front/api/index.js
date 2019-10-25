import axios from 'axios'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/'
}

function getUserList() {
	return axios.get(`${config.baseUrl}users`)
}

function getUser(userId) {
	return axios.get(`${config.baseUrl}users/${userId}`)
}

function findUserEmail(userEmail) {
	return axios.get(`${config.baseUrl}users/${userEmail}`)
}

function createUser(userData) {
	return axios.post(`${config.baseUrl}users`, userData)
}

function putUser(userId) {
	// put에 어울리는지 patch에 어울리는지 파악 필요
	return axios.put(`${config.baseUrl}users/${userId}`)
}

function deleteUser(userId) {
	return axios.delete(`${config.baseUrl}users/${userId}`)
}

function loginUser(loginData) {
	return axios.post(`${config.baseUrl}users/login`, loginData)
}

export {
	getUserList,
	getUser,
	findUserEmail,
	createUser,
	putUser,
	deleteUser,
	loginUser
}