<template>
  <v-container fluid>
    <v-tabs
      v-model="tab"
      background-color="deep-purple accent-4"
      class="elevation-2"
      dark
      grow
      right
    >
      <v-tabs-slider></v-tabs-slider>

      <v-tab v-for="(tab, index) in tabs" :key="index" :href="`#tab-${index}`">{{ tab.title }}</v-tab>

      <v-tab-item v-for="(tab, index) in tabs" :key="index" :value="'tab-' + index">
        <v-row class="pa-2">
          <v-col cols="6" v-for="(room , index) in tab.list" :key="index">
            <v-card v-if="isMyRoomTab(tab.title)" class="pa-2" outlined hover dark nuxt :to="`/MyRoom/${room.id}`">
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>{{room.title ? room.title : '제목없음'}}</v-list-item-title>
                  <v-divider></v-divider>
                  <v-list-item-subtitle>{{ isMyRoomTab(tab.title) ? getCompleteFlag(room.completeFlag) + '/' : '' }} {{ getRoomFlag(room.roomFlag) }}</v-list-item-subtitle>
                  <v-list-item-subtitle>{{ room.steps }} 주</v-list-item-subtitle>
                  <v-list-item-subtitle
                    v-if="isMyRoomTab(tab.title)"
                  >시작 : {{ getStartDate(room.createDate) }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-card>
            <v-card v-else class="pa-2" outlined hover dark nuxt :to="`/Room/${room.id}`">
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>{{room.title ? room.title : '제목없음'}}</v-list-item-title>
                  <v-divider></v-divider>
                  <v-list-item-subtitle>{{ isMyRoomTab(tab.title) ? getCompleteFlag(room.completeFlag) + '/' : '' }} {{ getRoomFlag(room.roomFlag) }}</v-list-item-subtitle>
                  <v-list-item-subtitle>{{ room.steps }} 주</v-list-item-subtitle>
                  <v-list-item-subtitle
                    v-if="isMyRoomTab(tab.title)"
                  >시작 : {{ getStartDate(room.createDate) }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-card>
          </v-col>
        </v-row>
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import { findRoomByUserId } from "../api/rooms.js"
import { findModelRoomByUserId } from "~/api/modelRoom.js"

export default {
  layout: 'default',
  components: {

  },
  data: () => ({
    tab: null,
    noDataText: '현재 참가한 방이 없습니다.',
    ipp: 4,
    tabs: [
      { 
        title:'참가한 방 목록',
        list: []
      },
      { 
        title:'만든 방 목록',
        list: []
      }
    ],
    search: '',
    page: 1,
    itemsPerPage: 4,
    sortBy: 'name',
    keys: [
      'ID',
      '내용'
    ],
    key: '',
    id: '',
    roomlist: [],
    roomsprint:[],
    complete_flag: false
  }),
  async created() {
  },
  async mounted(){
    this.id = this.$session.get("account").id
    try {
      let response = await findRoomByUserId(this.$session.get('account').id);
      let response2 = await findModelRoomByUserId(this.$session.get('account').id);
      // let roomdata = JSON
      // let templist = JSON.parse(response2.data.roomData);
      response2.data.forEach(room => {
        let temp = JSON.parse(room.roomData);
        let cnt = 0;
        temp.sprint.forEach(element => {
          cnt++;
        });
        temp.steps = cnt;
        temp.id = room.id;
        temp.roomFlag = temp.public;
        this.tabs[1].list.push(temp);
        // console.log(temp);
      });

      this.tabs[0].list = response.data;
    } catch(err) {
      console.log(err)
    }
    console.log(this.tabs[0].list)
  },
  computed: {
  },
  methods: {
    nextPage () {
      if (this.page + 1 <= this.numberOfPages) this.page += 1
    },
    formerPage () {
      if (this.page - 1 >= 1) this.page -= 1
    },
    numberOfPages(list) {
      return Math.ceil(list.length / this.ipp);
    },
    getCompleteFlag(flag) {
      return flag ? '완료' : '미완료';
    },
    getRoomFlag(flag) {
      return flag ? '공개' : '비공개';
    },
    getStartDate(date) {
      let day = new Date(date);
      return day.toLocaleString();
    },
    isMyRoomTab(tab){
      return tab==="참가한 방 목록";
    }
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
.card-black {
  background-color: black;
  color: white;
}
.text-gray {
  color: #666666;
}
.room-card-icon {
  color: white;
  font-size: 16px;
  padding: 0px 2px 4px 0px;
}
a {
  text-decoration: none;
  color: white;
}
.v-application a:hover {
  color: #aaa;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  position: absolute;
  width: 100%;
}
</style>