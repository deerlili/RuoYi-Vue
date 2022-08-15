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
        <el-table v-loading="listLoading" :data="list" border style="width: 100%">
      <el-table-column align="center" label="整刀号">
        <template slot-scope="{row}">
          <span>{{ row.tool_whole_id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="刀具类型">
        <template slot-scope="{row}">
          <span>{{ row.tool_type }}</span>
        </template>
      </el-table-column>
      
      <el-table-column align="center" label="追溯号">
        <template slot-scope="{row}">
          <span>{{ row.review_id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="寿命">
        <template slot-scope="{row}">
          <span>{{ row.tool_life }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="数量">
        <template slot-scope="{row}">
          <span>{{ row.tool_num }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="备注">
        <template slot-scope="{row}">
          <span>{{ row.remark }}</span>
        </template>
      </el-table-column>


      <el-table-column min-width="150px" label="异常原因">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.tool_life" class="edit-input" size="small" />
            <el-button
              class="cancel-btn"
              size="small"
              icon="el-icon-refresh"
              type="warning"
              @click="cancelEdit(row)"
            >
              cancel
            </el-button>
          </template>
          <span v-else>{{ row.tool_life }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="{row}">
          <el-button
            v-if="row.edit"
            type="success"
            size="small"
            icon="el-icon-circle-check-outline"
            @click="confirmEdit(row)"
          >
            Ok
          </el-button>
          <el-button
            v-else
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="row.edit=!row.edit"
          >
            Edit
          </el-button>
        </template>
      </el-table-column>
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
  name: "Abnormal",
  data() {
    return {
      list: [{
          tool_whole_id: '8001',
          tool_type: '测试刀具',
          review_id: '100',
          tool_life: '5000',
          tool_num: '1',
          remark: 'xx',
          edit: true
        },{
          tool_whole_id: '8002',
          tool_type: '测试刀具',
          review_id: '100',
          tool_life: '5000',
          tool_num: '1',
          remark: 'xx',
          edit: true
        }],
      form: {
        applyNum: "",
        line: "",
        machine: "",
      },
      search: ''
    };
  },created() {
    this.getList()
  },methods: {
    async getList() {
      this.listLoading = true
      const items = this.list
      this.list = items.map(v => {
        this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
        v.originalTitle = v.tool_life //  will be used when user click the cancel botton
        return v
      })
      this.listLoading = false
    },
    cancelEdit(row) {
      row.tool_life = row.originalTitle
      row.edit = false
      this.$message({
        message: 'The title has been restored to the original value',
        type: 'warning'
      })
    },
    confirmEdit(row) {
      row.edit = false
      row.originalTitle = row.tool_life
      this.$message({
        message: 'The title has been edited',
        type: 'success'
      })
    }
  }
};
</script>
<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
.bg-purple {
  background: #d3dce6;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>