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
                  type="password"
                  :rules="passRules"
                  label="Password"
                  solo
                  required
                ></v-text-field>
                <v-flex class="ivory text-left">비밀번호 확인</v-flex>
                <v-text-field
                  v-model="repassword"
                  type="password"
                  :rules="repassRules"
                  label="Password"
                  solo
                  required
                ></v-text-field>
              </v-form>
              <v-flex class="ivory">{{message}}</v-flex>
              <v-btn block class="backivory" @click="validate">확인</v-btn>
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
  data:() => ({
    title: 'Email Authentification',
    message: '',
    authCompleted: true,
    password: '',
    passRules: [
      (v) => !!v || 'Password를 입력해 주세요.',
      (v) => (v && v.length >= 8) || '비밀번호는 최소 8자리입니다.'
    ],
    repassword: '',
    repassRules: [

    ]
  }),
  methods: {
    validate() {
      if(this.$refs.form.validate()) {
        // 비밀번호 유효성 검사 완료
        // 비밀번호 재설정 진행
      }
    }
  }
}
</script>

<style src="assets/color.css"></style>