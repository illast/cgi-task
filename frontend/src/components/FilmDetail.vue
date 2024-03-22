<template>
  <div class="container">
    <div v-if="film" class="content">
      <h1>{{ film.title }}</h1>
      <div class="image-container">
        <img :src="'https://image.tmdb.org/t/p/w500' + film.imagePath" alt="Film Poster" class="film-poster">
      </div>
      <div class="details">
        <p><b>Year:</b> {{ film.dateYear }}</p>
        <p><b>Rating:</b> {{ film.rating }}</p>
        <p><b>Overview:</b> {{ film.overview }}</p>
        <p><b>Genre:</b> {{ film.genre }}</p>
        <p><b>Adult:</b> {{ film.adult ? 'Yes' : 'No' }}</p>
        <p><b>Language:</b> {{ film.language }}</p>
        <p><b>Start Time:</b> {{ film.startTime }}</p>
      </div>
    </div>
    <div v-else class="loading">
      <p>Loading...</p>
    </div>
    <button v-if="film" class="select-button">Сhoose seats</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FilmDetail',
  data() {
    return {
      film: null
    };
  },
  mounted() {
    this.fetchFilm();
  },
  methods: {
    fetchFilm() {
      const filmId = this.$route.params.id;
      axios.get(`http://localhost:8080/api/films/${filmId}`)
          .then(response => {
            this.film = response.data;
          })
          .catch(error => {
            console.error('Error fetching film:', error);
          });
    }
  }
};
</script>

<style>
.container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.content {
  text-align: left;
}

.image-container {
  margin-bottom: 20px;
}

.film-poster {
  width: 100%;
  height: auto;
}

.details p {
  margin-bottom: 10px;
}

.loading {
  text-align: center;
  font-style: italic;
}

.select-button {
  float: right;
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  border-radius: 5px;
}

.select-button:hover {
  background-color: #45a049;
}

.select-button:active {
  background-color: #3e8e41;
}
</style>
