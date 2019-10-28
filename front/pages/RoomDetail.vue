<template>
  <v-container fluid>
    <v-row>
      <v-col sm="8" class="pa-0">
        <v-card class="vh30" outlined>
          <v-stepper></v-stepper>
        </v-card>
      </v-col>
      <v-col sm="4" class="pa-0">
        <v-card class="vh30" outlined></v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col sm="8" class="pa-0">
        <v-card class="vh65 scroll" outlined>
          <v-stepper v-model="el" vertical>
            <template v-for="n in sprintsLen">
              <v-stepper-step :key="`${n}-step`" complete :step="n" editable>Day {{n}}</v-stepper-step>
              <v-stepper-content :key="`${n}-content`" :step="n">
                <v-card class="pa-1" outlined>
                  <v-list shaped>
                    <v-list-item-group multiple>
                      <template v-for="(todo, i) in sprints[idx][n-1]">
                        <v-list-item
                          :key="`item-${i}`"
                          :value="todo.todo"
                          :aria-selected="todo.flag"
                          active-class="deep-purple--text text--accent-4"
                        >
                          <template>
                            <v-list-item-content>
                              <v-list-item-title v-text="todo.todo"></v-list-item-title>
                            </v-list-item-content>
                            <v-list-item-action>
                              <v-checkbox
                                :input-value="todo.flag"
                                :true-value="todo.todo"
                                color="deep-purple accent-4"
                                @click="toggle(idx, n-1, !todo.flag)"
                              ></v-checkbox>
                            </v-list-item-action>
                          </template>
                        </v-list-item>
                      </template>
                    </v-list-item-group>
                  </v-list>
                </v-card>
              </v-stepper-content>
            </template>
          </v-stepper>
        </v-card>
      </v-col>
      <v-col sm="4" class="pa-0">
        <v-card class="vh65" outlined></v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { mapGetters } from 'vuex'

export default {
  layout: 'default',
  data: () => ({
    idx: 0,
    el: 1,
    sprints: [],
    done: []
  }),
  created() {
    this.sprints = this.getSprints
  },
  methods: {
    toggle(idx, n, value) {
      console.log(this.$store)
    }
  },
  computed: {
    ...mapGetters({
      getSprints: 'room/getSprints'
    }),
    sprintsLen() {
      return 7;
    }
  }
}
</script>
<style>
.vh65 {
  height: 65vh;
}
.vh30 {
  height: 30vh;
}
.scroll {
  overflow-y: auto;
}
</style>