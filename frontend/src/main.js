import { createApp } from 'vue';
import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router';
import FilmDetail from './components/FilmDetail.vue';
import FilmList from "@/components/FilmList.vue";

const routes = [
    { path: '/', redirect: '/films' },
    { path: '/films', component: FilmList },
    { path: '/films/:id', component: FilmDetail, props: true },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);
app.use(router);
app.mount('#app');