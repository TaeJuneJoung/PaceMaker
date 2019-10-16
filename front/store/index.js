export const state = () => ({
	authCompleted: false
})

export const getters = {
	isAuthCompleted: state => {
		return state.authCompleted
	}
}
export const mutations = {
	authInit (state) {
		state.authCompleted = false
	},
	authComplete (state) {
		state.authCompleted = true
	}
}
export const actions = {
	authInit (context) {
		context.commit('authInit')
	},
	authComplete (context) {
		context.commit('authComplete')
	}	
}

// deprecated Classic mode
// const store = () => new Vuex.Store({
// 	state: {
// 		authCompleted: false
// 	},
// 	getters: {

// 	},
// 	mutations: {
// 		authInit (state) {
// 			state.authCompleted = false
// 		},
// 		authComplete (state) {
// 			state.authCompleted = true
// 		}
// 	},
// 	actions: {
// 		authInit (context) {
// 			context.commit('authInit')
// 		},
// 		authComplete (context) {
// 			context.commit('authComplete')
// 		}
// 	}
// })

// export default store