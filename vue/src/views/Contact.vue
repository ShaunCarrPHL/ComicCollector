<template>
  <div id="main">
      <div id="contactform">
      <h2>Contact</h2>
      <form id="formtosubmit" method="POST" action="https://example.com/api/reservation">
            <div id="email">
                <label for="e-mail">E-mail:</label>
                <input id="e-mail" name="e-mail" type="text" />
            </div>
            <div id="hasAccount">
                <label for="hasAccount">Do you already have an account with us?:</label>
                <input id="hasAccount" name="hasAccount" type="checkbox" />
            </div>
            <div id="question">
                <label for="question">What can we help you with?:</label>
                <input id="question" name="question" type="text-area" />
            </div>
            <div id="submit">
                <input type="submit" />
            </div>
        </form>
        </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

div#main{
    display: grid;
    grid-template-columns: 1fr, 1fr, 1fr, 1fr, 1fr;
    grid-template-rows: auto 1fr 50px;
    grid-template-areas: 
    "contactform contactform contactform contactform contactform";
    margin-right: 75%;
    text-align: center;
    height: 50vh;
    border-radius: 25px;
    color: #ED1D24;
    font-size: 20px;
}

#contactform{
    grid-area: 2 / 1 / 2 / 6;
}

h2{
    
    font-size: 50px;
}
div#email{
    
    margin-bottom: 20px;
}
div#hasAccount{
    
    margin-bottom: 20px;
}
div#question{
    
    margin-bottom: 20px;
}
@media screen and (max-width: 700px) and (min-width: 400px){
    #main{
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: auto 1fr 50px;
    grid-template-areas: 
    "contactform";
    margin-right: 75%;
    text-align: center;
    height: 50vh;
    border-radius: 25px;
    color: #ED1D24;
    font-size: 20px;
    overflow: scroll;
}
#contactform{
    grid-area: 2 / 1 / 2 / 1;
}
h2{
    font-size: 50px;
}
div#email{
    margin-bottom: 20px;
}
div#hasAccount{
    margin-bottom: 20px;
}
div#question{
    margin-bottom: 20px;
}
}

</style>