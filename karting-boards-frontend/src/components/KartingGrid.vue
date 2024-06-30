<template>
  <div class="flex flex-col items-center justify-center min-h-screen pt-20">
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Welcome to Karting Boards</h1>
      <p class="text-xl text-gray-600 mt-4">Check the latest times to beat!</p>
    </section>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl">
      <router-link 
        v-for="track in tracks" 
        :key="track.id" 
        :to='`/tracks/${track.id}/leaderboard`'
        class="bg-mcl-orange shadow-md rounded-lg p-4 hover:bg-gray-800 cursor-pointer flex flex-col"
      >
        <h2 class="text-xl font-bold mt-2">{{ track.name }}</h2>
        <p class="text-gray-700">{{ track.city }}</p>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "KartingGrid",
  data() {
    return {
      tracks: []
    };
  },
  async mounted() {
    await this.fetchTracks()
  },
  methods: {
    handleTrackClick(track) {
      alert(`Clicked on: ${track.name}`);
    },
    async fetchTracks() {
      const response = await axios.get('api/tracks')
      const trackList = response.data['content']

      trackList.forEach((track) => {
        this.tracks.push(track)
      });
    }
  },
};
</script>