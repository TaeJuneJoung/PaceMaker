<template>
  <v-layout row justify-center>
    <v-flex class="ivory mx-3">
      PaceMaker에서 제공하는
      <a href="#" @click.stop.prevent="dialog = true">서비스 약관</a>에 동의합니다.
    </v-flex>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
      <v-card class="back-gray">
        <v-toolbar flat>
          <v-toolbar-title class="ml-12 title">
            Pace Maker
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn class="mr-12" icon @click.native="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      agreement: false,
    }
  }
}
</script>

<style scoped>
#__layout .back-gray {
  background-color: #F5F5F5;
}

a {
  text-decoration: none;
  color: #73C2FB;
}
</style>