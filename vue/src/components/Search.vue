<template>
  <div>
      <div id="search">
       <input type="text" v-model="search" placeholder="Search" />
       
       <select v-model="searchCategory">
              <option v-for="category in searchCategories" v-bind:key="category" :value="category">{{category}}</option>
        </select>
        <button v-on:click="searchComics()">Search</button> 
        <div v-for="comic in collectionComics" v-bind:key="comic.id" style="color: gray;">
          <h3>Title: {{comic.comicTitle}}</h3>
          <p>{{comic.description}}</p>
          <img v-bind:src="comic.imageURL" width="40" height="60">
          <add-to-collection :selectedComic="comic"/>
        </div>
      </div>
  </div>
</template>

<script>
import ComicCollectionService from "@/services/ComicCollectionService.js"
import AddToCollection from './AddToCollection.vue'

export default {
  components: { 
    AddToCollection
  },
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
    searchComics(){
        if(this.searchCategory === "Character"){
           ComicCollectionService.getComicsByCharacter(this.search).then(response=>{
             this.$store.commit("SET_COMICS", response.data)
             console.log(response.data);
           }).catch(function (error) {
            if (error.response) {
              // Request made and server responded
              console.log("Response Error");
              console.log(error.response.data);
            } else if (error.request) {
              // The request was made but no response was received
              console.log("Request Error");
              console.log(error.request);
            } else {
              // Something happened in setting up the request that triggered an Error
              console.log("Misc Error");
              console.log('Error', error.message);
            }
          });
         }
         else if(this.searchCategory === "Title"){
           console.log("TODO");
         }
         else if(this.searchCategory === "Author"){
           console.log("TODO");
         }
         else if(this.searchCategory === "Series"){
           console.log("TODO");
         }
         else{
           ComicCollectionService.getComics().then(response=>{
             this.$store.commit("SET_COMICS", response.data)
             console.log(response.data);
           });
         }

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
       },
       userCollections(){
         return this.$store.state.collections;
       }
  },
  created (){

  }
}
  
</script>

<style>

</style>