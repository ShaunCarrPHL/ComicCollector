<template>
  <div>
      <h2>Comics</h2>
      <comic v-for="comic in comic" v-bind:key="comic.id"/>
  </div>
</template>

<script>
import Comic from "@/components/Comic.vue";
export default {
    name: "comic-list",
    computed: {
        comics() {
            return this.$store.state.comics;
        }
        //TODO: Search feature
    },
    components: {
        Comic
    }
}
</script>

<style>

</style>