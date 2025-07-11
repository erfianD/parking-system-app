<template>
  <q-page class="bg-grey-2">
    <div class="q-pa-md row justify-around items-start q-gutter-md">
      <div class="col-12 col-md-4">
        <q-card class="q-pa-lg shadow-2">
          <div class="text-h6">Entry Gate</div>
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

      <div class="col-12 col-md-3">
        <q-card class="q-pa-md shadow-2">
          <div class="text-h6">Slot Parkir</div>
          <q-card-section>
            <q-table title="Slot available"
            :rows="parkingRow"
            :columns="parkingInfo"
            row-key="jenisKendaraan"
            />
          </q-card-section>
        </q-card>
      </div>

    </div>
  </q-page>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const inputParam = reactive({
  platKendaraan: '',
  jenisKendaraan: ''
})

const options = ['Bus', 'Mobil', 'Sepeda Motor']

const parkingRow = ref([])
const parkingInfo = ref([
  {
    name: 'jenisKendaraan',
    label: 'Jenis Kendaraan',
    align: 'left',
    field: 'jenisKendaraan'
  },
    {
    name: 'totalKendaraan',
    label: 'Jumlah Kendaraan',
    align: 'left',
    field: 'totalKendaraan'
  },
    {
    name: 'slotMaks',
    label: 'Kapasitas Parkir',
    align: 'left',
    field: 'slotMaks'
  },
    {
    name: 'slotAvailable',
    label: 'Parkir Tersedia',
    align: 'left',
    field: 'slotAvailable'
  }
])
function submit() {
  axios.post('http://localhost:9095/vehicle/entry', inputParam)
    .then(response => {
      console.log('Data has been submitted', response.data)
    })
    .catch(error => {
      console.error('Submit failed')
    })
}

function getSlotParkir() {
  axios.get('http://localhost:9095/parking/getAllSlot')
  .then(response => {
    console.log(response.data)
    parkingRow.value = response.data
  })
}

function logout() {
  router.push('/login')
}

onMounted(() => {
  getSlotParkir()
})
</script>
