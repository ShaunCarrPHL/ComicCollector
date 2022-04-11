<template>
  <div>
      <form class="new-comic-form" v-on:submit.prevent="saveComic">
          <input class="title-input" type="text" placeholder="Title" v-model="comic.title" />
          <input class="author-input" type="text" placeholder="Author" v-model="comic.author" />
          <button>Save</button>
      </form>
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
            }
        }
    },
    methods: {
        saveComic() {
            this.$store.commit("SAVE_COMIC", this.comic);
            this.comic = {
                title: "",
                author: "",
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