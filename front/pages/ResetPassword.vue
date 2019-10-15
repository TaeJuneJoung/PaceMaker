<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="5" xs="10">
        <auth></auth>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import auth from '../components/Authentication.vue'

export default {
  layout: 'login',
  components: {
    auth
  },
  data:() => ({
    title: '',
    sendNum: false,
    disableNum: false,
    email: '',
    timer: null,
    totalTime: 10,
    emailRules: [
      (v) => !!v || 'E-mail을 입력해 주세요.',
      (v) => /.+@.+\..+/.test(v) || '유효한 E-mail을 입력해 주세요'
    ],
    verifyNum: '',
    verifyNumRules: [
      (v) => !!v || '인증번호를 입력해 주세요',
      (v) => (v && v.length == 8) || '인증번호는 8자리입니다.'
    ],
    message: ''
  }),
  methods: {
    emailValidate() {
      if(this.$refs.emailform.validate()) {
        // 이메일 유호함
        // 이메일이 등록된 회원인지 확인후 이메일로 인증번호 전송
        if(!this.sendNum){
          this.sendNum = true;
          this.startTimer();
        } else {
          this.resetTimer();
          this.startTimer();
        }
      }
    },
    numValidate() {
      if(tis.$refs.verifyform.validate()) {
        // 인증번호 유효함
        // 인증번호 맞는지 확인후 비밀번호 재설정 진행
      }
    },
    startTimer() {
      this.timer = setInterval(() => this.countdown(), 1000);
    },
    resetTimer() {
      this.totalTime = 180;
      clearInterval(this.timer);
      this.timer = null;
    },
    padTime(time) {
      return (time < 10 ? '0' : '') + time;
    },
    countdown() {
      if(this.totalTime >= 1){
        this.totalTime--;
      } else {
        this.totalTime = 0;
        this.message = '요청시간 초과';
        this.disableNum = true;
      }
    }
  },
  computed: {
    getMinute() {
      const minutes = Math.floor(this.totalTime / 60);
      return this.padTime(minutes);
    },
    getSecond() {
      const seconds = this.totalTime % 60;
      return this.padTime(seconds);
    }
  }

}
</script>

<style src="assets/color.css"></style>