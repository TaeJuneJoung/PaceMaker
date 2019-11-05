<template>
  <v-container fluid class="pb-0">
    <v-row align="center">
      <v-col sm="8" cols="12" class="pa-0">
        <v-card outlined>
          <v-stepper non-linear v-model="date.sprint">
            <v-stepper-header>
              <template v-for="n in room.steps">
                <v-stepper-step editable :step="n" :key="n" @click="changeSprint(n)">{{n}} 주차</v-stepper-step>
                <v-divider v-if="n != room.steps" :key="`${n}-divider`"></v-divider>
              </template>
            </v-stepper-header>
          </v-stepper>
        </v-card>
      </v-col>
      <v-col sm="4" cols="12" class="pa-0">
        <v-card outlined class="text-center pb-2 pl-2 pr-2">
          <span class="sprintOverline">전체 달성도</span>
          <v-progress-linear
            color="light-blue"
            :value="entireCompleted"
            height="16"
            reactive
            striped
          >
            <strong>{{ Math.ceil(entireCompleted) }}%</strong>
          </v-progress-linear>
          <span class="sprintOverline">스프린트 달성도</span>
          <v-progress-linear color="amber" :value="sprintCompleted" height="16" reactive striped>
            <strong>{{ Math.ceil(sprintCompleted) }}%</strong>
          </v-progress-linear>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col sm="8" cols="12" class="pa-0">
        <v-card outlined>
          <v-row justify="center" align="center">
            <v-item-group v-model="day" class="shrink mr-4 ml-4" mandatory tag="v-flex">
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
            <v-col cols="12" class="h-100">
              <v-window v-model="day" class="elevation-1 h-100" vertical>
                <v-window-item v-for="n in length" :key="n" class="h-100">
                  <v-col cols="12" class="pa-0 h-100">
                    <v-card dark class="pa-2 h-100 dayCard">
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
                            <v-col class="pa-0 dayText" sm="11" cols="10">
                              <strong>{{todo.todo}}</strong>
                            </v-col>
                            <v-col class="pa-0 text-center" sm="1" cols="2" justify="end">
                              <v-icon
                                right
                              >{{ todo.flag ? 'mdi-check-circle' : 'mdi-radiobox-blank' }}</v-icon>
                            </v-col>
                          </v-row>
                        </v-card>
                      </template>
                      <v-card light flat class="pa-2 ma-1">
                        <v-row justify="center" alingn="center">
                          <v-col class="pa-0" cols="10" sm="11">
                            <v-text-field
                              dense
                              class="ml-4"
                              hide-details
                              label="할일 추가"
                              outlined
                              v-model="todoinput"
                            ></v-text-field>
                          </v-col>
                          <v-col class="pa-0 text-center" cols="2" sm="1" justify="end">
                            <v-btn text icon color="primary" @click="addTodo(n-1)">
                              <v-icon>mdi-plus-circle</v-icon>
                            </v-btn>
                          </v-col>
                        </v-row>
                      </v-card>
                    </v-card>
                  </v-col>
                </v-window-item>
              </v-window>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
      <v-col sm="4" cols="12" class="pa-0 vh20">
        <comment-view></comment-view>
      </v-col>
      <v-col cols="12" class="pt-0 pb-0 mt-sm-3">
        <comment></comment>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import CommentView from '../../components/CommentView.vue'
import Comment from '../../components/Comment.vue'
import { findRoomById, updateRoomSprintById } from '~/api/rooms.js'
import { getAchieve, putAchieve } from '~/api/achieve.js'

export default {
  layout: 'default',
  middleware: 'auth',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '나의 목표방 | %s'
    }
  },
  components: {
    CommentView,
    Comment
  },
  data: () => ({
    todoinput: '',
    model: [],
    curStep: 1,
    length: 7,
    day: 0,
    roomId: 0,
    sprints: [],
    sprint: [],
    room: {},
    date: {
      day:0,
      sprint:0
    }
  }),
  async created() {
    this.roomId = this.$route.params.id
    this.room.steps = 0
    let response
    try {
      response = await findRoomById(this.roomId);
      this.room = response.data;
      this.sprints = JSON.parse(this.room.sprints);
      this.sprint = this.sprints[this.room.currentDay];
      this.date = this.getCurrentDay();
      this.changeSprint(this.getCurrentDay().sprint);
      this.day = this.getCurrentDay().day;
    } catch (err) {

    }
  },
  methods: {
    async toggleTodo(day, idx) {
      //axios todo 한걸로 체크
      if(this.sprint[day][idx].flag){
        let achieveData = {}
        await getAchieve(this.$session.get('account').id).then(({ data }) => {
          achieveData = data
        })
        achieveData.coin += 10
        await putAchieve(achieveData)
        this.sprint[day][idx].flag = !this.sprint[day][idx].flag
      } else {
        let achieveData = {}
        await getAchieve(this.$session.get('account').id).then(({ data }) => {
          achieveData = data
        })
        achieveData.coin -= 10
        await putAchieve(achieveData)
        this.sprint[day][idx].flag = !this.sprint[day][idx].flag
      }
      const achieveModal = this.$store.state.achievement.coinAchieve
      achieveModal.forEach((element) => {
        if (element.number == achieveData.coin) {
          this.$store.commit('modal/setModalData', {
            header: element.name,
            body: '코인 획득!! \n 업적을 취득하였습니다.',
            img: element.img
          })
          this.$store.commit('achievement/setShowModal', true)
        }
      })
      try {
        let sprints = {
          sprints: JSON.stringify(this.sprints)
        }
        let response = await updateRoomSprintById(
          this.$route.params.id,
          sprints
        )
      } catch (err) {
        console.error(err)
      }
    },
    changeSprint(n) {
      this.curStep = n
      this.day = 0
      this.sprint = this.sprints[n - 1]
    },
    async addTodo(day) {
      let todo = {
        todo: this.todoinput,
        flag: false
      }
      this.sprint[day].push(todo);
      this.todoinput = '';
      let sprints = {
        sprints: JSON.stringify(this.sprints)
      }
      try {
        let respons = await updateRoomSprintById(
          this.$route.params.id,
          sprints
        )
      } catch(err) {
        console.log(err);
      }
    },
    getCurrentDay() {
      let cur = {
        day: 0,
        sprint: 0
      }
      // 날짜 계산
      let date = new Date();
      let sdate = new Date(this.room.createDate);
      let dif = parseInt((date - sdate)/(24*60*60*1000));
      let week = Math.floor(dif/7);
      if(week <= this.room.steps) {
        cur.sprint = week +1
        cur.day = dif%8;
      }
      return cur;

    },
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
    }
  }
}
</script>
<style scoped>
.dayText {
  text-indent: 15px;
}
.dayCard {
  overflow-y: auto;
  overflow-x: hidden;
}
.h-100 {
  height: 100%;
  padding-top: 0;
  padding-bottom: 0;
}
.sprintOverline {
  font-weight: bold;
  font-size: 0.95rem;
}
@media screen and (min-width: 768px) {
  .vh65 {
    height: 66.7vh;
  }
  .vh25 {
    height: 25vh;
  }
  .vh50 {
    height: 50vh;
  }
}

@media screen and (max-width: 767px) {
  .vh65 {
    height: 40vh;
  }
  .vh20 {
    height: 26vh;
  }
}

.scroll {
  overflow-y: auto;
  overflow-x: hidden;
}
</style>