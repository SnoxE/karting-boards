<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex pt-6 text-2xl">
      <span>Add a new session</span>
    </div>
    <div class="mx-auto mt-4 flex flex-col">
      <form
        method="post"
        class="login-form z-2 mx-auto flex w-80 flex-col p-6"
        @submit.prevent="addSession"
      >
        <div class="mx-auto my-2 flex w-full flex-col gap-2">
          <select
            id="selector"
            v-model="trackId"
            name="selector"
            class="h-10 rounded bg-light-gray pl-2"
          >
            <option v-for="track in trackIdList" :key="track.id" :value="track.id">
              {{ track.name }}
            </option>
          </select>
          <select
            id="selector"
            v-model="sessionId"
            name="selector"
            class="h-10 rounded bg-light-gray pl-2"
          >
            <option v-for="session in sessionIdList" :key="session.id" :value="session.id">
              {{ session.date }} {{ session.time }}
            </option>
          </select>
          <InputField
            id="laptime"
            v-model="time"
            class="mb-2 h-10 rounded bg-light-gray pl-2"
            type="text"
            name="laptime"
            placeholder="00:26.458"
            required
          />
        </div>
        <button
          type="submit"
          class="submit-btn mx-auto mt-6 w-full rounded-lg bg-gray-500 p-2 hover:bg-mcl-orange"
        >
          Dodaj
        </button>
        <span v-if="wasLapTimeAdded" class="mt-1 text-sm text-green-600"
          >Pomyślnie dodano czas</span
        >
      </form>
    </div>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue'
import axios from 'axios'

export default {
  name: 'AddCarForm',
  components: { InputField },
  data() {
    return {
      trackIdList: [],
      sessionIdList: [],
      trackId: null,
      sessionId: null,
      driverId: null,
      time: '',
      wasLapTimeAdded: false
    }
  },
  async mounted() {
    await this.fetchUserId()
    await this.fetchTracks()
  },
  watch: {
    trackId(newTrackId) {
      if (newTrackId) {
        this.fetchSessions(newTrackId)
      }
    }
  },
  methods: {
    async fetchUserId() {
      const response = await axios.get('api/users/user')
      this.driverId = response.data['id']
    },
    async fetchTracks() {
      const response = await axios.get('api/tracks')
      const trackList = response.data['content']

      trackList.forEach((track) => {
        this.trackIdList.push(track)
      });
    },
    async addSession() {
        console.log(this.trackId, this.sessionId, this.driverId, this.time);

      const response = await axios.post('api/laptime', {
        trackId: this.trackId,
        sessionId: this.sessionId,
        driverId: this.driverId,
        time: this.time
      })

      if (response.status == 201) {
        this.wasLapTimeAdded = true
      }
    }
  }
}
</script>

<script setup>
// import axios from 'axios'
// import { ref } from 'vue'

// const date = ref(new Date())
</script>
