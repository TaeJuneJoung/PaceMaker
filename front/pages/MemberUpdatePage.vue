<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="6" xs="10">
        <v-card class="pa-2 text-center translate">
          <v-flex class="ivory ma-2 headline">Member Update</v-flex>
          <v-card-text>
            <v-form ref="joinform" v-model="valid">
              <v-flex class="ivory text-left">Nickname</v-flex>
              <v-text-field
                v-model="nickname"
                :counter="15"
                :rules="[rules.minLength(1), rules.maxLength(15)]"
                solo
                label="Nickname"
                required
                @blur="nicknameCheck"
              >
                <template v-if="isOnlyNickname" v-slot:append>
                  <v-flex class="ml-1" @click="nicknameCancle">취소</v-flex>
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
                <small class="text-success" v-if="password === rePassword">비밀번호가 일치합니다.</small>
                <small class="text-warning" v-else>비밀번호가 일치하지 않습니다.</small>
              </v-flex>
              <v-checkbox v-model="checkboxEmail" color="success">
                <template v-slot:label>
                  <v-flex class="ivory">PaceMaker에 대한 알림허용에 동의합니다.</v-flex>
                </template>
              </v-checkbox>
            </v-form>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn class="ma-4" color="success" @click="joinValidate">회원 수정</v-btn>
          </v-card-actions>
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
    return {
      valid: true,
      email: '',
      nickname: '',
      password: '',
      rePassword: '',
      passwordShow: false,
      rePasswordShow: false,
      checkboxEmail: false,
      checkboxAgree: false,
      isOnlyEmail: false,
      emailReadOnly: false,
      isOnlyNickname: false,
      nicknameReadOnly: false,
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
    joinValidate() {
      if (this.$refs.joinform.validate()) {
        // 계정 생성
      }
    },
    nicknameCheck() {
      // emailCheck와 로직 동일
    },
    nicknameCancle() {
      this.nickname = ''
      this.isOnlyNickname = false
      this.nicknameReadOnly = false
    }
  }
}
</script>
