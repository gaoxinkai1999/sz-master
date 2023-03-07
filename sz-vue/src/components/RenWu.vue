<template>
  <div>
    <el-table
        :data="a.teams">
      <el-table-column
          prop="TeamId"
          label="队伍id"
          style="width: 50%">
      </el-table-column>
      <el-table-column
          prop="task.type"
          label="任务"
          style="width: 50%">
      </el-table-column>

    </el-table>
    <el-button @click="run">启动</el-button>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "RenWu",
  computed: {
    // 把mapState返回值那个对象进行展开操作（把对象的属性添加到该位置）
    ...mapState(['a', 'count'])
  },
  methods: {
    run() {
      this.$http.post('http://localhost:8080/demo', this.$store.state.a)
          .then(res => {
            console.log(res.data)
          }, res => {
            console.log(res)
          });
    }
  }
}
</script>

<style scoped>

</style>
