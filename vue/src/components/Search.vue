<template>
  <div>
      <div id="search">
       <input type="text" v-model="search" placeholder="Search" />
       
       <select v-model="searchCategory">
              <option v-for="category in searchCategories" v-bind:key="category" :value="category">{{category}}</option>
        </select>
       <button>Search</button> 
       <h3 v-for="comic in filteredComics" v-bind:key="comic" style="color: white;">{{comic.title}}||{{comic.author}}</h3>   
      </div>
  </div>
</template>

<script>
import ComicCollectionService from "@/services/ComicCollectionService.js"
export default {
  data() {
        return {
           /*  collections: [ //placeholder
                {
                    name: "Deadpool Collection",
                    public: true
                },
                {
                    name: "Supermen Who Aren't Superman",
                    public: false
                }
            ],
            comics: [
                {
                    title: "Marvelous Marvel",
                    author: "Neil Armstrong"
                },
                {
                    title: "The Amazing Spider-Man #1",
                    author: "Stan Lee"
                },
                {
                    title: "The Fantastic Four #4",
                    author: "Jack Kirby"
                }
            ], */
            search: "",
            searchCategories: [
                "General",
                "Title",
                "Author"
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
           })
       }
   },
   computed: {
       filteredComics() {
           let filtered = this.comics;
           //Filtering based solely off of comic name and author.
           //Other fields can be added later
            if(this.search != ""){
                filtered = [];
                this.comics.forEach((comic) => {
                    let chosen = false;
                    if(comic.title.toLowerCase().includes(this.search.toLowerCase()) && !chosen){
                        filtered.push(comic);
                        chosen = true;
                    }
                    if(comic.author.toLowerCase().includes(this.search.toLowerCase()) && !chosen){
                        filtered.push(comic);
                        chosen = true;
                    }
                });
            }

            return filtered;
        //    return this.comics.filter(comic => {
        //        return comic.author.toLowerCase().indexOf(this.search.toLowerCase()) != -1;
        //    });
       }
  },
  created (){
    this.loadComics();
  }
}
  
</script>

<style>

</style>