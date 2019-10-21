<template>
	<v-layout>
		{{getRoomById(this.$route.params.id - 1)}}
	</v-layout>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
	data() {
		return {
		}
	},
	computed: {
		...mapGetters({
			getRoomById: 'room/getRoomById'
		})
	},
}
</script>

<style>

</style>