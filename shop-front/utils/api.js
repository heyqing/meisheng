// 引入文件
import { request } from './request'

export default  {
  getSwiper: () => request('/goods/swiperdata','GET'),
  getSort: () => request('/goods/sort','GET'),
  getFloor: (data) => request('/goods/floor','POST',data),
  getCategory: (data) => request('/category','POST',data),
  getGoodsDetail: (id) => request('/goods/detail/'+ id,'GET'),
  getCategoryGoods: (id) => request('/goods/categories/'+ id,'GET'),
  login: (data) => request('/user/login','POST',data),
  goodsSearch: (data) => request('/goods/search/'+data,'GET'),
  getSceneSwiper: () => request('/scene/swiperdata','GET'),
  getSceneFloor: () => request('/scene/floor','GET'),
  getSceneDetail: (data) => request('/scene/detail/'+data,'GET'),
  getAvatar: () => request('/scene/custom','GET'),
  saveCustom: (data) => request('/scene/save','POST',data)
}