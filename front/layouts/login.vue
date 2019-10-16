<template>
  <v-app>
    <v-img src="./pacemaker1.png" class="backimg">
      <nuxt />
    </v-img>
    <v-footer app>
      <span>&copy; 2019</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  data() {
    return {}
  }
}
</script>
<style scoped>
@media (min-width: 1000px) {
  .backimg{
    height: 100vh;
  }
}

</style>
