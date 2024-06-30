<!-- <template>
  <nav class="bg-gray-800 p-4 w-full fixed top-0 left-0 z-10">
    <div class="container mx-auto flex justify-between items-center">
      <div class="text-white text-xl font-bold">Karting Boards</div>
      <ul class="flex gap-x-4">
        <li><a href="#" class="text-gray-300 hover:text-white">Log In</a></li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBar",
};
</script> -->

<template>
  <nav id="navbar" class="fixed z-10 w-full bg-black">
    <div class="mx-auto flex max-w-screen-xl flex-wrap items-center justify-between">
      <router-link to="/" class="flex items-center p-4">
        <span class="self-center whitespace-nowrap text-2xl font-semibold dark:text-white"
          >Karting Boards</span
        >
      </router-link>
      <button
        data-collapse-toggle="navbar-default"
        type="button"
        class="inline-flex h-10 w-10 items-center justify-center rounded-lg p-2 text-sm text-white hover:bg-gray-800 focus:outline-none md:hidden"
        @click="toggleMenu()"
      >
        <span class="sr-only">Open main menu</span>
        <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
          <path
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M1 1h15M1 7h15M1 13h15"
          />
        </svg>
      </button>
      <div id="navbar-default" class="hidden w-full p-4 md:block md:w-auto">
        <ul v-show="!loggedIn" class="mt-0 flex space-x-8 bg-transparent p-0 font-medium">
          <li v-for="url in urls" :key="url.name">
            <NavBarItem :url="url.url">{{ url.name }}</NavBarItem>
          </li>
        </ul>
        <ul v-show="loggedIn" class="mt-0 flex space-x-8 bg-transparent p-0 font-medium">
          <li v-for="url in loggedUrls" :key="url.name">
            <NavBarItem
              v-if="url.id === 'user'"
              @mouseover="showUserMenu = true"
              @mouseleave="showUserMenu = false"
              >{{ url.name }}</NavBarItem
            >
            <NavBarItem v-if="url.id === 'logout'" @click=logout()>{{ url.name }}</NavBarItem>
            <NavBarItem v-else :url="url.url">{{ url.name }}</NavBarItem>
            <div
              v-if="url.id === 'user'"
              v-show="showUserMenu"
              class="absolute flex flex-col bg-light-gray p-4 text-white ease-in-out"
              @mouseover="showUserMenu = true"
              @mouseleave="showUserMenu = false"
            >
              <router-link to="/user/profile">Profile</router-link>
              <router-link to="/user/samochody">Samochody</router-link>
              <router-link to="/laptime">Add Lap Time</router-link>
              <!-- <router-link
                v-if="userRole === 'ADMIN' || userRole === 'EMPLOYEE'"
                to="/user/kalendarz"
                >Kalendarz</router-link
              > -->
              <router-link to="" @click="logout()">Log Out</router-link>
            </div>
          </li>
        </ul>
      </div>
      <div
        id="navbar-mobile"
        class="hidden min-h-screen w-full translate-x-full bg-light-gray bg-opacity-30 backdrop-blur-sm transition-transform duration-300 md:hidden"
      >
        <ul v-if="!loggedIn" class="flex flex-col pl-10 pt-10 font-medium">
          <li v-for="url in urls" :key="url.id">
            <NavBarItem v-if="url.id === 'login'" :url="url.url">{{ url.name }}</NavBarItem>
          </li>
        </ul>
        <ul v-else class="flex flex-col pl-10 pt-10 font-medium text-white">
          <router-link to="/laptime">Add Lap Time</router-link>
          <router-link to="" @click="logout()">Log Out</router-link>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import NavBarItem from '@/components/NavBarItem.vue'
import { useAuthStore } from '@/stores/auth'
import { isLoggedIn } from '@/authStatus'

export default {
  name: 'NavBar',
  components: {
    NavBarItem
  },
  setup() {
    return { loggedIn: isLoggedIn }
  },
  data() {
    return {
      urls: [
        { id: 'login', name: 'Log In', url: '/login' },
      ],
      loggedUrls: [
        // { id: 'profile', name: 'Profile', url: '/user' },
        { id: 'addLaptime', name: 'Add Lap Time', url: '/laptime' },
        { id: 'logout', name: 'Log Out' }
      ],
      showUserMenu: false,
      userRole: null
    }
  },
  created() {
    this.userRole = JSON.parse(localStorage.getItem('role'))
  },
  methods: {
    toggleMenu() {
      const navbar = document.querySelector('#navbar-mobile')
      navbar.classList.toggle('translate-x-1/4')
      navbar.classList.toggle('translate-x-full')
      navbar.classList.toggle('hidden')
    },
    navScroll() {
      const navbar = document.querySelector('#navbar')

      navbar.classList.toggle('bg-mcl-orange')
    },
    logout() {
      useAuthStore().logout()
      isLoggedIn.value = false
      localStorage.removeItem('user')
      localStorage.removeItem('token')
    }
  }
}
</script>

<style>
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}
</style>
