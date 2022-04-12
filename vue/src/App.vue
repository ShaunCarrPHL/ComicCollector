<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{name: 'login'}"></router-link>
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Rubik+Moonrocks&display=swap" rel="stylesheet">
    </div>
      
    <div id='h1'> A World Of Marvels</div>
    <body class="body">
    <div class="parent">
      <div class="sidebar">
        <a class="active" href="#home">Home</a>
        <a href="#collection">Collection</a>
        <a href="#contact">Contact</a>
        <a href="#search">Search</a>
      </div>
    </div>
    <div>
    <router-view />
    </div>

    <div>
      <footer class="footer">Data provided by Marvel. © 2014 Marvel</footer>
    </div>

    </body>
  </div>

</template>

<script>

export default {
  components: {
    
  }
}

</script>


<style scoped>

.body{
  display: flex;
}

.footer{
  flex-shrink: 0;
}

div#nav{
  color: yellow;
  font-size: 20px;
}

div#h1{
  @import url('https://fonts.googleapis.com/css2?family=Rubik+Moonrocks&display=swap');
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 100px;
  font-family: 'Rubik Moonrocks', cursive;
  height: 15vh;
  padding-top: 10vh;
  border-radius: 25px;
  background: rgb(150, 9, 9);
  padding: 20px;
}

div#app{
  font-family: 'Roboto', 'Trebuchet MS', 'Helvetica', 'Arial', sans-serif;
  background-color: rgb(20, 19, 19); 
  color: white;
  height: 100vh;
}

a{
  color: yellow;
}

div#parent{
  display: flex;
}

.sidebar {
  margin: 0;
  padding: 0;
  width: 200px;
  background-color: rgb(122, 119, 119);
  position: fixed;
  height: 100%;
  overflow: auto;

}

/* Sidebar links */
.sidebar a {
  display: block;
  color: rgb(245, 235, 235);
  padding: 16px;
  text-decoration: none;
}

/* Active/current link */
.sidebar a.active {
  background-color: #a5331f;
  color: white;
}

/* Links on mouse-over */
.sidebar a:hover:not(.active) {
  background-color: #d6d57c;
  color: white;
}

/* Page content. The value of the margin-left property should match the value of the sidebar's width property */
div.content {
  margin-left: 200px;
  padding: 1px 16px;
  height: 1000px;
}

/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
  .sidebar {
    width: 100%;
    height: auto;
    position: relative;
  }
  .sidebar a {float: left;}
  div.content {margin-left: 0;}
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
  .sidebar a {
    text-align: center;
    float: none;
  }
}

/* TODO: Change router-link colors to red/yellow */

</style>
