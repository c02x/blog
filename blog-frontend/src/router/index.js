import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [{
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [{
      path: '/redirect/:path(.*)',
      component: (resolve) => require(['@/views/redirect'], resolve)
    }]
  },
  {
    path: '/cms',
    component: (resolve) => require(['@/views/cms/index'], resolve),
    hidden: true,
    redirect: '/cms/main/cmsIndex',
    children: [
      {
        path: 'main',
        component: (resolve) => require(['@/views/cms/main'], resolve),
        hidden: true,
        children: [
          {
            path: 'cmsIndex',
            component: (resolve) => require(['@/views/cms/components/cmsIndex'], resolve),
            name: 'cmsIndex',
            meta: {
              title: '首页',
            }
          },
          {
            path: 'essay',
            component: (resolve) => require(['@/views/cms/components/cmsEssay'], resolve),
            name: 'essay',
            meta: {
              title: '随笔',
            }
          },
          {
            path: 'message',
            component: (resolve) => require(['@/views/cms/components/cmsMessage'], resolve),
            name: 'message',
            meta: {
              title: '留言',
            }
          },
          {
            path: 'blog',
            component: (resolve) => require(['@/views/cms/components/cmsBlog'], resolve),
            name: 'blog',
            meta: {
              title: '博客详情',
            }
          }
        ]
      },
      {
        path: 'doucument',
        component: (resolve) => require(['@/views/cms/components/cmsDoucument'], resolve),
        hidden: true,
        name: '文档',
      }
    ]
  },
  {
    path: '/cmsLogin',
    component: (resolve) => require(['@/views/cms/cmslogin'], resolve),
    hidden: true,
  },
  {
    path: '/cmsRegister',
    component: (resolve) => require(['@/views/cms/cmsRegister'], resolve),
    hidden: true
  },
  {
    path: '/admin',
    redirect: '/login',
    hidden: true
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/register',
    component: (resolve) => require(['@/views/register'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: '/cms',
    children: [{
      path: 'index',
      component: (resolve) => require(['@/views/index_v2'], resolve),
      name: 'Index',
      meta: {
        title: '首页',
        icon: 'dashboard',
        affix: true
      }
    }]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [{
      path: 'profile',
      component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
      name: 'Profile',
      meta: {
        title: '个人中心',
        icon: 'user'
      }
    }]
  },
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    children: [{
      path: 'role/:userId(\\d+)',
      component: (resolve) => require(['@/views/system/user/authRole'], resolve),
      name: 'AuthRole',
      meta: {
        title: '分配角色',
        activeMenu: '/system/user'
      }
    }]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    children: [{
      path: 'user/:roleId(\\d+)',
      component: (resolve) => require(['@/views/system/role/authUser'], resolve),
      name: 'AuthUser',
      meta: {
        title: '分配用户',
        activeMenu: '/system/role'
      }
    }]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index/:dictId(\\d+)',
      component: (resolve) => require(['@/views/system/dict/data'], resolve),
      name: 'Data',
      meta: {
        title: '字典数据',
        activeMenu: '/system/dict'
      }
    }]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index',
      component: (resolve) => require(['@/views/monitor/job/log'], resolve),
      name: 'JobLog',
      meta: {
        title: '调度日志',
        activeMenu: '/monitor/job'
      }
    }]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index',
      component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
      name: 'GenEdit',
      meta: {
        title: '修改生成配置',
        activeMenu: '/tool/gen'
      }
    }]
  }
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRoutes
})
