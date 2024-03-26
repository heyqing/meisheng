import api from "../../utils/api"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sceneName: "",
    sceneDetail: {},
    isShow: false,
    icon: "info",
  },
  productInfo:{},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getSceneDetailList(options.id);
    this.pSetNavigationBarTitle(options);
  },
  pSetNavigationBarTitle(options){
    setTimeout(() => {
      wx.setNavigationBarTitle({
        title: options.title || this.data.sceneName
      })
    }, 300);
  },
  getSceneDetailList(id){
    api.getSceneDetail(id).then(res =>{
      this.productInfo = res.data.sceneGoods
      this.setData({
        sceneDetail: res.data,
        sceneName: res.data.sceneName
      })
    })
  },
  handleLookButton(){
    this.setData({
      isShow : this.data.isShow ? false : true,
      icon : this.data.icon === "info" ? "success" : "info"
    })
  },
    // 加入购物车
    setCartAdd(){
      // 获取缓存中的购物车 数组格式
      let cart=wx.getStorageSync('cart')||[];
      console.log(this.productInfo)
      // 判断商品对象中是否存在于购物车数组中
      for(let i= 0 ;i<this.productInfo.length ; i++){
        let index=cart.findIndex(v=>v.goodsId===this.productInfo[i].goodsId);
        console.log(index)
        if(index===-1){  // 不存在
          this.productInfo[i].num=1;
          this.productInfo[i].checked=true;
          cart.push(this.productInfo[i]);
        }else{  // 已经存在
          cart[index].num++;
        }
        console.log(this.productInfo[i].num)
      }
      wx.setStorageSync('cart', cart); // 把购物车添加到缓存中
    },
  
    // 点击 加入购物车
    handleCartAdd() {
      this.setCartAdd();
      // 弹窗提示
      wx.showToast({
        title: '加入成功',
        icon:'success',
        mask:true
      })
    },
    // 点击 立即购买
  handleBuy(){
    this.setCartAdd();
    wx.switchTab({
      url: '/pages/cart/index',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})