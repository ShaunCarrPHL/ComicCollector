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
      }
    }
  },
  methods:{
    createCollection(){
      ComicCollectionService.createCollection(this.collection);
    }
  }
}
</script>

<style>

</style>