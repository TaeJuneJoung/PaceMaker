<template>
  <v-container fluid>
    <v-row>
      <v-col sm="12" cols="12">
          <v-row>
            <v-col v-for="room in getRoomList" :key="room.roomId" cols="12" lg="4" sm="6">
              <room :room="room" :limits="6" :load-more="true"></room>
            </v-col>
          </v-row>
      </v-col>
    </v-row>
  </v-container>    
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import Room from '~/components/Room.vue'

export default {
  layout: 'default',
  middleware: 'auth',
  components: {
    Room
  },
  data: () => ({

  }),
  computed: {
    ...mapGetters({
      getRoomList: 'room/getRoomList'
    })
  },
}
</script>

<style scoped>
.col-sm-6, .col-lg-4, .col-12 {
	padding: 0;
}
.container {
	padding: 0;
}
</style>