<template>
  <div>
    <NavBar />
    <KartingGrid />
    <LeaderBoard />
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue';
import KartingGrid from './components/KartingGrid.vue';
import LeaderBoard from './components/LeaderBoard.vue';

export default {
  name: 'App',
  components: {
    NavBar,
    KartingGrid,
    LeaderBoard
  },
};
</script>

<style>
/* Add any global styles if necessary */
body {
  font-family: 'Inter', sans-serif;
}
</style>