<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        卡密:
        <el-input v-model="getVal" placeholder="请输入卡密"></el-input>
        <!--      <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
      </div>
      <el-table
          :data="messages"
          style="width: 100%"
      height="300">
        <el-table-column
            prop="date"
            label="时间">
        </el-table-column>
        <el-table-column
            prop="message"
            label="消息">
        </el-table-column>
      </el-table>
    </el-card>


  </div>
</template>

<script>
export default {
  name: "MyUser",
  computed: {
    getVal: {
      get() {
        // 这里也是用了Vuex里的 modules 大家可以当成普通的变量来看
        return this.$store.state.a.卡密
      },
      set(newVal) {
        this.$store.commit('修改卡密', newVal)
      }
    }
  },
  created() {
    this.initWebSocket()
  },
  data(){
    return {
      messages:[],
    }
  },
  methods: {
    initWebSocket(){ //初始化weosocket
      const wsuri = "ws://127.0.0.1:8080/api/websocket";
      this.websock = new WebSocket(wsuri);
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen(){ //连接建立之后执行send方法发送数据
      let actions = {"message":"12345"};
      this.websocketsend(JSON.stringify(actions));
    },
    websocketonerror(){//连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e){ //数据接收

      this.messages.push(JSON.parse(e.data))
    },
    websocketsend(Data){//数据发送
      this.websock.send(Data);
    },
    websocketclose(e){  //关闭
      console.log('断开连接',e);
    },
  }



}
</script>

<style scoped>

</style>
