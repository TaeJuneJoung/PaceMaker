import axios from 'axios'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/modelrooms'
}

async function getAllModelRoom() {
  try{
    return await axios.get(`${config.baseUrl}`)
  } catch (err) {
    return 'err'
  }
}

async function findModelRoomById(id) {
  try {
    return await axios.get(`${config.baseUrl}/${id}`)
  } catch (err) {
    return 'err'
  }
}

async function deleteRoomById(id) {
  try {
    return await axios.delete(`${config.baseUrl}/${id}`)
  } catch (err) {
    return 'err'
  }
}

export {
  getAllModelRoom,
  findModelRoomById,
  deleteRoomById
}
