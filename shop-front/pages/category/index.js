// 导入request请求工具方法
import api from "../../utils/api"
import regeneratorRuntime from '../../lib/runtime/runtime';
Page({

  /**
   * 页面的初始数据
   */
  data: {
  // 左侧的菜单数据
  leftMenuList:[],
  // 右侧的商品数据
  rightContent:[],
  baseUrl:'',
  // 被点击的左侧的菜单
  currentIndex:0,
  // 右侧内容的滚动条距离顶部的距离
  scrollTop:0
  },
  // 获取商品大类数据
  async getSort(){
    api.getSort().then(res=>{
      this.setData({
        leftMenuList: res.data
      })
    })
  },

  // 获取商品大类数据 从首页跳转过来的
  async getCategories(sortId){
    const param = {
      "sortId": sortId
    }
    api.getCategory(param).then(res=>{
    this.setData({
      rightContent: res.data
    })
  })
},
  // 左侧菜单的点击事件
  handleItemTap(e){
    let index=e.currentTarget.dataset.index
    this.getCategories(index+1)
    this.setData({
      currentIndex:index,
      scrollTop:0
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getSort(),
    this.getCategories(1)
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    const app=getApp();
    var index=app.globalData.index;
    if(index!=-1){
      this.setData({
        currentIndex: index
      })
      this.getCategories(index+1)
      // 用完后重置
      app.globalData.index=-1
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})