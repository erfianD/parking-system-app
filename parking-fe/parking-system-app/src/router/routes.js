const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', redirect: '/login'},
      { path: 'entryGate', component: () => import('pages/EntryGate.vue')},
      { path: 'exitGate', component: () => import('pages/ExitGate.vue')},
      { path: 'login', component: () => import('pages/LoginPage.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it

  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }

]

export default routes
