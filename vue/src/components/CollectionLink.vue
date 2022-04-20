<template>
  <div>
      <p style="cursor: pointer;" v-on:click="setActive">{{collection.collectionName}}</p>
  </div>
</template>

<script>
import ComicCollectionService from "../services/ComicCollectionService"


export default {
  data (){
    return{

    }
  },
  props:[
        'collection'
    ],
    methods:{
      setActive(){
        ComicCollectionService.getComicsInCollection(this.collection.collectionId).then(response => {
               
                this.$store.commit('SET_ACTIVE_COLLECTION_COMICS', response.data)
           });
        
        this.$emit("selectedCollection", this.collection);


      }
    }
}
</script>

<style>

</style>