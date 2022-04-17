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

    getComicsByTitle(title){
        return http.get(`/comics/${title}`);
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
        console.log(collectionId);
        return http.post(`/collection/comic/${collectionId}`, comic);
    }
}