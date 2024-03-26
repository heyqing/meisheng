<template>
  <el-card>
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input placeholder="请输入商品名称..." clearable v-model="queryForm.query"></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initProductList">搜索</el-button>
      <el-button type="primary" @click="handleDialogValue()">添加商品</el-button>
    </el-row>
    <el-table :data="tableData" stripe  style="width: 100%" height="500">

      <el-table-column prop="goodsId" label="#ID" width="80" show-overflow-tooltip/>
      <el-table-column prop="goodsName" label="商品名称" width="150" show-overflow-tooltip/>
      <el-table-column prop="goodsPrice" label="商品价格" width="100" show-overflow-tooltip/>
      <el-table-column prop="goodsAvatar" label="商品图片" width="150" show-overflow-tooltip>
        <template v-slot="scope">
          <img :src="scope.row.goodsAvatar" width="80" height="80"/>
        </template>
      </el-table-column>
      <el-table-column prop="goodsDesc" label="商品描述" width="200" show-overflow-tooltip/>
      <el-table-column prop="goodsVideo" label="商品视频" width="200" show-overflow-tooltip>
        <template v-slot="scope">
          <video controls width="150px">
            <source :src="scope.row.goodsVideo"/>
          </video>
        </template>
      </el-table-column>
      <el-table-column prop="goodsType" label="商品类别" width="150" show-overflow-tooltip/>
      <el-table-column prop="sortName"  label="所属大类" width="150" show-overflow-tooltip/>
      <el-table-column prop="categoryName" label="所属小类" width="150" show-overflow-tooltip/>
      <el-table-column prop="goodsPhoto" label="幻灯图片" width="180" align="center" show-overflow-tooltip>
        <template v-slot="{row}">
          <el-carousel indicator-position="outside" height="auto" autoplay>
            <el-carousel-item v-for="item in row.goodsPhoto" :key="item" style="height: 100px">
              <img :src="item" width="150"/>
            </el-carousel-item>
          </el-carousel>
        </template>
      </el-table-column>
      <el-table-column prop="goodsTitle" label="标签" width="150" show-overflow-tooltip/>
      <el-table-column prop="goodsDetail" label="商品详情" width="180" align="center" show-overflow-tooltip>
        <template v-slot="{row}">
          <el-carousel indicator-position="outside" height="auto" autoplay>
            <el-carousel-item v-for="item in row.goodsDetail" :key="item" style="height: 100px">
              <img :src="item" width="150"/>
            </el-carousel-item>
          </el-carousel>
        </template>
      </el-table-column>
      <el-table-column prop="goodsParam" label="参数" width="180" align="center" show-overflow-tooltip>
        <template v-slot="{row}">
          <el-carousel indicator-position="outside" height="auto" autoplay>
            <el-carousel-item v-for="item in row.goodsParam" :key="item" style="height: 100px">
              <img :src="item" width="150"/>
            </el-carousel-item>
          </el-carousel>
        </template>
      </el-table-column>

      <el-table-column prop="createTime" label="创建日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="updateTime" label="修改日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="action" label="操作" fixed="right" width="130" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="primary" :icon="Edit" @click="handleDialogValue(scope.row)"></el-button>
          <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row.goodsId)"></el-button>
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

  <Dialog v-model="dialogVisible" :dialogTitle="dialogTitle" @initProductList="initProductList"
          :dialogValue="dialogValue"/>
  </template>

<script setup>

import {Search, Edit, Delete} from '@element-plus/icons-vue'
import {ref} from 'vue'
import axios from '@/util/axios'
import Dialog from './components/dialog'


import {ElMessageBox, ElMessage} from 'element-plus'

const queryForm = ref({
  query: '',
  pageNum: 1,
  pageSize: 10
})

const total = ref(0)

const id = ref(-1)

const tableData = ref([])

const dialogValue = ref({})

const dialogTitle = ref('')

const initProductList = async () => {
  try {
    const res = await axios.post("admin/product/list", queryForm.value);
    tableData.value = res.data.data.productList;
    total.value = res.data.data.total;
  }catch (e){
    ElMessage.warning("出错了 ")
  }
}

initProductList();

const dialogVisible = ref(false)



const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1;
  queryForm.value.pageSize = pageSize;
  initProductList();
}

const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum;
  initProductList();
}


const handleDialogValue = (row) => {
  if (row) {
    dialogValue.value = JSON.parse(JSON.stringify(row));
    dialogTitle.value = "商品修改"
  } else {
    dialogValue.value = {}
    dialogTitle.value = "商品添加"
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
        let res = await axios.get("admin/product/delete/" + id);
        if (res.data.mate.code == 200) {
          ElMessage({
            type: 'success',
            message: '删除成功！',
          });
          initProductList();
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