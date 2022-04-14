<template>
  <div id="main">
      
      <div class="collection">
        <h2>My Collection</h2>
        <!--TODO: add v-for to iterate through comics for display-->
       Add Comic to Collection:<new-comic /> 
       <!--TODO: add dropdown menu to select collection for comic-->
      </div>
      <div id="addCollection">
       <add-collection collections="collections" /> 
      </div>
      </div>
      
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
#main{
    display: grid;
    grid-template-columns: 1fr, 1fr, 1fr, 1fr, 1fr;
    grid-template-areas: 
    "collection collection collection addCollection addCollection";

    margin-right: 5px;
    /*background-color: rgb(66, 64, 64);*/
    background-image: linear-gradient(to right,rgba(255,0,0,0), rgba(255,0,0,1));
    opacity: 0.7;
    text-align: center;
    height: 110vh;
    border-radius: 25px;
    color: white;
    font-size: 20px;
    
    
}

.collection{
    
    grid-area: collection;
    padding: 25px;
    margin: 25px;
    
}
 .addCollection{
    
    grid-area: addCollection;
    padding: 25px;
    margin: 25px;
}


</style>