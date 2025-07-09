const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      //  { path: '', component: () => import('pages/IndexPage.vue') },
      { path: 'entryGate', component: () => import('pages/EntryGate.vue')},
      { path: 'exitGate', component: () => import('pages/ExitGate.vue')}
    ]
  },

  // Always leave this as last one,
  // but you can also remove it

  {  path: '/login', component: () => import('pages/LoginPage.vue') },
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }

]

export default routes
