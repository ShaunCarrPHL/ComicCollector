<template>
  <div id="atc">
    <select id="atcSelect" v-model="targetCollection" v-on:change="hideConfirm">
      <option
        class="optionLabel"
        v-for="collection in availableCollections"
        v-bind:key="collection.collectionId"
        :value="collection"
      >
        {{ collection.collectionName }}
      </option>
    </select>
    
    <img
      v-on:click="addToSelectedCollection"
      class="addBtn"
      src="1383934974.png"
      title="Add"
    />
    <span id="added">Got it!</span>
  </div>
</template>

<script>

import ComicCollectionService from "../services/ComicCollectionService";

export default {
  data() {
    return {
      targetCollection: "",
    };
  },
  props: ["selectedComic"],
  methods: {
      loadActive(){
        ComicCollectionService.getComicsInCollection(this.targetCollection.collectionId).then(response => {
               //this.selectedCollection.getComicsInCollection.comics = response.data;
                this.$store.commit('SET_ACTIVE_COLLECTION_COMICS', response.data)
           });

        this.$emit("selectedCollection", this.collection);


      },
    addToSelectedCollection(event) {

      this.loadActive();

      const confirm = event.target.nextElementSibling;

      const existing = this.$store.state.activeCollectionComics.filter(
        (comic) => {
          //alert(comic.marvelId + ' : ' + this.selectedComic.marvelId);
          return comic.marvelId == this.selectedComic.marvelId;
        }
      );

      //alert(existing.length);

      if (existing.length < 1) {
        //Will need to run this axios call...
        ComicCollectionService.addComicToCollection(
          this.targetCollection.collectionId,
          this.selectedComic
        ).then((response) => {
          console.log(response);
        });

        confirm.innerText = "Got It!";

        this.loadActive();
      } else {
        confirm.innerText = "Already There!";
      }


      //alert(confirm.innerText);

      confirm.style.display = "inline-block";
    },

    hideConfirm(event) {

      this.loadActive();

      const confirm = event.target.nextElementSibling.nextElementSibling;

      confirm.style.display = "none";

    },
  },
  computed: {
    availableCollections() {
      return this.$store.state.collections;
    },
  },
};
</script>

<style>
@media screen and (max-width: 700px) and (min-width: 400px) {
  #main {
    grid-template-areas:
      "collection"
      "addCollection";
    grid-template-columns: 1fr;
  }
  .optionLabel {
    font-size: 25px;
  }
  #comicList {
    font-size: 20px;
  }
}
.addBtn {
  height: 30px;
  width: 30px;
  display: inline-block;
}

#added {
  display: none;
}

.optionLabel {
  font-size: 1em;
  height: 1rem;
}

#atc {
  text-align: center;
}

#atcSelect {
  vertical-align: top;
  height: 30px;
  max-width: 150px;
}
</style>