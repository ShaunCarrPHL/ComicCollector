<template>
  <div>
      <h2>Create New Collection</h2>
      <form class="new-collection" type="text" @submit.prevent="createCollection">
          <input class="collection-name" type="text" placeholder="Name" v-model='collection.collectionName' />
          <input class="public" type="checkbox" v-model='collection.private'/>Private?
          <button>Save</button>
      </form>
  </div>
</template>


<script>
import ComicCollectionService from '../services/ComicCollectionService'

export default {
  data () {
    return{
      collection: {
        collectionName: "",
        private: "",
        userId: this.$store.state.user.id
      },
      newCollectionId: 0,
    }
  },
  methods:{
    createCollection(){
      
      if(this.collection.collectionName){
      
      ComicCollectionService.createCollection(this.collection)
        .then(addedCollectionID => {
            this.newCollectionId = addedCollectionID
            ComicCollectionService.getCollections(this.$store.state.user.id)
              .then(updatedCollections => {
                this.$store.commit('SET_COLLECTIONS', updatedCollections.data);
                this.collection = {};
              }
            
              )

            
        }

      );
      }
    }
  }
}
</script>

<style>

</style>