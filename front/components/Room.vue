<template>
  <v-card hover>
    <v-card-title class="headline">{{ room.roomId }}</v-card-title>
    <v-divider></v-divider>
    <v-card-text class="text--primary">
      <v-flex>{{room.summary}}</v-flex>
      <v-flex v-if="room.roomFlag" class="subheader">인원 : {{room.userCount}}명</v-flex>
      <v-flex>{{room.completeFlag}}</v-flex>
      <v-card-actions></v-card-actions>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  props: {
    room: {}
  },
  created() {

  }
}
</script>