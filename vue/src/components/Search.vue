<template>
  <div>
      <div id="search">
       <input type="text" v-model="search" placeholder="Search" />
       
       <select v-model="searchCategory">
              <option v-for="category in searchCategories" v-bind:key="category" :value="category">{{category}}</option>
        </select>
       <button v-on:click="searchCollection()">Search</button> 
       <h3 v-for="comic in searchResults" v-bind:key="comic.id" style="color: white;">{{comic.comicTitle}}||{{comic.imageURL}}</h3>    -->

     
      </div>
  </div>
</template>

<script>
import ComicCollectionService from "@/services/ComicCollectionService.js"

export default {
  data() {
        return {
           
            searchResults: [],
            search: "",
            searchCategories: [
                "Title",
                "Author",
                "Series",
                "Character"
            ],
            searchCategory: ""
        }
  },
  
   methods:{
       loadComics(){
           //This would pull the comics from the selected collection, and
           //insert them into the this.comics array
           ComicCollectionService.getComics().then(response=>{
             this.$store.commit("SET_COMICS", response.data)
           });
       },
       searchCollection(){

            alert(this.$store.state.comics.length);
            alert(this.search);

            //this.collectionComics.forEach(comic => alert(comic.comicTitle));

           this.searchResults = this.$store.state.comics.filter((comic) => {
                return (comic.comicTitle).toLowerCase().includes((this.search).toLowerCase());
                }
           );

           alert(this.searchResults.length + " in results");
       }

   },
   computed: {
       collectionComics() {
           //Filtering based solely off of comic name and author.
           //Other fields can be added later



           

            return this.$store.state.comics;
        
       }
  },
  created (){
    this.loadComics();
  }
}
  
</script>

<style>

</style>