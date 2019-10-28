<template>
  <v-app>
    <v-img src="./pacemaker.jpg" class="backimg">
      <nuxt />
    </v-img>
  </v-app>
</template>

<script>
export default {
  data() {
    return {}
  }
}
</script>

<style src="~/assets/color.css"></style>
<style scoped>
@media (min-width: 1000px) {
  .backimg {
    height: 100vh;
  }
}
</style>
