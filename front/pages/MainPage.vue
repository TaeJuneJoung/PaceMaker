<template>
  <v-container fluid>
    <v-row>
      <v-col sm="4" xs10>
        <v-card outlined class="translate" height="1900">
          <v-btn class="ivory" text nuxt to="/">마이 페이지</v-btn>
          <v-btn class="ivory" text>로그아웃</v-btn>
        </v-card>
      </v-col>
      <v-col sm="8" xs10>
        <v-card outlined></v-card>
      </v-col>
    </v-row>
  </v-container>    
</template>
<script>
export default {
  layout: 'default',
  data: () => ({

  })
}
</script>
