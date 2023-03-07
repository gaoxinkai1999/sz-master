<template>
  <div id="app">
    <!--    <img alt="Vue logo" src="./assets/logo.png">-->
    <el-container>
      <el-header><h1>三战小助手</h1></el-header>
      <el-divider></el-divider>
      <el-container>
        <el-aside style="width: 35%">
          <MyUser></MyUser>
        </el-aside>


        <el-container>
          <el-main>
            <MyMenu></MyMenu>
          </el-main>
        </el-container>
      </el-container>
    </el-container>


  </div>
</template>

<script>


import MyMenu from "@/components/MyMenu";
import MyUser from "@/components/MyUser";

export default {
  name: 'App',
  components: {
    MyUser,
    MyMenu


  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;

}


</style>
