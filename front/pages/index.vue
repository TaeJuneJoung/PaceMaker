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
              <v-text-field v-model="email" :rules="[rules.email]" label="E-mail" solo required @keyup.enter="validate"></v-text-field>
              <v-flex class="ivory text-left">Password</v-flex>
              <v-text-field v-model="password" type="password" :rules="[rules.password]" label="Password" solo required @keyup.enter="validate"></v-text-field>
            </v-form>
            <v-flex class="ivory">{{message}}</v-flex>
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
import { loginUser } from '../api/index.js'

export default {
  layout: 'login',
  data: () => ({
    email: '',
    password: '',
    rules: {
      email: (v) => /.+@.+\..+/.test(v) || '유효한 E-mail을 입력해 주세요',
      password: (v) => !!v || 'Password를 입력해 주세요.'
    },
    message: ''
  }),

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        let loginData = {'email': this.email, 'pass': this.password}
        loginUser(loginData)
          .then(({data}) => {
            if (data) {
              //store.user에 저장 --> session저장
              this.$store.nickName = data.email
              this.$router.push('/MainPage')
            } else {
              this.message = "Email이나 비밀번호가 맞지 않습니다."
            }
          })
          .catch(error => {
            console.error(error)
          })
      }
    }
  }
}
</script>

<style>
  .v-messages__wrapper{
    font-weight: bold;
  }
</style>
