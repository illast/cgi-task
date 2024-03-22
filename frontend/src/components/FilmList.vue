<template>
  <div>
    <div class="filter-container">
      <b>Filter by:</b>
      <label for="genre">Genre</label>
      <select v-model="selectedGenre">
        <option value="">All Genres</option>
        <option v-for="genre in genres" :key="genre">{{ genre }}</option>
      </select>

      <label for="year">Year</label>
      <input type="number" id="year" name="year" min="1900" max="2024" v-model="selectedYear">

      <label for="rating">Rating</label>
      <input type="range" id="rating" name="rating" min="0" max="10" v-model="selectedRating">
      <span> ≥ {{ selectedRating }}</span>

      <label for="language">Language</label>
      <select id="language" v-model="selectedLanguage">
        <option value="">Select Language</option>
        <option v-for="(name, code) in languages" :key="code" :value="code">{{ name }}</option>
      </select>

      <button @click="applyFilters">Apply</button>
      <button @click="resetFilters">Reset</button>
    </div>

    <div class="table-container">
      <table>
        <thead>
        <tr>
          <th>Image</th>
          <th>Title</th>
          <th>Year</th>
          <th>Rating</th>
          <th>Overview</th>
          <th>Genre</th>
          <th>Adult</th>
          <th>Language</th>
          <th>Start Time</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="film in filteredFilms" :key="film.id">
          <td>
            <img :src="'https://image.tmdb.org/t/p/w500' + film.imagePath" alt="Film Poster" width="200"
                 @click="handleImageClick(film.id)"
                 :class="{ 'clickable': film.id }"></td>
          <td>{{ film.title }}</td>
          <td>{{ film.dateYear }}</td>
          <td>{{ film.rating }}</td>
          <td>{{ film.overview }}</td>
          <td>{{ film.genre }}</td>
          <td>{{ film.adult ? 'Yes' : 'No' }}</td>
          <td>{{ languages[film.language] || film.language }}</td>
          <td>{{ film.startTime }}</td>
        </tr>
        </tbody>
      </table>
    </div>

    <div>
      <button @click="prevPage" :disabled="currentPage === 0 || filteredFilms.length === 0">Previous</button>
      <span v-if="totalPages > 0">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
      <span v-else>No films found</span>
      <button @click="nextPage" :disabled="currentPage === totalPages - 1 || filteredFilms.length === 0">Next</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FilmList',
  data() {
    return {
      films: [],
      filteredFilms: [],
      currentPage: 0,
      totalPages: 1,
      selectedGenre: '',
      selectedYear: null,
      selectedRating: 0,
      selectedLanguage: '',
      genres: ['Comedy', 'Thriller', 'Horror'],
      applyFiltersClicked: false,
      languages: {
        'en': '🇺🇸 English',
        'es': '🇪🇸 Spanish',
        'zh': '🇨🇳 Chinese',
        'hi': '🇮🇳 Hindi',
        'fr': '🇫🇷 French',
        'ja': '🇯🇵 Japanese',
        'de': '🇩🇪 German',
        'ko': '🇰🇷 Korean',
        'it': '🇮🇹 Italian',
        'ru': '🇷🇺 Russian'
      }
    };
  },
  mounted() {
    this.fetchFilms();
  },
  methods: {
    fetchFilms() {
      let url = `http://localhost:8080/api/films?page=${this.currentPage}`;
      if (this.applyFiltersClicked) {
        if (this.selectedGenre) {
          url += `&genre=${this.selectedGenre}`;
        }
        if (this.selectedYear) {
          url += `&year=${this.selectedYear}&beforeYear=true`;
        }
        if (this.applyFiltersClicked && this.selectedLanguage) {
          url += `&language=${this.selectedLanguage}`;
        }
        url += `&rating=${this.selectedRating}`;
      }
      axios.get(url)
          .then(response => {
            this.films = response.data.content;
            this.totalPages = response.data.totalPages;
            this.filteredFilms = this.films;
          })
          .catch(error => {
            console.error('Error fetching films:', error);
          });
    },
    nextPage() {
      if (this.currentPage < this.totalPages - 1) {
        this.currentPage++;
        this.fetchFilms();
      }
    },
    prevPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.fetchFilms();
      }
    },
    applyFilters() {
      this.applyFiltersClicked = true;
      this.currentPage = 0;
      this.fetchFilms();
    },
    resetFilters() {
      this.selectedGenre = '';
      this.selectedYear = null;
      this.selectedRating = 0;
      this.selectedLanguage = '';
      this.applyFiltersClicked = false;
      this.currentPage = 0;
      this.fetchFilms();
    },
    handleImageClick(filmId) {
      this.$router.push(`/films/${filmId}`);
    }
  }
}
</script>

<style>
.filter-container {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}

.table-container {
  margin-top: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
}

.clickable {
  cursor: pointer;
}
</style>
