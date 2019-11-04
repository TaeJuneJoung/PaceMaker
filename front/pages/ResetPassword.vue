<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="5" xs="10">
        <authentication :title="title" v-if="isAuthCompleted == false"></authentication>
        <template v-if="showResetCard">
          <v-card class="pa-2 text-center translate">
            <v-flex class="ivory ma-2 headline">Reset Password</v-flex>
            <v-card-text>
              <v-form ref="form">
                <v-flex class="ivory text-left">비밀번호</v-flex>
                <v-text-field
                  v-model="password"
                  :type="passwordShow ? 'text' : 'password'"
                  :rules="[rules.password, rules.minLength(8), rules.maxLength(20)]"
                  label="Password"
                  :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
                  @click:append="passwordShow = !passwordShow"
                  solo
                  required
                ></v-text-field>
                <v-flex class="ivory text-left">비밀번호 확인</v-flex>
                <v-text-field
                  v-model="rePassword"
                  :type="rePasswordShow ? 'text' : 'password'"
                  :rules="[rules.password, rules.minLength(8), rules.maxLength(20)]"
                  label="Confirm Password"
                  :append-icon="rePasswordShow ? 'mdi-eye' : 'mdi-eye-off'"
                  @click:append="rePasswordShow = !rePasswordShow"
                  solo
                  required
                ></v-text-field>
              </v-form>
              <v-flex class="mb-3" v-if="password.length >= 8 && rePassword.length >= 8">
                <small class="text-success" v-if="isValidPassword">비밀번호가 일치합니다.</small>
                <small class="text-warning" v-else>비밀번호가 일치하지 않습니다.</small>
              </v-flex>
              <v-btn
                block
                class="backivory"
                :disabled="isValidPassword == false"
                @click="updatePassword"
              >변경</v-btn>
            </v-card-text>
          </v-card>
        </template>
        <template v-if="showResultCard">
          <v-card class="pa-2 text-center translate">
            <v-flex class="ivory pa-2">비밀번호 재설정 완료</v-flex>
            <v-flex class="ivory pa-2">다시 로그인해 주세요.</v-flex>
            <v-card-actions class="text-xs-center">
              <v-btn class="backivory" block nuxt to="/">로그인 페이지로</v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import Authentication from '~/components/Authentication.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  layout: 'login',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '비밀번호 재설정 | %s'
    }
  },
  components: {
    Authentication
  },
  data() {
    return {
      title: 'Email Authentification',
      resultCard: false,
      message: '',
      messageType: '',
      password: '',
      rePassword: '',
      passwordShow: false,
      rePasswordShow: false,
      rules: {
        password: (v) =>
          /^(?=.*[a-z])(?=.*\d)(?=.*(_|[^\w])).+$/.test(v || '') ||
          '비밀번호를 입력해주세요. 비밀번호는 영문, 숫자, 특수문자를 포함하여야 합니다.',
        minLength: (len) => (v) =>
          (v || '').length >= len || `해당 내용은 ${len}자를 넘어야 합니다.`,
        maxLength: (len) => (v) =>
          (v || '').length <= len || `해당 내용은 ${len}자를 넘을 수 없습니다.`
      }
    }
  },
  computed: {
    ...mapGetters(['isAuthCompleted']),
    isValidPassword() {
      if (this.password === '') return false

      return this.password === this.rePassword
    },
    showResetCard() {
      if (this.isAuthCompleted == true && this.resultCard == false) return true

      return false
    },
    showResultCard() {
      if (this.isAuthCompleted == true && this.resultCard == true) return true

      return false
    }
  },
  methods: {
    ...mapActions(['authInit']),
    updatePassword() {
      // 비밀번호 유효성 검사 완료
      // 비밀번호 재설정 진행
      console.log('OK' + this.isAuthCompleted)
      this.resultCard = true
    }
  },
  created() {
    this.authInit()
  }
}
</script>
