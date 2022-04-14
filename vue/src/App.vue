<template>
  <div id="app">
    
    <div id="nav">
    <!--   <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{name: 'login'}"></router-link> -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bangers&display=swap" rel="stylesheet">
    </div>
      
    
    <div id='title'>
      <div> A World Of Marvels </div>
    
      <div class="menu">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
          <router-link v-bind:to="{name: 'login'}"></router-link>
         <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{name: 'collection'}" v-if="$store.state.token != ''">Collection</router-link>
        <router-link v-bind:to="{name: 'contact'}">Contact</router-link>
        <router-link v-bind:to="{name: 'search'}">Search</router-link>
      </div>
    </div>
    

    
    <div id="content" :style="{'background-image':'url(https://wallpaperaccess.com/full/430398.jpg)'}">
      <router-view />
    </div>

    <div id="footer">
      <footer>Data provided by Marvel. © 2014 Marvel</footer>
    </div>
  
  </div>
</template>

<script>

export default {
  components: {
    
  }
}

</script>


<style scoped>

#app {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas: 
    "header header header header header"
    "app-content app-content app-content app-content app-content"
    ". app-footer app-footer app-footer ."
    /*
     "header header header header header"
     "nav-menu app-content app-content app-content app-content"
      ". app-footer app-footer app-footer ."
     */
  ;  
  font-family: 'Roboto', 'Trebuchet MS', 'Helvetica', 'Arial', sans-serif;
  background-color: rgb(20, 19, 19); 
  color: white;
}

#footer{
  grid-area: app-footer;

}

div#nav{
  color: yellow;
  font-size: 20px;
}

div#title{
  @import url('https://fonts.googleapis.com/css2?family=Bangers&display=swap');
  grid-area: header ;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 100px;
  font-family: 'Bangers', cursive;
  height: 15vh;
  padding-top: 10vh;
  border-radius: 25px;
  background: rgb(150, 9, 9);
  padding: 20px;
}

/*Fix the grid area of app*/


a{
  color: yellow;
}

.menu {
  font-size: 1rem;
  margin: 0;
  padding: 0;
  width: 100%;
  /* background-color: rgb(122, 119, 119); */
  /* position: fixed; */
  height: 50px;
  /* overflow: auto; */
  padding-bottom: 35px;
  margin-bottom: 35px;
  margin-top: 10px;

}

/* Sidebar links */
.menu a {
  color: rgb(245, 235, 235);
  padding: 16px;
  text-decoration: none;
}

/* Active/current link */
.menu a:active {
  background-color: #a5331f;
  color: white;
}

/* Links on mouse-over */
.menu a:hover:not(active) {
  background-color: #d6d57c;
  color: white;
}

/* Page content. The value of the margin-left property should match the value of the sidebar's width property */
div#content {
   grid-area: app-content;
  padding: 1px 16px;
  height: 800px;
  background-size: cover;
  margin-top: 25px;
}


/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
  .menu {
    width: 100%;
    height: auto;
    position: relative;
  }
  .menu a {float: left;}
  div.content {margin-left: 0;}
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
  .menu a {
    text-align: center;
    float: none;
  }
}

/* TODO: Change router-link colors to red/yellow */

</style>
