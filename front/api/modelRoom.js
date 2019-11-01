import axios from 'axios'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/modelrooms'
}

function getAllModelRoom() {
  return axios.get(`${config.baseUrl}`)
}

function findModelRoomById(id) {
  return axios.get(`${config.baseUrl}/${id}`)
}

function deleteRoomById(id) {
  return axios.delete(`${config.baseUrl}/${id}`)
}

function findModelRoomByUserId(id) {
  return axios.get(`${config.baseUrl}/user/${id}`)
}

export {
  getAllModelRoom,
  findModelRoomById,
  deleteRoomById,
  findModelRoomByUserId
}
