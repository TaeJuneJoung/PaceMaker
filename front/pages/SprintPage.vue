<template>
  <v-container class="grey lighten-5">
    <template>
      <v-row>
        <v-col cols="8" class="pa-2">
          <v-col :xs="4" class="pa-0">
            <v-card class="pa-2" outlined tile>목록목록</v-card>
          </v-col>
          <v-col :xs="4" class="pa-0 pt-2">
            <v-card class="pa-2" outlined tile>스프린트 날짜</v-card>
          </v-col>
        </v-col>
        <v-col cols="4" :xs="4" rows="2" class="pa-2 pl-0">
          <v-card class="pa-2 h-100" outlined tile>진행률</v-card>
        </v-col>
      </v-row>
    </template>
    <template>
      <v-row class="sprint-height">
        <v-col cols="8" :xs="4" class="pa-2">
          <v-card class="pa-2 h-100" outlined tile>스프린트 내용</v-card>
        </v-col>
        <v-col cols="4" rows="2" class="pa-2 pl-0">
          <v-card class="pa-2 h-100" outlined tile>채팅창</v-card>
        </v-col>
      </v-row>
    </template>
  </v-container>
</template>

<script>
export default {
  layout: 'default',
  middleware: 'auth',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '스프린트 | %s'
    }
  }
}
</script>

<style scoped>
.h-100 {
  height: 100%;
}
.sprint-height {
  height: 700px;
}
</style>