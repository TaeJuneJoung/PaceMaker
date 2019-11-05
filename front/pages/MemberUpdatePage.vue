<template>
  <v-container>
    <v-row justify="center" align="center">
      <v-col sm="6" xs="10">
        <v-card class="pa-2 text-center translate">
          <v-flex class="ivory ma-2 headline">Member Update</v-flex>
          <v-card-text>
            <v-form ref="updateform" v-model="valid">
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
              <v-checkbox v-model="checkboxAlarm" color="success" dark>
                <template v-slot:label>
                  <v-flex class="ivory">PaceMaker에 대한 알림허용에 동의합니다.</v-flex>
                </template>
              </v-checkbox>

              <v-file-input
                :rules="[rules.profile]"
                accept="image/*"
                @change="setImg"
                placeholder="Pick an avatar"
                prepend-icon="mdi-camera"
                label="Avatar"
                dark
              ></v-file-input>
            </v-form>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn class="ma-4" color="success" @click="memberUpdate">회원 수정</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { putUser, updatePass, getCheckNickname } from '../api/index.js'
import FormData from 'form-data'
import { addImg } from '../api/roomAdd.js'

export default {
  layout: 'default',
  middleware: 'auth',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '회원수정 | %s'
    }
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
      checkboxAlarm: false,
      checkboxAgree: false,
      isOnlyNickname: false,
      nicknameReadOnly: false,
      img: '',
      beforeImg: '',
      point: 0,
      id: 0,
      rules: {
        minLength: (len) => (v) =>
          (v || '').length >= len || `해당 내용은 ${len}자를 넘어야 합니다.`,
        maxLength: (len) => (v) =>
          (v || '').length <= len || `해당 내용은 ${len}자를 넘을 수 없습니다.`,
        profile: (value) =>
          !value ||
          value.size < 2000000 ||
          'Avatar size should be less than 2 MB!'
      }
    }
  },
  created() {
    this.memberUpdate
  },
  mounted() {
    this.email = this.$session.get('account').email
    this.nickname = this.$session.get('account').nickname
    this.checkboxAlarm = this.$session.get('account').alarmFlag
    this.point = this.$session.get('account').point
    this.id = this.$session.get('account').id
    this.img = this.$session.get('account').img
    this.beforeImg = this.$session.get('account').img
  },
  methods: {
    modalOn(header, body, img) {
      this.$store.commit('modal/setModalData', {
        header: header,
        body: body,
        img: img
      })
      this.$store.commit('achievement/setShowModal', true)
    },
    setImg(e) {
      this.beforeImg = this.img
      if (e) {
        this.img = e
      } else {
        this.img = this.beforeImg
      }
    },
    async memberUpdate() {
      if (this.$refs.updateform) {
        let img = new FormData()
        let imgName = null
        if (this.img === this.beforeImg) {
          console.log(this.img)
          imgName = this.img
        } else if (this.img !== '') {
          img.append('file', this.img, this.img.fileName)
          await addImg(img).then(({ data }) => {
            imgName = '/images/' + data.fileName
          })
        }
        const userData = {
          email: this.email,
          nickname: this.nickname,
          img: imgName,
          alarmFlag: this.checkboxAlarm,
          point: this.point,
          id: this.id
        }
        putUser(userData)
          .then((res) => {
            let isUpdatePass = false
            if (this.password != '' && this.rePassword != '') {
              if (
                this.password === this.rePassword &&
                this.password.length >= 8 &&
                /^(?=.*[a-z])(?=.*\d)(?=.*(_|[^\w])).+$/.test(this.password)
              ) {
                const user = { email: this.email, password: this.password }
                updatePass(user)
                  .then((res) => {
                    this.modalOn('회원 수정', '회원정보가 수정되었습니다.', '')
                    this.$session.set('account', userData)
                    this.$router.push('/MemberInfoPage')
                  })
                  .catch((error) => {
                    this.modalOn(
                      '회원 수정',
                      '회원정보가 수정이 실패하였습니다.',
                      ''
                    )
                  })
              } else {
                this.modalOn(
                  '회원 수정',
                  '비밀번호는 영문, 숫자, \n특수문자를 포함하여야 합니다.',
                  ''
                )
              }
            } else if (this.password != '') {
              this.modalOn('주의', '비밀번호 확인도 작성하여주세요', '')
            } else if (this.rePassword != '') {
              this.modalOn('주의', '비밀번호를 작성하여주세요', '')
            } else {
              isUpdatePass = true
              this.modalOn('회원 수정', '회원정보가 수정되었습니다.', '')
            }
            if (isUpdatePass) {
              this.$session.set('account', userData)
              this.$router.push('/MemberInfoPage')
            }
          })
          .catch((err) => {
            this.modalOn('회원 수정', '회원정보가 수정이 실패하였습니다.', '')
          })
      }
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
