<template>
  <v-card class="mx-auto card-black">
    <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"></v-img>

    <v-container>
      <v-flex>
        <nuxt-link :to="`/Room/${room.roomId}`">
          <v-icon class="room-card-icon">mdi-door-open</v-icon>
          {{ room.summary }}
        </nuxt-link>
      </v-flex>
      <v-flex class="text-gray">Date:</v-flex>
      <v-flex class="text-gray">Action:</v-flex>
      <v-flex class="text-gray mb-3">Member:</v-flex>

      <v-btn icon color="white">
        <v-icon>mdi-chevron-down</v-icon>
      </v-btn>
      
         <v-flex class="float-right">
            <v-btn outlined color="white">참여하기</v-btn>
         </v-flex>
    </v-container>


      <!-- Member부분에 사용하자 -->

    <!-- <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text>I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.</v-card-text>
      </div>
    </v-expand-transition>-->
  </v-card>
</template>

<script>
export default {
  props: {
    room: {}
  },
  data() {
    return {
    }
  },
  created() {
   },
   methods: {
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
   color: #AAA;
}
</style>