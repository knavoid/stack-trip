<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark faded">
      <b-navbar-brand>
        <router-link to="/">
          <img id="logo" src="@/assets/img/stacktrip-removebg.png" alt="" />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/trip"> ATTRACTION </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/bookmark"> FAVORITES </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/plan"> TRIP PLAN </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/board"> COMMUNITY </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/qna"> Q&A </router-link>
          </b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item v-if="user">
            <span>{{ user.name }}님 안녕하세요 </span>
            <b-button class="mr-1 ml-1" pill variant="success"
              ><router-link to="/profile">마이 페이지</router-link></b-button
            >
            <b-button
              class="mr-1 ml-1"
              pill
              variant="warning"
              @click="deleteToken"
              >로그아웃</b-button
            >
          </b-nav-item>
          <b-button v-else pill variant="success"
            ><router-link to="/login">로그인</router-link></b-button
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import {
  BNavbar,
  BNavbarBrand,
  BNavbarToggle,
  BNavbarNav,
  BNavItem,
} from "bootstrap-vue";
import http from "@/util/http-common.js";

export default {
  name: "NavBar",
  components: {
    BNavbar,
    BNavbarBrand,
    BNavbarToggle,
    BNavbarNav,
    BNavItem,
  },
  data() {
    return {
      user: null,
    };
  },
  mounted() {
    this.getUser();
    console.log(this.user);
  },
  methods: {
    async getUser() {
      const token = sessionStorage.getItem("token");
      if (token) {
        try {
          const response = await http.get(`/user`, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          if (response.status === 200) {
            this.user = response.data;
          }
        } catch (error) {
          console.log(error);
        }
      }
    },
    deleteToken() {
      sessionStorage.removeItem("token");
      this.$router.push("/");
      location.reload();
    },
  },
};
</script>

<style>
#logo {
  width: 60%;
}
img {
  width: 80%;
}
nav a.router-link-exact-active {
  color: #ffffff;
  text-decoration: none;
}
.navbar-brand {
  width: 200px;
}
.nav-link {
  margin: 0 20px;
}
</style>