<template>
  <v-container fluid>
    <v-row>
      <v-col sm="12" cols="12">
        <v-row>
          <template v-for="room in modelRoomList">
            <v-col v-if="room.roomData.public" :key="room.id" cols="12" lg="4" sm="6" xl="3">
              <room :room="room" :limits="6" :load-more="true"></room>
            </v-col>
          </template>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { getAllModelRoom } from '~/api/modelRoom.js'
import Room from '~/components/Room.vue'

export default {
  layout: 'default',
  middleware: 'auth',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: 'Main | %s'
    }
  },
  components: {
    Room
  },
  data: () => ({
    userId: 0,
    nickname: '',
    modelRoomList: []
  }),
  async created() {
    let response = await getAllModelRoom()
    this.modelRoomList = response.data
    this.modelRoomList.forEach((room) => {
      room.roomData = JSON.parse(room.roomData)
    })
  },
  mounted() {
    const user = this.$session.get('account')
    this.userId = user.id
    this.nickname = user.nickname
  },
  computed: {}
}
</script>

<style scoped>
.col-sm-6,
.col-lg-4,
.col-12 {
  padding: 0;
}
.container {
  padding: 0;
}
</style>