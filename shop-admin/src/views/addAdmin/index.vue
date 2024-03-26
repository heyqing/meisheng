<template>
  <div v-if="!auth" class="authDiv">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>管理员权限认证</span>
        </div>
      </template>
      <el-form ref="formRef" :model="form" label-width="150px" :rules="rules">
        <el-form-item label="管理员：" prop="userName">
          <el-input v-model="form.userName" type="text"></el-input>
        </el-form-item>
        <el-form-item label="密码：" prop="password">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">验证</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>

  <div v-if="auth" class="addDiv">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>添加管理员</span>
        </div>
      </template>
      <el-form ref="formRef" :model="adminForm" label-width="150px">
        <el-form-item label="账户名称：" prop="adminName">
          <el-input v-model="adminForm.adminName" type="text"></el-input>
        </el-form-item>
        <el-form-item label="账户密码：" prop="adminPassword">
          <el-input v-model="adminForm.adminPassword" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addAdmin">添加</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="listDiv">
      <el-row :gutter="20" class="header">
        <el-col :span="7">
          <el-input placeholder="请输入管理员账户名称..." clearable v-model="queryForm.query"></el-input>
        </el-col>
        <el-button type="primary" :icon="Search" @click="initAdminList">搜索</el-button>
      </el-row>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="adminId" label="#ID" width="120" show-overflow-tooltip/>
        <el-table-column prop="adminName" label="账户名称" width="200" show-overflow-tooltip/>
        <el-table-column prop="adminPassword" label="账户密码" width="200" show-overflow-tooltip/>
        <el-table-column prop="createTime" label="创建日期" width="250" show-overflow-tooltip/>
        <el-table-column prop="action" label="操作" width="150" show-overflow-tooltip>
          <template v-slot="scope">
            <el-button type="danger" :icon="Delete" @click="handleDelete(scope.row.adminId)"></el-button>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination
          v-model:currentPage="queryForm.pageNum"
          :page-sizes="[5,10,15,20,25]"
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
  </div>
</template>
<script setup>

import {ref} from "vue";
import axios from "@/util/axios";
import {ElMessage, ElMessageBox} from "element-plus";
import {Delete, Edit, Search} from "@element-plus/icons-vue";

const form = ref({
  id: "",
  userName: "",
  password: "",
})

const adminForm = ref({
  adminName: "",
  adminPassword: ""
})

const queryForm = ref({
  query: '',
  pageNum: 1,
  pageSize: 10
})
const rules = ref({
  userName: [
    {
      required: true,
      message: '请输入用户名！',
    }
  ],
  password: [
    {
      required: true,
      message: '请输入密码！',
    },
  ]
})

const formRef = ref(null)

const auth = ref(false)
const tableData = ref([])
const total = ref(0)

const initAdminList = async () => {
  try {
    const res = await axios.post("admin/user/adminList", queryForm.value);
    tableData.value = res.data.data.adminList;
    total.value = res.data.data.total;
  }catch (e){
    ElMessage.warning("出错了 ")
  }
}
initAdminList();
const handleSizeChange = (pageSize) => {
  queryForm.value.pageNum = 1;
  queryForm.value.pageSize = pageSize;
  initAdminList();
}
const handleCurrentChange = (pageNum) => {
  queryForm.value.pageNum = pageNum;
  initAdminList();
}

const onSubmit = () => {
  formRef.value.validate(async (valid) => {
    if (form.value.userName === "MeiShengAdmin") {
      // 带上用户名和密码 请求后端
      try {
        let result = await axios.post("admin/user/adminAuth", form.value);
        let data = result.data;
        if (data.mate.code === 200) {
          ElMessage.success("验证成功");
          formRef.value.resetFields();
          auth.value = true;
          setTimeout(function () {
            ElMessage.warning("1小时后验证过期")
          }, 3000)
          setTimeout(function () {
            ElMessage.warning("添加管理员权限已失效，请重新验证");
            auth.value = false;
          }, 3600000);
        } else {
          ElMessage.error(data.mate.msg);
        }
      } catch (err) {
        console.log("error:" + err);
        ElMessage.error('系统运行出错，请联系管理员');
      }
    } else {
      ElMessage.error("暂无权限");
    }
  })
}
const addAdmin = async () => {
  let res = await axios.post("admin/user/addAdmin", adminForm.value);
  let data = res.data;
  try {
    if (data.mate.code === 200) {
      adminForm.value = {
        adminName: "",
        adminPassword: ""
      }
      ElMessage.success("添加成功");
      await initAdminList();
    } else {
      ElMessage.error("账户名称已存在")
    }
  } catch (err) {
    console.log("xxxxxxxxxxxxxxx")
    console.log(err)
    ElMessage.error('系统运行出错，请联系管理员');
  }


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
        let res = await axios.get("admin/user/delete/" + id);
        if (res.data.mate.code === 200) {
          ElMessage({
            type: 'success',
            message: '删除成功！',
          });
          await initAdminList();
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
.authDiv {
  width: 100vh;
  text-align: center;
  padding-top: 50px;
  padding-left: 150px;
}

.card-header {
  font-weight: bolder;
}

.listDiv {
  margin-top: 25px;
}

:deep(.el-input__inner) {
  width: 300px;
}
</style>