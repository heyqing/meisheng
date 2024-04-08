# 美声电器接口详情

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

  - url：`/user/login`

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

  - url: `/goods/swiperdata`

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

  - url: `/goods/floor`

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

  - url: `/goods/detail/{goodsId}`

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

  - url:`/goods/sort`

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

  - url: `/category`

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

  - url: `/goods/categories/{categoryId}`

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

  - url: `/goods/search/{query}`

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

  - url:`/scene/swiperdata`

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

  - url: `/scene/floor`

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

  - url:`/scene/detail/{id}`

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

  - url:` /scene/custom`

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

  - url:  `/scene/save`

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

  - url: `/my/order/create`

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

  - url:`/my/order/preparePay`

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

  - url:`/my/order/list`

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

  - url:`/my/order/detail/{id}`

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

# 管理员-用户

## 登录

- 接口

  - url:` /adminLogin`

  - 请求方式：POST

  - desc：管理员登录

  - 请求参数：

    |   名称   |  类型  |     *      |
    | :------: | :----: | :--------: |
    | userName | String | 管理员名称 |
    | password | String | 管理员密码 |
    |          |        |            |

  - 返回参数：

    ```json
    {
        "data":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJvamF5NjZ5b2NGa2Z3WXd5eHNmd0ZVTWFJTzJNIiwic3ViIjoi5L2V5Lul5pm0IiwiaXNzIjoiSmF2YTEyMzQiLCJpYXQiOjE3MTE0NDE5MzIsImV4cCI6MTcxMTUyODMzMn0.KY6zf6Hgo1OHF_JUH4zcq82jY3VS5xBQFRKfGNezHI8",
      "mate":{
          "code": 200,
          "msg": "获取成功"
      }
    }
    ```

## 查询普通用户

- 接口

  - url:`/admin/user/list `

  - 请求方式：POST

  - desc：查询普通用户信息

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回参数：

    ```json
    {
        "data": {
            "total": 2,
            "userList": [
                {
                    "userId": 1766014628350939138,
                    "openid": "*******************",
                    "nickname": "微信用户",
                    "userAvatarUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132",
                    "createTime": "2024-03-08T08:14:33.000+00:00",
                    "lastTime": "2024-03-29T07:29:49.000+00:00"
                },
                {
                    "userId": 1753368330783727617,
                    "openid": "*******************",
                    "nickname": "何以晴",
                    "userAvatarUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/yy4RlSUfRR1mhEoeenAtgcL8EMiaUzoDRORDV4NlwcwvD13U1OoKdxbFib2rn9A3oeDlon3u3pBUu5FWYHicmOxjw/132",
                    "createTime": "2024-02-02T10:42:41.000+00:00",
                    "lastTime": "2024-03-06T11:08:18.000+00:00"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 修改密码

- 接口

  - url:`/admin/user/modifyPassword `

  - 请求方式：POST

  - desc：管理员修改密码

  - 请求参数：

    |    名称     |  类型  |      *       |
    | :---------: | :----: | :----------: |
    |  userName   | String |  管理员名称  |
    |  password   | String |  管理员密码  |
    | newPassword | String | 管理员新密码 |

  - 返回参数：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 身份验证

- 接口

  - url:`/admin/user/adminAuth `

  - 请求方式：POST

  - desc：管理员身份验证,用于添加、删除操作前置操作

  - 请求参数：

    |   名称   |  类型  |       *        |
    | :------: | :----: | :------------: |
    | userName | String | 超级管理员名称 |
    | password | String | 超级管理员密码 |
    |          |        |                |

  - 返回参数：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 查询管理员

- 接口

  - url:`/admin/user/adminList `

  - 请求方式：POST

  - desc：获取管理员信息

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回参数：

    ```json
    {
        "data": {
            "total": 1,
            "adminList": [
                {
                    "adminId": "1",
                    "adminName": "heyqing",
                    "adminPassword": "123456",
                    "createTime": "2024-02-13T14:07:19.000+00:00"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 添加管理员

- 接口

  - url:`/admin/user/addAdmin `

  - 请求方式：POST

  - desc：添加管理员

  - 请求参数：

    |     名称      |  类型  |       *        |
    | :-----------: | :----: | :------------: |
    |   adminName   | String | 管理员账户名称 |
    | adminPassword | String | 管理员账户密码 |
    |               |        |                |

  - 返回参数：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 删除管理员

- 接口

  - url:`/admin/user/delete/{id} `

  - 请求方式：GET

  - desc：删除管理员

  - 请求参数：

    |  名称   | 类型 |    *     |
    | :-----: | :--: | :------: |
    | adminId | Long | 管理员Id |
    |         |      |          |

  - 返回参数：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

# 管理员-商品

## 获取商品列表

- 接口

  - url: `/admin/product/list`

  - 请求方式：POST

  - desc: 获取商品列表

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回数据：

    ```json
    {
        "data": {
            "total": 46,
            "productList": [
                {
                    "goodsId": "1",
                    "categoryName": "容声冰箱",
                    "goodsType": "swiper",
                    "goodsVideo": "null",
                    "goodsAvatar": "https://.../images/goods/avatar/1710569604839.jpg",
                    "goodsPrice": 6599.00,
                    "goodsName": "容声（Ronshen）BCD-475WSK1FPCQA 一级能效风冷变频十字门自由零嵌冰箱",
                    "goodsTitle": "平嵌冰箱",
                    "goodsDesc": "容声冰箱",
                    "goodsPhoto": [
                        "https://.../images/goods/swiper/1710569925466.jpg",
                    ],
                    "goodsParam": [
                        "https://.../images/goods/param/1710569730949.jpg"
                    ],
                    "goodsDetail": [
                        "https://.../images/goods/detail/1710569630068.jpg",
                        "https://.../images/goods/detail/1710569632904.jpg",
                        "https://.../images/goods/detail/1710569650930.jpg",
                    ],
                    "sortName": "冰箱",
                    "createTime": "2024-03-16T06:13:38.000+00:00",
                    "updateTime": "2024-03-16T06:26:58.000+00:00"
                },
                {
                    "goodsId": "2",
                    "categoryName": "海尔冰箱",
                    "goodsType": "floor",
                    "goodsVideo": "null",
                    "goodsAvatar": "https://.../images/goods/avatar/1710571327706.jpg",
                    "goodsPrice": 7499.00,
                    "goodsName": "海尔（Haier）冰箱658升十字对开门四开门全空间保鲜一级能效双变频EPP超净系统大容量电冰箱BCD-658WGHTD19SCU1",
                    "goodsTitle": "高端冰箱",
                    "goodsDesc": "海尔全空间冰箱",
                    "goodsPhoto": [
                        "https://.../images/goods/swiper/1710571366422.jpg",
                        "https://.../images/goods/swiper/1710571371782.jpg",
                    ],
                    "goodsParam": [
                        "https://.../images/goods/param/1710571490264.jpg"
                    ],
                    "goodsDetail": [
                        "https://.../images/goods/detail/1710571385086.jpg",
                        "https://.../images/goods/detail/1710571389482.png",
                    ],
                    "sortName": "冰箱",
                    "createTime": "2024-03-16T06:45:09.000+00:00",
                    "updateTime": "2024-03-16T06:45:09.000+00:00"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 商品展示图片上传

- 接口

  - url: `/admin/product/uploadImage`

  - 请求方式：POST

  - desc: 上传商品展示图片

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data":"https://.../images/goods/avatar/1710571385086.jpg",
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 视频上传

- 接口

  - url: `/admin/product/uploadVideo`

  - 请求方式：POST

  - desc： 上传商品视频

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 视频实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data":"https://.../video/goods/1710575875086.mp4",
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 商品详情-轮播图

- 接口

  - url: `/admin/product/uploadSwiper`

  - 请求方式：POST

  - desc: 商品详情轮播图上传

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data":"https://.../images/goods/swiper/1710571385086.jpg",
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 商品详情-详情图片上传

- 接口

  - url: `/admin/product/uploadDetail`

  - 请求方式：POST

  - desc: 商品详情,详情图片上传

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data":"https://.../images/goods/detail/1710571385086.jpg",
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 商品详情-参数

- 接口

  - url: `/admin/product/uploadParam`

  - 请求方式：POST

  - desc: 商品参数图片上传

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data":"https://.../images/goods/param/1710571385086.jpg",
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 保存商品

- 接口

  - url: `/admin/product/save`

  - 请求方式：POST

  - desc：保存商品信息

  - 请求参数：

    |     名称     |     类型     |     *      |
    | :----------: | :----------: | :--------: |
    |   goodsId    |     Long     |   商品id   |
    |  goodsName   |    String    |  商品名称  |
    |  goodsPrice  |  BigDecimal  |  商品价格  |
    | goodsAvatar  |    String    |  商品图片  |
    |  goodsDesc   |    String    |  商品描述  |
    |  goodsType   |    String    |  商品类型  |
    |  goodsTitle  |    String    |  商品标签  |
    |  goodsVideo  |    String    |  商品视频  |
    |  goodsPhoto  | List<String> | 商品轮播图 |
    | goodsDetail  | List<String> |  商品详情  |
    |  goodsParam  | List<String> |  商品参数  |
    |   sortName   |    String    |  商品大类  |
    | categoryName |    String    |  商品小类  |

    

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 删除商品

- 接口

  - url: `/admin/product/delete/{id}`

  - 请求方式：GET

  - desc：删除商品信息

  - 请求参数：

    |  名称   | 类型 |   *    |
    | :-----: | :--: | :----: |
    | goodsId | Long | 商品Id |
    |         |      |        |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 查询商品

- 接口

  - url: `/admin/product/goods`

  - 请求方式：POST

  - desc：查询商品信息，根据商品完整名称

  - 请求参数：

    | 名称  |  类型  |    *     |
    | :---: | :----: | :------: |
    | query | String | 商品名称 |
    |       |        |          |

  - 返回数据：

    ```json
    {
        "data": {
            "goodsId": "1",
            "goodsName": "海尔（Haier）冰箱658升十字对开门四开门全空间保鲜一级能效双变频EPP超净系统大容量电冰箱BCD-658WGHTD19SCU1",
            "goodsPrice": 7499.00
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

# 管理员-商品大类

## 获取商品大类列表

- 接口

  - url: `/admin/bigType/list`

  - 请求方式：POST

  - desc:查询商品大类

  - 请求参数

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回数据：

    ```json
    {
        "data": {
            "total": 8,
            "bigTypeList": [
                {
                    "sortId": "1",
                    "sortName": "冰箱",
                    "sortAvatar": "https://.../images/sort/1710307302748.jpg",
                    "createTime": "2024-03-13T05:19:21.000+00:00",
                    "updateTime": "2024-03-13T05:21:46.000+00:00",
                    "remark": "冰箱"
                },
                {
                    "sortId": "2",
                    "sortName": "洗衣机",
                    "sortAvatar": "https://.../images/sort/1710307327780.jpg",
                    "createTime": "2024-03-13T05:22:01.000+00:00",
                    "updateTime": "2024-03-13T05:22:09.000+00:00",
                    "remark": "洗衣机"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 查询所有大类

- 接口

  - url: `/admin/bigType/listAll`

  - 请求方式：POST

  - desc:查询所有大类-下拉框使用

  - 请求参数

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返回数据：

    ```json
    {
        "data": [
            {
                "sortId": 1,
                "sortType": "sort",
                "sortAvatar": "https://.../images/sort/1710307302748.jpg",
                "sortName": "冰箱",
                "createTime": "2024-03-13T05:19:21.000+00:00",
                "updateTime": "2024-03-13T05:21:46.000+00:00",
                "remark": "冰箱"
            },
            {
                "sortId": 2,
                "sortType": "sort",
                "sortAvatar": "https://.../images/sort/1710307327780.jpg",
                "sortName": "洗衣机",
                "createTime": "2024-03-13T05:22:01.000+00:00",
                "updateTime": "2024-03-13T05:22:09.000+00:00",
                "remark": "洗衣机"
            },
            {
                "sortId": 3,
                "sortType": "sort",
                "sortAvatar": "https://.../images/sort/1710307348356.jpg",
                "sortName": "空调",
                "createTime": "2024-03-13T05:22:23.000+00:00",
                "updateTime": "2024-03-13T05:22:30.000+00:00",
                "remark": "空调"
            },
            ...
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }{
        
    }
    ```

## 查询大类信息

- 接口

  - url: `/admin/bigType/{id}`

  - 请求方式：GET

  - desc:查询大类信息

  - 请求参数

    |  名称  | 类型 |   *    |
    | :----: | :--: | :----: |
    | sortId | Long | 大类Id |
    |        |      |        |

  - 返回数据：

    ```json
    {
        "data": {
            "sortId": 1,
            "sortType": "sort",
            "sortAvatar": "https://.../images/sort/1710307302748.jpg",
            "sortName": "冰箱",
            "createTime": "2024-03-13T05:19:21.000+00:00",
            "updateTime": "2024-03-13T05:21:46.000+00:00",
            "remark": "冰箱"
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 图片上传

- 接口

  - url: `/admin/bigType/uploadImage`

  - 请求方式：POST

  - desc:大类图片上传

  - 请求参数

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data": "https://.../images/sort/1710307302748.jpg",
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

    

## 商品大类保存

- 接口

  - url: `/admin/bigType/save`

  - 请求方式：POST

  - desc: 商品大类保存

  - 请求参数

    |    名称    |  类型  |    *     |
    | :--------: | :----: | :------: |
    |   sortId   |  Long  |  大类id  |
    |  sortName  | String | 大类名称 |
    | sortAvatar | String | 大类图片 |
    |   remark   | String | 大类描述 |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 商品大类删除

- 接口

  - url: `/admin/bigType/delete/{id}`

  - 请求方式：GET

  - desc: 删除商品大类

  - 请求参数

    |  名称  | 类型 |   *    |
    | :----: | :--: | :----: |
    | sortId | Long | 大类Id |
    |        |      |        |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

# 管理员-商品小类

## 获取商品小类列表

- 接口

  - url： `/admin/smallType/list`

  - 请求方式：POST

  - desc: 查询小类信息

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返货数据：

    ```json
    {
        "data": {
            "total": 12,
            "smallTypeList": [
                {
                    "categoryId": "1710320534346",
                    "categoryAvatar": "https://.../images/category/1710321013209.jpg",
                    "categoryName": "海尔冰箱",
                    "categoryTitle": "冰箱",
                    "sortName": "冰箱",
                    "bigType": {
                        "sortId": "1",
                    },
                    "createTime": "2024-03-13T09:02:14.000+00:00",
                    "updateTime": "2024-03-14T05:38:09.000+00:00",
                    "remark": "国内一线冰箱"
                },
                {
                    "categoryId": "1710565636059",
                    "categoryAvatar": "https://.../images/category/1710321054609.jpg",
                    "categoryName": "容声冰箱",
                    "categoryTitle": "冰箱",
                    "sortName": "冰箱",
                    "bigType": {
                        "sortId": "1",
                    },
                    "createTime": "2024-03-16T05:07:16.000+00:00",
                    "updateTime": "2024-03-16T05:07:22.000+00:00",
                    "remark": "国内一线品牌"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 查询所有小类

- 接口

  - url： `/admin/smallType/listAll`

  - 请求方式：POST

  - desc: 查询所有小类

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返货数据：

    ```json
    {
        "data": [
            {
                "categoryId": 1710320534346,
                "sortId": "1",
                "categoryAvatar": "https://.../images/category/1710321013209.jpg",
                "categoryName": "海尔冰箱",
                "categoryTitle": "冰箱",
                "createTime": "2024-03-13T09:02:14.000+00:00",
                "updateTime": "2024-03-14T05:38:09.000+00:00",
                "remark": "国内一线冰箱"
            },
            {
                "categoryId": 1710391182893,
                "sortId": "1",
                "categoryAvatar": "https://.../images/category/1710394120278.jpg",
                "categoryName": "冰柜",
                "categoryTitle": "冰柜",
                "createTime": "2024-03-14T04:39:43.000+00:00",
                "updateTime": "2024-03-14T05:28:41.000+00:00",
                "remark": "冰柜"
            },
            ...
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 上传图片

- 接口

  - url： `/admin/smallType/uploadImage`

  - 请求方式：POST

  - desc: 上传图片

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返货数据：

    ```json
    {
        "data":"https://.../images/category/1710394120278.jpg",
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 商品小类保存

- 接口

  - url： `/admin/smallType/save`

  - 请求方式：POST

  - desc:

  - 请求参数：

    |      名称      |     类型      |       *       |
    | :------------: | :-----------: | :-----------: |
    |   categoryId   |     Long      |  商品小类id   |
    |  categoryName  |    String     | 商品小类名称  |
    | categoryTitle  |    String     | 商品小类title |
    | categoryAvatar |    String     | 商品小类图片  |
    |     remark     |    String     | 商品小类描述  |
    |     sortId     |     Long      |  商品大类id   |
    |    bigType     | SaveBigTypePO |   商品大类    |

  - 返货数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 商品小类删除

- 接口

  - url： `/admin/smallType/delete/{id}`

  - 请求方式：GET

  - desc: 删除商品小类

  - 请求参数：

    |    名称    | 类型 |     *      |
    | :--------: | :--: | :--------: |
    | categoryId | Long | 商品小类Id |
    |            |      |            |

  - 返货数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

# 管理员-场景管

## 获取场景管列表

- 接口

  - url: `/admin/scene/list`

  - 请求方式：POST

  - desc：获取场景管列表信息

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回数据：

    ```json
    {
        "data": {
            "total": 5,
            "sceneList": [
                {
                    "sceneId": "1",
                    "sceneAvatar": "https://.../images/scene/avatar/1710837902949.jpg",
                    "sceneName": "烧烤店",
                    "sceneDesc": "小型烧烤店",
                    "sceneType": "floor",
                    "sceneGoods": [
                        {
                            "goodsId": "1710583130963",
                            "goodsName": "海尔（Haier）510升冰箱法式多门双变频干湿分储无霜低噪冰箱BCD-510WGHFD5DGQU1",
                            "goodsPrice": 5599.00
                        },
                        {
                            "goodsId": "1710831066685",
                            "goodsName": "COLMO 533升十字门冰箱超薄全嵌AI抑嘌呤光量子增养CRBS533M-A5熔幔岩",
                            "goodsPrice": 15999.00
                        },
                        {
                            "goodsId": "1711506023763",
                            "goodsName": "海尔洗烘套机 10公斤滚筒洗干集成机 热泵洗干一体机 3D透视烘干 H9 YF10BLD86BCU1 直驱变频+3D透视烘干",
                            "goodsPrice": 8999.00
                        }
                    ],
                    "sceneDetail": [
                        "https://.../images/scene/detail/1710837902949.jpg",
                        "https://.../images/scene/detail/1710837906276.jpg",
                    ],
                    "createTime": "2024-03-29T06:40:49.000+00:00",
                    "updateTime": "2024-03-29T06:40:49.000+00:00"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 图片上传-展示

- 接口

  - url: `/admin/scene/uploadImage`

  - 请求方式：POST

  - desc：上传展示图片

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |
  
- 返回数据：
  
  ```json
    {
        "data": "https://.../images/scene/avatar/1710307302748.jpg",
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
  ```

## 图片上传-详情

- 接口

  - url: `/admin/scene/uploadDetail`

  - 请求方式：POST

  - desc：场景管详情图片上传

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |
  
- 返回数据：
  
  ```json
    {
        "data": "https://.../images/scene/detail/1710307302748.jpg",
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
  ```

## 场景管保存

- 接口

  - url: `/admin/scene/`

  - 请求方式：POST

  - desc：

  - 请求参数：

    |    名称     |       类型       |     *      |
    | :---------: | :--------------: | :--------: |
    |   sceneId   |       Long       |  场景管id  |
    | sceneAvatar |      String      | 场景管图片 |
    |  sceneName  |      String      | 场景管名称 |
  |  sceneDesc  |      String      | 场景管描述 |
    |  sceneType  |      String      | 场景管类型 |
  | sceneGoods  | List<SceneGoods> | 场景管商品 |
    | sceneDetail |   List<String>   | 场景管详情 |
  
  - 返回数据：
  
    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 场景管删除

- 接口

  - url: `/admin/scene/delete/{id}`

  - 请求方式：GET

  - desc：删除场景管信息

  - 请求参数：

    |  名称   | 类型 |    *     |
    | :-----: | :--: | :------: |
    | sceneId | Long | 场景管Id |
    |         |      |          |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

# 管理员-订单

## 获取订单列表

- 接口

  - url: `/admin/order/list`

  - 请求方式：POST

  - desc：获取订单列表

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回数据：

    ```json
    {
        "data": {
            "total": 4,
            "orderList": [
                {
                    "orderId": 1711695868303,
                    "orderNo": "MeiSheng20240329030428000000303",
                    "userId": 1766014628350939138,
                    "totalPrice": 7499.00,
                    "consignee": "测试",
                    "tellPhone": "110",
                    "address": "北京市北京市东城区东华门街道测试",
                    "createTime": "2024-03-29T07:04:28.000+00:00",
                    "payTime": null,
                    "state": 1
                },
                {
                    "orderId": 1711696072594,
                    "orderNo": "MeiSheng20240329030752000000594",
                    "userId": 1766014628350939138,
                    "totalPrice": 77093.00,
                    "consignee": "测试",
                    "tellPhone": "110",
                    "address": "北京市北京市东城区东华门街道测试",
                    "createTime": "2024-03-29T07:07:53.000+00:00",
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

  - url: `/admin/order/detail/{id}`

  - 请求方式：GET

  - desc：获取订单详情

  - 请求参数：

    |  名称   | 类型 |   *    |
    | :-----: | :--: | :----: |
    | orderId | Long | 订单Id |
    |         |      |        |

  - 返回数据：

    ```json
    {
        "data": [
            {
                "orderDetailId": 1711696072606,
                "mid": 1711696072594,
                "goodsId": 1710571509630,
                "goodsNum": 2,
                "goodsPrice": 7499.00,
                "goodsName": "海尔（Haier）冰箱658升十字对开门四开门全空间保鲜一级能效双变频EPP超净系统大容量电冰箱BCD-658WGHTD19SCU1",
                "goodsAvatar": "https://.../images/goods/avatar/1710571327706.jpg"
            },
            {
                "orderDetailId": 1711696072611,
                "mid": 1711696072594,
                "goodsId": 1710572146795,
                "goodsNum": 1,
                "goodsPrice": 8699.00,
                "goodsName": "海尔（Haier）520升十字四开全空间宽幅变温十字门超薄平嵌冰箱BCD-520WGHTD1BGCU1东方既白",
                "goodsAvatar": "https://.../images/goods/avatar/1710572254099.jpg"
            }
        ],
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 修改状态

- 接口

  - url: `/admin/order/updateStatus`

  - 请求方式：POST

  - desc：更改订单状态

  - 请求参数：

    |  名称   |  类型   |    *     |
    | :-----: | :-----: | :------: |
    | orderId |  Long   |  订单id  |
    |  state  | Integer | 订单状态 |
    |         |         |          |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 删除订单

- 接口

  - url: `/admin/order/delete/{id}`

  - 请求方式：GET

  - desc：删除订单

  - 请求参数：

    |  名称   | 类型 |   *    |
    | :-----: | :--: | :----: |
    | orderId | Long | 订单id |
    |         |      |        |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

# 管理员-定制

## 获取定制列表

- 接口

  - url: `/admin/choice/list`

  - 请求方式：POST

  - desc：获取定制列表

  - 请求参数：

    |   名称   |  类型   |     *      |
    | :------: | :-----: | :--------: |
    |  query   | String  | 查询关键字 |
    | pageNum  | Integer |  查询页数  |
    | pageSize | Integer |  查询数量  |

  - 返回数据：

    ```json
    {
        "data": {
            "total": 1,
            "choiceList": [
                {
                    "choiceId": "1711696038909",
                    "choiceShop": "火锅店",
                    "choiceCurrentRegion": "北京市,北京市,东城区",
                    "choiceChainStore": 1,
                    "choiceGoods": "冰箱",
                    "choicePhone": "110",
                    "createTime": "2024-03-29T07:07:18.000+00:00",
                    "updateTime": "2024-03-29T07:07:18.000+00:00"
                }
            ]
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 删除定制

- 接口

  - url: `/admin/choice/delete/{id}`

  - 请求方式：GET

  - desc：删除定制

  - 请求参数：

    |   名称   | 类型 |   *    |
    | :------: | :--: | :----: |
    | choiceId | Long | 定制Id |
    |          |      |        |

  - 返回数据：

    ```json
    {
        "data":null,
        "mate":{
              "code": 200,
              "msg": "获取成功"
          }
    }
    ```

## 获取定制图片

- 接口

  - url: `/admin/choice/avatar/url`

  - 请求方式：GET

  - desc：获取定制图片url

  - 请求参数：

    | 名称 | 类型 |  *   |
    | :--: | :--: | :--: |
    | 暂无 | 暂无 | 暂无 |
    |      |      |      |

  - 返回数据：

    ```json
    {
        "data": {
            "themeAvatar": "https://.../images/choice/1710572254099.jpg",
            "detailAvatar": "https://.../images/choice/1710572254099.jpg"
        },
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 上传定制图片

- 接口

  - url: `/admin/choice/avatar/uploadImage`

  - 请求方式：POST

  - desc：图片上传

  - 请求参数：

    | 名称 |     类型      |    *     |
    | :--: | :-----------: | :------: |
    | file | MultipartFile | 图片实体 |
    |      |               |          |

  - 返回数据：

    ```json
    {
        "data": "https://.../images/choice/1710572254099.jpg",
        "mate": {
            "code": 200,
            "msg": "获取成功"
        }
    }
    ```

## 定制保存

- 接口

  - url: `/admin/choice/avatar/save`

  - 请求方式：POST

  - desc：保存定制图片信息

  - 请求参数：

    |  名称  |  类型  |    *     |
    | :----: | :----: | :------: |
    |  type  | String | 图片类型 |
    | imgUrl | String | 图片url  |
    |        |        |          |

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