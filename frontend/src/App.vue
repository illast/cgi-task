<template>
  <div id="app">
    <FilmList />
  </div>
</template>

<script>
import FilmList from './components/FilmList.vue';

export default {
  name: 'App',
  components: {
    FilmList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
