<template>
  <v-expansion-panels>
    <v-expansion-panel :key="i" v-for="i in propsdata.length">
      <!--sprints-->
      <v-expansion-panel-header>Sprint{{i}}</v-expansion-panel-header>
      <v-expansion-panel-content>
        <ul :key="day" v-for="day in propsdata[i-1].length">
          <!--sprint-->
          <li>{{day+7*(i-1)}}일</li>
          <!--day-->
          <li class="shadow" :key="item" v-for="item in propsdata[i-1][day-1].length">
            <!--todo-->
            <v-text-field solo required></v-text-field>
          </li>
        </ul>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
/*
{{ getAddRoom.sprint[i].day0[0].todo }}
  {
    day0: [{ todo: '', flag: false }],
    day1: [{ todo: '', flag: false }],
    day2: [{ todo: '', flag: false }],
    day3: [{ todo: '', flag: false }],
    day4: [{ todo: '', flag: false }],
    day5: [{ todo: '', flag: false }],
    day6: [{ todo: '', flag: false }]
  }
*/
import { mapGetters } from 'vuex'
export default {
  props:['propsdata'],
  mounted(){
    console.log(this.propsdata[0][0].length);
  },
  computed: {
    ...mapGetters({
      getAddRoom: 'roomAdd/getAddRoom'
    })
  }
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