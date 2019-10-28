<template>
  <v-container fluid>
    <v-row>
      <v-col sm="12" cols="12">
        <v-row class="bgimg">
          <v-container justify="center" align="center">
            <v-card class="pa-2 text-center cardSetting">
              <v-col align="center" class="cardTitle">방 생성</v-col>
              <v-card-text>
                <v-form ref="roomAddform" v-model="valid">
                  <v-col cols="12">
                    <v-text-field
                      v-model="title"
                      :counter="20"
                      :rules="[rules.minLength(1), rules.maxLength(20)]"
                      solo
                      label="목표"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-combobox v-model="select" :items="items" label="공개/비공개"></v-combobox>
                  </v-col>
                  <v-item-group mandatory>
                    <v-container>
                      <v-row>
                        <v-col md="6">
                          <v-item v-slot:default="{ active, toggle }">
                            <v-card
                              :color="active ? 'green' : ''"
                              class="d-flex align-center"
                              dark
                              height="100"
                              @click="sprintCnt(1)"
                            >
                              <v-scroll-y-transition>
                                <div
                                  v-if="active"
                                  class="display-1 flex-grow-1 text-center"
                                >Sprint 1개</div>
                              </v-scroll-y-transition>
                            </v-card>
                          </v-item>
                        </v-col>
                        <v-col md="6">
                          <v-item v-slot:default="{ active, toggle }">
                            <v-card
                              :color="active ? 'primary' : ''"
                              class="d-flex align-center"
                              dark
                              height="100"
                              @click="sprintCnt(4)"
                            >
                              <v-scroll-y-transition>
                                <div
                                  v-if="active"
                                  class="display-1 flex-grow-1 text-center"
                                >Sprint 4개</div>
                              </v-scroll-y-transition>
                            </v-card>
                          </v-item>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-item-group>
                  <SprintComp :propsdata="sprint"></SprintComp>
                </v-form>
              </v-card-text>
              <v-card-actions class="justify-end">
                <v-btn class="ma-4" color="success">방 생성</v-btn>
              </v-card-actions>
            </v-card>
          </v-container>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SprintComp from '~/components/Sprint.vue';

export default {
    ayout: 'default',
    data() {
        return {
            model: '',
            valid: true,
            title: '',
            public: true,
            sprint: 1,
            select: '공개',
            items: [
                '공개',
                '비공개'
            ],
            rules: {
            minLength: (len) => (v) =>
                (v || '').length >= len || `해당 내용은 ${len}자를 넘어야 합니다.`,
            maxLength: (len) => (v) =>
                (v || '').length <= len || `해당 내용은 ${len}자를 넘을 수 없습니다.`,
            }
        }
    },
    components: {
        SprintComp
    },
    methods: {
        onCardClick(n){
            this.model = n - 1;
        },
        sprintCnt(cnt){
            if(cnt==4) {
                this.sprint=4;
                this.toggle=!this.toggle;
            }else if(cnt==1){
                this.sprint=1;
                this.toggle=!this.toggle;
            } 
        }
    }
}
</script>

<style scoped>
.bgimg {
  background-image: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)),
    url('../static/back.jpg');
  background-size: cover;
}
.col-sm-6,
.col-lg-4,
.col-12 {
  padding: 0;
}
.container {
  padding: 0;
}
.cardSetting {
  background: rgba(255, 255, 255, 0.9);
  margin: 10vh auto;
  min-height: 80vh;
  max-width: 700px;
}
.cardTitle {
  font-size: 1.5rem;
  font-weight: bold;
}
@media (max-width: 800px) {
  .cardSetting {
    margin: 10vh 20px;
  }
}
</style>

