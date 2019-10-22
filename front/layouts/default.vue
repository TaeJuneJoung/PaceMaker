<template>
  <v-app>
    <v-app-bar class="logo-margin" flat fixed dense app>
      <v-toolbar-title>
        <v-icon :class="scrollPosition ? 'ivory': 'black--text'">mdi-run</v-icon>PaceMaker
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-toolbar-items class="draw-bar">
        <v-btn text @click="drawBar">
          <v-icon>mdi-menu</v-icon>
        </v-btn>
      </v-toolbar-items>
      <template class="nav-bar">
        <v-toolbar-items class="nav-bar" v-if="getUserEmail.email">
					<v-btn icon @click="updateAlarm">
						<v-icon :class="scrollPosition ? 'ivory': 'black--text'" v-if="isAlarm">mdi-bell</v-icon>
						<v-icon :class="scrollPosition ? 'ivory': 'black--text'" v-else>mdi-bell-off</v-icon>
					</v-btn>
          <v-btn text :class="scrollPosition ? 'ivory': 'black--text'">Room List</v-btn> 
          <v-btn text :class="scrollPosition ? 'ivory': 'black--text'">MyPage</v-btn>
          <v-btn text :class="scrollPosition ? 'ivory': 'black--text'">Logout</v-btn>
        </v-toolbar-items>

				<!-- 로그인이 되어야 확인할 수 있기에 연동 후 불필요 -->
        <v-toolbar-items class="nav-bar" v-else>
					<nuxt-link to="/MemberJoin">
          	<v-btn text :class="scrollPosition ? 'ivory': 'black--text'">Join</v-btn>
					</nuxt-link>
					<nuxt-link to="/">
          	<v-btn text :class="scrollPosition ? 'ivory': 'black--text'">Login</v-btn>
					</nuxt-link>
        </v-toolbar-items>
      </template>
  </v-app-bar>


    <template v-if="isDrawer">
      <v-card class="draw-bar">
        <v-navigation-drawer expand-on-hover permanent fixed right>
          <template v-slot:prepend>
            <v-list>
              <v-list-item>
                <v-list-item-avatar>
                  <v-img src="https://randomuser.me/api/portraits/women/85.jpg"></v-img>
                </v-list-item-avatar>
              </v-list-item>
              <v-list-item link two-line>
                <v-list-item-content>
                  <v-list-item-title class="title">Sandra Adams</v-list-item-title>
                  <v-list-item-subtitle>sandra_a88@gmail.com</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action>
                  <v-icon>mdi-menu-down</v-icon>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </template>

          <v-divider></v-divider>

          <v-list nav dense>
            <v-list-item link v-for="(item, idx) in items" :key="idx">
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>
      </v-card>
    </template>
    <v-content>
      <v-container>
        <v-container :class="isDrawer ? 'blur-container': 'not-container'" @click="drawCloser"></v-container>
        <nuxt />
      </v-container>
    </v-content>
  </v-app>
</template>



<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  data() {
    return {
      isAlarm: false,
      isDrawer: false,
			scrollPosition: 0,
      items: [
        {
          icon: 'mdi-home',
          title: 'Home',
          to: '/MainPage'
        },
        {
          icon: 'mdi-apps',
          title: 'RoomList',
          to: '/RoomList'
        }
      ],
      title: 'PaceMaker'
    }
	},
	mounted() {
		window.addEventListener('scroll', this.updateScroll)
	},
  computed: {
    ...mapGetters({
      getUserEmail: 'user/getUserEmail',
      getUserName: 'user/getUserName',
      getUserIcon: 'user/getUserIcon'
    })
	},
  methods: {
    drawBar() {
      this.isDrawer = !this.isDrawer
    },
    drawCloser(e) {
      if (e.target.classList.value === 'container blur-container') {
        this.isDrawer = false
      }
		},
		updateScroll() {
			this.scrollPosition = window.scrollY
		},
		updateAlarm() {
			this.isAlarm = !this.isAlarm
		}
  }
}
</script>

<style scoped>
.logo-margin {
  z-index: 50;
}
@media screen and (min-width: 1024px) {
  .logo-margin {
    padding: 0 220px 0 220px;
    max-height: 64px;
  }
  #__layout .nav-bar {
    display: block;
  }
  #__layout .draw-bar {
    display: none;
  }
}
@media screen and (max-width: 1024px) {
  #__layout .nav-bar {
    display: none;
  }
  #__layout .draw-bar {
    display: block;
    z-index: 100;
  }
}
.blur-container {
  position: absolute;
  display: block;
  height: 100vh;
  width: 100%;
  z-index: 80;
}
.not-container {
  display: none;
}
.v-app-bar--is-scrolled {
  background-color: rgba(0, 0, 0, 0.4);
  color: ivory;
}
#__layout .ivory {
	color: ivory;
}
a {
	text-decoration: none;
}
</style>