<template>
  <div class="app-container">
    <h1 width="280px" align="center">换型换刀申请</h1>
    <el-divider content-position="left">基本信息</el-divider>
    <el-form ref="form" :model="form" label-width="80px">
      <el-row :gutter="20">
      <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="申请编号">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="产线">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="机台">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="件号">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
      </el-row>
      <el-row :gutter="20">
      <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="预约时间">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="项目">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="产品">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-form-item label="班次">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
        </div></el-col>
      </el-row>
    </el-form>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-divider content-position="left">应归还刀具</el-divider>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="date" label="整刀号" width="120"></el-table-column>
          <el-table-column prop="name" label="刀具类型" width="120"></el-table-column>
          <el-table-column prop="address" label="追溯号"> </el-table-column>
          <el-table-column prop="address" label="数量"> </el-table-column>
          <el-table-column prop="address" label="备注"> </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="12">
        <el-divider content-position="left">应准备刀具</el-divider>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="date" label="整刀号" width="120"></el-table-column>
          <el-table-column prop="name" label="刀具类型" width="120"></el-table-column>
          <el-table-column prop="address" label="追溯号"> </el-table-column>
          <el-table-column prop="address" label="数量"> </el-table-column>
          <el-table-column prop="address" label="备注"> </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-alert
      style="margin-top: 5px"
      title="1、应准备刀具为新项目刀具计划的刀具列表与当前机台刀具列表剔除交集的部分"
      type="info"
      show-icon
    >
    </el-alert>
    <el-alert
      title="2、应归还刀具为当前刀具列表剔除与新项目刀具计划交集的部分"
      type="info"
      show-icon
    >
    </el-alert>
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