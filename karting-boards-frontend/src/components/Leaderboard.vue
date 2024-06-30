<template>
  <div class="flex flex-col items-center min-h-screen pt-20 ">
    <section class="w-11/12 max-w-4xl">
      <h2 class="text-2xl font-bold text-white">Leaderboard</h2>
      <span class="text-xl font-bold text-white mb-8">Category Open</span>
      <table class="min-w-full bg-gray-600 rounded-lg overflow-hidden shadow-lg">
        <thead class="bg-gray-800 text-white">
          <tr>
          <th v-for="(columnName, index) in columnNames" :key="index" class="py-2 px-4">
            {{ columnName }}
          </th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="(entry, index) in laptimes" :key="entry.id" :class="{'border-b border-gray-200': index !== laptimes.length - 1}" class="text-white">
            <td class="py-3 px-4 text-center">{{ index + 1 }}</td>
            <td class="py-3 px-4 text-center">{{ formatLapTime(entry.laptimeMinutes, entry.laptimeSeconds, entry.laptimeMilliseconds) }}</td>
            <td class="py-3 px-4 text-center">{{ entry.sessionDate }}</td>
            <td class="py-3 px-4 text-center">{{ entry.sessionTime }}</td>
            <td class="py-3 px-4 text-center">{{ getDriverFullName(entry.driverFirstName, entry.driverLastName) }}</td>
          </tr>
        </tbody>
      </table>
    </section>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LeaderBoard',
  data() {
    return {
      columnNames: [
        '#',
        'Lap Time',
        'Session Date',
        'Session Time',
        'Driver'
      ],
      laptimes: [],
    };
  },
  async mounted() {
    await this.fetchLeaderboard()
  },
  methods: {
    async fetchLeaderboard() {
      const response = await axios.get('api/tracks/' + this.$route.params.id + '/leaderboard')
      const laptimeList = response.data['content']

      laptimeList.forEach((laptime) => {
        this.laptimes.push(laptime)
      });
    },
    formatLapTime(minutes, seconds, milliseconds) {
    const pad = (num, size) => String(num).padStart(size, '0');

    const formattedMinutes = pad(minutes, 2);
    const formattedSeconds = pad(seconds, 2);
    const formattedMilliseconds = pad(milliseconds, 3);

    return `${formattedMinutes}:${formattedSeconds}.${formattedMilliseconds}`;
  },
  getDriverFullName(firstName, lastName) {
      return `${firstName} ${lastName || ''}`.trim();
    }
  }
};
</script>