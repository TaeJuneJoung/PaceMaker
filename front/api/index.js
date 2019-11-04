import axios from 'axios'

const config = {
  baseUrl: 'http://13.124.241.124:8080/api/v1/'
}

function getUserList() {
  return axios.get(`${config.baseUrl}users`)
}

function getUser(userId) {
  return axios.get(`${config.baseUrl}users/id/${userId}`)
}

function findUserEmail(email) {
  return axios.get(`${config.baseUrl}users/email/${email}`)
}
/**
 * 가입시, 이메일 중복 확인
 * param {} email
 * return boolean
 */
function getCheckEmail(email) {
  //MemberJoin
  return axios.get(`${config.baseUrl}users/check/email/${email}`)
}

/**
 * 가입/수정시, 닉네임 중복 확인
 * param {} nickname
 * return boolean
 */
function getCheckNickname(nickname) {
  //MemberJoin, MemberUpdatePage
  return axios.get(`${config.baseUrl}users/check/nick/${nickname}`)
}

function createUser(userData) {
  //사용중
  return axios.post(`${config.baseUrl}users`, userData)
}

/**
 * 가입시, 인증 이메일 Send
 * param {} email
 */
function sendUserMail(email) {
  return axios.get(`${config.baseUrl}send/${email}`)
}

function putUser(userData) {
  //사용중
  return axios.put(`${config.baseUrl}users`, userData)
}

function deleteUser(userId) {
  return axios.delete(`${config.baseUrl}users/${userId}`)
}

function loginUser(loginData) {
  //사용중
  return axios.post(`${config.baseUrl}users/login`, loginData)
}

function updatePass(userPass) {
  //사용중
  return axios.put(`${config.baseUrl}users/pass`, userPass)
}

export {
  getUserList,
  getUser,
  findUserEmail,
  getCheckEmail,
  getCheckNickname,
  createUser,
  sendUserMail,
  putUser,
  deleteUser,
  loginUser,
  updatePass
}
