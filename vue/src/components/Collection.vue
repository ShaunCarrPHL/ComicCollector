<template>
  <div id="main">
    <div class="collection">
      <p class="sectionLabel" v-show="selectedCollection.collectionName">
        My {{ selectedCollection.collectionName }} Collection
      </p>

      <!--TODO: add v-for to iterate through comics for display-->
      <!--TODO: add dropdown menu to select collection for comic-->
      <ul id="comicList" v-show="selectedCollection.collectionName">
        <li>Comics</li>
        <li
          v-for="comic in comicsInCollection"
          v-bind:key="comic.id"
          :title="comic.comicTitle"
        >
          <img class="comicCover" v-bind:src="comic.imageURL" />
        </li>
      </ul>
    </div>
    <div id="addCollection">
      <add-collection />

      <p class="sectionLabel">Collections</p>
      <select
        name="collectionDDL"
        id="collectionDDL"
        v-on:change="selectCollection($event)"
        v-model="ddlCollection"
      >
        <option
          v-for="collection in this.$store.state.collections"
          v-bind:key="collection.collectionId"
          :value="collection.collectionId"
        >
          {{ collection.collectionName }}
        </option>
      </select>
      <collection-link
        class="comicLink"
        v-for="collection in this.$store.state.collections"
        v-bind:key="collection.collectionId"
        :collection="collection"
        v-on:selectedCollection="setActiveCollection"
      />

      <!-- <p v-for='collection in collections' v-bind:key='collection.collectionName'>{{collection.collectionName}}</p> -->
    </div>
  </div>
</template>

<script>
//import NewComic from "@/views/NewComic.vue"
import AddCollection from "../views/AddCollection.vue";
import ComicCollectionService from "../services/ComicCollectionService";
import CollectionLink from "./CollectionLink.vue";
//import Comic from "@/components/Comic.vue"

export default {
  name: "collection",
  data() {
    return {
      ddlCollection: 0,
      collections: [],
      collectionToCreate: {
        collectionName: "",
        private: false,
        userId: this.$store.state.user.id,
      },
      collectionsPlaceholder: [
        //placeholder
        {
          name: "Deadpool Collection",
          public: true,
        },
        {
          name: "Supermen Who Aren't Superman",
          public: false,
        },
      ],
      comics: [],
      selectedCollection: "",
      search: "",
      searchCategories: ["Title", "Author", "Series", "Character"],
      searchCategory: "",
    };
  },
  components: {
    //NewComic,
    AddCollection,
    CollectionLink,
    //Comic
  },

  methods: {
    loadComics() {
      //This would pull the comics from the selected collection, and
      //insert them into the this.comics array
      ComicCollectionService.getComicsInCollection(
        //this.$store.state.collection.id
        this.$store.state.activeCollectionId
      ).then((response) => {
        this.selectedCollection.getComicsInCollection.comics = response.data;
        this.$store.commit("SET_ACTIVE_COLLECTION_COMICS", response.data);
      });
    },
    loadCollections() {
      ComicCollectionService.getCollections(this.$store.state.user.id).then(
        (updatedCollections) => {
          this.$store.commit("SET_COLLECTIONS", updatedCollections.data);
          this.collections = this.$store.state.collections;
        }
      );
    },
    setActiveCollection(collection) {
      this.selectedCollection = collection;
    },
    selectCollection(event) {
      const selection = parseInt(event.target.value);

      this.$store.state.activeCollectionId = selection;
      this.selectedCollection = this.$store.state.collections
        .filter((collection) => {
          return collection.collectionId == selection;
        })
        .at(0);

      ComicCollectionService.getComicsInCollection(
        //this.$store.state.collection.id
        this.$store.state.activeCollectionId
      ).then((response) => {
        //this.selectedCollection.getComicsInCollection.comics = response.data;
        this.$store.commit("SET_ACTIVE_COLLECTION_COMICS", response.data);
      });
    },
  },
  created() {
    this.loadCollections();
  },
  computed: {
    filteredComics() {
      let filtered = this.comics;
      //Filtering based solely off of comic name and author.
      //Other fields can be added later
      if (this.search != "") {
        filtered = [];
        this.comics.forEach((comic) => {
          let chosen = false;
          if (
            comic.title.toLowerCase().includes(this.search.toLowerCase()) &&
            !chosen
          ) {
            filtered.push(comic);
            chosen = true;
          }
          if (
            comic.author.toLowerCase().includes(this.search.toLowerCase()) &&
            !chosen
          ) {
            filtered.push(comic);
            chosen = true;
          }
        });
      }

      return filtered;
    },
    comicsInCollection() {
      return Object.assign([], this.$store.state.activeCollectionComics);
    },
  },
};
</script>

<style scoped>
#collectionDDL {
  display: none;
}

#main {
  display: grid;
  grid-template-columns: 1fr, 1fr, 1fr, 1fr, 1fr;
  grid-template-areas: "collection collection collection addCollection addCollection";

  margin-right: 10px;
  /*background-color: rgb(66, 64, 64);*/
  background-image: linear-gradient(
    to right,
    rgba(255, 0, 0, 0),
    rgba(255, 0, 0, 1)
  );
  opacity: 0.7;
  text-align: center;
  height: 85vh;
 
  color: white;
  font-size: 20px;
}

.collection {
  grid-area: collection;
  padding: 25px;
  margin: 25px;
  overflow: scroll;
  overflow-x:hidden;
}
.addCollection {
  grid-area: addCollection;
  padding: 25px;
  margin: 25px;
}

#comicList {
  justify-content: center;
  list-style: none;
  text-decoration: underline;
}

.comicCover {
  /* display: flex;
    flex-direction: row;
    flex-flow: row wrap;
    align-items: flex-end; */
  max-height: 500px;
}

@media screen and (max-width: 700px)  {
  #collectionDDL {
    display: unset;
  }

  #main {
    grid-template-areas:
      "collection"
      "addCollection";
    grid-template-columns: 1fr;
    overflow: scroll;
  }
  .sectionLabel {
    font-size: 25px;
  }
  #comicList {
    font-size: 20px;
  }
  #comicLink {
    font-size: 20px;
   
  }
  .comicLink{
       display: none;
  }
  .collection {
    grid-area: collection;
    overflow: scroll;
  }
  .addCollection {
    grid-area: addCollection;
  }
}
</style>