<template>
  <v-app dark>
    <v-navigation-drawer v-model="drawer" clipped fixed app>
      <v-container>
        <v-flex class=headline ma-3>
          <v-icon icon>mdi-run</v-icon>
          {{ title }}
        </v-flex>
        <v-btn text nuxt to="/MemberInfoPage">마이 페이지</v-btn>
        <v-btn text @click="logout">로그아웃</v-btn>
        <v-flex class="ma-2">
          <v-avatar color="grey">
            <v-icon v-if="getUserIcon" dark>{{getUserIcon}}</v-icon>
          </v-avatar>
          <span class="ml-2" v-if="user">{{ user.nickname }}</span>
        </v-flex>
      </v-container>
      <v-list>
        <v-list-item v-for="(item, i) in items" :key="i" :to="item.to" router exact>
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title v-text="item.title" />
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-content class="mx-6">
      <nuxt />
    </v-content>
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  data() {
    return {
      user: '',
      drawer: null,
      items: [
        {
          icon: 'mdi-home',
          title: 'Home',
          to: '/MainPage'
        },
        {
          icon: 'mdi-apps',
          title: '방 생성',
          to: '/RoomAdd'
        },
      ],
      title: 'PaceMaker'
    }
  },
  mounted() {
    this.user = this.$session.get("account")
  },
  computed: {
    ...mapGetters({
      getUserEmail: 'user/getUserEmail',
      getUserName: 'user/getUserName',
      getUserIcon: 'user/getUserIcon'
    }),
  },
  methods: {
    logout() {
      this.$session.remove('account')
      this.$store.state.user.user = ''
      this.$router.redirect('/')
    }
  }
}
</script>

<style src="../assets/color.css"></style>
