<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="6" xs="10">
        <v-card class="pa-2 text-center translate">
          <v-flex class="ivory ma-2 headline">Join</v-flex>
          <v-card-text>
            <v-form ref="joinform" v-model="valid" method="POST">
              <v-flex class="ivory text-left">Email</v-flex>
              <v-text-field
                v-model="email"
                :counter="30"
                :rules="[rules.email]"
                solo
                label="E-mail"
                required
                @blur="emailCheck"
                :readonly="emailReadOnly"
              >
                <template v-slot:append>
                  <v-btn text class="text-cursor" color="grey" v-if="isOnlyEmail==0">결과</v-btn>
                  <v-row class="pr-1" v-else-if="isOnlyEmail==1">
                    <v-icon class="check-btn" color="success">mdi-check</v-icon>
                    <v-icon color="grey" @click="emailCheckCancel">mdi-close-circle-outline</v-icon>
                  </v-row>
                  <v-btn text class="text-cursor" color="warning" v-else>사용불가</v-btn>
                </template>
              </v-text-field>
              <v-flex class="ivory text-left">Nickname</v-flex>
              <v-text-field
                v-model="nickname"
                :counter="15"
                :rules="[rules.minLength(1), rules.maxLength(15)]"
                solo
                label="Nickname"
                required
                @blur="nicknameCheck"
                :readonly="nicknameReadOnly"
              >
                <template v-slot:append>
                  <v-btn text color="grey" class="text-cursor" v-if="isOnlyNickname==0">결과</v-btn>
                  <v-row class="pr-1" v-else-if="isOnlyNickname==1">
                    <v-icon class="check-btn" color="success">mdi-check</v-icon>
                    <v-icon color="grey" @click="nicknameCheckCancel">mdi-close-circle-outline</v-icon>
                  </v-row>
                  <v-btn text color="warning" class="text-cursor" v-else>사용불가</v-btn>
                </template>
              </v-text-field>
              <v-flex class="ivory text-left">Password</v-flex>
              <v-text-field
                v-model="password"
                :counter="20"
                :rules="[rules.password, rules.minLength(8), rules.maxLength(20)]"
                solo
                label="Password"
                required
                :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
                :type="passwordShow ? 'text' : 'password'"
                @click:append="passwordShow = !passwordShow"
              ></v-text-field>
              <v-flex class="ivory text-left">Confirm Password</v-flex>
              <v-text-field
                v-model="rePassword"
                :counter="20"
                :rules="[rules.password, rules.minLength(8), rules.maxLength(20)]"
                solo
                label="Confirm Password"
                required
                :append-icon="rePasswordShow ? 'mdi-eye' : 'mdi-eye-off'"
                :type="rePasswordShow ? 'text' : 'password'"
                @click:append="rePasswordShow = !rePasswordShow"
              ></v-text-field>
              <v-flex v-if="password.length >= 8 && rePassword.length >= 8">
                <small class="success--text" v-if="password === rePassword">비밀번호가 일치합니다.</small>
                <small class="warning--text" v-else>비밀번호가 일치하지 않습니다.</small>
              </v-flex>
              <v-checkbox v-model="checkboxAlarm" color="success" dark>
                <template v-slot:label>
                  <v-flex class="ivory">PaceMaker에 대한 알림을 받는 것에 동의합니다.</v-flex>
                </template>
              </v-checkbox>
              <v-checkbox
                v-model="checkboxAgree"
                color="success"
                required
                :rules="[rules.required]"
                dark
              >
                <template v-slot:label>
                  <terms-of-service></terms-of-service>
                </template>
              </v-checkbox>
            </v-form>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn class="joinBtn" color="error" nuxt to="/">홈으로</v-btn>
            <v-btn class="joinBtn" color="success" @click="joinValidate">회원가입</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TermsOfService from '~/components/TermsOfService.vue'
import {
  getCheckEmail,
  getCheckNickname,
  sendUserMail,
  createUser
} from '../api/index.js'
import { createAchieve } from '../api/achieve.js'
import { getAchieve, putAchieve } from '../api/achieve.js'

export default {
  layout: 'login',
  middleware: 'guest',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '회원가입 | %s'
    }
  },
  components: {
    TermsOfService
  },
  data() {
    return {
      valid: true,
      email: '',
      isEmail: false,
      isOnlyEmail: 0,
      emailReadOnly: false,
      nickname: '',
      isOnlyNickname: 0,
      nicknameReadOnly: false,
      checkboxAlram: false,
      password: '',
      rePassword: '',
      isAgreeEmail: false,
      passwordShow: false,
      rePasswordShow: false,
      checkboxAlarm: false,
      checkboxAgree: false,
      rules: {
        email: (v) => /.+@.+\..+/.test(v) || '유효한 E-mail을 입력해 주세요.',
        password: (v) =>
          /^(?=.*[a-z])(?=.*\d)(?=.*(_|[^\w])).+$/.test(v || '') ||
          '비밀번호를 작성하여주세요. 비밀번호는 영문, 숫자, 특수문자를 포함하여야 합니다.',
        minLength: (len) => (v) =>
          (v || '').length >= len || `해당 내용은 ${len}자를 넘어야 합니다.`,
        maxLength: (len) => (v) =>
          (v || '').length <= len || `해당 내용은 ${len}자를 넘을 수 없습니다.`,
        required: (v) => !!v || '약관에 동의해주세요.'
      }
    }
  },
  methods: {
    async joinValidate() {
      if (this.$refs.joinform.validate()) {
        let data = {
          email: this.email,
          nickname: this.nickname,
          password: this.password,
          authenticationFlag: this.checkboxAgree,
          alarmFlag: this.checkboxAlarm
        }
				let email = this.email
        email = email.replace('@', '%40')
        await sendUserMail(email)
        let user = await createUser(data)
        await createAchieve(user.data.id)
          .then(({ data }) => {
            alert('가입이 완료되었습니다.')
            this.$router.push('/')
          })
          .catch((error) => {
            console.error(error)
          })
      }
    },
    emailCheck() {
      let email = this.email
      this.isEmail = /.+@.+\..+/.test(email)
      if (this.isEmail) {
        email = email.replace('@', '%40')
        getCheckEmail(email)
          .then(({ data }) => {
            if (data) {
              this.isOnlyEmail = 1
              this.emailReadOnly = data
            } else {
              this.isOnlyEmail = 2
            }
          })
          .catch((error) => {
            console.error(error)
          })
      }
    },
    emailCheckCancel() {
      this.email = ''
      this.isOnlyEmail = 0
      this.emailReadOnly = false
    },
    nicknameCheck() {
      let nickname = this.nickname
      if (nickname.length > 0) {
        getCheckNickname(nickname)
          .then(({ data }) => {
            if (data) {
              this.isOnlyNickname = 1
              this.nicknameReadOnly = data
            } else {
              this.isOnlyNickname = 2
            }
          })
          .catch((error) => {
            console.error(error)
          })
      }
    },
    nicknameCheckCancel() {
      this.nickname = ''
      this.isOnlyNickname = 0
      this.nicknameReadOnly = false
    }
  }
}
</script>

<style scoped>
.check-btn {
  z-index: 3;
}
.text-cursor {
  cursor: text;
}
</style>