<template>
  <q-page class="flex flex-center bg-grey-2">
    <q-card class="q-pa-lg shadow-2" style="min-width: 300px; width: 100%; max-width: 400px">
      <q-card-section class="text-center">
        <div class="text-h6">Login</div>
      </q-card-section>

      <q-card-section>
        <q-input
          v-model="username"
          label="Username"
          outlined
          dense
          class="q-mb-md"
        />
        <q-input
          v-model="password"
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
        />
      </q-card-actions>
    </q-card>
  </q-page>
</template>

<!-- <script setup>
import { ref } from 'vue'

const username = ref('')
const password = ref('')

function handleLogin () {
  if (!username.value || !password.value) {
    alert('Username dan password harus diisi.')
    return
  }

  // Simulasi login
  if (username.value === 'admin' && password.value === '1234') {
    alert('Login berhasil!')
    // TODO: redirect atau set token di sini
  } else {
    alert('Username atau password salah!')
  }
}
</script> -->
