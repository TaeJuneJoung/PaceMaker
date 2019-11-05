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

/**
 * 유저 생성시,
 * param {} userData
 */
function createUser(userData) {
  return axios.post(`${config.baseUrl}users`, userData)
}

/**
 * 가입시, 인증 이메일 Send
 * param {} email
 */
function sendUserMail(email) {
  return axios.get(`${config.baseUrl}send/${email}`)
}

/**
 * 회원 수정시,
 * param {} userData 
 */
function putUser(userData) {
  return axios.put(`${config.baseUrl}users`, userData)
}

/**
 * 회원 탈퇴시
 * param {} userId
 */
function deleteUser(userId) {
  return axios.delete(`${config.baseUrl}users/${userId}`)
}

/**
 * 로그인시,
 * param {} loginData 
 */
function loginUser(loginData) {
  return axios.post(`${config.baseUrl}users/login`, loginData)
}

/**
 * 비밀번호 수정시,
 * param {} {email, password}
 */
function updatePass(user) {
  return axios.put(`${config.baseUrl}users/pass`, user)
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
