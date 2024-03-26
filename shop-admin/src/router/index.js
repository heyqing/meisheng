import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: "/",
    name: "首页",
    component: () =>import( "../views/layout"),
    redirect:'/home',
    children:[
      {
        path: "/home",
        name: "首页",
        component: () =>
            import( "../views/home/index"),
      },
      {
        path: "/user",
        name: "用户管理",
        component: () =>
            import( "../views/user"),
      },
      {
        path: "/bigType",
        name: "商品大类管理",
        component: () =>
            import( "../views/bigType"),
      },
      {
        path: "/smallType",
        name: "商品小类管理",
        component: () =>
            import( "../views/smallType"),
      },
      {
        path: "/product",
        name: "商品管理",
        component: () =>
            import( "../views/product"),
      },
      {
        path: "/order",
        name: "订单管理",
        component: () =>
            import( "../views/order"),
      },
      {
        path: "/scene",
        name: "场景管",
        component: () =>
            import( "../views/scene"),
      },
      {
        path: "/choice",
        name: "定制管理",
        component: () =>
            import( "../views/choice"),
      },
      {
        path: "/modifyPassword",
        name: "修改密码",
        component: () =>
            import( "../views/modifyPassword"),
      },
      {
        path: "/addAdmin",
        name: "添加管理员",
        component: () =>
            import( "../views/addAdmin"),
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/login')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
