import axios from 'axios'
import { findRoomById } from './rooms'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/modelrooms'
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
  findModelRoomById,
  deleteRoomById
}
