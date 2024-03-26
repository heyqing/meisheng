// 导入request请求工具方法
import api from "../../utils/api"
import {requestUtil} from "../../utils/requestUtil.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orders:[],
    tabs:[
      {
        id:0,
        value:"全部订单",
        isActive:true
      },
      {
        id:1,
        value:"待付款",
        isActive:false
      },
      {
        id:2,
        value:"待收货",
        isActive:false
      },
      {
        id:3,
        value:"退款/退货",
        isActive:false
      }
    ]
  },
  // 接口要的参数
  QueryParams:{
    type:0,
    page:1, // 第几页
    pageSize:10 // 每页记录数
  },

  // 总页数
  totalPage:1,


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.QueryParams.type = options.type
    wx.setNavigationBarTitle({
      title: this.data.tabs[options.type].value,
    })
    this.getOrders()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function (options) {
    
  },

  // 获取订单列表的方法
  async getOrders(){
    try{
      const res=await requestUtil({url:'/my/order/list',data:this.QueryParams});
      if(res.data.orderList.length === 0){
        wx.showToast({
          title: '暂无信息',
        })
      }
      this.totalPage=res.data.totalPage;
      this.setData({
        orders:[...this.data.orders,...res.data.orderList]  // 拼接数组
      })
    }catch(e){
      wx.showModal({
        title:'友情提示',
        content:'登录过期，请重新登录',
        success:(res)=>{
         wx.removeStorage({
           key: 'token',
         })
         wx.reLaunch({  
          url: '/pages/my/index'
        });  
        }
      })
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
    console.log("下拉")
    // 重置数组
    this.setData({
      orders:[]
    });
    // 重置页码
    this.QueryParams.page=1;
    // 重新发送请求
    this.getOrders();
    // 手动关闭等待效果
    wx.stopPullDownRefresh({
    
    })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    // 判断有没有下一页数据
    if(this.QueryParams.page>=this.totalPage){
      // 没有下一页数据
      console.log("没有下页数数据");
      wx.showToast({
        title: '没有下一页数据了'
      })
    }else{
      console.log("有下页数数据");
      this.QueryParams.page++;
      this.getOrders();
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})