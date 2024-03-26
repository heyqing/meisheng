// 导入request请求工具方法
import api from "../../utils/api"
import regeneratorRuntime from '../../lib/runtime/runtime';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    productObj:{},
    baseUrl:'',
    activeIndex:0
  },

  productInfo:{

  },

  /**
   * tab点击事件
   * @param {} e 
   */
  handleItemTap(e){
    console.log(e)
    const {index}=e.currentTarget.dataset;
    console.log(index);
    this.setData({
      activeIndex:index
    })
  },
  previewImage(e){
    const current = e.target.dataset.url;  
    console.log(e)
    wx.previewImage({  
      current: current, // 当前显示图片的http链接  
      urls: this.data.productObj[0].goodsPhoto // 需要预览的图片http链接列表  
    }); 
  },
  previewImageDetail(e){
    const current = e.target.dataset.url;  
    console.log(e)
    wx.previewImage({  
      current: current, // 当前显示图片的http链接  
      urls: this.data.productObj[0].goodsDetail // 需要预览的图片http链接列表  
    }); 
  },
  previewImageParam(e){
    const current = e.target.dataset.url;  
    wx.previewImage({  
      current: current, // 当前显示图片的http链接  
      urls: this.data.productObj[0].goodsParam // 需要预览的图片http链接列表  
    }); 
  },
  // 获取产品信息
  async getProductDetail(id){
    api.getGoodsDetail(id).then(res=>{
      this.productInfo =res.data[0]
      this.setData({
        productObj: res.data
      })
    })
  },

  // 加入购物车
  setCartAdd(){
    // 获取缓存中的购物车 数组格式
    let cart=wx.getStorageSync('cart')||[];
    console.log(this.productInfo)
    // 判断商品对象中是否存在于购物车数组中
    let index=cart.findIndex(v=>v.goodsId===this.productInfo.goodsId);
    console.log(index)
    if(index===-1){  // 不存在
      this.productInfo.num=1;
      this.productInfo.checked=true;
      cart.push(this.productInfo);
    }else{  // 已经存在
      cart[index].num++;
    }
    console.log(this.productInfo.num)
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    this.getProductDetail(options.id)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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