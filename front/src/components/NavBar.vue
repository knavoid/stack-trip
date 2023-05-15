<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand>
        <router-link to="/">
          <img src="@/assets/img/logo-removebg-preview.png" alt="">
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/trip">
              여행지 탐색
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/board">
              여행지 게시판
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/bookmark">
              가고싶은 여행지
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/plan">
              여행 계획
            </router-link>
          </b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-btn pill variant="success"><router-link to="/login">로그인</router-link></b-btn>
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

export default {
  name: "NavBar",
  components: {
    BNavbar,
    BNavbarBrand,
    BNavbarToggle,
    BNavbarNav,
    BNavItem,
  },
};
</script>

<style>
#logo {
  width: 8%;
}
img {
  width: 80%;
}
nav a.router-link-exact-active {
  color: #ffffff;
  text-decoration: none;
}

</style>