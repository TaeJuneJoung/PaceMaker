export const state = () => ({
    header : "",
    body : "",
    img : ""
})

export const mutations = {
	setModalData(state, value) {
      state.header = value.header
      state.body = value.body
      state.img = value.img
	},
}