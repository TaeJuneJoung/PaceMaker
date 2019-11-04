import axios from 'axios'

const config = {
  baseUrl: 'http://0.0.0.0:8080/api/v1/'
}

function createRoom(roomData) {
  return axios.post(`${config.baseUrl}modelrooms`, roomData)
}

function addImg(imgData) {
  return axios.post(`${config.baseUrl}uploadFile`, imgData)
}

export { createRoom, addImg }
