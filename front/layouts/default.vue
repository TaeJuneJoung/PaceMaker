<template>
  <v-app dark>
    <v-btn class="menu-icon" fab color="white" v-if="!drawer" @click="menuBtn">
      <v-icon>mdi-format-list-bulleted-square</v-icon>
    </v-btn>
    <v-navigation-drawer v-model="drawer" clipped fixed app>
      <v-container>
        <v-flex class=headline ma-3>
          <nuxt-link to="/">
            <v-icon icon>mdi-run</v-icon>
            {{ title }}
          </nuxt-link>
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
      <Modal v-if="this.$store.state.achievement.showModal" @close="modalClose">
        <h3 slot="header">
          {{this.$store.state.modal.header}}
          <span class="fas fa-times closeModalBtn" @click="modalClose">X</span>
        </h3>
        <div slot="body">
          {{this.$store.state.modal.body}}
          <img :src="this.$store.state.modal.img"> 
        </div>
      </Modal>
      <nuxt />
    </v-content>
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import Modal from '~/components/Modal.vue';

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
        }
      ],
      title: 'PaceMaker'
    }
  },
  components: {
    Modal
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
      this.$storage.setUniversal('isAuth', false)
      this.$router.push('/')
    },
    menuBtn() {
      this.drawer = !this.drawer
    },
    modalClose() {
      this.$store.commit('achievement/setShowModal',false);
    }
  }
}
</script>

<style src="../assets/color.css"></style>
<style scoped>
.menu-icon {
  width: 40px;
  height: 40px;
  z-index: 80;
  margin: 13px 0 0 11px;
  font-size: 50px;
  position: fixed;
  border-radius: 50px;
}
a {
  text-decoration: none;
  color: black;
}
</style>
