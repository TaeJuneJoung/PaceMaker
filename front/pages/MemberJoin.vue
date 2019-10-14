<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="5" xs="10">
        <v-card class="pa-2 text-center">
          <v-flex class="headline ma-2">Join</v-flex>
          <v-card-text>
            <v-form ref="form" v-model="valid">
              <v-text-field
                v-model="email"
                :counter="30"
                :rules="emailRules"
                label="Email"
                required
              ></v-text-field>
              <v-text-field
                v-model="nickname"
                :counter="15"
                :rules="nicknameRules"
                label="Nickname"
                required
              ></v-text-field>
              <v-text-field
                v-model="password"
                :counter="20"
                :rules="passwordRules"
                label="Password"
                required
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show ? 'text' : 'password'"
                @click:append="show = !show"
                @change="checkPassword"
              ></v-text-field>
              <v-text-field
                v-model="rePassword"
                :counter="20"
                :rules="rePasswordRules"
                label="Confirm Password"
                required
                :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show2 ? 'text' : 'password'"
                @click:append="show2 = !show2"
                @change="checkPassword"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn class="ma-4" color="success">회원가입</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      valid: true,
      email: '',
      emailRules: [
        (v) => !!v || 'E-mail is required',
        (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid'
      ],
      nickname: '',
      nicknameRules: [(v) => !!v || 'NickName is required'],
      password: '',
      passwordRules: [
        (v) => !!v || 'Password is required',
        (v) =>
          (v && v.length >= 8) ||
          v.length <= 20 ||
          'Password must be more than 8 characters or less then 20 characters'
      ],
      rePassword: '',
      rePasswordRules: [
        (v) => !!v || 'Password is required',
        (v) =>
          this.password === this.rePassword || '비밀번호가 일치하지 않습니다.'
      ],
      show: false,
      show2: false,
      isPasswordOk: false
    }
  }
}
</script>

<style>
</style>
