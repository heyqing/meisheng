<template>
  <el-drawer v-model="drawer" title="场景管商品" :with-header="false">
    <h2>场景【{{drawerSceneName}}】商品</h2>
    <br><br>
    <el-table :data="drawerView" stripe style="width: 100%">
      <el-table-column prop="goodsId" label="#ID" width="80" show-overflow-tooltip />
      <el-table-column prop="goodsName" label="商品名称" width="200" show-overflow-tooltip />
      <el-table-column prop="goodsPrice" label="商品价格" width="150" show-overflow-tooltip />
    </el-table>
  </el-drawer>
  <el-drawer v-model="drawerDetail" title="场景管详情" :with-header="false">
    <h2>场景【{{drawerSceneName}}】详情</h2>
    <br><br>
    <div v-for="(item ,index) in drawerDetailView" :key="index">
      <el-image :src="item" style="width: 100%"/>
    </div>
  </el-drawer>
  <el-card>
    <el-row :gutter="20" class="header" >
      <el-col :span="7">
        <el-input placeholder="请输入场景名称..." clearable v-model="queryForm.query"></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initSceneList">搜索</el-button>
      <el-button type="primary" @click="handleDialogValue">添加场景</el-button>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%" height="500">
      <el-table-column prop="sceneId" label="#ID" width="80" show-overflow-tooltip />

      <el-table-column prop="sceneName" label="场景名称" width="120" show-overflow-tooltip />

      <el-table-column  prop="sceneAvatar" label="场景图片" width="150" show-overflow-tooltip>
        <template v-slot="scope">
          <img :src="scope.row.sceneAvatar" width="150" height="72"/>
        </template>
      </el-table-column>
      <el-table-column prop="sceneDesc" label="场景描述" width="200" show-overflow-tooltip/>
      <el-table-column prop="action" label="场景详情" width="150" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="warning"  round :icon="View" @click="changeDrawerDetail(scope.row.sceneDetail,scope.row.sceneName)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="sceneType" label="场景类型" width="120" show-overflow-tooltip/>
      <el-table-column prop="action" label="场景商品" width="150" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="warning"  round :icon="View" @click="changeDrawer(scope.row.sceneGoods,scope.row.sceneName)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="updateTime" label="修改日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="action" label="操作" fixed="right" width="200" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="primary" :icon="Edit" @click="handleDialogValue(scope.row)"></el-button>
          <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row.sceneId)"></el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination
        v-model:currentPage="queryForm.pageNum"
        :page-sizes="[5,10, 15, 20, 25]"
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
  <Dialog v-model="dialogVisible" :dialogVisible="dialogVisible" :dialogValue="dialogValue" :dialogTitle="dialogTitle" @initSceneList="initSceneList" :id="id"/>
</template>

<script setup>
import {Search,Edit,Delete,View } from '@element-plus/icons-vue'
import { ref } from 'vue'
import  axios from '@/util/axios'
import Dialog from './components/dialog'
import {ElMessageBox,ElMessage} from 'element-plus'

const queryForm=ref({
  query:'',
  pageNum:1,
  pageSize:10
})

const drawerSceneName = ref('')
const drawer = ref(false)
const drawerDetail = ref(false)

const total=ref(0)

const id=ref(-1)

const tableData=ref([
])

const dialogValue = ref({})
const dialogTitle=ref('')
const drawerView = ref([])
const drawerDetailView = ref([])
const changeDrawer = (sceneGoods,sceneName) => {
  drawer.value = true;
  drawerView.value = sceneGoods;
  drawerSceneName.value = sceneName;
}
const changeDrawerDetail =(sceneDetail,sceneName) =>{
  drawerDetail.value = true;
  drawerDetailView.value = sceneDetail;
  drawerSceneName.value = sceneName;
}
const initSceneList=async()=>{
  console.log('xxx')
  const res=await axios.post("admin/scene/list",queryForm.value);
  tableData.value=res.data.data.sceneList;
  total.value=res.data.data.total;
}

initSceneList();

const dialogVisible=ref(false)


const handleSizeChange=(pageSize)=>{
  queryForm.value.pageNum=1;
  queryForm.value.pageSize=pageSize;
  initSceneList();
}

const handleCurrentChange=(pageNum)=>{
  queryForm.value.pageNum=pageNum;
  initSceneList();
}


const handleDialogValue = (row) => {
  if(row){
    dialogValue.value = JSON.parse(JSON.stringify(row));
    dialogTitle.value="场景管修改"
  }else{
    dialogValue.value = {
      sceneId: -1,
      sceneAvatar: '',
      sceneName: '',
      sceneDesc: '',
      sceneType: '',
      sceneGoods: [],
      sceneDetail: []
    }
    dialogTitle.value="场景管添加"
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
        let res=await axios.get("admin/scene/delete/"+id);
        if(res.data.mate.code==200){
          ElMessage({
            type: 'success',
            message: '删除成功！',
          });
          initSceneList();
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