<template>
  <section>
      <h2>My Collection</h2>
      <div class="collection">
        <!--TODO: add v-for to iterate through comics for display-->
       Add Comic to Collection:<new-comic /> 
       <!--TODO: add dropdown menu to select collection for comic-->
       <add-collection /> 
       <input type="text" v-model="search" placeholder="Search" />
       <button>Search</button>      
      </div>
  </section>
</template>

<script>
import NewComic from "@/views/NewComic.vue"
import AddCollection from '../views/AddCollection.vue'

export default {
    data() {
        return {
            comics: [],
            search: ""
        };
    },
   components: {
      NewComic,
      AddCollection
   },
   computed: {
       filteredComics() {
           return this.comics.filter(comic => {
               return comic.author.toLowerCase().indexOf(this.search.toLowerCase()) != -1;
           })
       }
   }
}
</script>

<style>

</style>