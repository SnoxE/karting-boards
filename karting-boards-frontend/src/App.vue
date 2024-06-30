<template>
  <div>
    <NavBar />
    <router-view />
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue';

export default {
  name: 'App',
  components: {
    NavBar,
  },
};
</script>

<!-- <style>
/* Add any global styles if necessary */
body {
  font-family: 'Inter', sans-serif;
}
</style> -->