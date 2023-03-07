<template>
  <div>
    <el-form ref="form" :model="form">

      <el-form-item label="选择队伍">
        <el-select v-model="form.TeamId" placeholder="请选择队伍">
          <el-option label="主城一" value=0></el-option>
          <el-option label="主城二" value=1></el-option>
          <el-option label="主城三" value=2></el-option>
          <el-option label="主城四" value=3></el-option>
          <el-option label="主城五" value=4></el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="练级地点">
        <el-input v-model.number="form.task.配置JSON.练级地点.x" autocomplete="off" style="width: 30%"></el-input>
        <el-input v-model.number="form.task.配置JSON.练级地点.y" autocomplete="off" style="width: 30%"></el-input>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="add">添加任务</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "练级",
  data() {
    return {
      form: {
        TeamId: '',
        task: {
          type: "练级",
          配置JSON: {
            练级地点:
                {x: 0, y: 0},
          }
        },
      }
    }
  },
  methods: {
    add() {
      this.$store.commit('add', this.form)
    }
  }
}
</script>

<style scoped>

</style>
