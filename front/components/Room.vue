<template>
  <v-hover v-slot:default="{ hover }">
    <v-card class="mx-auto card-black" :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }">
      <v-img :src="getImgsrc">
        <v-expand-transition>
          <v-flex
            v-if="hover"
            class="d-flex transition-fast-in-fast-out black v-card--reveal white--text"
            style="height: 100%;"
          >
            <v-btn outlined bold color="white" :to="`/Room/${room.id}`" nuxt>참여하기</v-btn>
          </v-flex>
        </v-expand-transition>
      </v-img>

      <v-container>
        <v-flex>
          <nuxt-link :to="`/Room/${room.id}`">
            <v-icon class="room-card-icon">mdi-door-open</v-icon>
          </nuxt-link>
        </v-flex>
        <v-flex class="text-gray">기간 : {{ period }}주</v-flex>
        <v-flex class="text-gray">목표 : {{ title }}</v-flex>
        <v-flex class="text-gray mb-3">만든 사람 : {{ madeBy }}</v-flex>

        <v-btn icon color="white">
          <v-icon>mdi-chevron-down</v-icon>
        </v-btn>

        <!-- <v-flex class="float-right">
              <v-btn outlined color="white">참여하기</v-btn>
        </v-flex>-->
      </v-container>

      <!-- Member부분에 사용하자 -->

      <!-- <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.</v-card-text>
        </div>
      </v-expand-transition>-->
    </v-card>
  </v-hover>
</template>

<script>
import { getUser } from '~/api/index.js'

export default {
  props: {
    room: {},
  },
  data() {
    return {
      roomData: {},
      title: '',
      madeBy: '',
      period: 0
    }
  },
  async created() {
    // this.roomData = JSON.parse(this.room.roomData);
    this.roomData = this.room.roomData;
    this.title = this.roomData.title;
    this.roomData.sprint.forEach(element => {
      this.period++;
    });
    try {
      let response = await getUser(this.room.userId);
      this.madeBy = response.data.nickname;
    } catch (err) {

    }

  },
  methods: {
  },
  computed: {
    getImgsrc() {
      if(this.room.img === ( '' || null ))
        return 'https://source.unsplash.com/random/600x450';
      else
        return this.room.img;
    }
  }
}
</script>

<style scoped>
.card-black {
  background-color: black;
  color: white;
}
.text-gray {
  color: #666666;
}
.room-card-icon {
  color: white;
  font-size: 16px;
  padding: 0px 2px 4px 0px;
}
a {
  text-decoration: none;
  color: white;
}
.v-application a:hover {
  color: #aaa;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.7;
  position: absolute;
  width: 100%;
}
</style>