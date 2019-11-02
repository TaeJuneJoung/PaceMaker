<template>
  <v-text-field
    v-model="message"
    :append-outer-icon="message? 'mdi-send' : ''"
    outlined
    clear-icon="mdi-close-circle"
    clearable
    label="Comment"
    type="text"
    @click:append-outer="sendMessage"
    @click:clear="clearMessage"
  ></v-text-field>
</template>

<script>
import { mapGetters } from 'vuex'
import { createComment } from '../api/comment.js'

export default {
  data: () => ({
    message: '',
    nickname: ''
  }),
  mounted() {
    this.nickname = this.$session.get('account').nickname
  },
  computed: {
    ...mapGetters({ getModelRoomId: 'comment/getModelRoomId' })
  },
  methods: {
    sendMessage() {
      const commentData = {
        nickname: this.nickname,
        modelRoomId: this.getModelRoomId,
        context: this.message
      }
      createComment(commentData)
        .then(({ data }) => {
					console.log(data)
					// state에 추가
        })
        .catch((error) => {
          console.error(error)
        })
      let elem = document.getElementById('scroll-content')
      let container = document.getElementById('scroll-target')
      container.scrollTop = Math.floor(elem.offsetHeight)
      this.clearMessage()
    },
    clearMessage() {
      this.message = ''
    }
  }
}
</script>
