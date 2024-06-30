<template>
  <div class="min-h-screen bg-dark-mode-gray">
    <SeparatorComp />
    <AddLapTimeForm />
  </div>
</template>

<script>
import AddLapTimeForm from '@/components/AddLapTimeForm.vue'
import SeparatorComp from '@/components/SeparatorComp.vue'

export default {
  name: 'AddCarView',
  components: {
    AddLapTimeForm,
    SeparatorComp
  }
}
</script>
