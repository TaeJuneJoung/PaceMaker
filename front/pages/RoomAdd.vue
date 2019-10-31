<template>
  <v-container fluid>
    <v-row>
      <v-col sm="12" cols="12">
        <v-row class="bgimg">
          <v-container justify="center" align="center">
            <v-card class="pa-2 text-center cardSetting">
              <v-col align="center" class="cardTitle">방 생성</v-col>
              <v-card-text>
                <v-form ref="roomAddform" v-model="valid">
                  <v-row cols="12">
                    <v-text-field
                      v-model="title"
                      :counter="20"
                      :rules="[rules.minLength(1), rules.maxLength(20)]"
                      solo
                      label="목표"
                      required
                    ></v-text-field>
                  </v-row>
                  <v-row cols="12">
                    <v-select v-model="select" :items="items" label="공개/비공개"></v-select>
                  </v-row>
                  <v-row cols="12">
                    <v-file-input label="이미지" accept="image/*" @change="setImg"></v-file-input>
                  </v-row>
                  <v-row>
                    <v-col md="12">
                      <p>Sprint</p>
                      <v-btn-toggle mandatory>
                        <v-btn value="1" @click="sprintCnt(1)" class="sprintBtn">1</v-btn>
                        <v-btn value="4" @click="sprintCnt(4)" class="sprintBtn">4</v-btn>
                      </v-btn-toggle>
                    </v-col>
                  </v-row>
                  <SprintComp :propsdata="todoItems"></SprintComp>
                </v-form>
              </v-card-text>
              <v-card-actions class="justify-end">
                <v-btn class="ma-4" color="success" @click="send">방 생성</v-btn>
              </v-card-actions>
            </v-card>
          </v-container>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SprintComp from '~/components/Sprint.vue';
import FormData from 'form-data'
import { createRoom } from '../api/roomAdd.js'
import { addImg } from '../api/roomAdd.js'

export default {
    ayout: 'default',
    data() {
        return {
            model: '',
            valid: true,
            title: '',
            select: true,
            img : '',
            items: [
                {text:'공개',value:true},
                {text:'비공개',value:false}
            ],
            todoItems:this.$store.state.roomAdd.room.sprint,
            rules: {
            minLength: (len) => (v) =>
                (v || '').length >= len || `해당 내용은 ${len}자를 넘어야 합니다.`,
            maxLength: (len) => (v) =>
                (v || '').length <= len || `해당 내용은 ${len}자를 넘을 수 없습니다.`,
            }
        }
    },
    components: {
        SprintComp
    },
    methods: {
      setImg(e){
        this.img = e;
      },
      sprintCnt(cnt){
        const room = {sprint:[]};
        for(let i=0;i<cnt;i++){
          room.sprint.push(
            [
              [{ todo: '', flag: false }],
              [{ todo: '', flag: false }],
              [{ todo: '', flag: false }],
              [{ todo: '', flag: false }],
              [{ todo: '', flag: false }],
              [{ todo: '', flag: false }],
              [{ todo: '', flag: false }]
            ]
          )
        }
        this.$store.commit('roomAdd/setRoom',room);
        this.todoItems = this.$store.state.roomAdd.room.sprint;
      },
      async send(){
        const room = {title:"",public:true,sprint:this.$store.state.roomAdd.room.sprint};
        room.title = this.title;
        room.public = this.select;
        this.$store.commit('roomAdd/setRoom',room);
        const roomJson = this.$store.state.roomAdd.room;
        let img = new FormData();
        let imgName = '';
        img.append('file', this.img, this.img.fileName);
        await addImg(img).then(({data}) => {
          imgName = data.fileName;
        });
        createRoom({"email": this.$session.get('account').email,"roomData": JSON.stringify(roomJson), "img":"./image/"+imgName}).then(({data}) => {
          if(data){
            alert("등록 완료");
          }else{
            alert("등록 실패");
          }
        }).catch(error => {
          console.error(error)
        });
      }
    }
}
</script>

<style scoped>
.bgimg {
  background-image: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)),
    url('../static/back.jpg');
  background-size: cover;
}
.col-sm-6,
.col-lg-4,
.col-12 {
  padding: 0;
}
.container {
  padding: 0;
}
.cardSetting {
  background: rgba(255, 255, 255, 0.9);
  margin: 10vh auto;
  min-height: 80vh;
  max-width: 700px;
}
.cardTitle {
  font-size: 1.5rem;
  font-weight: bold;
}
.v-form .sprintBtn {
  width: 150px;
  color: #5a0808;
  background-color: #ffbbbb !important;
}
@media (max-width: 800px) {
  .cardSetting {
    margin: 10vh 20px;
  }
}
</style>



