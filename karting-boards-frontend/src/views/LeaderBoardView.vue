<template>
  <div class="bg-dark-mode-gray min-h-screen">
    <SeparatorComp />
    <LeaderBoards />
  </div>
</template>

<script>
import SeparatorComp from '@/components/SeparatorComp.vue'
import LeaderBoards from '@/components/LeaderBoards.vue'


export default {
  name: 'LeaderBoardView',
  components: {
    LeaderBoards,
    SeparatorComp
  }
}
</script>