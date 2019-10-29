<template>
  <v-expansion-panels>
    <v-expansion-panel :key="i" v-for="i in propsdata.length">
      <!--sprints-->
      <v-expansion-panel-header>Sprint{{i}}</v-expansion-panel-header>
      <v-expansion-panel-content>
        <ul :key="day" v-for="day in propsdata[i-1].length">
          <!--sprint-->
          <li class="dayPlace">
            <span class="dayStyle">{{day+7*(i-1)}}일</span>
            <span class="addBtn" @click="addDay(i-1,day-1)">
              <v-icon dark>mdi-plus</v-icon>
            </span>
          </li>
          <!--day-->
          <li class="shadow" :key="item" v-for="item in propsdata[i-1][day-1].length">
            <!--todo-->
            <v-text-field
              solo
              required
              :value="propsdata[i-1][day-1][item-1].todo"
              @focus="getEvent"
              @blur="insertTodo(i-1,day-1,item-1)"
            ></v-text-field>
            <span class="removeBtn" @click="removeTodo(i-1,day-1,item-1)">
              <v-icon dark>mdi-minus</v-icon>
            </span>
          </li>
        </ul>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  props:['propsdata'],
  data() {
    return {
      newEvent : ""
    }
  },
  computed: {
    ...mapGetters({
      getAddRoom: 'roomAdd/getAddRoom'
    })
  },
  methods:{
    addDay(sprint,day){
      const obj = {sprint:sprint,day:day, todo: '', flag: false };
      this.$store.commit('roomAdd/setAddDay',obj);
    },
    getEvent(e){
      this.newEvent = e.target;
    },
    insertTodo(sprint,day,todo){
      const obj = {sprint:sprint,day:day,todo:todo,text:this.newEvent.value};
      this.$store.commit('roomAdd/setInsertDay',obj);
    },
    removeTodo(sprint,day,todo){
      const obj = {sprint:sprint,day:day,todo:todo};
      this.$store.commit('roomAdd/removeDay',obj);
    }
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
.dayPlace {
  position: relative;
}
.dayStyle {
  position: absolute;
  left: 15px;
  background: #e4e4e4e4;
  cursor: pointer;
  border-radius: 7px;
  font-weight: bold;
  height: 40px;
  line-height: 40px;
  width: 40px;
  top: 8px;
  text-align: center;
}
.addBtn {
  position: absolute;
  right: 15px;
  background: #0595aa;
  color: #fff;
  cursor: pointer;
  border-radius: 40px;
  font-weight: bold;
  height: 40px;
  line-height: 40px;
  width: 40px;
  top: 8px;
  text-align: center;
}
.addBtn:hover {
  background: #226872;
}
.removeBtn {
  background: #e29b9b;
  cursor: pointer;
  border-radius: 40px;
  font-weight: bold;
  height: 40px;
  line-height: 40px;
  width: 40px;
  text-align: center;
  margin-left: 10px;
  margin-top: 4px;
}
.removeBtn:hover {
  background: #b24d4e;
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