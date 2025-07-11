<template>
  <q-page class="flex flex-center bg-grey-2">
    <q-card class="q-pa-lg shadow-2" style="min-width: 300px; width: 100%; max-width: 400px">
      <q-card-section class="text-center">
        <div class="text-h6">Login</div>
      </q-card-section>

      <q-card-section>
        <q-input
          v-model="inputParam.username"
          label="Username"
          outlined
          dense
          class="q-mb-md"
        />
        <q-input
          v-model="inputParam.password"
          label="Password"
          type="password"
          outlined
          dense
        />
      </q-card-section>

      <q-card-actions align="center">
        <q-btn
          label="Login"
          color="primary"
          unelevated
          @click="handleLogin"
        />
      </q-card-actions>
    </q-card>
  </q-page>
</template>

<script setup>
import axios from 'axios'
import { useRouter } from 'vue-router'
import { reactive, ref } from 'vue'

const router = useRouter()
const inputParam = reactive({
  username: '',
  password: ''
})

const responseLogin = ref({
  userId: '',
  username: '',
  password: '',
  role: '',
  token: '',
  status: ''

})

function handleLogin () {
  axios.post('http://localhost:9095/users/login', inputParam)
  .then((response) => {
    responseLogin.value = response.data
    if(responseLogin.value !== null) {
      const role = responseLogin.value.role

      localStorage.setItem('auth', 'true')
      localStorage.setItem('role', role)

      if (role === 'Operator Entry') {
        router.push('/entryGate')
      } else if (role === 'Operator Exit Gate 1') {
        router.push('/exitGate')
      } else if (role === 'Operator Exit Gate 2') {
        router.push('/exitGate')
      }
    } else if (responseLogin.value === null) {
      alert('User tidak ditemukan. Silakan hubungi administrator.')
    }
  })
}
</script>
