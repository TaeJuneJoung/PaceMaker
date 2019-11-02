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
import { getAchieve, putAchieve } from '../api/achieve.js'
import { createRoom, addImg } from '../api/roomAdd.js'

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
        let imgName = null;
        if(this.img!==''){
          img.append('file', this.img, this.img.fileName);
          await addImg(img).then(({data}) => {
            imgName = "/images/"+data.fileName;
          });
        }

        let achieveData = {};
        await getAchieve(this.$session.get("account").id).then(({data}) => {
          achieveData = data;
        });
        achieveData.modelRoom += 1;
        await putAchieve(achieveData);
        
        
        await createRoom({"email": this.$session.get('account').email,"roomData": JSON.stringify(roomJson), "img":imgName}).then(({data}) => {
          if(data){ 
            this.$store.commit('modal/setModalData',{header:"등록 성공",body:"방이 생성되었습니다.",img:""});
            this.$store.commit('achievement/setShowModal',true);
          }else{
            this.$store.commit('modal/setModalData',{header:"등록 실패",body:"방 생성을 실패하였습니다.",img:""});
            this.$store.commit('achievement/setShowModal',true);
          }
        }).catch(error => {
          console.error(error)
        });
        
        this.$router.push('/MainPage');

        const achieveModal = this.$store.state.achievement.makerAchieve;
        achieveModal.forEach(element => {
          if(element.number == achieveData.modelRoom){
            this.$store.commit('modal/setModalData',{header:element.name,body:"방 생성 성공!! \n 업적을 취득하였습니다.",img:element.img});
            this.$store.commit('achievement/setShowModal',true);
          }
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
.v-btn-toggle{
  width: 100%;
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
  width: 50%;
  color: #5a0808;
  background-color: #ffbbbb !important;
}
@media (max-width: 800px) {
  .cardSetting {
    margin: 10vh 20px;
  }
}
</style>



