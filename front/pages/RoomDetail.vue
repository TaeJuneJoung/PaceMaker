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
                  <v-card
                    class="pa-1"
                    outlined
                    hover
                    v-for="(todo, i) in sprints[idx][n-1]"
                    :key="i"
                  >
                    <v-checkbox class="mx-2" v-model="todo.flag" :label="todo.todo"></v-checkbox>
                  </v-card>
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
export default {
  layout: 'default',
  middleware: 'auth',
  head() {
    return {
      title: 'PaceMaker',
      titleTemplate: '스프린트 | %s'
    }
  },
  data: () => ({
    idx: 0,
    el: 1,
    sprints: [
      [
        [{ todo: 'aaa0', flag: false }, { todo: 'bbb0', flag: true }],
        [{ todo: 'aaa1', flag: false }, { todo: 'bbb1', flag: true }],
        [{ todo: 'aaa2', flag: false }, { todo: 'bbb2', flag: true }],
        [{ todo: 'aaa3', flag: false }, { todo: 'bbb3', flag: true }],
        [{ todo: 'aaa4', flag: false }, { todo: 'bbb4', flag: true }],
        [{ todo: 'aaa5', flag: false }, { todo: 'bbb5', flag: true }],
        [{ todo: 'aaa6', flag: false }, { todo: 'bbb6', flag: true }]
      ],
      [
        [{ todo: 'aaa00', flag: false }, { todo: 'bbb00', flag: true }],
        [{ todo: 'aaa11', flag: false }, { todo: 'bbb11', flag: true }],
        [{ todo: 'aaa22', flag: false }, { todo: 'bbb22', flag: true }],
        [{ todo: 'aaa33', flag: false }, { todo: 'bbb33', flag: true }],
        [{ todo: 'aaa44', flag: false }, { todo: 'bbb44', flag: true }],
        [{ todo: 'aaa55', flag: false }, { todo: 'bbb55', flag: true }],
        [{ todo: 'aaa66', flag: false }, { todo: 'bbb66', flag: true }]
      ],
      [
        [{ todo: 'aaa000', flag: false }, { todo: 'bbb000', flag: true }],
        [{ todo: 'ccc111', flag: false }, { todo: 'ddd111', flag: true }],
        [{ todo: 'aaa222', flag: false }, { todo: 'bbb222', flag: true }],
        [{ todo: 'aaa333', flag: false }, { todo: 'bbb333', flag: true }],
        [{ todo: 'aaa444', flag: false }, { todo: 'bbb444', flag: true }],
        [{ todo: 'aaa555', flag: false }, { todo: 'bbb555', flag: true }],
        [{ todo: 'aaa666', flag: false }, { todo: 'bbb666', flag: true }]
      ]
    ]
  }),
  created() {},
  methods: {},
  computed: {
    sprintsLen() {
      return 7
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