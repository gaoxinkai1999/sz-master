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
      <el-form-item label="是否主城征兵">
        <el-switch v-model="form.task.配置JSON.是否主城征兵"></el-switch>
      </el-form-item>

      <el-form-item label="调动点1">
        <span>x:</span>
        <el-input-number v-model="form.task.配置JSON.调动点[0].x"  :min="1" :max="10000" style="width: 30%" ></el-input-number>
        <span>y:</span>
        <el-input-number v-model="form.task.配置JSON.调动点[0].y"  :min="1" :max="10000" style="width: 30%" ></el-input-number>

      </el-form-item>
<!--      <el-form-item label="调动点2">-->
<!--        x:-->
<!--        <el-input v-model="form.task.配置JSON.调动点[1].x" style="width: 30%"></el-input>-->

<!--        y:-->
<!--        <el-input v-model="form.task.配置JSON.调动点[1].y" style="width: 30%"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="调动点3">-->
<!--        x:-->
<!--        <el-input v-model="form.task.配置JSON.调动点[2].x" style="width: 30%"></el-input>-->
<!--        y:-->
<!--        <el-input v-model="form.task.配置JSON.调动点[2].y" style="width: 30%"></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="圈数">
        <el-input-number v-model="form.task.配置JSON.圈数"  :min="1" :max="10" style="width: 30%" ></el-input-number>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="add">添加任务</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {

  name: "DaYe",
  data() {
    return {
      form: {
        TeamId: '',
        task: {
          type: "打野",
          配置JSON: {
            调动点: [
              {x: 0, y: 0},
            ],
            圈数: 0,
            是否主城征兵: true
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
