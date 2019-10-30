<template>
  <v-container fluid>
    <v-row align="center">
      <v-col sm="8" cols="12" class="pa-0">
        <v-card outlined>
          <v-stepper non-linear>
            <v-stepper-header>
              <template v-for="n in steps">
                <v-stepper-step editable :step="n" :key="n" @click="changeSprint(n)">Sprint {{n}}</v-stepper-step>
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
            <strong>{{ Math.ceil(entireCompleted) }}</strong>
          </v-progress-linear>
          <span class="overline">스프린트 달성도</span>
          <v-progress-linear color="amber" v-model="sprintCompleted" height="16" reactive striped>
            <strong>{{ Math.ceil(sprintCompleted) }}%</strong>
          </v-progress-linear>
        </v-card>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col sm="8" cols="12" class="pa-0">
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
                        <v-spacer></v-spacer>
                      </v-row>
                    </v-card-text>
                    <v-list shaped>
                      <v-list-item-group multiple>
                        <template v-for="(item, i) in sprint[n-1]">
                          <v-list-item
                            :key="`item-${i}`"
                            :value="item.todo"
                            active-class="deep-purple--text text--accent-4"
                          >
                            <template v-slot:default="{ active }">
                              <v-list-item-content>
                                <v-list-item-title v-text="item.todo"></v-list-item-title>
                              </v-list-item-content>

                              <v-list-item-action>
                                <v-checkbox
                                  :input-value="active"
                                  :true-value="item"
                                  color="deep-purple accent-4"
                                  @click="toggleTodo(n-1, i)"
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
import { mapGetters , mapActions } from 'vuex'

export default {
  layout: 'default',
  data: () => ({
		message: '',
		steps: 4,
		curStep: 1,
		length: 7,
    day: 0,
		roomId: 0,
    sprint: 
    [
      [ { todo : "aaa0", flag : false } , { todo : "bbb0", flag : true }  , { todo : "ccc0", flag : true }  , { todo : "ddd0", flag : true }  , { todo : "eee0", flag : true } ],
      [ { todo : "aaa1", flag : false } , { todo : "bbb1", flag : true } ],
      [ { todo : "aaa2", flag : false } , { todo : "bbb2", flag : true } ],
      [ { todo : "aaa3", flag : false } , { todo : "bbb3", flag : true } ],
      [ { todo : "aaa4", flag : false } , { todo : "bbb4", flag : true } ],
      [ { todo : "aaa5", flag : false } , { todo : "bbb5", flag : true } ],
      [ { todo : "aaa6", flag : false } , { todo : "bbb6", flag : true } ]
		],
		comments: [
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaa"},
			{title: "nickname1" , subtitle: "content1aaaaaaaaaaaaaaaaaaaqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"}
    ],
    room: {}
  }),
  created() {
		this.roomId = this.$route.params.id
		this.setRoomId(this.roomId)
		//this.comments = this.getComments;
		let cur = this.getCurrentDay;
		this.curStep = cur.sprint;
		this.day = cur.day;
    this.sprint = this.room.sprint[curStep];
    
  },
  methods: {
		...mapActions({
			setRoomId: 'room/setRoomId'
		}),
		toggleTodo(day, idx){
			this.sprint[day][idx].flag = !this.sprint[day][idx].flag
			//axios todo 한걸로 체크
		},
		changeSprint(n) {
			this.curStep = n;
			this.day = 0;
			this.sprint = this.room.sprint[n-1];
		},
		sendMessage() {
			//axios

			let elem = document.getElementById('scroll-content')
			let container = document.getElementById('scroll-target')
			container.scrollTop = Math.floor(elem.offsetHeight);
			this.clearMessage();
		},
		clearMessage() {
			this.message ='';
		}
  },
  computed: {
		...mapGetters({
			getRoomId: 'room/getRoomId'
		}),
		sprintCompleted() {
			let sum = 0;
			let cnt = 0;
			this.sprint.forEach(element => {
				element.forEach(element => {
					sum++;
					if(element.flag)
						cnt++;
				});
			});
			return cnt/sum * 100;
		},
		entireCompleted() {
			//axios 현재 Room의 전체 진행도
			return 50;
		},
		getCurrentDay() {
			let cur = {
				day: 0,
				sprint: 0
			}
			// 날짜 계산
			return cur;
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
}
</style>