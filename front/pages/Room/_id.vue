<template>
  <v-container fluid class="pb-0 mt-10">
    <v-row align="center">
      <v-col cols="12" class="pa-0">
        <v-card outlined class="heightsm">
          <v-card-title>{{title}}</v-card-title>
        </v-card>
      </v-col>
      <v-col cols="12" class="pa-0">
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
    </v-row>
    <v-row>
      <v-col cols="12" class="pa-0">
        <v-card outlined>
          <v-row justify="center" align="center">
            <v-item-group v-model="day" class="shrink mr-4 ml-4" mandatory tag="v-flex">
              <v-item v-for="n in length" :key="n" v-slot:default="{ active, toggle }">
                <v-btn
                  :color="active ? 'primary' : 'grey'"
                  :input-value="active"
                  icon
                  @click="toggle"
                >
                  <v-icon>mdi-record</v-icon>
                </v-btn>
              </v-item>
            </v-item-group>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <v-col cols="12" class="pa-0">
      <v-row justify="center" class="vh65 scroll">
        <v-col cols="12" class="h-100">
          <v-card outlined>
            <v-window v-model="day" class="elevation-1" vertical>
              <v-window-item v-for="n in length" :key="n">
                <v-card-text>
                  <strong class="title ml-5">Day {{ n }}</strong>
                  <v-divider></v-divider>
                </v-card-text>
                <v-list shaped>
                  <v-col>
                    <v-list-item-group multiple>
                      <template v-for="(item, i) in sprint[n-1]">
                        <v-list-item :key="`item-${i}`" active-class="primary--text text--accent-4">
                          <v-list-item-content>
                            <v-list-item-title v-text="item.todo"></v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </template>
                    </v-list-item-group>
                  </v-col>
                </v-list>
              </v-window-item>
            </v-window>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
    <div class="text-center">
      <v-btn class="ma-2" color="grey" dark @click="back">
        <v-icon left>mdi-arrow-left-circle</v-icon>뒤로가기
      </v-btn>
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
import { createRoom, countByUserIdAndModelId } from '~/api/rooms.js'

export default {
  layout: 'default',
  middleware: 'auth',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '모델방 | %s'
    }
  },
  data: () => ({
    title: '',
    message: '',
    steps: 4,
    curStep: 1,
    length: 7,
    day: 0,
    roomId: 0,
    sprint: [],
    room: {},
    roomMaker: ''
  }),
  async created() {
    this.roomId = this.$route.params.id
    let response
    try {
      response = await findModelRoomById(this.roomId)
    } catch (err) {}
    this.room = response.data
    this.room.roomData = JSON.parse(this.room.roomData)
    this.title = this.room.roomData.title
    this.sprint = this.room.roomData.sprint[0]
    this.steps = this.room.roomData.sprint.length
  },
  mounted() {
    this.roomMaker = this.$session.get('account').id
  },
  methods: {
    changeSprint(n) {
      this.curStep = n
      this.day = 0
      this.sprint = this.room.roomData.sprint[n - 1]
    },
    async joinRoom() {
      let count = await countByUserIdAndModelId(this.$session.get('account').id , this.roomId);
      if(count.data > 0) {
        window.alert("이미 진행중인 스프린트입니다.");
        return;
      }

      let data = {
        title: this.title,
        steps: this.steps,
        currentDay: 0,
        createDate: new Date(),
        userId: this.$session.get('account').id,
        userName: this.$session.get('account').nickname,
        modelId: this.roomId,
        roomFlag: this.room.roomData.public,
        completeFlag: false,
        sprints: JSON.stringify(this.room.roomData.sprint)
      }

      try {
        await createRoom(data)
      } catch (err) {
        console.error(err)
      }
    },
    deleteRoom() {
      if (this.checkUser) {
        if (deleteRoomById(this.roomId) == 'err') {
          window.alert('오류 : 삭제 실패')
        } else {
          window.alert('삭제 성공')
          this.$router.push('/MainPage')
        }
      }
    },
    back() {
      this.$router.push('/MainPage')
    }
  },
  computed: {
    checkUser() {
      return this.roomMaker == this.room.userId
    }
  }
}
</script>
<style scoped>
.h-100 {
  height: 100%;
  padding-top: 0;
  padding-bottom: 0;
}

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

.v-item--active {
  color: red;
}
</style>