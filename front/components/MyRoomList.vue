<template>
  <v-container fluid>
      <v-col cols="12">
          <v-data-iterator
            :items="roomlist"
            hide-default-footer
          >
            <template v-slot:header>
              <v-toolbar flat>
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
            
              <v-row class="pa-2">
                <v-col v-for="(room, index) in roomlist" :key="room.title" cols="12" lg="4" sm="6">
                  <v-hover v-slot:default="{ hover }">
                      <v-card class="mx-auto card-black" :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }">
                        <v-container @click="moveRoom(index+1)">
                          <v-flex class="text-gray">방 분류: {{ room.public ? "공개" : "비공개" }} </v-flex>
                          <v-flex class="text-gray">방 이름: {{ room.title }} </v-flex>
                          <v-flex class="text-gray mb-3">완료 여부: {{ complete_flag ? "완료" : "진행중"}} </v-flex>
                        </v-container>
                      </v-card>
                  </v-hover>
                </v-col>
              </v-row>
            </template>
          </v-data-iterator>
      </v-col>
  </v-container>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import { findModelRoomByUserId } from "../api/modelRoom.js"

export default {
  layout: 'default',
  components: {

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
    id: '',
    roomlist: [],
    roomsprint:[],
    complete_flag: false
  }),
  created() {

  },
  mounted(){
    this.id = this.$session.get("account").id
    this.getMyRoomList()
  },
  computed: {
    
  },
  methods: {
    async moveRoom(idx){
      this.$router.push("/MyRoom/"+idx)
    },
    async getMyRoomList(){
      
      let Myroom = findModelRoomByUserId(this.id)
        .then((res) => {
          for(let i=0; i<res.data.length; i++){
            this.roomlist.push(JSON.parse(res.data[i].roomData))
            this.roomsprint = JSON.parse(res.data[i].roomData).sprint[0]
          }
          
          for(let i=0; i<this.roomsprint.length; i++){
            if(!this.roomsprint[i][0].flag){
              break;
            }
            else{
              this.complete_flag = true;
            }
          }
        }
      )
      
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