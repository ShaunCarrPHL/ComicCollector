<template>
  <div>
    <select v-model="targetCollection">
        <option v-for="collection in availableCollections" v-bind:key="collection.collectionName" :value="collection">{{collection.collectionName}}</option>
    </select>
    <button v-on:click="addToSelectedCollection">Add to Collection</button>
  </div>
</template>

<script>
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
            console.log("Comic: " + this.selectedComic.comicTitle);
            console.log("TODO");

            //Will need to run this axios call...
            //addComicToCollection(targetCollection.collectionId, comic)
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