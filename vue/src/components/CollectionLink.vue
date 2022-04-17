<template>
  <div>
      <p v-for='collection in collections' v-bind:key='collection.collectionName'>{{collection.collectionName}}</p>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>