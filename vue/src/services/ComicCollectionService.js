import axios from "axios";

const http=axios.create({
    baseURL:"http://localhost:8080"
});

export default{
    getComics(){
        return http.get('/comics', {
            headers:{
                "Access-Control-Allow-Origin": "http://localhost:8080"
            }
        });
    },

    getComicsByCharacter(characterName){
        return http.get(`/characters/name/${characterName}`);
    },

    getComicsByTitle(title){
        return http.get(`/comics/${title}`);
    },

    getComicById(comicId){
        return http.get(`/comics/${comicId}`);
    }
}