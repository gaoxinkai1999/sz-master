<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        卡密:
        <el-input v-model="getVal" placeholder="请输入卡密"></el-input>
        <!--      <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
      </div>
      <div>
        信息
      </div>
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
  }
}
</script>

<style scoped>

</style>
