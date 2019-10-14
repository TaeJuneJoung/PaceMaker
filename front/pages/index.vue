<template>
  <v-container justify-center align-center fill-height="true">
    <v-row justify="center" align="center">
      <v-col sm="5" xs="10">
        <v-card raised class="pa-2 text-center" color="rgba(0,0,0,0.4)">
          <v-flex class="headline ma-2">Member login</v-flex>
          <v-icon x-large color="rgb(248, 246, 207)" class="display-4"
            >mdi-account-supervisor</v-icon
          >
          <v-card-text>
            <v-form ref="form">
              <v-text-field
                v-model="email"
                :rules="emailRules"
                label="E-mail"
                solo
                required
              ></v-text-field>

              <v-text-field
                v-model="password"
                :rules="passRules"
                label="Password"
                solo
                required
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions class="text-xs-center">
            <v-btn block color="rgb(248, 246, 207)" @click="validate"
              >Login</v-btn
            >
          </v-card-actions>
          <v-btn color="rgb(248, 246, 207)" text nuxt to>회원 가입</v-btn>
          <v-btn color="rgb(248, 246, 207)" text nuxt to>ID/PW 찾기</v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  layout: 'login',
  data: () => ({
    email: '',
    emailRules: [
      (v) => !!v || 'E-mail is required',
      (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid'
    ],
    password: '',
    passRules: [
      (v) => !!v || 'Password is required',
      (v) => (v && v.length >= 8) || 'Password must be more than 8 characters'
    ]
  }),

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true
      }
    }
  }
}
</script>

<style scoped>
.headline {
  color: rgb(248, 246, 207);
}
</style>
