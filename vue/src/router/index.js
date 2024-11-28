import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'
import Cookies from "js-cookie";

Vue.use(VueRouter)

const routes = [
    // =========== 登录 ==========
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/login/Login'),
    },

    // =========== 主页 ==========
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: '/home',
        children: [ // 子路由
            // 主页
            {
                path: 'home',
                name: 'Home',
                component: () => import('@/views/home/HomeView'),

            },
            // ==========  User =========
            {
                path: 'userList',
                name: 'UserList',
                component: () => import('@/views/user/User.vue'),

            },
            {
                path: 'addUser',
                name: 'AddUser',
                component: () => import('@/views/user/AddUser.vue'),

            },
            {
                path: 'editUser',
                name: 'EditUser',
                component: () => import('@/views/user/EditUser'),

            },
            // ============== Admin ==============
            {
                path: 'adminList',
                name: 'AdminList',
                component: () => import('@/views/admin/List'),
            },
            {
                path: 'addAdmin',
                name: 'AddAdmin',
                component: () => import('@/views/admin/Add'),
            },
            {
                path: 'editAdmin',
                name: 'EditAdmin',
                component: () => import('@/views/admin/Edit'),
            },
            // ============== Category ==============
            {
                path: 'categoryList',
                name: 'CategoryList',
                component: () => import('@/views/category/List'),
            },
            {
                path: 'addCategory',
                name: 'AddCategory',
                component: () => import('@/views/category/Add'),
            },
            {
                path: 'editCategory',
                name: 'EditCategory',
                component: () => import('@/views/category/Edit'),
            },

            // ============== eatCategory ==============
            {
                path: 'eatCategoryList',
                name: 'EatCategoryList',
                component: () => import('@/views/eatCategory/List'),
            },
            {
                path: 'addEatCategory',
                name: 'AddEatCategory',
                component: () => import('@/views/eatCategory/Add'),
            },
            {
                path: 'editEatCategory',
                name: 'EditEatCategory',
                component: () => import('@/views/eatCategory/Edit'),
            },

            // ============== Hotel ==============
            {
                path: 'hotelList',
                name: 'HotelList',
                component: () => import('@/views/hotel/List'),
            },
            {
                path: 'addHotel',
                name: 'AddHotel',
                component: () => import('@/views/hotel/Add'),
            },
            {
                path: 'editHotel',
                name: 'EditHotel',
                component: () => import('@/views/hotel/Edit'),
            },

            // ============== Eat ==============
            {
                path: 'eatList',
                name: 'EatList',
                component: () => import('@/views/eat/List'),
            },
            {
                path: 'addEat',
                name: 'AddEat',
                component: () => import('@/views/eat/Add'),
            },
            {
                path: 'editEat',
                name: 'EditEat',
                component: () => import('@/views/eat/Edit'),
            },

            // ============== Reserve ==============
            {
                path: 'reserveList',
                name: 'ReserveList',
                component: () => import('@/views/reserve/List'),
            },
            {
                path: 'addReserve',
                name: 'AddReserve',
                component: () => import('@/views/reserve/Add'),
            },
            {
                path: 'editReserve',
                name: 'EditReserve',
                component: () => import('@/views/reserve/Edit'),
            },

            // ============== EatReserve ==============
            {
                path: 'eatReserveList',
                name: 'EatReserveList',
                component: () => import('@/views/eatReserve/List'),
            },
            {
                path: 'addEatReserve',
                name: 'AddEatReserve',
                component: () => import('@/views/eatReserve/Add'),
            },
            {
                path: 'editEatReserve',
                name: 'EditEatReserve',
                component: () => import('@/views/eatReserve/Edit'),
            },

            // ============== Retur ==============
            {
                path: 'returList',
                name: 'returList',
                component: () => import('@/views/retur/List.vue')
            },
        ]
    },

    {
        path: '*',
        component: () => import('@/views/404')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login') next()
    const admin = Cookies.get("admin")
    if (!admin && to.path !== '/login') return next("/login") // 强制退回到登录页面
    // 访问 /home 的时候，并且cookie里面存在数据，这个时候就直接放行
    next()
})

export default router
