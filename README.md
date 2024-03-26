# 用户

## 登录

- 技术介绍
  - 微信小程序调用`wx.getUserProfile()` 获取请求参数传递给开发者服务端。
  - 开发者服务端，通过HttpClient向微信接口服务发送请求，并携带appId+appsecret+code三个参数。
  - 开发者服务端，接收微信接口服务返回的数据，session_key+opendId等。opendId是微信用户的唯一标识。
  - 开发者服务端，自定义登录态，生成令牌(token)和openid等数据返回给小程序端，方便后绪请求身份校验。
  - 小程序端，收到自定义登录态，存储storage。
  - 小程序端，后绪通过wx.request()发起业务请求时，携带token。
  - 开发者服务端，收到请求后，通过携带的token，解析当前登录用户的id。
  - 开发者服务端，身份校验通过后，继续相关的业务逻辑处理，最终返回业务数据。

- 接口

  - url：/user/login

  - 请求方式：POST

  - desc：用户登录

  - 请求参数：

    |   名称    |  类型  |      *       |
    | :-------: | :----: | :----------: |
    |   code    | String | 用户登录凭证 |
    | nickName  | String |   用户昵称   |
    | avatarUrl | String | 用户头像地址 |

  - 返回数据：

    ```json
    {
        "data":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJvamF5NjZ5b2NGa2Z3WXd5eHNmd0ZVTWFJTzJNIiwic3ViIjoi5L2V5Lul5pm0IiwiaXNzIjoiSmF2YTEyMzQiLCJpYXQiOjE3MTE0NDE5MzIsImV4cCI6MTcxMTUyODMzMn0.KY6zf6Hgo1OHF_JUH4zcq82jY3VS5xBQFRKfGNezHI8",
      "mate":{
          "code": 200,
          "msg": "获取成功"
      }
    }
    ```

    

# 商品

## 轮播图



- 接口

  - url: /goods/swiperdata

  - 请求方式：GET

  - desc:获取轮播图数据

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |

  - 返回数据：

    ```json
    {
        "data": [
            {
                "goodsId": "1",
                "goodsAvatar": "https://.../images/goods/avatar/1710577443450.jpg",
                "goodsPrice": 6699.00,
                "goodsName": "海尔（Haier）506升十字四门变频风冷全空间保鲜零嵌入冰箱BCD-506WGHTD14SJU1",
                "goodsTitle": "零嵌冰箱",
                "goodsDesc": "海尔506升十字四门零嵌入冰箱"
            },
            {
                "goodsId": "2",
                "goodsAvatar": "https://.../images/goods/avatar/1710582251314.jpg",
                "goodsPrice": 4299.00,
                "goodsName": "海尔（Haier）470升十字四门干湿分储冰箱三档变温BCD-470WGHTD5DSF",
                "goodsTitle": "性价比",
                "goodsDesc": "海尔470升十字四门冰箱"
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

    

## 楼层

- 接口

  - url: /goods/floor

  - 请求方式: POST

  - desc:获取楼层数据

  - 请求参数：

    |   名称    |  类型   |    *     |
    | :-------: | :-----: | :------: |
    | floorType | String  | 楼层标识 |
    |  pageNum  | Integer | 查询页数 |
    | pageSize  | Integer | 查询数量 |

  - 返回数据

    ```json
    {
        "data": [
            {
                "goodsId": "1",
                "goodsAvatar": "https://.../images/goods/avatar/1710565811414.jpg",
                "goodsPrice": 18999.00,
                "goodsName": "COLMO新象冰箱531升十字对开四门超薄纯平全嵌变频一级能效智能高端冰箱CRBUS531FD-A6",
                "goodsTitle": "高端冰箱",
                "goodsDesc": "COLMO新象冰箱531升十字对开四门超薄纯平全嵌变频一级能效智能高端冰箱CRBUS531FD-A6"
            },
            {
                "goodsId": "2",
                "goodsAvatar": "https://.../images/goods/avatar/1710569604839.jpg",
                "goodsPrice": 6599.00,
                "goodsName": "容声（Ronshen）BCD-475WSK1FPCQA 一级能效风冷变频十字门自由零嵌冰箱",
                "goodsTitle": "平嵌冰箱",
                "goodsDesc": "容声冰箱"
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

    

## 商品详情

- 接口

  - url: /goods/detail/{goodsId}

  - 请求方式：GET

  - desc: 获取商品详情

  - 请求参数：

    |  名称   | 类型 |   *    |
    | :-----: | :--: | :----: |
    | goodsId | Long | 商品Id |
    |         |      |        |

  - 返回数据

    ```json
    {
        "data": [
            {
                "goodsId": "2",
                "goodsAvatar": "https://.../images/goods/avatar/1710569604839.jpg",
                "goodsVideo": "null",
                "goodsPhoto": [
                    "https://.../images/goods/swiper/1710569925466.jpg",
                    "https://.../images/goods/swiper/1710569930477.jpg",
                    "https://.../images/goods/swiper/1710569933562.png"
                ],
                "goodsPrice": 6599.00,
                "goodsName": "容声（Ronshen）BCD-475WSK1FPCQA 一级能效风冷变频十字门自由零嵌冰箱",
                "goodsDesc": "容声冰箱",
                "goodsParam": [
                    "https://.../images/goods/param/1710569730949.jpg"
                ],
                "goodsDetail": [
                    "https://.../images/goods/detail/1710569630068.jpg",
                    "https://.../images/goods/detail/1710569632904.jpg",
                    "https://.../images/goods/detail/1710569650930.jpg",
                ]
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

  

## 商品大类图标

- 接口

  - url:/goods/sort

  - 请求方式：GET

  - desc:获取商品大类图标

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返回数据

    ```json
    {
        "data": [
            {
                "sortId": "1",
                "sortName": "冰箱",
                "sortAvatar": "https://.../images/sort/1710307302748.jpg",
            },
            {
                "sortId": "2",
                "sortName": "洗衣机",
                "sortAvatar": "https://.../images/sort/1710307327780.jpg",
            },
            {
                "sortId": "3",
                "sortName": "空调",
                "sortAvatar": "https://.../images/sort/1710307348356.jpg",
            },
            {
                "sortId": "4",
                "sortName": "彩电",
                "sortAvatar": "https://.../images/sort/1710307369659.jpg",
            },
            {
                "sortId": "5",
                "sortName": "小家电",
                "sortAvatar": "https://.../images/sort/1710307409271.jpg",
            },
            {
                "sortId": "6",
                "sortName": "两季电器",
                "sortAvatar": "https://.../images/sort/1710307442073.jpg",
            },
            {
                "sortId": "7",
                "sortName": "厨电",
                "sortAvatar": "https://.../images/sort/1710307473448.jpg",
            },
            {
                "sortId": "8",
                "sortName": "智慧家",
                "sortAvatar": "https://.../images/sort/1710307502435.jpg",
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```



## 商品大类商品

- 接口

  - url: /category

  - 请求方式：POST

  - desc： 获取大类商品

  - 请求参数：

    |  名称  | 类型 |     *      |
    | :----: | :--: | :--------: |
    | sortId | Long | 商品大类Id |
    |        |      |            |

  - 返回参数：

    ```json
    {
        "data": [
            {
                "categoryTitle": "",
                "getCategoryVOList": [
                    {
                        "categoryId": "2",
                        "categoryAvatar": "https://.../images/category/1710321013209.jpg",
                        "categoryName": "海尔冰箱",
                        "categoryTitle": "",
                        "remark": "国内一线冰箱"
                    }
                ]
            },
            {
                "categoryTitle": "冰柜",
                "getCategoryVOList": [
                    {
                        "categoryId": "3",
                        "categoryAvatar": "https://.../images/category/1710394120278.jpg",
                        "categoryName": "冰柜",
                        "categoryTitle": "冰柜",
                        "remark": "冰柜"
                    }
                ]
            },
            {
                "categoryTitle": "冰箱",
                "getCategoryVOList": [
                    {
                        "categoryId": "1",
                        "categoryAvatar": "",
                        "categoryName": "容声冰箱",
                        "categoryTitle": "冰箱",
                        "remark": "国内一线品牌"
                    },
                    {
                        "categoryId": "6",
                        "categoryAvatar": "",
                        "categoryName": "美菱冰箱",
                        "categoryTitle": "冰箱",
                        "remark": "国内一线冰箱"
                    }
                ]
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

    

## 商品小类

- 接口

  - url: /goods/categories/{categoryId}

  - 请求方式:GET

  - desc:获取商品小类

  - 请求参数：

    |    名称    | 类型 |     *      |
    | :--------: | :--: | :--------: |
    | categoryId | Long | 商品小类Id |
    |            |      |            |

  - 返回数据

    ```json
    {
        "data": [
            {
                "goodsId": "1",
                "goodsAvatar": "https://.../images/goods/avatar/1710571327706.jpg",
                "goodsPrice": 7499.00,
                "goodsName": "海尔（Haier）冰箱658升十字对开门四开门全空间保鲜一级能效双变频EPP超净系统大容量电冰箱BCD-658WGHTD19SCU1",
                "goodsTitle": "高端冰箱",
                "goodsDesc": "海尔全空间冰箱"
            },
            {
                "goodsId": "2",
                "goodsAvatar": "https://.../images/goods/avatar/1710572254099.jpg",
                "goodsPrice": 8699.00,
                "goodsName": "海尔（Haier）520升十字四开全空间宽幅变温十字门超薄平嵌冰箱BCD-520WGHTD1BGCU1东方既白",
                "goodsTitle": "平嵌冰箱",
                "goodsDesc": "海尔四门平嵌冰箱"
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

    

## 商品搜索

- 接口

  - url: /goods/search/{query}

  - 请求方式:GET

  - desc:商品搜索

  - 请求参数：

    | 名称  |  类型  |   *    |
    | :---: | :----: | :----: |
    | query | String | 搜索词 |
    |       |        |        |

    

  - 返回数据

    ```json
    {
        "data": [
            {
                "goodsId": "1",
                "goodsAvatar": "https://.../images/goods/avatar/1710576242399.jpg",
                "goodsPrice": 6699.00,
                "goodsName": "海尔（Haier）502升法式多门冰箱全空间超净智能零嵌冰箱BCD-502WGHFD14SJU1",
                "goodsTitle": "零嵌冰箱",
                "goodsDesc": "海尔502升零嵌入法式多门冰箱"
            },
            {
                "goodsId": "2",
                "goodsAvatar": "https://.../images/goods/avatar/1710577443450.jpg",
                "goodsPrice": 6699.00,
                "goodsName": "海尔（Haier）506升十字四门变频风冷全空间保鲜零嵌入冰箱BCD-506WGHTD14SJU1",
                "goodsTitle": "零嵌冰箱",
                "goodsDesc": "海尔506升十字四门零嵌入冰箱"
            } "goodsDesc": "海尔623升大冷冻对开门冰箱"
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

# 场景管

## 轮播图

- 接口

  - url:/scene/swiperdata

  - 请求方式：GET

  - desc：获取轮播图数据

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返回数据：

    ```json
    {
        "data": [
            {
                "sceneId": "1",
                "sceneAvatar": "https://.../images/scene/avatar/1710577443450.jpg"
            },
            {
                "sceneId": "2",
                "sceneAvatar": "https://.../images/scene/avatar/1710577443450.jpg"
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 楼层

- 接口

  - url: /scene/floor

  - 请求方式：GET

  - desc：获取楼层数据

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返回数据：

    ```json
    {
        "data": [
            {
                "sceneId": "3",
                "sceneAvatar": "https://.../images/scene/avatar/1710577443450.jpg",
                "sceneName": "烤肉店"
            },
            {
                "sceneId": "4",
                "sceneAvatar": "https://.../images/scene/avatar/1710577443450.jpg",
                "sceneName": "洗衣店"
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 场景管详情

- 接口

  - url:/scene/detail/{id}

  - 请求方式：GET

  - desc：查询场景详情

  - 请求参数：

    |  名称   | 类型 |    *     |
    | :-----: | :--: | :------: |
    | sceneId | Long | 场景管Id |
    |         |      |          |

  - 返回数据：

    ```json
    {
        "data": {
            "sceneId": "3",
            "sceneAvatar": "https://.../images/scene/avatar/1710577443450.jpg",
            "sceneName": "烤肉店",
            "sceneDesc": "烤肉店",
            "sceneGoods": [
                {
                "goodsId": "1",
                "goodsAvatar": "https://.../images/goods/avatar/1710565811414.jpg",
                "goodsPrice": 18999.00,
                "goodsName": "COLMO新象冰箱531升十字对开四门超薄纯平全嵌变频一级能效智能高端冰箱CRBUS531FD-A6",
                "goodsTitle": "高端冰箱",
                "goodsDesc": "COLMO新象冰箱531升十字对开四门超薄纯平全嵌变频一级能效智能高端冰箱CRBUS531FD-A6"
            },
            {
                "goodsId": "2",
                "goodsAvatar": "https://.../images/goods/avatar/1710569604839.jpg",
                "goodsPrice": 6599.00,
                "goodsName": "容声（Ronshen）BCD-475WSK1FPCQA 一级能效风冷变频十字门自由零嵌冰箱",
                "goodsTitle": "平嵌冰箱",
                "goodsDesc": "容声冰箱"
            }
            ],
            "sceneDetail": [
                "https://.../images/goods/avatar/1710569604839.jpg",
                "https://.../images/goods/avatar/1710569604839.jpg"
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 客户定制-图片

- 接口

  - url: /scene/custom

  - 请求方式：GET

  - desc：获取定制图片

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返回数据：

    ```json
    {
        "data": {
            "choiceAvatarId": *******,
            "choiceAvatar": "https://.../images/scene/choice/1710069933461.jpg",
            "choicePhoto": "https://.../images/scene/choice/1710070043776.jpg"
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 保存客户定制

- 接口

  - url:  /scene/save

  - 请求方式：POST

  - desc：存储客户定制

  - 请求参数：

    |     名称      |     类型     |      *       |
    | :-----------: | :----------: | :----------: |
    | currentRegion | List<String> |   定制地址   |
    |   isSwitch    |   Boolean    | 是否为连锁店 |
    |   shopType    |    String    |  定制店类型  |
    |   goodsType   |    String    | 定制商品类型 |
    |     phone     |    String    |   客户电话   |

    

  - 返回数据：

    ```json
    {
        "data": null,
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

# 订单

- 技术介绍
  - 创建自定义拦截器-SysInterceptor
  - my/开头的必须携带token
  - 使用JWT技术验证token

## 创建订单

- 接口

  - url: /my/order/create

  - 请求方式：POST

  - desc:创建订单

  - 请求参数：

    |    名称    |    类型     |     *     |
    | :--------: | :---------: | :-------: |
    |   token    |   String    | 用户token |
    | totalPrice | BigDecimal  | 支付金额  |
    |  address   |   String    | 收货地址  |
    | consignee  |   String    |  收货人   |
    | tellPhone  |   String    | 联系方式  |
    |   goods    | List<Goods> |   商品    |

  - 返回数据：

    ```json
    {
        "data": "MeiSheng20240306091208000000535",
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 预支付

- 接口

  - url:/my/order/preparePay

  - 请求方式：POST

  - desc: 预支付

  - 请求参数：

    |  名称   |  类型  |     *     |
    | :-----: | :----: | :-------: |
    |  token  | String | 用户token |
    | orderNo | String | 订单编号  |
    |         |        |           |

  - 返回数据：

    ```json
    {
        "data":{
            "appId":"***********",
            "nonceStr":"/**随机串*/",
            "package":"**********",
            "timeStamp":"/**时间戳*/",
            "signType":"/**加密方式*/",
            "paySign":"/**重新签名*/",
            "orderNo":"/**订单编号*/",
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 订单列表

- 接口

  - url:/my/order/list

  - 请求方式：GET

  - desc:订单查询 

  - 请求参数：

    |   名称   |  类型   |                            *                             |
    | :------: | :-----: | :------------------------------------------------------: |
    |  token   | String  |                        用户token                         |
    |   type   | Integer | 查询类型：<br />0 全部订单 1 待付款 2 待收货 3 退款/退货 |
    |   page   | Integer |                         查询页数                         |
    | pageSize | Integer |                         查询大小                         |

  - 返回数据：

    ```json
    {
        "data": {
            "total": 2,
            "totalPage": 1,
            "orderList": [
                {
                    "orderId": 1710147115662,
                    "orderNo": "MeiSheng20240311045155000000662",
                    "userId": 1766014628350939138,
                    "totalPrice": 1000.00,
                    "consignee": "张三",
                    "tellPhone": "020-81167888",
                    "address": "广东省广州市海珠区新港中路397号",
                    "createTime": "2024-03-11T08:51:56.000+00:00",
                    "payTime": null,
                    "state": 1
                },
                {
                    "orderId": 1710147201196,
                    "orderNo": "MeiSheng20240311045321000000196",
                    "userId": 1766014628350939138,
                    "totalPrice": 1000.00,
                    "consignee": "张三",
                    "tellPhone": "020-81167888",
                    "address": "广东省广州市海珠区新港中路397号",
                    "createTime": "2024-03-11T08:53:21.000+00:00",
                    "payTime": null,
                    "state": 1
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 订单详情

- 接口

  - url:/my/order/detail/{id}

  - 请求方式：GET

  - desc:查询订单详情

  - 请求参数：

    |  名称   |  类型  |     *     |
    | :-----: | :----: | :-------: |
    |  token  | String | 用户token |
    | orderId |  Long  |  订单Id   |
    |         |        |           |

  - 返回数据：

    ```json
    {
        "data": {
            "orderDetail": [
                {
                    "orderDetailId": 1710147201203,
                    "mid": 1710147201196,
                    "goodsId": 4,
                    "goodsNum": 1,
                	"goodsAvatar": "https://.../images/goods/avatar/1710565811414.jpg",
                	"goodsPrice": 18999.00,
               		"goodsName": "COLMO新象冰箱531升十字对开四门超薄纯平全嵌变频一级能效智能高端冰箱CRBUS531FD-A6"
                }
            ],
            "order": {
                "orderId": 1710147201196,
                "orderNo": "MeiSheng20240311045321000000196",
                "userId": 1766014628350939138,
                "totalPrice": 1000.00,
                "consignee": "张三",
                "tellPhone": "020-81167888",
                "address": "广东省广州市海珠区新港中路397号",
                "createTime": "2024-03-11T08:53:21.000+00:00",
                "payTime": null,
                "state": 1
            }
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```