<template>
  <div class="app-container">
    <div>
      <h1 width="280px" align="center" >对刀管理</h1>
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
    <h3 style="margin-left: 10px;">参数信息</h3>
    <div class="editor-container">
      <json-editor ref="jsonEditor" v-model="value" />
    </div>
  </div>
</template>

<script>
import JsonEditor from '@/components/JsonEditor'
const jsonData = '[{"items":[{"market_type":"forexdata","symbol":"UKOIL"},{"market_type":"forexdata","symbol":"USOIL"},{"market_type":"forexdata","symbol":"NGAS"}],"name":"能源化工"}]'


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
    
  }
};
</script>
<style scoped>
</style>