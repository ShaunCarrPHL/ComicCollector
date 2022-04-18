<template>
  <div id="searchPage">
      <div id="search">
       <input type="text" v-model="search" placeholder="Search" />
       
       <select v-model="searchCategory">
              <option v-for="category in searchCategories" v-bind:key="category" :value="category">{{category}}</option>
        </select>
        <button v-on:click="searchComics()">Search</button> 
        <div id="parentDiv">
          <div id ="listComics" v-for="comic in collectionComics" v-bind:key="comic.id" style="color: white;">
            <h3 id="headerTitle">Title: {{comic.comicTitle}}</h3>
            <p id="description">{{comic.description}}</p>
            <img id="image" v-bind:src="comic.imageURL" width="40" height="60">
            <add-to-collection :selectedComic="comic"/>
          </div>
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
           ComicCollectionService.getComicsByTitle(this.search).then(response=>{
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
         else if(this.searchCategory === "Author"){
           ComicCollectionService.getComicsByAuthor(this.search).then(response=>{
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
         else if(this.searchCategory === "Series"){
           ComicCollectionService.getComicsBySeries(this.search).then(response=>{
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
       },
      loadCollections(){
        ComicCollectionService.getCollections(this.$store.state.user.id).then(response => {
          this.$store.commit("SET_COLLECTIONS", response.data)
        });
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
    this.loadCollections();
  }
}
  
</script>

<style>
div#searchPage{
   display: grid;
   grid-template-columns: 1fr, 1fr, 1fr, 1fr, 1fr;
   grid-template-areas: 
   "search search search search search"
   "parentDiv parentDiv parentDiv parentDiv parentDiv";
   background-image: linear-gradient(to right,rgba(255,0,0,0), rgba(255,0,0,1));
    opacity: 0.7;
    height: 85vh;
    border-radius: 25px;
    color: white;
    font-size: 20px;
    
    
}

div#parentDiv{
  grid-area: parentDiv;
  display: flex;
  flex-direction: row;
  flex-flow: row wrap;
  justify-content: space-evenly;
  height: 60vh;
}
div#listComics{
  font-size: 12px;
  outline: 5px dotted green;
}



@media screen and (max-height: 700px) {
  
  #searchPage{

  }
}

</style>