<template>
  <v-expansion-panels>
    <v-expansion-panel v-for="(item,i) in propsdata" :key="i">
      <v-expansion-panel-header>Sprint{{i+1}}</v-expansion-panel-header>
      <v-expansion-panel-content></v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
export default {
    props:['propsdata']
};
</script>

<style>
</style>