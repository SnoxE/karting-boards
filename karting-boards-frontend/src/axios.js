import axios from 'axios'

axios.defaults.baseURL = 'https://karting-boards-backend.onrender.com/'
axios.interceptors.request.use(function (config) {
  const token = localStorage.getItem('token') ? JSON.parse(localStorage.getItem('token')) : ''
  config.headers.Authorization = token ? `Bearer ${token}` : ''
  return config
})
