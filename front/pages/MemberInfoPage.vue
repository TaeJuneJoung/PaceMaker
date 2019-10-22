<template>
  <v-container class="bgimg" justify-center align-center fill-height="true">
    <v-row>
      <v-col sm="4" cols="12">
        <v-card class="pa-2 translate" outlined height="550">
          <v-list-item class="grow justify-center">
            <v-list-item-avatar color="grey darken-3" tile size="150">
            <v-img
              class="elevation-6" 
              src="http://image.itdonga.com/files/2018/09/07/004.png"
            ></v-img>
            </v-list-item-avatar>
          </v-list-item>
          <v-list-item class="justify-center">
              {{nickname}}
          </v-list-item>
          <v-list-item class="justify-center">
              Point: {{userpoint}}
          </v-list-item>
        </v-card>
      </v-col>
      <v-col sm="8" cols="12">
        <v-card class="pa-2 translate" outlined height="550">
          <v-row justify="end">
           <v-btn color=primary nuxt to="/MemberUpdatePage">회원 수정</v-btn>
           <v-btn class="mx-4" color=primary nuxt to="/*">회원 탈퇴</v-btn>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
  layout: 'default',
   components: {
     
  },
  data() {
    return{
      nickname: '',
      userpoint: 0
    }
  },
  created(){
    this.nickname = this.$store.state.user.nickName
    this.userpoint = this.$store.state.user.userPoint
  },
  methods: {
    
  }
}
</script>
<style scoped>
.bgimg{
  background-image: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url("../static/bgimg3.jpg");
  background-size: cover;
}
</style>
