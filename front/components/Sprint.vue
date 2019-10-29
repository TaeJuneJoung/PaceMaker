<template>
  <v-expansion-panels>
    <v-expansion-panel v-for="(item,i) in propsdata.sprint" :key="i">
      <v-expansion-panel-header>Sprint{{i+1}}</v-expansion-panel-header>
      <v-expansion-panel-content>
        <transition-group name="list" tag="ul">
          <li
            :key="todoItem.item"
            v-for="(todoItem, index) in this.$store.state.roomAdd.room.sprint[i]"
            class="shadow"
          >
            <span>{{ todoItem.item }}</span>
          </li>
        </transition-group>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
export default {
  props:['propsdata']
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0px;
  text-align: left;
}
li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
}
/* transition effect */
.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter,
.list-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>