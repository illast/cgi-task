<template>
  <div id="app">
    <div class="header">
      <router-link to="/films" class="films-button">
        <button>Films</button>
      </router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
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

.header {
  position: fixed;
  top: 0;
  right: 0;
  padding: 10px;
}
</style>
