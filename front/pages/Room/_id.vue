<template>
  <v-container fluid>
    <v-row align="center">
      <v-col cols="6" class="pa-0">
        <v-card outlined>
          <v-stepper non-linear>
            <v-stepper-header class="heightsm">
              <template v-for="n in steps">
                <v-stepper-step editable :step="n" :key="n" @click="changeSprint(n)">{{n}} 주차</v-stepper-step>
                <v-divider v-if="n != steps" :key="`${n}-divider`"></v-divider>
              </template>
            </v-stepper-header>
          </v-stepper>
        </v-card>
      </v-col>
      <v-col cols="6" class="pa-0">
        <v-card outlined class="heightsm">
          <v-card-title>{{title}}</v-card-title>
        </v-card>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col cols="12" class="pa-0">
        <v-card outlined>
          <v-row align="center" class="vh65 scroll">
            <v-item-group v-model="day" class="shrink mr-6 ml-6" mandatory tag="v-flex">
              <v-item v-for="n in length" :key="n" v-slot:default="{ active, toggle }">
                <div>
                  <v-btn :input-value="active" icon @click="toggle">
                    <v-icon>mdi-record</v-icon>
                  </v-btn>
                </div>
              </v-item>
            </v-item-group>
            <v-col>
              <v-window v-model="day" class="elevation-1 mr-5" vertical>
                <v-window-item v-for="n in length" :key="n">
                  <v-card>
                    <v-card-text>
                      <v-row class="mb-4" align="center">
                        <strong class="title ml-5">Day {{ n }}</strong>
                      </v-row>
                      <v-divider></v-divider>
                    </v-card-text>
                    <v-list shaped>
                      <v-list-item-group multiple>
                        <template v-for="(item, i) in sprint[n-1]">
                          <v-list-item
                            :key="`item-${i}`"
                            active-class="deep-purple--text text--accent-4"
                          >
                            <template>
                              <v-list-item-content>
                                <v-list-item-title v-text="item.todo"></v-list-item-title>
                              </v-list-item-content>
                              <v-list-item-action>
                                <v-checkbox
                                  :true-value="item"
                                  color="deep-purple accent-4"
                                ></v-checkbox>
                              </v-list-item-action>
                            </template>
                          </v-list-item>
                        </template>
                      </v-list-item-group>
                    </v-list>
                  </v-card>
                </v-window-item>
              </v-window>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
      <v-col cols="6" class="pa-0"></v-col>
    </v-row>
    <div class="text-center">
      <v-btn class="ma-2" color="indigo" dark @click="joinRoom()">
        <v-icon left>mdi-pencil</v-icon>참여하기
      </v-btn>
      <v-btn v-if="checkUser" class="ma-2" color="error" dark @click="deleteRoom()">
        <v-icon left>mdi-trash-can</v-icon>삭제하기
      </v-btn>
    </div>
  </v-container>
</template>
<script>
import { findModelRoomById, deleteRoomById } from '~/api/modelRoom.js'
import { createRoom } from '~/api/rooms.js'

export default {
  layout: 'default',
  data: () => ({
    title: '',
		message: '',
		steps: 4,
		curStep: 1,
		length: 7,
    day: 0,
		roomId: 0,
    sprint: 
    [],
    room: {},
    roomMaker: ''
  }),
  async created() {
		this.roomId = this.$route.params.id;
    let response;
    try {
      response = await findModelRoomById(this.roomId);
    } catch (err) {

    }
    this.room = response.data;
    this.room.roomData = JSON.parse(this.room.roomData)
    this.title = this.room.roomData.title;
    this.sprint = this.room.roomData.sprint[0];
    this.steps = this.room.roomData.sprint.length;
  },
  mounted() {
    this.roomMaker = this.$session.get('account').id;
  },  
  methods: {
		changeSprint(n) {
      this.curStep = n;
      this.day = 0;
			this.sprint = this.room.roomData.sprint[n-1];
    },
    async joinRoom() {
      let data = {
        title: this.title, steps : this.steps ,currentDay: 0,
        createDate: new Date(),
        userId: this.$session.get('account').id,
        userName: this.$session.get('account').nickname,
        modelId: this.roomId,
        roomFlag: this.room.roomData.public,
        completeFlag: false,
        sprints: JSON.stringify(this.room.roomData.sprint)
      }
      
      try {
        await createRoom(data);
      } catch (err) {
        console.log(err);
      }
    },
    deleteRoom() {
      if(this.checkUser){
        if(deleteRoomById(this.roomId)=='err'){
          window.alert('오류 : 삭제 실패')
        }else{
          window.alert('삭제 성공')
          this.$router.push('/MainPage')
        }
      }
    }
  },
  computed: {
    checkUser() {
      return (this.roomMaker == this.room.userId);
    }
  }
}
</script>
<style scoped>
@media screen and (min-width: 769px) {
  .vh65 {
    height: 65vh;
  }
  .vh25 {
    height: 25vh;
  }
  .vh50 {
    height: 50vh;
  }
}

@media screen and (max-width: 768px) {
}

.scroll {
  overflow-y: auto;
}
.heightsm {
  height: 70px;
}
.col-sm-6,
.col-lg-4,
.col-12 {
  padding: 0;
}
.container {
  padding: 0;
}
</style>