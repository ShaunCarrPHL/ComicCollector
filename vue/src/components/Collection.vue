<template>
  <div id="main">
      
      <div class="collection">
        <p class="sectionLabel" v-show="selectedCollection.collectionName">My {{selectedCollection.collectionName}} Collection</p>
        <!--TODO: add v-for to iterate through comics for display-->
       <!--TODO: add dropdown menu to select collection for comic-->
        <table id="comicList" v-show="selectedCollection.collectionName">
          <thead>
            <tr>
                <th>Comics</th>
            </tr>
          </thead>
          <tbody>
              <tr v-for='comic in comicsInCollection' v-bind:key='comic.comicTitle'>
                  <td>{{comic.comicTitle}}</td>
              </tr>
          </tbody>
      </table>
      </div>
      <div id="addCollection">
       <add-collection /> 

       <p class="sectionLabel">Collections</p>
       <collection-link class="comicLink" v-for="collection in this.$store.state.collections" v-bind:key="collection.collectionId" :collection="collection" v-on:selectedCollection="setActiveCollection"/>
      <!-- <p v-for='collection in collections' v-bind:key='collection.collectionName'>{{collection.collectionName}}</p> -->
      </div>
    </div>
      
</template>

<script>
//import NewComic from "@/views/NewComic.vue"
import AddCollection from '../views/AddCollection.vue'
import ComicCollectionService from '../services/ComicCollectionService'
import CollectionLink from './CollectionLink.vue'
//import Comic from "@/components/Comic.vue"


export default {
    name: "collection",
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
                /*  {
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
                 } */
            ],
            selectedCollection: "",
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
     //NewComic,
      AddCollection,
      CollectionLink
      //Comic
   },
   methods:{
       loadComics(){
           //This would pull the comics from the selected collection, and
           //insert them into the this.comics array
           ComicCollectionService.getComicsInCollection(this.$store.state.collection.id).then(response => {
               this.selectedCollection.getComicsInCollection.comics = response.data;
               this.$store.commit('SET_ACTIVE_COLLECTION_COMICS', response.data)
           });
       },
       loadCollections(){

        ComicCollectionService.getCollections(this.$store.state.user.id)
              .then(updatedCollections => {
                this.$store.commit('SET_COLLECTIONS', updatedCollections.data);
                this.collections = this.$store.state.collections;
              }
            
              );

       },
       setActiveCollection(collection){
           this.selectedCollection = collection;
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

       },
       comicsInCollection(){
           return this.$store.state.activeCollectionComics;
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
    height: 85vh;
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

@media screen and (max-width: 700px) and (min-width: 400px){
    #main{
        grid-template-areas:
        "collection"
        "addCollection";
        grid-template-columns: 1fr;
    }
    .sectionLabel{
        font-size: 25px;
    }
    #comicList{
        font-size: 20px;
    }
    #comicLink{
        font-size: 20px;
    }
}

</style>