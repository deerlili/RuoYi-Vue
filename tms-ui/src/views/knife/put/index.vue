<template>
  <div class="app-container">
    <div>
    <h1 width="280px" align="center" >上架扫描</h1>
    <el-divider ></el-divider>
  </div>
    <el-form align="center"  :model="queryParams1" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="追溯号" prop="reviewId">
        <el-input
          v-model="queryParams1.reviewId"
          placeholder="请扫描或输入追溯号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    </el-form>
    <el-form align="center"  :model="queryParams2" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="货位码" prop="locationCode">
        <el-input
          v-model="queryParams2.locationCode"
          placeholder="请扫描或输入货位码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    </el-form>
    <div>
        <h1 width="280px" align="center">扫描信息</h1>
        <el-divider></el-divider>
      </div>
<el-row :gutter="20">
  <el-col :span="12">
  <el-descriptions class="margin-top" title="刀具信息" :column="2" :size="size" border>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        追溯号
      </template>
      {{toolInfo.review_id}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        刀具号
      </template>
      {{toolInfo.tool_id}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        名称
      </template>
      S203精绞刀
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        当前寿命
      </template>
      15000
    </el-descriptions-item>
  </el-descriptions>
  </el-col>
  <el-col :span="12">
  <el-descriptions class="margin-top" title="货位信息" :column="2" :size="size" border>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        货位码
      </template>
      {{cargoLocationInfo.locationCode}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        库区
      </template>
      {{cargoLocationInfo.storeArea}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        库房
      </template>
      {{cargoLocationInfo.storeHouse}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        当前使用率
      </template>
      {{cargoLocationInfo.utilizationRate}}
    </el-descriptions-item>
  </el-descriptions>
  </el-col>
</el-row>
  </div>
</template>

<script>

export default {
  name: "Change",
  data() {
    return {
      toolInfo: {
        review_id: "1067",
        tool_id: "8-152747-203-000"
      },
      cargoLocationInfo: {
        locationCode: "X1-L1-506",
        storeHouse: "刀具间",
        storeArea: "整刀货架X1",
        utilizationRate: "1/5"
      },
      // 显示搜索条件
      showSearch: true,
      // 查询参数
      queryParams1: {
        reviewId: null
      },
      queryParams2: {
        locationCode: null
      },
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