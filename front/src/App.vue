<template>
  <div id="app">
    <nav-bar></nav-bar>
    <div class="container">
      <router-view/>
    </div>
  </div>
</template>
<script>
import NavBar from "@/components/NavBar.vue";
export default {
  components: { NavBar },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

a{
  color: #ffffff;
    text-decoration: none;
}

nav a {
  font-weight: bold;
  color: #ffffff;
}

nav a.router-link-exact-active {
  color: #ffffff;

}
.container{
    max-width: 80%;
    margin: 3% 2% 0 2%;
    }
</style>
