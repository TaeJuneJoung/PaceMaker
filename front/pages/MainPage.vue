<template>
  <v-container fluid>
    <v-row>
      <v-col sm="12" cols="12">
        <v-row>
          <template v-for="room in modelRoomList">
            <v-col v-if="room.roomData.public" :key="room.id" cols="12" lg="4" sm="6">
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
  components: {
    Room
  },
  data: () => ({
    modelRoomList: []
  }),
  async created() {
    let response = await getAllModelRoom();
    this.modelRoomList = response.data;
    this.modelRoomList.forEach(room => {
      room.roomData = JSON.parse(room.roomData)
    });
  }, 
  computed: {

  },
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