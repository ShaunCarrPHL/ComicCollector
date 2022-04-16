<template>
  <div id="main">
      
      <div class="collection">
        <h2>My "{{selectedCollection.title}}" Collection</h2>
        <!--TODO: add v-for to iterate through comics for display-->
       Add Comic to Collection:<new-comic /> 
       <!--TODO: add dropdown menu to select collection for comic-->
        <table id="comicList">
          <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
            </tr>
          </thead>
          <tbody>
              <tr v-for='comic in comics' v-bind:key='comic.comicTitle'>
                  <td>{{comic.comicTitle}}</td>
                  <td>{{comic.author}}</td>
              </tr>
          </tbody>
      </table>
      </div>
      <div id="addCollection">
       <add-collection/> 

       <h3>Collections</h3>
       <p v-for='collection in collections' v-bind:key='collection.collectionName'>{{collection.collectionName}}</p>
      </div>
    </div>
      
</template>

<script>
import NewComic from "@/views/NewComic.vue"
import AddCollection from '../views/AddCollection.vue'
import ComicCollectionService from '../services/ComicCollectionService'


export default {
    data() {
        return {
            collections: [],
            collectionToCreate: {
                collectionName: "",
                private: false,
                userId: this.$store.state.user.id
            },
            collectionsPlaceholder: [ //placeholder
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
                    comicTitle: "Marvelous Marvel",
                    marvelId: 111,
                    comicId: 123,
                    author: "Neil Armstrong",
                    imageURL: "http://i.annihil.us/u/prod/marvel/i/mg/c/60/4bc69f11baf75/portrait_uncanny.jpg",
                    releaseDate: "09/23/1997",
                    description: "A marvel of the human experience! And the struggles of confusing tax brackets! Find out!! Now!!!"
                },
                {
                    comicTitle: "The Amazing Spider-Man #1",
                    author: "Stan Lee"
                },
                {
                    comicTitle: "The Fantastic Four #4",
                    author: "Jack Kirby"
                }
            ],
            selectedCollection: {
                title: "Placeholder",
                public: true
            },
            search: "",
            searchCategories: [
                "Title",
                "Author",
                "Series",
                "Character"
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
       },
       loadCollections(){
           //Pulling in all collections owned by the user
           console.log(this.$store.state.user.id);
           ComicCollectionService.getCollections(this.$store.state.user.id).then(response => {
               this.collections = response.data;
           });
           console.log(this.collectionsTwo);
       }
   },
   created(){
       this.loadCollections();
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

#comicList{
    justify-content: center;
}

</style>