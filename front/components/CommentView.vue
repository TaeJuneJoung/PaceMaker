<template>
  <v-card outlined class="pa-2">
    <v-card class="vh65 scroll" id="scroll-target">
      <v-list three-line id="scroll-content">
        <v-col cols="12">
          <v-row v-for="(comment, index) in getCommentList" :key="index">
            <v-col cols="12" :class="comment.nickname == nickname ? 'blue--text' : 'black--text'">
              <b>{{comment.nickname}}</b>
              : {{comment.context}}
              <v-flex class="text-right">
                <small>{{ getCommentDate[index] }}</small>
              </v-flex>
            </v-col>
          </v-row>
        </v-col>
      </v-list>
    </v-card>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
import { findRoomById } from '../api/rooms'

export default {
  data() {
    return {
      nickname: ''
    }
  },
  async created() {
    const roomId = this.$route.params.id
    await findRoomById(roomId)
      .then(({ data }) => {
        this.$store.dispatch('comment/setCommentList', data.modelId)
      })
      .catch((error) => {
        console.error(error)
      })
  },
  mounted() {
    this.nickname = this.$session.get('account').nickname
    let container = document.getElementById('scroll-target')
    container.scrollTop = container.scrollHeight
  },
  computed: {
    ...mapGetters({ getCommentList: 'comment/getCommentList' }),
    ...mapGetters({ getCommentDate: 'comment/getCommentDate' })
  },
  methods: {}
}
</script>

<style scoped>
@media screen and (min-width: 768px) {
  .vh65 {
    height: 69vh;
  }
  .vh25 {
    height: 25vh;
  }
  .vh50 {
    height: 50vh;
  }
}
@media screen and (max-width: 767px) {
  .vh65 {
    height: 22vh;
  }
}

.scroll {
  overflow-y: auto;
}
</style>