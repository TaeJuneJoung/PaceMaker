<template>
  <v-card class="pa-2 text-center translate">
    <v-flex class="ivory ma-2 headline">{{title}}</v-flex>
    <v-card-text>
      <v-form ref="emailform">
        <v-flex class="ivory text-left">Email</v-flex>
        <v-text-field v-model="email" :rules="emailRules" label="E-mail" solo required></v-text-field>
      </v-form>
      <v-flex class="ivory">{{message}}</v-flex>
      <v-btn block class="backivory" @click="emailValidate">인증번호 전송</v-btn>
      <template v-if="sendNum">
        <v-form ref="verifyform" class="mt-4">
          <v-flex class="ivory text-left">인증번호 {{getMinute}} : {{getSecond}}</v-flex>
          <v-text-field
            v-model="authNum"
            :rules="authNumRules"
            :disabled="disableNum"
            label="인증번호"
            solo
            required
          ></v-text-field>
        </v-form>
        <v-btn block class="backivory" @click="numValidate">확인</v-btn>
      </template>
    </v-card-text>
    <v-card-actions class="text-xs-center">
			<v-btn color="error" nuxt to="/">홈으로</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	props: {
		title: String
	},
  data: () => ({
    sendNum: false,
    disableNum: false,
    timer: null,
		totalTime: 180,
		email: '',
    emailRules: [
      (v) => !!v || 'E-mail을 입력해 주세요.',
      (v) => /.+@.+\..+/.test(v) || '유효한 E-mail을 입력해 주세요'
    ],
    authNum: '',
    authNumRules: [
      (v) => !!v || '인증번호를 입력해 주세요',
      (v) => (v && v.length == 8) || '인증번호는 8자리입니다.'
    ],
    message: ''
  }),
  methods: {
    ...mapActions([
      'authInit',
      'authComplete'
    ]),
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
      if(this.$refs.verifyform.validate()) {
        // 인증번호 유효함
        // 인증번호 맞는지 확인후 비밀번호 재설정 진행
        this.authComplete();
        console.log(this.$store.state.authCompleted);
        clearInterval(this.timer);
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
      if(this.totalTime >= 1) {
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