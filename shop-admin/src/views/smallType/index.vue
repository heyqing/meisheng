<template>
  <el-card>
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input placeholder="请输入商品小类名称..." clearable v-model="queryForm.query"></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initSmallTypeList">搜索</el-button>
      <el-button type="primary" @click="handleDialogValue()">添加商品小类</el-button>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%" height="500">
      <el-table-column prop="categoryId" label="#ID" width="80" show-overflow-tooltip/>
      <el-table-column prop="categoryName" label="商品小类名称" width="200" show-overflow-tooltip/>
      <el-table-column  prop="image" label="商品小类图片" width="150" show-overflow-tooltip>
        <template v-slot="scope">
          <img :src="scope.row.categoryAvatar" width="50" height="50"/>
        </template>
      </el-table-column>

      <el-table-column prop="sortName"  label="所属大类" width="100" show-overflow-tooltip />
      <el-table-column prop="categoryTitle"  label="所属小类title" width="150" show-overflow-tooltip />

      <el-table-column prop="remark" label="商品小类描述" width="200" show-overflow-tooltip/>
      <el-table-column prop="createTime" label="创建日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="updateTime" label="修改日期" width="120" show-overflow-tooltip/>

      <el-table-column prop="action" label="操作" width="300" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="success" @click="handleChangeImage(scope.row)">更换图片</el-button>
          <el-button type="primary" :icon="Edit" @click="handleDialogValue(scope.row)"></el-button>
          <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row.categoryId)"></el-button>
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

  <Dialog v-model="dialogVisible" :dialogTitle="dialogTitle" @initSmallTypeList="initSmallTypeList"  :dialogValue="dialogValue"/>
  <ImageDialog v-model="imageDialogVisible" :imageDialogValue="imageDialogValue" @initSmallTypeList="initSmallTypeList"/>

</template>

<script setup>

import {Search,Edit,Delete } from '@element-plus/icons-vue'
import { ref } from 'vue'
import  axios from '@/util/axios'
import Dialog from './components/dialog'
import {ElMessageBox,ElMessage} from 'element-plus'
import ImageDialog from "./components/imageDialog";

const queryForm=ref({
  query:'',
  pageNum:1,
  pageSize:10
})

const total=ref(0)

const imageDialogValue=ref({})
const imageDialogVisible=ref(false)

const tableData=ref([
])

const dialogValue=ref({})

const dialogTitle=ref('')


const initSmallTypeList=async()=>{
  console.log('xxx')
  const res=await axios.post("admin/smallType/list",queryForm.value);

  tableData.value=res.data.data.smallTypeList;
  total.value=res.data.data.total;
}

initSmallTypeList();

const dialogVisible=ref(false)



const handleSizeChange=(pageSize)=>{
  queryForm.value.pageNum=1;
  queryForm.value.pageSize=pageSize;
  initSmallTypeList();
}

const handleCurrentChange=(pageNum)=>{
  queryForm.value.pageNum=pageNum;
  initSmallTypeList();
}


const handleDialogValue = (row) => {
  if(row){
    dialogValue.value=JSON.parse(JSON.stringify(row));
    dialogTitle.value="商品小类修改"
  }else{
    dialogValue.value={
      bigType:{
        sortId:""
      }
    }
    dialogTitle.value="商品小类添加"
  }
  dialogVisible.value=true;
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
    .then(async() => {
      console.log("id="+id)
      let res=await axios.get("admin/smallType/delete/"+id);
      if(res.data.mate.code==200){
        ElMessage({
          type: 'success',
          message: '删除成功！',
        });
        initSmallTypeList();
      }else{
        ElMessage({
          type: 'error',
          message: res.data.mate.msg,
        });
      }

    })
    .catch(() => {

    })
}

const handleChangeImage = (row) => {
  imageDialogValue.value=JSON.parse(JSON.stringify(row));
  imageDialogVisible.value=true;
}

</script>

<style lang="scss" scoped>

.header{
  padding-bottom: 16px;
  box-sizing: border-box;
}

.el-pagination{
  padding-top: 15px;
  box-sizing: border-box;
}


</style>