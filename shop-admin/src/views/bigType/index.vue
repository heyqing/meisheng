<template>
  <el-card>
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input placeholder="请输入商品大类名称..." clearable v-model="queryForm.query"></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initBigTypeList">搜索</el-button>
      <el-button type="primary" @click="handleDialogValue()">添加商品大类</el-button>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%" height="500">
      <el-table-column prop="sortId" label="#ID" width="80" show-overflow-tooltip/>

      <el-table-column prop="sortName" label="商品大类名称" width="120" show-overflow-tooltip/>

      <el-table-column prop="image" label="商品大类图片" width="150" show-overflow-tooltip>
        <template v-slot="scope">
          <img :src="scope.row.sortAvatar" width="50" height="50"/>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="商品大类描述" width="200" show-overflow-tooltip/>
      <el-table-column prop="createTime" label="创建日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="updateTime" label="修改日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="action" label="操作" width="300" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="success" @click="handleChangeImage(scope.row)">更换图片</el-button>
          <el-button type="primary" :icon="Edit" @click="handleDialogValue(scope.row)"></el-button>
          <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row.sortId)"></el-button>
        </template>

      </el-table-column>

    </el-table>

    <el-pagination
        v-model:currentPage="queryForm.pageNum"
        :page-sizes="[10, 20, 30, 40,50]"
        :page-size="queryForm.pageSize"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    >
    </el-pagination>
  </el-card>
  <Dialog v-model="dialogVisible" :dialogVisible="dialogVisible" :dialogTitle="dialogTitle"
          @initBigTypeList="initBigTypeList" :id="id"/>
  <ImageDialog v-model="imageDialogVisible" :imageDialogValue="imageDialogValue" @initBigTypeList="initBigTypeList"/>
</template>

<script setup>
import {Search, Edit, Delete} from '@element-plus/icons-vue'
import {ref} from 'vue'
import axios from '@/util/axios'
import {getServerUrl} from "@/config/sys";
import Dialog from './components/dialog'
import ImageDialog from './components/imageDialog'
import {ElMessageBox, ElMessage} from 'element-plus'

const queryForm = ref({
  query: '',
  pageNum: 1,
  pageSize: 10
})

const total = ref(0)

const id = ref(-1)

const tableData = ref([])

const imageDialogValue = ref({})

const dialogTitle = ref('')

const initBigTypeList = async () => {
  try {

    console.log('xxx')
    const res = await axios.post("admin/bigType/list", queryForm.value);
    tableData.value = res.data.data.bigTypeList;
    total.value = res.data.data.total;
  } catch (e) {
    ElMessage.warning("出错了")
  }
}

initBigTypeList();

const dialogVisible = ref(false)

const imageDialogVisible = ref(false)


const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1;
  queryForm.value.pageSize = pageSize;
  initBigTypeList();
}

const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum;
  initBigTypeList();
}


const handleDialogValue = (row) => {
  if (row) {
    id.value = row.sortId;
    console.log(id)
    dialogTitle.value = "商品大类修改"
  } else {
    id.value = -1;
    dialogTitle.value = "商品大类添加"
  }
  dialogVisible.value = true;
}

const handleDelete = (id) => {

  ElMessageBox.confirm(
      '您确定要删除这条记录吗?',
      '系统提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(async () => {
        console.log("id=" + id)
        let res = await axios.get("admin/bigType/delete/" + id);
        if (res.data.mate.code === 200) {
          ElMessage({
            type: 'success',
            message: '删除成功！',
          });
          initBigTypeList();
        } else {
          ElMessage({
            type: 'error',
            message: res.data.mate.msg,
          });
        }

      })
      .catch(() => {

      })
}

// 更换图片处理
const handleChangeImage = (row) => {
  imageDialogValue.value = JSON.parse(JSON.stringify(row));
  imageDialogVisible.value = true;
}

</script>

<style lang="scss" scoped>


.header {
  padding-bottom: 16px;
  box-sizing: border-box;
}

.el-pagination {
  padding-top: 15px;
  box-sizing: border-box;
}

</style>