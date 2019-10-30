<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="5" xs="10">
        <v-card raised class="pa-2 text-center translate member-text-bold">
          <v-flex class="ivory ma-2 headline">Member login</v-flex>
          <v-icon x-large class="d-none d-sm-flex display-4 ivory">mdi-account-supervisor</v-icon>
          <v-card-text>
            <v-form ref="form">
              <v-flex class="ivory text-left">Email</v-flex>
              <v-text-field
                v-model="email"
                :rules="[rules.email]"
                label="E-mail"
                solo
                required
                @keyup.enter="validate"
                @focus="focusField"
              ></v-text-field>
              <v-flex class="ivory text-left">Password</v-flex>
              <v-text-field
                v-model="password"
                type="password"
                :rules="[rules.password]"
                label="Password"
                solo
                required
                @keyup.enter="validate"
                @focus="focusField"
              ></v-text-field>
            </v-form>
            <v-flex class="ivory" v-if="isFocus">{{message}}
              <v-btn class="pb-1" text color="error" @click="reAuthentication" v-if="emailAuthentication">
                {{ authMessage  }}
              </v-btn>
            </v-flex>
          </v-card-text>
          <v-card-actions class="text-xs-center">
            <v-btn block class="backivory" @click="validate">Login</v-btn>
          </v-card-actions>
          <v-btn class="ivory" text nuxt to="/MemberJoin">회원 가입</v-btn>
          <v-btn class="ivory" text nuxt to="/ResetPassword">PW 분실</v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { loginUser, sendUserMail } from '../api/index.js'

export default {
  layout: 'login',
  middleware: 'guest',
  head () {
    return {
      title: 'PaceMaker',
      titleTemplate: '로그인 | %s',
    }
  },
  data: () => ({
    email: '',
    password: '',
    emailAuthentication: false,
    isFocus: false,
    isAuth: false,
    authMessage: '인증 재요청',
    rules: {
      email: (v) => /.+@.+\..+/.test(v) || '유효한 E-mail을 입력해 주세요',
      password: (v) => !!v || 'Password를 입력해 주세요.'
    },
    message: ''
  }),
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        const loginData = {'email': this.email, 'password': this.password}   
        loginUser(loginData)
          .then(({data}) => {
            this.isAuth = true
            if (data.authenticationFlag === false){
              this.isFocus = true
              this.message = "이메일 인증이 필요한 아이디입니다."
              this.emailAuthentication = true
            } else if(data.activateFlag === false) {
              this.isFocus = true
              this.message = "비활성화된 아이디입니다."
            } else if (data) {
              this.$session.start()
              this.$session.set('account',{'email':data.email,'nickname':data.nickname,'img':data.img,'point':data.point,'alarmFlag':data.alarmFlag})
              this.$storage.setUniversal('isAuth', true)
              this.$router.push('/MainPage')
            } else {
              this.isFocus = true
              this.message = "Email이나 비밀번호가 맞지 않습니다."
            }
          })
          .catch(error => {
            console.error(error)
          })
      }
    },
    focusField() {
      this.isFocus = false
      this.isAuth = false
    },
    reAuthentication() {
      if (this.isAuth) {
        this.authMessage = 'Loading'
        sendUserMail(this.email)
          .then((response) => {
            this.authMessage = '인증 재요청'
            alert('메일이 발송되었습니다.')
          })
      }
    }
  }
}
</script>

<style scoped>
.v-messages__wrapper {
  font-weight: bold;
}
</style>
