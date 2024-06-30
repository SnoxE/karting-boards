<template>
  <div class="bg-dark-mode-gray min-h-screen">
    <SeparatorComp />
    <LeaderBoard />
  </div>
</template>

<script>
import SeparatorComp from '@/components/SeparatorComp.vue'
import LeaderBoard from '../components/LeaderBoard.vue'


export default {
  name: 'LeaderBoardView',
  components: {
    LeaderBoard,
    SeparatorComp
  }
}
</script>