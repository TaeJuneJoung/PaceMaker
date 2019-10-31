import axios from 'axios'

const config = {
	baseUrl: 'http://localhost:8080/api/v1/rooms'
}

async function findRoomById(id) {
	return await axios.get(`${config.baseUrl}/${id}`)
}

function returnTestRoom() {
	return {'userId':'15' ,'title':'testRoom','public':false,'sprint':[[[{'todo':'qwerqwer','flag':false}],[{'todo':'qwerqwe','flag':false}],[{'todo':'rqwer','flag':false}],[{'todo':'qwereqwr','flag':false}],[{'todo':'wqerqwer','flag':false}],[{'todo':'qwerqw','flag':false}],[{'todo':'rwrwer','flag':false}]],[[{'todo':'werwer','flag':false}],[{'todo':'werwer','flag':false}],[{'todo':'werwe','flag':false}],[{'todo':'rewr','flag':false}],[{'todo':'erwer','flag':false}],[{'todo':'werw','flag':false}],[{'todo':'werwe','flag':false}]],[[{'todo':'','flag':false}],[{'todo':'','flag':false}],[{'todo':'qwerqwer','flag':false}],[{'todo':'qwerqwer','flag':false}],[{'todo':'qwerqwer','flag':false}],[{'todo':'qwerqw','flag':false}],[{'todo':'rqwer','flag':false}]],[[{'todo':'qwerqwer','flag':false}],[{'todo':'','flag':false}],[{'todo':'','flag':false}],[{'todo':'','flag':false}],[{'todo':'','flag':false}],[{'todo':'','flag':false}],[{'todo':'','flag':false}]]]}
}

async function getMyRoomList() {
  return await null
}

export {
  findRoomById,
  returnTestRoom,
  getMyRoomList
}