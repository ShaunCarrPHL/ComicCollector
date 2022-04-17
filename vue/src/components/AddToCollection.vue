<template>
  <div>
    <select v-model="targetCollection">
        <option v-for="collection in availableCollections" v-bind:key="collection.collectionName" :value="collection">{{collection.collectionName}}</option>
    </select>
    <button v-on:click="addToSelectedCollection">Add to Collection</button>
  </div>
</template>

<script>
import ComicCollectionService from '../services/ComicCollectionService'

export default {
    data () {
        return{
            targetCollection: ""
        }
    },
    props:[
        'selectedComic'
    ],
    methods:{
        addToSelectedCollection(){
            //Add the comic to the selected collection
            console.log("Collection: " + this.targetCollection.collectionId);
            console.log(this.selectedComic);
            console.log("TODO");

            //Will need to run this axios call...
            ComicCollectionService.addComicToCollection(this.targetCollection.collectionId, this.selectedComic).then(response =>{
                console.log(response);
            });
        }
    },
    computed: {
        availableCollections(){
            return this.$store.state.collections;
        }
    }
}
</script>

<style>

</style>