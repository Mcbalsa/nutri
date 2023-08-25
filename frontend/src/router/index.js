import { createRouter, createWebHistory } from "vue-router";
import AdminPage from "../pages/AdminPage.vue";
import HomePage from "../pages/HomePage.vue";
import ProfilePage from "../pages/ProfilePage.vue";

const routes = [
  {
    path: "/",
    name: "HomePage",
    component: HomePage,
  },
  {
    path: "/profile",
    name: "ProfilePage",
    component: ProfilePage,
  },
  {
    path: "/admin",
    name: "AdminPage",
    component: AdminPage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
