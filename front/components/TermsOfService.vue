<template>
  <v-layout row justify-center>
    <v-flex class="ivory mx-3">
      PaceMaker에서 제공하는
      <a href="#" @click.stop.prevent="dialog = true">서비스 약관</a>에 동의합니다.
    </v-flex>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
      <v-card class="back-gray">
        <v-toolbar flat>
          <v-toolbar-title class="ml-10 title">
            약관 동의
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn class="mr-5" icon @click.native="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
          <v-col class="content_wid">
            <h1>Pace Maker에 오신 것을 환영합니다.</h1>
            
            <li>pacemaker 제품 및 서비스를 이용해 주셔서 감사합니다. </li>
            <p> pacemaker은 서비스의 변경사항을 반영하거나 법령, 규제, 보안 등의 이유로 본 약관이나 서비스에 적용되는 추가약관을 수정할 수 있습니다. pacemaker은 중대한 변경에 관하여 합당한 사전 통지를 제공할 것입니다. 모든 변경은 적어도 통지 후 30일 이후에 적용됩니다. 단, 이용자에게 편익을 주는 서비스의 새로운 기능 또는 설정과 관련된 변경이나 법률적인 사유로 인한 변경은 즉시 발효될 수 있습니다. 서비스에 대해 변경된 약관에 동의하지 않는 경우 해당 서비스의 사용을 중지해야 합니다.</p>
          
            <h1>알림 서비스 약관 동의</h1>
            <li>알림 서비스 제공</li>
            <p>이 서비스는 이용자의 목표 달성을 도와주고자 주기적으로 알림을 받을 수 있습니다.</p>
            <li>동의 거부 권리 사실 및 불이익 내용</li>
            <p>이용자는 동의를 거부할 권리가 있습니다. 동의를 거부할 경우에는 서비스 이용에 제한됨을 알려드립니다.</p>
          </v-col>
      </v-card>
      
    </v-dialog>
  </v-layout>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      agreement: false
    }
  }

}
</script>

<style scoped>
#__layout .back-gray {
  background-color: #F5F5F5;
}

a {
  text-decoration: none;
  color: #73C2FB;
}

.content_wid{
  width: 650px;
  margin: 0 auto;
  justify-content: center;
  padding: 50px 48px;
}

h1{
  padding: 40px 0 30px 0;
}
li{
  padding: 0 0 20px 0;
}

</style>