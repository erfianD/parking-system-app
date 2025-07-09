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
          <div class="col-12 col-md-3">
            <q-card class="q-pa-md shadow-2">
              <div class="text-h6">Slot Parkir</div>
              <q-card-section>
                <q-table title="Slot available"
                :rows="parkingBill"
                :columns="parkingBillColumn"
                row-key="platNomor"
                />
              </q-card-section>
            </q-card>
          </div>
          <q-card-actions align="center">
            <q-btn label="Submit" color="primary" unelevated @click="submit"/>
          </q-card-actions>
        </q-card>
      </div>

    </div>
  </q-page>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'

const inputParam = reactive({
  platKendaraan: '',
  jenisKendaraan: ''
})

const options = ['Bus', 'Mobil', 'Sepeda Motor']

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
      console.log('Vehicle has been out', response.data)
      parkingBill.value = response.data
    })
    .catch(error => {
      console.error('Submit failed')
    })
}
</script>
