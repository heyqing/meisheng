<template>
  <el-card>
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input placeholder="请输入订单号..." clearable v-model="queryForm.query"></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initOrderList">搜索</el-button>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%" height="500">

      <el-table-column prop="orderId" label="#ID" width="80" show-overflow-tooltip />

      <el-table-column prop="orderNo" label="订单号" width="300"  show-overflow-tooltip/>

      <el-table-column prop="consignee" label="收货人" width="80" show-overflow-tooltip />

      <el-table-column prop="totalPrice" label="订单总价" width="100" show-overflow-tooltip/>

      <el-table-column prop="state" label="订单状态" width="80" :formatter="statusFormatter" show-overflow-tooltip/>

      <el-table-column prop="payTime" label="订单支付日期" width="200" :formatter="payTimeFormatter" show-overflow-tooltip/>

      <el-table-column prop="address" label="收货地址" width="400" show-overflow-tooltip/>

      <el-table-column prop="tellPhone" label="联系电话" width="150" show-overflow-tooltip/>

      <el-table-column prop="createTime" label="订单创建日期" width="200" show-overflow-tooltip/>




      <el-table-column prop="action" label="操作" width="300" fixed="right" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="success"  @click="handleOrderDetail(scope.row.orderId)">详情</el-button>
          <el-button type="primary" @click="handleOrderStatus(scope.row.orderId,2)">发货</el-button>
          <el-button type="primary" @click="handleOrderStatus(scope.row.orderId,3)">退货</el-button>
          <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row)"></el-button>
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

  <el-drawer v-model="drawer" title="场景管商品" :with-header="false" >

    <h2>订单编号【{{drawerData[0].mid}}】</h2>
    <el-table :data="drawerData" stripe style="width: 100%">
      <el-table-column prop="goodsId" label="#ID" width="46" show-overflow-tooltip />
      <el-table-column prop="goodsName" label="商品名称" width="90" show-overflow-tooltip />

      <el-table-column prop="goodsPrice" label="商品价格" width="90" show-overflow-tooltip />
      <el-table-column prop="goodsNum" label="商品数量" width="60" show-overflow-tooltip />
      <el-table-column  prop="goodsAvatar" label="场景图片" width="120" show-overflow-tooltip>
        <template v-slot="scope">
          <img :src="scope.row.goodsAvatar" width="120" height="64"/>
        </template>
      </el-table-column>
    </el-table>
  </el-drawer>

</template>

<script setup>

import {Search,Edit,Delete } from '@element-plus/icons-vue'
import { ref } from 'vue'
import  axios from '@/util/axios'
import {getServerUrl} from "@/config/sys";

import {ElMessageBox,ElMessage} from 'element-plus'

const queryForm=ref({
  query:'',
  pageNum:1,
  pageSize:10
})

const total=ref(0)
const drawer = ref(false)
const drawerData = ref([])

const tableData=ref([
])

const id=ref(-1)


const initOrderList=async()=>{
  console.log('xxx')
  const res=await axios.post("admin/order/list",queryForm.value);
  tableData.value=res.data.data.orderList;
  total.value=res.data.data.total;
}

initOrderList();




const handleSizeChange=(pageSize)=>{
  queryForm.value.pageNum=1;
  queryForm.value.pageSize=pageSize;
  initOrderList();
}

const handleCurrentChange=(pageNum)=>{
  queryForm.value.pageNum=pageNum;
  initOrderList();
}


const handleOrderDetail = async (id) =>{
   let res = await axios.get('admin/order/detail/'+id)
  if (res.data.mate.code === 200){
    drawer.value = true
    drawerData.value = res.data.data
    console.log(drawerData.value)
  }else {
    ElMessage.error(res.data.mate.msg)
  }
}

const handleDelete = (row) => {

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
      if (row.state === 2){
        ElMessage.warning("该订单未发货，请谨慎处理")
      }else {
        let res=await axios.get("admin/order/delete/"+row.orderId);
        if(res.data.mata.code===200){
          ElMessage({
            type: 'success',
            message: '删除成功！',
          });
          await initOrderList();
        }else{
          ElMessage({
            type: 'error',
            message: res.data.mate.msg,
          });
        }
      }
    })
    .catch(() => {

    })
}

const handleOrderStatus = (orderId,state) => {
  ElMessageBox.confirm(
    '您确定要更新这个订单状态吗?',
    '系统提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async() => {

      let res=await axios.post("admin/order/updateStatus",{orderId:orderId,state:state});
      if(res.data.mate.code===200){
        ElMessage({
          type: 'success',
          message: '执行成功！',
        });
        await initOrderList();
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


const statusFormatter = (row) => {
  let status=row.state;
  switch (status) {
    case 1:
      return "待支付";
    case 2:
      return "已发货"
    case 3:
      return "退款/退货"
  }
}
const payTimeFormatter = (row) => {
  let state = row.state
  if (state === 1){
    return '未支付'
  }else {
    return row.payTime
  }
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