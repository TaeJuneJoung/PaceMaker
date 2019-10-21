<template>
  <v-container fluid>
    <v-data-iterator :items="getRoomList" :items-per-page.sync="itemsPerPage" hide-default-footer>
      <template v-slot:header>
        <v-toolbar src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg" dark flat>
          <v-row justify="end" align="center">
            <v-col cols="8" sm="3">
              <v-text-field
                label="검색"
                v-model="search"
                hide-details
                append-outer-icon="mdi-magnify"
                single-line
              ></v-text-field>
            </v-col>
            <v-col cols="4" sm="3">
              <v-select label="분류" v-model="key" :items="keys" hide-details></v-select>
            </v-col>
          </v-row>
        </v-toolbar>
      </template>
      <v-row>
        <v-col v-for="room in getRoomList" :key="room.roomId" cols="12" sm="6">
          <nuxt-link :to="`/Room/${room.roomId}`">
            <room :room="room"></room>
          </nuxt-link>
        </v-col>
      </v-row>
      <template v-slot:footer>
        <v-toolbar class="mt-2" color="indigo" dark flat>
          <v-toolbar-title class="subheading">This is a footer</v-toolbar-title>
        </v-toolbar>
      </template>
    </v-data-iterator>
  </v-container>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import room from '~/components/Room.vue'

export default {
  layout: 'default',
  components: {
    room
  },
  data: () => ({
    search: '',
    page: 1,
    itemsPerPage: 4,
    sortBy: 'name',
    keys: [
      'ID',
      '내용'
    ],
    key: '',
    rooms: [],
  }),
  computed: {
    ...mapGetters({
			getRoomList: 'room/getRoomList'
		})
  },
  methods: {

  },
}
</script>