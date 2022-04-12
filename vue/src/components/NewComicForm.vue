<template>
  <div>
      <form class="new-comic-form" v-on:submit.prevent="saveComic">
          <input class="title-input" type="text" placeholder="Title" v-model="comic.title" />
          <input class="author-input" type="text" placeholder="Author" v-model="comic.author" />
          <!-- <input class="dropdown" input type="select"> -->
          <!-- <select v-model="collectionName">
              <option v-for="collection in collections" v-bind:key="collection.name" :value="collection.name">{{collection.name}}</option>
          </select> -->
         <!--  <v-menu> <v-list>
          <v-list-item
            v-for="(item, index) in items"
            :key="index"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
        </v-menu> -->
          <button>Save</button>
      </form>
      <!-- <p v-for="collection in collections" v-bind:key="collection" :value="collection.name">{{collection.name}}</p> -->
  </div>
</template>

<script>
export default {
    name: "new-comic-form",
    data() {
        return {
            comic: {
                title: "",
                author: "",
            },
            collectionName: ""
        }
    },
    props: ['collections'],
    methods: {
        saveComic() {
            this.$store.commit("SAVE_COMIC", this.comic);
            this.comic = {
                title: "",
                author: ""
            };
            this.$router.push({name: "myComics"});
        }


    }
}
</script>

<style>
    .new-comic-form {
        margin: 20px;

    }

</style>