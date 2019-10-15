<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="5" xs="10">
        <auth :title="title" v-if="authCompleted == false"></auth>
        <template v-if="authCompleted">
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
                <small class="text-success" v-if="password === rePassword">비밀번호가 일치합니다.</small>
                <small class="text-warning" v-else>비밀번호가 일치하지 않습니다.</small>
              </v-flex>
              <v-btn block class="backivory">확인</v-btn>
            </v-card-text>
            <v-card-actions class="text-xs-center">
              <v-btn color="error" nuxt to="/">홈으로</v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import auth from '~/components/Authentication.vue'

export default {
  layout: 'login',
  components: {
    auth
  },
  data() {
    return {
      title: 'Email Authentification',
      message: '',
      messageType: '',
      authCompleted: true,
      password: '',
      rePassword: '',
      passwordShow: false,
      rePasswordShow: false,
      rules: {
        password: (v) =>
          /^(?=.*[a-z])(?=.*\d)(?=.*(_|[^\w])).+$/.test(v || '') ||
          '비밀번호를 작성하여주세요. 비밀번호는 영문, 숫자, 특수문자를 포함하여야 합니다.',
        minLength: (len) => (v) =>
          (v || '').length >= len || `해당 내용은 ${len}자를 넘어야 합니다.`,
        maxLength: (len) => (v) =>
          (v || '').length <= len || `해당 내용은 ${len}자를 넘을 수 없습니다.`
      }
    }
  }
}
</script>

<style src="assets/color.css"></style>