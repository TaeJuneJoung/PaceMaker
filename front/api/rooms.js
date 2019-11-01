import axios from 'axios'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/rooms'
}

async function findRoomById(id) {
	return await axios.get(`${config.baseUrl}/${id}`)
}

function createRoom(room) {
	return axios.post(`${config.baseUrl}` , room)
}

function findRoomByUserId(id) {
	return axios.get(`${config.baseUrl}/user/${id}`)
}

export {
	findRoomById,
	findRoomByUserId,
  createRoom
}