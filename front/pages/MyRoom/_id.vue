<template>
  <v-container fluid>
    <v-row align="center">
      <v-col sm="8" cols="12" class="pa-0">
        <v-card outlined>
          <v-stepper non-linear>
            <v-stepper-header>
              <template v-for="n in room.steps">
                <v-stepper-step editable :step="n" :key="n" @click="changeSprint(n)">{{n}} 주차</v-stepper-step>
                <v-divider v-if="n != steps" :key="`${n}-divider`"></v-divider>
              </template>
            </v-stepper-header>
          </v-stepper>
        </v-card>
      </v-col>
      <v-col sm="4" cols="12" class="pa-0">
        <v-card outlined class="text-center pb-2 pl-2 pr-2">
          <span class="overline">전체 달성도</span>
          <v-progress-linear
            color="light-blue"
            v-model="entireCompleted"
            height="16"
            reactive
            striped
          >
            <strong>{{ Math.ceil(entireCompleted) }}%</strong>
          </v-progress-linear>
          <span class="overline">스프린트 달성도</span>
          <v-progress-linear color="amber" v-model="sprintCompleted" height="16" reactive striped>
            <strong>{{ Math.ceil(sprintCompleted) }}%</strong>
          </v-progress-linear>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col sm="8" cols="12" class="pa-0">
        <v-card outlined>
          <v-row justify="center" align="center">
            <v-item-group v-model="day" class="shrink mr-6 ml-6" mandatory tag="v-flex">
              <v-item v-for="n in length" :key="n" v-slot:default="{ active, toggle }">
                <v-btn :input-value="active" icon @click="toggle">
                  <v-icon>mdi-record</v-icon>
                </v-btn>
              </v-item>
            </v-item-group>
          </v-row>
        </v-card>
        <v-card>
          <v-row justify="center" align="center" class="vh65">
            <v-col cols="8">
              <v-window v-model="day" class="elevation-1" vertical>
                <v-window-item v-for="n in length" :key="n">
                  <v-col cols="12" class="pa-0">
                    <v-card dark class="pa-2">
                      <v-row align="center">
                        <strong class="title ml-5">Day {{ n }}</strong>
                      </v-row>
                      <v-divider></v-divider>
                      <template v-for="(todo, index) in sprint[n-1]">
                        <v-card
                          v-if="todo.todo"
                          light
                          flat
                          hover
                          class="pa-2 ma-1"
                          :key="index"
                          @click="toggleTodo(n-1, index)"
                        >
                          <v-row justify="center" align="center">
                            <v-col class="pa-0" sm="10" cols="9">
                              <strong>{{todo.todo}}</strong>
                            </v-col>
                            <v-col class="pa-0" sm="1" cols="2" justify="end">
                              <v-icon
                                right
                              >{{ todo.flag ? 'mdi-check-circle' : 'mdi-radiobox-blank' }}</v-icon>
                            </v-col>
                          </v-row>
                        </v-card>
                      </template>
                    </v-card>
                  </v-col>
                </v-window-item>
              </v-window>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
      <v-col sm="4" cols="12" class="pa-0">
        <v-card outlined class="pa-2">
          <span class="headline pl-2">COMMENTS</span>
          <v-card class="vh65 scroll" id="scroll-target">
            <v-list three-line id="scroll-content">
              <template v-for="(comment, index) in comments">
                <v-list-item :key="`${index}-list`">
                  <v-list-item-content>
                    <v-list-item-title class="text--primary" v-text="comment.title"></v-list-item-title>
                    <v-list-item-subtitle v-text="comment.subtitle"></v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
                <v-divider v-if="index != comments.length" :key="index"></v-divider>
              </template>
            </v-list>
          </v-card>
        </v-card>
      </v-col>
      <v-col cols="12">
        <v-text-field
          v-model="message"
          :append-outer-icon="message? 'mdi-send' : ''"
          outlined
          clear-icon="mdi-close-circle"
          clearable
          label="Comment"
          type="text"
          @click:append-outer="sendMessage"
          @click:clear="clearMessage"
        ></v-text-field>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { findRoomById , updateRoomSprintById } from '~/api/rooms.js'

export default {
  layout: 'default',
  data: () => ({
    model: [],
    message: '',
    steps: 4,
    curStep: 1,
    length: 7,
    day: 0,
    roomId: 0,
    sprints: [],
    sprint: [],
    comments: [],
    room: {}
  }),
  async created() {
    this.roomId = this.$route.params.id
    this.room.steps = 0;
    let response
    try {
      response = await findRoomById(this.roomId)
      this.room = response.data
      this.sprints = JSON.parse(this.room.sprints)
      // console.log(this.room)
      this.sprint = this.sprints[this.room.currentDay]
    } catch (err) {}
  },
  methods: {
    async toggleTodo(day, idx) {
      this.sprint[day][idx].flag = !this.sprint[day][idx].flag
      console.log(this.sprint[day][idx].flag)
      //axios todo 한걸로 체크
      try {
        let sprints = {
          sprints: JSON.stringify(this.sprints)
        };
        let response = await updateRoomSprintById( this.$route.params.id , sprints );
      } catch(err) {
        console.log(err)
      }
    },
    changeSprint(n) {
      this.curStep = n
      this.day = 0
      this.sprint = this.sprints[n - 1]
    },
    sendMessage() {
      //axios

      let elem = document.getElementById('scroll-content')
      let container = document.getElementById('scroll-target')
      container.scrollTop = Math.floor(elem.offsetHeight)
      this.clearMessage()
    },
    clearMessage() {
      this.message = ''
    }
  },
  computed: {
    sprintCompleted() {
      let sum = 0
      let cnt = 0
      this.sprint.forEach((element) => {
        element.forEach((element) => {
          if (element.todo) {
            sum++
            if (element.flag) cnt++
          }
        })
      })
      return (cnt / sum) * 100
    },
    entireCompleted() {
      //axios 현재 Room의 전체 진행도
      let cnt = 0
      let done = 0
      this.sprints.forEach((sprint) => {
        sprint.forEach((day) => {
          day.forEach((todo) => {
            if (todo.todo != '') {
              cnt++
              if (todo.flag) done++
            }
          })
        })
      })
      return (done / cnt) * 100
    },
    getCurrentDay() {
      let cur = {
        day: 0,
        sprint: 0
      }
      // 날짜 계산
      return cur
    },
    getComments() {
      //댓글 가져오기
      return null
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
  overflow-x: hidden;
}
</style>