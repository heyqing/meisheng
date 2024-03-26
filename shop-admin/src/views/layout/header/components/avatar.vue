<template>

    <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar class="meisheng-avatar" shape="square" :size="70" :src="squareUrl"></el-avatar>
    </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="logout">安全退出</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>


</template>

<script setup>
import {ref} from 'vue'
import {useStore} from 'vuex'
const store=useStore();
const squareUrl=ref("https://www.job916.com/data/upload/company/20210520/1621478794643_1.jpg")

const logout=()=>{
  store.dispatch('logout')
}
</script>

<style scoped>
.meisheng-avatar{
  margin-right: 40px;
}
</style>