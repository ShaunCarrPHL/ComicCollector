<template>
  <div class="home">
    <h1>Home</h1>
    <p>Welcome back! Here is your collection.</p>
    
    <router-link v-bind:to="{name: 'collection'}">View Your Collection</router-link>
  </div>
</template>

<script>


export default {
  name: "home",
  components: {
    
  }
};
</script>
<style scoped>
div#home{
  display: flex;
  align-content: center;
  justify-content: center;
}
</style>
