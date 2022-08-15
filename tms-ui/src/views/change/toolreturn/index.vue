<template>
  <div class="app-container">
    <h1 width="280px" align="center">退刀管理</h1>
    <el-divider content-position="left">待退刀清单</el-divider>
    <el-form ref="form" :model="form" label-width="80px">
      <el-row :gutter="20">
      <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="申请编号">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
      </el-row>
    </el-form>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="date" label="整刀号" width="120"></el-table-column>
          <el-table-column prop="name" label="刀具类型" width="120"></el-table-column>
          <el-table-column prop="address" label="追溯号"> </el-table-column>
          <el-table-column prop="address" label="数量"> </el-table-column>
          <el-table-column prop="address" label="备注"> </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "TypeAsk",
  data() {
    return {
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
    };
  },
};
</script>
<style>
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>