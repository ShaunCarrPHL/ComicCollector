<template>
  <div id="app">    
    <div id='title'>
      <h1 id="maintitle"> A World Of Marvels </h1>
    
      <div class="menu">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{name: 'collection'}" v-if="$store.state.token != ''">Collection</router-link>
        <router-link v-bind:to="{name: 'contact'}">Contact</router-link>
        <router-link v-bind:to="{name: 'search'}">Search</router-link>
         <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{name: 'login'}"></router-link>
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
  grid-template-rows: auto 1fr 50px;
  grid-template-areas: 
    "header header header header header"
    "app-content app-content app-content app-content app-content"
    "app-footer app-footer app-footer app-footer app-footer";  
  font-family: 'Roboto', 'Trebuchet MS', 'Helvetica', 'Arial', sans-serif;
  background-color: rgb(20, 19, 19); 
  color: white;
  overflow: auto;
  padding: 2px 5px 5px 5px;
}

/* #header {
  grid-area: header;
} */

#footer{
  grid-area: app-footer;
  position: relative;

}

#title {
  grid-area: header;
  outline-width: 0px;

}

#maintitle{
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 100px;
  font-family: 'Bangers', cursive;
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
 /*  margin: 0;
  padding: 0; */
  width: 100%;
  /* height: 50px; */

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
  grid-area: 2 / 1 / 2 / 6;
  padding: 1px 16px;
  height: auto;
  background-size: cover;
  margin-top: 25px;
}


/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
  #app {
  grid-template-columns: 1fr;
  grid-template-rows: auto 1fr 50px;
  grid-template-areas: 
    "header"
    "app-content"
    "app-footer"; 
  overflow: scroll;
  padding: 15px 5px 5px 5px;
}
  #title {
    width: 100%;
    height: auto;
    position: relative;
  }
  .menu a {
    float: left;
    }
 div#content {
  grid-area: 2 / 1 / 2 / 1;
  padding: 1px 16px;
  height: auto;
  background-size: cover;
  margin-top: 25px;
  overflow: scroll;
}

  #title>h1 {
    font-size: 30px;
  }
  .menu {
    width: 100%;
    height: auto;
    position: relative;
  }
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
  .menu a {
    text-align: center;
    float: none;
  }
}




</style>
