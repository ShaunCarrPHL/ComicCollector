import axios from "axios";

const http=axios.create({
    baseURL:"http://localhost:8080"
});

export default{
    getComics(){
        return http.get('/comics');
    },

    getComicsByCharacter(characterName){
        return http.get(`/characters/name/${characterName}`);
    },

    getComicsByAuthor(author){
        return http.get(`/creators/name/${author}`);
    },

    getComicsByTitle(title){
        return http.get(`/comic/bytitle/${title}`);
    },

    getComicsBySeries(series){
        return http.get(`/comic/byseries/${series}`);
    },

    getComicById(comicId){
        return http.get(`/comics/${comicId}`);
    },

    getCollections(userId){
        return http.get(`/collection/user/${userId}`);
    },

    createCollection(collection){
        return http.post('/collection', collection);
    },

    addComicToCollection(collectionId, comic){
        console.log(comic);
        return http.post(`/collection/comic/${collectionId}`, comic);
    },
    getComicsInCollection(collectionId){
        return http.get(`/collection/mycollection/mycomics/${collectionId}`);
    },

    getComicsNewThisWeek(thisWeek){
        return http.get(`/comics/date/thisWeek/${thisWeek}`);
    },
    getComicsNewNextWeek(nextWeek){
        return http.get(`/comics/date/nextWeek${nextWeek}`);
    }
}