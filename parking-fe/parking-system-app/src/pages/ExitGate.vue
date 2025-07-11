<template>
  <q-page class="bg-grey-2">
    <div class="q-pa-md row justify-around items-start q-gutter-md">
      <div class="col-12 col-md-4">
        <q-card class="q-pa-lg shadow-2">
          <div class="text-h6">Exit Gate</div>
          <q-card-section>
            <q-input
              v-model="inputParam.platKendaraan"
              label="Plat Nomor"
              outlined
              dense
              class="q-mb-md"
              @update:model-value="val => inputParam.platKendaraan = val.toUpperCase()"
            />
            <q-select filled v-model="inputParam.jenisKendaraan" :options="options" label="Jenis Kendaraan" />
          </q-card-section>
          <q-card-actions align="center">
            <q-btn label="Submit" color="primary" unelevated @click="submit"/>
            <q-btn label="Logout" color="primary" unelevated @click="logout"/>
          </q-card-actions>
        </q-card>
      </div>

      <q-dialog v-model="dialogVisible">
      <q-card>
         <q-card-section>
          <div><strong>Total Bayar:</strong> {{ responseBill.totalBayar }}</div>
          <div><strong>Total Waktu Parkir:</strong> {{ responseBill.totalWaktu }}</div>
          <div><strong>Tarif Per Jam</strong> {{ responseBill.tarifParkir }}</div>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat label="Bayar" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
    </div>
  </q-page>
</template>

<script setup>
import { ref, reactive } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const inputParam = reactive({
  platKendaraan: '',
  jenisKendaraan: ''
})

const options = ['Bus', 'Mobil', 'Sepeda Motor']

const responseBill = ref({
  platKendaraan: '',
  jenisKendaraan: '',
  tarifParkir: '',
  jamMasuk: '',
  jamKeluar: '',
  totalBayar: '',
  totalWaktu: ''
})
const dialogVisible = ref(false)

const parkingBill = ref([])
const parkingBillColumn = ref([
  {
    name: 'platNomor',
    label: 'Plat Nomor',
    align: 'left',
    field: 'platNomor'
  },
  {
    name: 'tarifParkir',
    label: 'Tarif Parkir',
    align: 'left',
    field: 'tarifParkir'
  },
  {
    name: 'jamMasuk',
    label: 'Jam Masuk',
    align: 'left',
    field: 'jamMasuk'
  },
    {
    name: 'jamKeluar',
    label: 'Jam Keluar',
    align: 'left',
    field: 'jamKeluar'
  },
  {
    name: 'totalWaktu',
    label: 'Durasi Parkir',
    align: 'left',
    field: 'totalWaktu'
  },
  {
    name: 'totalTarif',
    label: 'Biaya Parkir',
    align: 'left',
    field: 'totalTarif'
  }
])
function submit() {
  axios.patch('http://localhost:9095/vehicle/exit', inputParam)
    .then(response => {
      const data = response.data
      responseBill.value = {
        platNomor: data.platNomor,
        jenisKendaraan: data.jenisKendaraan,
        tarifParkir: data.tarifParkir,
        totalBayar: data.totalTarif,
        totalWaktu: data.totalWaktu
      }
      dialogVisible.value = true
    })
    .catch(error => {
      console.error('Submit failed')
    })
}
// function getSlotParkir() {
//   axios.get('http://localhost:9095/parking/getAllSlot')
//   .then(response => {
//     console.log(response.data)
//     parkingRow.value = response.data
//   })
// }
function logout() {
  router.push('/login')
}
// onMounted(() => {
//   getSlotParkir()
// })
</script>
