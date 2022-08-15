<template>
  <div class="app-container">
    <h1 width="280px" align="center">正常换刀申请</h1>
    <el-divider content-position="left">基本信息</el-divider>
    <el-form ref="form" :model="form" label-width="80px">
      <el-row :gutter="20">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="申请编号">
              <el-input v-model="form.applyNum"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="产线">
              <el-input v-model="form.line"></el-input>
            </el-form-item></div
        ></el-col>
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <el-form-item label="机台">
              <el-input v-model="form.machine"></el-input>
            </el-form-item></div
        ></el-col>
      </el-row>
    </el-form>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-divider content-position="left">机台刀具清单</el-divider>
        
        

        <el-table :data="tableData.filter(data => !search || data.tool_whole_id.toLowerCase().includes(search.toLowerCase()))" border style="width: 100%">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="tool_whole_id" label="整刀号">
            <template slot="header" slot-scope="scope">
              <el-input v-model="search" size="mini" placeholder="搜索整刀号" />
            </template>
          </el-table-column>
          <el-table-column prop="tool_type" label="刀具类型"></el-table-column>
          <el-table-column prop="review_id" label="追溯号"> </el-table-column>
          <el-table-column prop="tool_life" label="寿命"> </el-table-column>
          <el-table-column prop="tool_num" label="数量"> </el-table-column>
          <el-table-column prop="remark" label="备注"> </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <div style="display: flex;margin-top: 5px;">
      <div style="margin: 0 auto">
        <el-button type="primary" width="280px" round>提交申请</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "normal",
  data() {
    return {
      tableData: [{
          tool_whole_id: '8001',
          tool_type: '测试刀具',
          review_id: '100',
          tool_life: '5000',
          tool_num: '1',
          remark: 'xx'
        }],
      form: {
        applyNum: "",
        line: "",
        machine: "",
      },
      search: ''
    };
  },
};
</script>
<style scoped>
.bg-purple {
  background: #d3dce6;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>