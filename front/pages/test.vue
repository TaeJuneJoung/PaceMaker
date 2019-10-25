<template>
	<v-container>
		<v-row>
			<v-col>
				<v-flex v-for="user in getUserList" :key="user.id">
					<v-flex @click="test">{{user.id}}</v-flex>
					<v-flex>{{user.email}}</v-flex>
					<v-flex>{{user.password}}</v-flex>
				</v-flex>
			</v-col>
		</v-row>
	</v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import { deleteUser } from '../api/index.js'

export default {
	data() {
		return {
		}
	},
	created() {
		this.$store.dispatch('user/fetchUserList')
		// this.$store.dispatch('user/fetchUser')
	},
	computed: {
		...mapGetters({getUserList: 'user/getUserList'})
		// ...mapGetters({getUserList: 'user/getUser'})
	},
	methods: {
		test(e) {
			let id = e.target.firstChild.data
			deleteUser(id)
		}
	}
}
</script>

<style>

</style>