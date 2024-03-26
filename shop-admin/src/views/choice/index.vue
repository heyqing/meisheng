<template>
  <div class="image-container">
    <div class="image-wrapper" @click="changeThemeAvatar">
      <el-image :src="themeAvatar" alt="定制主题图片" class="image"/>
      <p class="image-caption">定制主题图片</p>
    </div>
    <div class="image-wrapper"  @click="changeDetailAvatar">
      <el-image :src="detailAvatar" alt="定制详情图片" class="image"/>
      <p class="image-caption">定制详情图片</p>
    </div>
  </div>
  <el-card>
    <el-row :gutter="20" class="header" >
      <el-col :span="7">
        <el-input placeholder="请输入产品类型..." clearable v-model="queryForm.query"></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initChoiceList">搜索</el-button>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%" height="365">
      <el-table-column prop="choiceId" label="#ID" width="80" show-overflow-tooltip />

      <el-table-column prop="choiceShop" label="定制店面类型" width="120" show-overflow-tooltip />

      <el-table-column prop="choiceCurrentRegion" label="定制位置" width="200" show-overflow-tooltip/>

      <el-table-column prop="choiceChainStore" label="是否为连锁店" :formatter="isChain" width="120" show-overflow-tooltip/>

      <el-table-column prop="choiceGoods" label="所需商品类型" width="120" show-overflow-tooltip/>
      <el-table-column prop="choicePhone" label="联系电话" width="120" show-overflow-tooltip/>

      <el-table-column prop="createTime" label="创建日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="updateTime" label="修改日期" width="120" show-overflow-tooltip/>
      <el-table-column prop="action" label="操作"  width="80" show-overflow-tooltip>
        <template v-slot="scope">
          <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row.choiceId)"></el-button>
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
  <ImageDialog v-model="imageDialogVisible" :dialogTitle="dialogTitle" :dialogType="dialogType" @initAvatar="initAvatar"/>

</template>

<script setup>
import {Search,Delete } from '@element-plus/icons-vue'
import { ref } from 'vue'
import  axios from '@/util/axios'
import {ElMessageBox,ElMessage} from 'element-plus'
import ImageDialog from "@/views/choice/components/imageDialog.vue";

const queryForm=ref({
  query:'',
  pageNum:1,
  pageSize:10
})

const dialogTitle=ref('')
const dialogType = ref('')
const imageDialogVisible=ref(false)

const total=ref(0)

const id=ref(-1)

const tableData=ref([
])
const themeAvatar = ref('')
const detailAvatar = ref('')


const isChain = (row) => {
  if (row.choiceChainStore === 1){
    return '是'
  }else{
    return '否'
  }
}
const initChoiceList=async()=>{
  const res=await axios.post("admin/choice/list",queryForm.value);
  tableData.value=res.data.data.choiceList;
  total.value=res.data.data.total;
}
const initAvatar=async () =>{
  const res = await  axios.get('admin/choice/avatar/url')
  themeAvatar.value = res.data.data.themeAvatar
  detailAvatar.value = res.data.data.detailAvatar
}

initAvatar();
initChoiceList();



const handleSizeChange=(pageSize)=>{
  queryForm.value.pageNum=1;
  queryForm.value.pageSize=pageSize;
  initChoiceList();
}

const handleCurrentChange=(pageNum)=>{
  queryForm.value.pageNum=pageNum;
  initChoiceList();
}

const changeThemeAvatar = () =>{
  imageDialogVisible.value=true;
  dialogType.value = 'theme'
  dialogTitle.value="定制主题图片修改"
}

const changeDetailAvatar = () => {
  imageDialogVisible.value=true;
  dialogType.value = 'detail'
  dialogTitle.value="定制详情图片修改"
}
const handleDelete = (choiceId) => {

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
        let res=await axios.get("admin/choice/delete/"+choiceId);
        if(res.data.mate.code==200){
          ElMessage({
            type: 'success',
            message: '删除成功！',
          });
          await initChoiceList();
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
.image-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: flex-start; /* 图片和文字顶部对齐 */
  margin-bottom: 10px;
}

.image-wrapper {
  height: 200px;
  text-align: center; /* 文字居中 */
  margin: 0 125px; /* 图片之间的间距 */
  border: 1px solid #efefef;
  border-radius: 12px;
  box-shadow: 10px 5px 5px #838c97;
}

.image {
  width: 235px; /* 或你想要的图片宽度 */
  margin-bottom: 10px; /* 图片下方的间距，用于分隔图片和文字 */
}

.image-caption {
  margin: 0; /* 移除默认的margin */
}
</style>