<template>
  <div>
    <el-menu :default-active="this.$route.path" class="el-menu-demo" mode="horizontal" router>
      <el-menu-item index="/0" style="width: 33%">
        <i class="el-icon-s-platform"></i>
        任务
      </el-menu-item>
      <el-menu-item index="/1" style="width: 33%">
        <i class="el-icon-s-platform"></i>
        打野
      </el-menu-item>
      <el-menu-item index="/2" style="width: 33%">
        <i class="el-icon-s-platform"></i>
        练级
      </el-menu-item>
    </el-menu>
    <router-view style="width: 100%"></router-view>

  </div>
</template>

<script>

export default {
  name: "MyMenu",
  data() {
    return {};
  },
}
</script>

<style scoped>

</style>
