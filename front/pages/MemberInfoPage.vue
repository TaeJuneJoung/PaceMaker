<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="12">
        <v-card class="pa-2">
          <v-row class="justify-sm-end justify-center pr-sm-4">
            <v-btn class="mr-2" color="primary" nuxt to="/MemberUpdatePage">회원 수정</v-btn>
            <v-btn class="ml-2" color="primary" nuxt to="/*">회원 탈퇴</v-btn>
          </v-row>
          <v-list-item class="grow">
            <v-row>
              <v-col cols="12" sm="4" class="text-center">
                <v-img
                  class="elevation-6 ma-auto"
                  :src="userImg || 'http://image.itdonga.com/files/2018/09/07/004.png'"
                  width="150"
                ></v-img>
              </v-col>
              <v-col cols="12" sm="8">
                <v-row>
                  <v-col cols="12" sm="4">
                    <v-icon>mdi-account</v-icon>
                    : {{nickname}}
                  </v-col>
                  <v-col cols="12" sm="8">
                    <v-icon>mdi-coin</v-icon>
                    : {{userCoin}} coin
                  </v-col>
                </v-row>
                <v-row class="achievePlace">
                  <span class="achieveEach" v-for="(n, index) in achieveValue" :key="index" @click="modalAchieve(n.name, n.img)">
                    <v-img
                      :src="`${n.img}`"
                      class="achieveImg mx-auto"
                    />
                  </span>
                </v-row>
              </v-col>
            </v-row>
          </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" sm="12">
        <v-card class="pa-2" outlined min-height="68vh" height="100%">
          <MyRoomList></MyRoomList>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MyRoomList from '../components/MyRoomList.vue'
import { mapGetters } from 'vuex'
import { getAchieve } from '../api/achieve.js'

export default {
  layout: 'default',
  components: {
    MyRoomList
  },
  data() {
    return {
      userId: 0,
      nickname: '',
      userImg: '',
      userCoin: 0,
      achieveValue: []
    }
  },
  mounted() {
    this.userId = this.$session.get('account').id
    this.nickname = this.$session.get('account').nickname
    this.getUserAchieve()
  },
  computed: {
    ...mapGetters({ coinAchieve: 'achievement/getCoinAchieve' }),
    ...mapGetters({ makerAchieve: 'achievement/getMakerAchieve' }),
    ...mapGetters({ roomAchieve: 'achievement/getRoomAchieve' }),
    ...mapGetters({ commentAchieve: 'achievement/getCommentAchieve' })
  },
  methods: {
    getUserAchieve() {
      getAchieve(this.userId)
        .then(({ data }) => {
          this.userCoin = data['coin']
          let valueList = []
          let dataList = [
            this.coinAchieve,
            this.makerAchieve,
            this.roomAchieve,
            this.commentAchieve
          ]
          const kind = ['coin', 'modelRoom', 'room', 'comment']

          for (let i = 0; i < dataList.length; i++) {
            for (let j = 0; j < dataList[i].length; j++) {
              if (dataList[i][j].number <= data[kind[i]]) {
                valueList.push({
                  name: dataList[i][j].name,
                  img: dataList[i][j].img
                })
              }
            }
          }
          this.achieveValue = valueList
        })
        .catch((error) => {
          console.error(error)
        })
    },
    modalAchieve(name, img) {
      this.$store.commit('modal/setModalData', {
        header: name,
        body: '업적을 취득하였습니다.',
        img: img
      })
      this.$store.commit('achievement/setShowModal', true)
    }
  }
}
</script>

<style>
.achieveImg {
  cursor: pointer;
  width: 20px;
  min-width: 20px;
  max-width: 20px;
  min-height: 20px;
  max-height: 20px;
  background-color: #e2e2e2;
}
.achieveEach{
  cursor: pointer;
  padding: 10px;
  background: #e2e2e2;
  border-radius: 5px;
  margin: 3px 2px;
  height: 40px;
}
</style>