<template>
  <div class="app-container">
    <div>
    <h1 width="280px" align="center" >整刀信息</h1>
    <el-divider ></el-divider>
  </div>
    <el-form align="center"  :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="整刀号" prop="wholeKnifeId">
        <el-input
          v-model="queryParams.wholeKnifeId"
          placeholder="请输入整刀号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="刀具号" prop="knifeHandleDrawId">
        <el-input
          v-model="queryParams.knifeHandleDrawId"
          placeholder="请输入刀具号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寿命" prop="knifeHandleDrawId">
        <el-input
          v-model="queryParams.knifeHandleDrawId"
          placeholder="请输入寿命"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="knifeHandleDrawId">
        <el-input
          v-model="queryParams.knifeHandleDrawId"
          placeholder="请输入类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    </el-form>
    <div>
        <h1 width="280px" align="center">消耗品信息</h1>
        <el-divider></el-divider>
      </div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          @click="handleAdd"
          v-hasPermi="['knife:info:add']"
        >扫描消耗品</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-full-screen"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['knife:info:remove']"
        >隔离此刀</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          :disabled="multiple"
          icon="el-icon-close"
          @click="handleDelete1"
          v-hasPermi="['knife:info:remove']"
        >报废此刀</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="零件号" align="center" prop="reviewId" />
      <el-table-column label="SAP No." align="center" prop="wholeKnifeId" />
      <el-table-column label="本包数量" align="center" prop="knifeHandleDrawId" />
     <el-table-column label="单件寿命" align="center" prop="knifeHandleDrawId" />
     <el-table-column label="批次号" align="center" prop="knifeHandleDrawId" />
     <el-table-column label="生成时间" width="180" align="center" prop="createTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['knife:info:edit']"
          >报废</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
            v-hasPermi="['knife:info:remove']"
          >修磨</el-button>
        </template>
      </el-table-column>
    </el-table>


    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  <div style="display: flex">
    <div style="margin: 0 auto">
      <el-button type="primary" width="280px" round>保存更改</el-button>
    </div>
  </div>

    <!-- 添加或修改追溯号管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="数量" prop="num">
          <el-input v-model="form.num" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="整刀号" prop="wholeKnifeId">
          <el-input v-model="form.wholeKnifeId" placeholder="请输入整刀号" />
        </el-form-item>
        <el-form-item label="刀柄号" prop="knifeHandleDrawId">
          <el-input v-model="form.knifeHandleDrawId" placeholder="请输入刀柄号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/knife/info";

export default {
  name: "Change",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 追溯号管理表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 寿命时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wholeKnifeId: null,
        knifeHandleDrawId: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询追溯号管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        reviewId: null,
        wholeKnifeId: null,
        knifeHandleDrawId: null,
        createTime: null,
        remark: null,
        status: "0",
        knifeType: null,
        updateTime: null,
        knifeLife: null,
        num:null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.reviewId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "生成追溯号";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reviewId = row.reviewId || this.ids
      getInfo(reviewId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改追溯号";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reviewId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const reviewIds = row.reviewId || this.ids;
      this.$modal.confirm('是否确认隔离零件号为"' + reviewIds + '"的数据项？').then(function() {
        return delInfo(reviewIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("隔离成功");
      }).catch(() => {});
    },
    handleDelete1(row) {
      const reviewIds = row.reviewId || this.ids;
      this.$modal.confirm('是否确认报废零件号为"' + reviewIds + '"的数据项？').then(function() {
        return delInfo(reviewIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("报废成功");
      }).catch(() => {});
    }
  }
};
</script>
