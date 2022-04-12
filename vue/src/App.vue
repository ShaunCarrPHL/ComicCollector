<template>
  <div id="app">
    <div id='h1'> A World Of Marvels
      </div>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{name: 'login'}"></router-link>
    </div>
    <router-view />
    
  </div>
</template>

<script>

export default {
  components: {
    
  }
}

</script>


<style scoped>
div#h1{
  color: white;
  text-align: center;
  font-size: 50px;
  height: 15vh;
  padding-top: 10vh;
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

/* TODO: Change router-link colors to red/yellow */

</style>
