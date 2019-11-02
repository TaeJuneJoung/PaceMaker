<template>
  <v-card outlined class="pa-2">
    <span class="headline pl-2">COMMENTS</span>
    <v-card class="vh65 scroll" id="scroll-target">
      <v-list three-line id="scroll-content">
        <v-col cols="12">
          <v-row v-for="(comment, index) in getCommentList" :key="index">
            <v-col cols="12" :class="comment.nickname == nickname ? 'blue--text' : 'black--text'">
              {{comment.nickname}}: {{comment.context}}
              <v-flex>
                <small>작성일_{{ getCommentDate[index] }}</small>
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
import { getAchieve } from '../api/achieve'
import { findRoomById } from '../api/rooms'

export default {
  data() {
    return {
      nickname: ''
    }
  },
  created() {
    const roomId = this.$route.params.id
    findRoomById(roomId)
      .then(({ data }) => {
        this.$store.dispatch('comment/setCommentList', data.modelId)
      })
      .catch((error) => {
        console.error(error)
      })
  },
  mounted() {
    this.nickname = this.$session.get('account').nickname
  },
  computed: {
    ...mapGetters({ getCommentList: 'comment/getCommentList' }),
    ...mapGetters({ getCommentDate: 'comment/getCommentDate' })
  },
  methods: {}
}
</script>

<style scoped>
@media screen and (min-width: 769px) {
  .vh65 {
    height: 65vh;
  }
  .vh25 {
    height: 25vh;
  }
  .vh50 {
    height: 50vh;
  }
}

@media screen and (max-width: 768px) {
}
.scroll {
  overflow-y: auto;
}
</style>