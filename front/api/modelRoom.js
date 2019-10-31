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

async function findModelRoomByUserId(id) {
  try {
    return await axios.get(`${config.baseUrl}/user/${id}`)
  } catch (err) {
    return 'err'
  }
}

export {
  getAllModelRoom,
  findModelRoomById,
  deleteRoomById,
  findModelRoomByUserId
}
