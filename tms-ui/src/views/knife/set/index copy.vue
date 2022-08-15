<template>
  <div class="app-container">
    <div>
      <el-divider></el-divider>
    </div>
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="108px"
    >
      <el-form-item label="对刀条码扫描" prop="wholeKnifeId" min-width="180">
        <el-input
          v-model="queryParams.wholeKnifeId"
          placeholder="请扫码对刀条码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    </el-form>
    <div>
      <el-divider></el-divider>
    </div>
    <el-input
      type="textarea"
      :rows="2"
      placeholder="请输入内容"
      v-model="textarea"
    >
    <div class="editor-container">
      <json-editor ref="jsonEditor" v-model="value" />
    </div>
    </el-input>
  </div>
</template>

<script>
import JsonEditor from '@/components/JsonEditor'
const jsonData = '[{"items":[{"market_type":"forexdata","symbol":"XAUUSD"},{"market_type":"forexdata","symbol":"UKOIL"},{"market_type":"forexdata","symbol":"CORN"}],"name":""},{"items":[{"market_type":"forexdata","symbol":"XAUUSD"},{"market_type":"forexdata","symbol":"XAGUSD"},{"market_type":"forexdata","symbol":"AUTD"},{"market_type":"forexdata","symbol":"AGTD"}],"name":"贵金属"},{"items":[{"market_type":"forexdata","symbol":"CORN"},{"market_type":"forexdata","symbol":"WHEAT"},{"market_type":"forexdata","symbol":"SOYBEAN"},{"market_type":"forexdata","symbol":"SUGAR"}],"name":"农产品"},{"items":[{"market_type":"forexdata","symbol":"UKOIL"},{"market_type":"forexdata","symbol":"USOIL"},{"market_type":"forexdata","symbol":"NGAS"}],"name":"能源化工"}]'


export default {
  name: "Change",
  components: { JsonEditor },
  data() {
    return {
      value: JSON.parse(jsonData),
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
      rules: {},
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
      if (null != this.daterangeCreateTime && "" != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] =
          this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listInfo(this.queryParams).then((response) => {
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
        num: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    
    /** 删除按钮操作 */
    handleDelete(row) {
      const reviewIds = row.reviewId || this.ids;
      this.$modal
        .confirm('是否确认隔离零件号为"' + reviewIds + '"的数据项？')
        .then(function () {
          return delInfo(reviewIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("隔离成功");
        })
        .catch(() => {});
    }
  },
};
</script>
<style scoped>
.editor-container{
  position: relative;
  height: 100%;
}
</style>