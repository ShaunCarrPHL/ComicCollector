<template>
  <section>
      <h2>My Collection</h2>
      <div class="collection">
        <!--TODO: add v-for to iterate through comics for display-->
       Add Comic to Collection:<new-comic /> 
       <!--TODO: add dropdown menu to select collection for comic-->
       <add-collection collections="collections" /> 
       <input type="text" v-model="search" placeholder="Search" />
       <!-- Dropdown to specify the search type (General search (default), By Series, By Author, By Character, etc...) -->
       <select v-model="searchCategory">
              <option v-for="category in searchCategories" v-bind:key="category" :value="category">{{category}}</option>
        </select>
       <button>Search</button>   
       <h3 v-for="comic in filteredComics" v-bind:key="comic" style="color: white;">{{comic.title}}||{{comic.author}}</h3>   
      </div>
  </section>
</template>

<script>
import NewComic from "@/views/NewComic.vue"
import AddCollection from '../views/AddCollection.vue'

export default {
    data() {
        return {
            collections: [ //placeholder
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
            ],
            search: "",
            searchCategories: [
                "General",
                "Title",
                "Author"
            ],
            searchCategory: ""
        };
    },
   components: {
      NewComic,
      AddCollection
   },
   methods:{
       loadComics(){
           //This would pull the comics from the selected collection, and
           //insert them into the this.comics array
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




   }
}
</script>

<style scoped>
section{
    display: grid;
    grid-area: section;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "header header header header header"
    "section section section section section"
    ". app-footer app-footer app-footer .";


     margin-right: 50%;
    background-color: rgb(66, 64, 64);
    text-align: center;
    height: 90vh;
    border-radius: 25px;
    color: white;
    font-size: 20px;
}

</style>